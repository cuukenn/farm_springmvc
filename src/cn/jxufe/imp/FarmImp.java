package cn.jxufe.imp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;

import cn.jxufe.bean.Message;
import cn.jxufe.dao.LandDAO;
import cn.jxufe.dao.LandViewDAO;
import cn.jxufe.dao.SeedBagDAO;
import cn.jxufe.dao.SeedDAO;
import cn.jxufe.dao.UserDAO;
import cn.jxufe.entity.CropsGrow;
import cn.jxufe.entity.Land;
import cn.jxufe.entity.Seed;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.entity.User;
import cn.jxufe.service.CropsGrowService;
import cn.jxufe.service.FarmService;
import cn.jxufe.utils.JSONConfig;
import cn.jxufe.view.LandView;
import cn.jxufe.websocket.FarmActionHandler;
import net.sf.json.JSONArray;

@Service
public class FarmImp implements FarmService {

	@Autowired
	FarmActionHandler farmActionHandler;

	@Autowired
	LandViewDAO landViewDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	LandDAO landDAO;

	@Autowired
	SeedDAO seedDAO;

	@Autowired
	SeedBagDAO seedBagDAO;

	@Autowired
	CropsGrowService cropsGrowService;

	@Override
	@Transactional
	public Message action(long landId, HttpSession session) {
		Message result = new Message();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			result.setCode(-1);
			result.setMsg("用户不合法");
			return result;
		}
		Iterable<LandView> landView = landViewDAO.findByUId(user.getId());
		JSONArray array = JSONArray.fromObject(landView);
		try {
			farmActionHandler.sendMessageToUser(user.getId(), new TextMessage(array.toString()));
		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("消息发送失败！");
			return result;
		}
		result.setCode(0);
		result.setMsg("消息发送成功！");
		return result;
	}

	@Override
	@Transactional
	public Message actionPlant(long landId, long cId, HttpSession session) {
		Message result = new Message();
		try {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				result.setCode(-1);
				result.setMsg("用户不合法");
				return result;
			}

			LandView landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);
			if (landView != null) {
				result.setCode(-1);
				result.setMsg("该土地上已经存在植物");
				return result;
			}

			Seed seed = seedDAO.findByCId(cId);
			if (seed == null) {
				result.setCode(-1);
				result.setMsg("不存在该种子！");
				return result;
			}

			SeedBag seedBag = seedBagDAO.findByCIdAndUId(cId, user.getId());
			if (seedBag.getcNumber() < 1) {
				result.setCode(-1);
				result.setMsg("该种子数量不足！");
				return result;
			}

			Land newLand = new Land();
			newLand.setId(0);
			newLand.setcId(cId);
			newLand.setLandId(landId);
			newLand.setuId(user.getId());

			CropsGrow cropsGrow = cropsGrowService.findFirstCrops(cId);
			if (cropsGrow == null) {
				result.setCode(-4);
				result.setMsg("该植物无生长阶段信息不全");
				return result;
			}
			newLand.setStatus(cropsGrow.getGrowStep());
			newLand = landDAO.save(newLand);

			// 初始化下季时间
			landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(landView.getPlantTime());
			calendar.add(Calendar.SECOND, landView.getGrowTime());// 作为秒加入
			newLand.setCurCropsEndTime(calendar.getTime());
			landDAO.save(newLand);

			landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);
			List<LandView> arrayList = new ArrayList<LandView>();
			arrayList.add(landView);

			// 格式化时间转JSON输出
			JSONArray array = JSONArray.fromObject(arrayList, JSONConfig.getJsonConfig());
			farmActionHandler.sendMessageToUser(user.getId(), new TextMessage(array.toString()));

			if ((seedBag.getcNumber() - 1) >= 2) {
				seedBag.setcNumber(seedBag.getcNumber() - 1);
				seedBagDAO.save(seedBag);
			} else {
				seedBagDAO.delete(seedBag);
			}

			user.setExp(user.getExp() + 2);
			user.setPrice(user.getPrice() + 1);
			user.setScore(user.getScore() + 2);
			userDAO.save(user);

			result.setCode(0);
			result.setMsg("种植成功！");
		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("种植失败！");
		}
		return result;
	}

	@Override
	@Transactional
	public Message actionKillWorm(long landId, HttpSession session) {
		Message result = new Message();
		try {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				result.setCode(-1);
				result.setMsg("用户不合法");
				return result;
			}
			Land land = landDAO.findByLandIdAndUId(landId, user.getId());
			if (land == null) {
				result.setCode(-1);
				result.setMsg("该土地上不存在植物");
				return result;
			}

			if (land.getWorm() == 0) {
				result.setCode(-1);
				result.setMsg("该植物不存在害虫");
				return result;
			}
			land.setWorm(0);
			landDAO.save(land);

			user.setExp(user.getExp() + 2);
			user.setPrice(user.getPrice() + 1);
			user.setScore(user.getScore() + 2);
			userDAO.save(user);

			result.setCode(0);
			result.setMsg("除虫成功！");

			LandView landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);
			ArrayList<LandView> arrayList = new ArrayList<>();
			arrayList.add(landView);
			JSONArray array = JSONArray.fromObject(arrayList, JSONConfig.getJsonConfig());
			farmActionHandler.sendMessageToUser(user.getId(), new TextMessage(array.toString()));

			return result;
		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("种植失败！");
		}
		return result;
	}

	@Override
	@Transactional
	public Message actionHarvest(long landId, HttpSession session) {
		Message result = new Message();
		try {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				result.setCode(-1);
				result.setMsg("用户不合法");
				return result;
			}

			Land land = landDAO.findByLandIdAndUId(landId, user.getId());
			if (land == null) {
				result.setCode(-2);
				result.setMsg("该土地上不存在植物");
				return result;
			}

			LandView landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);

			if (!landView.getCropsCaption().equals("成熟阶段")) {
				result.setCode(-3);
				result.setMsg("该土地上上的植物未到成熟阶段");
				return result;
			}

			user.setExp(user.getExp() + landView.getExp());
			user.setPrice(user.getPrice() + landView.getPrice4UnitSale() * (landView.getOutput() - landView.getLoss()));
			user.setScore(user.getScore() + 2);
			userDAO.save(user);

			// 存在下一季
			if (landView.getCurHarvestNum() < landView.getHarvestNum()) {
				CropsGrow cropsGrow = cropsGrowService.findFirstCrops(landView.getcId());
				if (cropsGrow == null) {
					result.setCode(-4);
					result.setMsg("该植物无生长阶段信息不全");
					return result;
				}
				land.setStatus(cropsGrow.getStatus());
				land.setCurHarvestNum(land.getCurHarvestNum() + 1);
				land.setLoss(0);
				land.setWorm(0);
				land.setPlantTime(new Date());

				// 更新下阶段结束时间
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(land.getPlantTime());
				calendar.add(Calendar.SECOND, cropsGrow.getGrowTime());// 作为秒加入
				land.setCurCropsEndTime(calendar.getTime());

				land = landDAO.save(land);
			}
			// 不存在下一季，直接删除
			else {
				landDAO.delete(land);
			}
			result.setCode(0);
			result.setMsg("收获成功！");
			landView = landViewDAO.findByUIdAndLandId(land.getuId(), land.getLandId());
			if (landView == null) {
				landView = new LandView();
				landView.setLandId(landId);
			}

			ArrayList<LandView> arrayList = new ArrayList<>();
			arrayList.add(landView);
			JSONArray array = JSONArray.fromObject(arrayList, JSONConfig.getJsonConfig());
			farmActionHandler.sendMessageToUser(user.getId(), new TextMessage(array.toString()));
		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("收获失败！");
		}
		return result;
	}

	@Override
	@Transactional
	public Message actionCleanLand(long landId, HttpSession session) {
		Message result = new Message();
		try {
			User user = (User) session.getAttribute("user");
			if (user == null) {
				result.setCode(-1);
				result.setMsg("用户不合法");
				return result;
			}

			Land land = landDAO.findByLandIdAndUId(landId, user.getId());
			if (land == null) {
				result.setCode(-1);
				result.setMsg("该土地上不存在植物");
				return result;
			}

			LandView landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);
			TextMessage textMessage;
			if (landView.getCurHarvestNum() < landView.getHarvestNum()) {
				land.setCurHarvestNum(landView.getCurHarvestNum() + 1);
				land.setStatus(1);
				landDAO.save(land);
				ArrayList<LandView> arrayList = new ArrayList<>();
				arrayList.add(landView);
				JSONArray array = JSONArray.fromObject(arrayList, JSONConfig.getJsonConfig());
				textMessage = new TextMessage(array.toString());
			} else {
				landDAO.delete(land);
				textMessage = new TextMessage("NOLAND");
			}

			user.setExp(user.getExp() + 2);
			user.setPrice(user.getPrice() + 1);
			user.setScore(user.getScore() + 2);
			userDAO.save(user);
			result.setCode(0);
			result.setMsg("除草成功！");

			farmActionHandler.sendMessageToUser(user.getId(), textMessage);

		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("除草失败！");
		}
		return result;
	}
}