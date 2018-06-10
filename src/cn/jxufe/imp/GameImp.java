package cn.jxufe.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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
	// public static List<LandView> list;
	// public static Iterator<LandView> iterator;
	

	@Override
	public void gameStart() {
		// list=new ArrayList<>();
		// 初始化
		// Iterable<LandView> iterable = landViewDAO.findAll();
		// if (iterable != null) {
		// iterable.forEach(new Consumer<LandView>() {
		// @Override
		// public void accept(LandView landView) {
		// list.add(landView);
		// }
		// });
		// }
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// System.out.println("状态轮询[" + new Date()+"]");
				checkCropStatus();
			}
		}, 0, 2000);
	}

	private void checkCropStatus() {
		Iterable<LandView> Iterable= landViewDAO.findAll();
		if (Iterable == null)
			return;
		Iterable.forEach(new Consumer<LandView>() {
			@Override
			public void accept(LandView landView) {
				final long cId=landView.getcId();
				Date date = new Date();
				// 判断是否达到下一阶段
				if (date.compareTo(landView.getCurCropsEndTime()) > 0) {
					if (!landView.getCropsCaption().equals("成熟阶段")) {

						// 自定义排序
						Specification specification = new Specification<CropsGrow>() {
							@Override
							public Predicate toPredicate(Root<CropsGrow> root, CriteriaQuery<?> criteriaQuery,
									CriteriaBuilder criteriaBuilder) {
								
								List<Predicate> predicates = new ArrayList<>();
								predicates.add(criteriaBuilder.equal(root.get("cId"),cId));
								
								List<Order> list = new ArrayList<>();
								list.add(new OrderImpl(root.get("status"), true));
								list.add(new OrderImpl(root.get("growStep"), true));
								criteriaQuery.orderBy(list);
								
								criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
								return criteriaQuery.getGroupRestriction();
							}
						};

						Land land = landDAO.findByLandIdAndUId(landView.getLandId(), landView.getuId());

						// 获取下个阶段,顺序排序
						List<CropsGrow> cropsGrowList = cropsGrowDAO.findAll(specification);
						for (int index = 0, len = cropsGrowList.size(); index < len; index++) {
							// 到达当前数据，说明下一条是下一个记录
							if (cropsGrowList.get(index).getGrowStep() == land.getStatus()) {
								if ((index + 1) < len) {
									
									land.setStatus(cropsGrowList.get(index + 1).getGrowStep());
									int addTime = cropsGrowList.get(index + 1).getGrowTime();
									
									
									Calendar calendar = Calendar.getInstance();
									calendar.setTime(land.getCurCropsEndTime());
									calendar.add(Calendar.SECOND, addTime);// 原有基础上加addTime秒

									land.setCurCropsEndTime(calendar.getTime());
									break;
								}
							}
							// 更新下阶段时间
							landDAO.save(land);
							LandView landViewN = landViewDAO.findByUIdAndLandId(landView.getuId(),
									landView.getLandId());
							System.out.println(JSONArray.fromObject(landViewN, JSONConfig.getJsonConfig()).toString());
							farmActionHandler.sendMessageToUser(landViewN.getuId(), new TextMessage(
									JSONArray.fromObject(landViewN, JSONConfig.getJsonConfig()).toString()));
						}
					}
				}
			}
		});
	}
}