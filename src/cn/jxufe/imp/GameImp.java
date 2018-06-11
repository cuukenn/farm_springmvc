package cn.jxufe.imp;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
				// checkCropStatus();
			}
		}, 0, 2000);
	}

	private void checkCropStatus() {
		Iterable<LandView> Iterable = landViewDAO.findAll();
		if (Iterable == null)
			return;
		Iterable.forEach(new Consumer<LandView>() {
			@Override
			public void accept(LandView landView) {
				final long cId = landView.getcId();
				Date date = new Date();
				// 判断是否达到下一阶段
				if (date.compareTo(landView.getCurCropsEndTime()) > 0) {
					if (!landView.getCropsCaption().equals("成熟阶段")) {

						Land land = landDAO.findByLandIdAndUId(landView.getLandId(), landView.getuId());

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
						land=landDAO.save(land);
						
						
						LandView landViewN = landViewDAO.findByUIdAndLandId(landView.getuId(), landView.getLandId());
						System.out.println(JSONArray.fromObject(landViewN, JSONConfig.getJsonConfig()).toString());
						farmActionHandler.sendMessageToUser(landViewN.getuId(), new TextMessage(
								JSONArray.fromObject(landViewN, JSONConfig.getJsonConfig()).toString()));
					}
				}
			}
		});
	}
}