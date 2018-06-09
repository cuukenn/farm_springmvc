package cn.jxufe.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.ejb.criteria.OrderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import cn.jxufe.dao.CropsGrowDAO;
import cn.jxufe.dao.LandDAO;
import cn.jxufe.dao.LandViewDAO;
import cn.jxufe.entity.CropsGrow;
import cn.jxufe.entity.Land;
import cn.jxufe.service.GameService;
import cn.jxufe.utils.JSONConfig;
import cn.jxufe.view.LandView;
import cn.jxufe.websocket.FarmActionHandler;
import net.sf.json.JSONArray;

@Service
public class GameImp implements GameService {
	@Autowired
	FarmActionHandler farmActionHandler;

	@Autowired
	LandViewDAO landViewDAO;

	@Autowired
	CropsGrowDAO cropsGrowDAO;

	@Autowired
	LandDAO landDAO;

	Timer timer = new Timer();
	public static Iterable<LandView> iterable;

	@Override
	public void gameStart() {
		iterable = landViewDAO.findAll();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// System.out.println("状态轮询[" + new Date()+"]");
				checkCropStatus();
			}
		}, 0, 2000);
	}

	private void checkCropStatus() {
		iterable.forEach(new Consumer<LandView>() {
			@Override
			public void accept(LandView landView) {
				Date date = new Date();
				// 判断是否达到下一阶段
				if (date.getTime() >= landView.getCurCropsEndTime().getTime()) {
					if (!landView.getGrowCaption().equals("成熟阶段")) {
						// 查询排好序的阶段
						Specification specification = new Specification<CropsGrow>() {
							@Override
							public Predicate toPredicate(Root<CropsGrow> root, CriteriaQuery<?> criteriaQuery,
									CriteriaBuilder criteriaBuilder) {
								List<Order> list = new ArrayList<>();
								list.add(new OrderImpl(root.get("status"), true));
								list.add(new OrderImpl(root.get("growStep"), true));
								criteriaQuery.orderBy(list);
								return criteriaQuery.getGroupRestriction();
							}
						};

						Land land = landDAO.findByLandIdAndUId(landView.getLandId(), landView.getuId());

						// 获取下个阶段
						List<CropsGrow> cropsGrowList = cropsGrowDAO.findAll(specification);
						System.out.println("size:" + cropsGrowList.size());
						for (int index = 0, len = cropsGrowList.size(); index < len; index++) {
							System.out.println(cropsGrowList.get(index).getGrowCaption() + " "
									+ cropsGrowList.get(index).getStatus());
							if (cropsGrowList.get(index).getId() == landView.getStatus()) {
								if ((index + 1) < len) {
									land.setStatus(cropsGrowList.get(index + 1).getId());
									long newTime = cropsGrowList.get(index + 1).getGrowTime();
									land.setCurCropsEndTime(new Date(land.getCurCropsEndTime().getTime() + newTime));
									break;
								}
							}
							// 更新下阶段时间
							landDAO.save(land);
							LandView landViewN = landViewDAO.findByUIdAndLandId(landView.getuId(),
									landView.getLandId());
							farmActionHandler.sendMessageToUser(landViewN.getuId(), new TextMessage(
									JSONArray.fromObject(landViewN, JSONConfig.getJsonConfig()).toString()));
						}
					}
				}
			}
		});
	}
}