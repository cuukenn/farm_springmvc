package cn.jxufe.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;

import cn.jxufe.dao.CropsGrowDAO;
import cn.jxufe.dao.LandDAO;
import cn.jxufe.dao.LandViewDAO;
import cn.jxufe.entity.CropsGrow;
import cn.jxufe.entity.Land;
import cn.jxufe.service.CropsGrowService;
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

	@Autowired
	CropsGrowService cropsGrowService;

	Timer timer = new Timer();
	private Set<Land> updateSet;
	// public static Iterator<LandView> iterator;

	private static final int MAX = 100;

	@Override
	public void gameStart() {
		updateSet = new HashSet<Land>();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				checkCropStatus();
			}
		}, 0, 2000);
	}

	// 此处有未知BUG
	private void checkCropStatus() {
		this.findShouldUpdate();
		updateSet.forEach(new Consumer<Land>() {

			@Override
			public void accept(Land land) {
				LandView landViewN = landViewDAO.findByUIdAndLandId(land.getuId(), land.getLandId());
				farmActionHandler.sendMessageToUser(landViewN.getuId(),
						new TextMessage(JSONArray.fromObject(landViewN, JSONConfig.getJsonConfig()).toString()));
			}
		});
		updateSet.clear();
	}

	// 事务提交，更新视图
	@Transactional
	private void findShouldUpdate() {
		Iterable<LandView> Iterable = landViewDAO.findAll();
		if (Iterable == null)
			return;
		Iterable.forEach(new Consumer<LandView>() {
			@Override
			public void accept(LandView landView) {
				final long cId = landView.getcId();
				Date date = new Date();
				Land land = landDAO.findByLandIdAndUId(landView.getLandId(), landView.getuId());

				// 判断是否达到下一阶段
				if (date.compareTo(landView.getCurCropsEndTime()) > 0) {
					if (!landView.getCropsCaption().equals("成熟阶段")) {
						CropsGrow cropsGrow = cropsGrowService.findNextCrops(cId, landView.getGrowStep());
						// 获取下个阶段,顺序排序
						if (cropsGrow == null)
							return;

						land.setStatus(cropsGrow.getGrowStep());

						int addTime = cropsGrow.getGrowTime();
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(land.getCurCropsEndTime());
						calendar.add(Calendar.SECOND, addTime);// 原有基础上加addTime秒

						land.setCurCropsEndTime(calendar.getTime());

						// 更新下阶段时间
						land = landDAO.save(land);

						// 加入到更新序列
						updateSet.add(land);
					}
				}

				// 概率生虫
				if (landView.getWorm() == 0) {
					double num = Math.random() * MAX;
					if ((num / MAX) < landView.getInsect()) {
						// 生虫
						land.setWorm(1);
						land = landDAO.save(land);
						// 加入到更新序列
						updateSet.add(land);
					}
				}
			}
		});
	}
}