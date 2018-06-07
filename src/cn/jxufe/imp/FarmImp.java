package cn.jxufe.imp;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import cn.jxufe.bean.Message;
import cn.jxufe.dao.LandDAO;
import cn.jxufe.dao.LandViewDAO;
import cn.jxufe.dao.SeedBagDAO;
import cn.jxufe.dao.SeedDAO;
import cn.jxufe.dao.UserDAO;
import cn.jxufe.entity.Land;
import cn.jxufe.entity.Seed;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.entity.User;
import cn.jxufe.service.FarmService;
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

	@Override
	public Message action(int landId, HttpSession session) {
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
			farmActionHandler.sendMessageToUser(user.toString(), new TextMessage(array.toString()));
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
	public Message actionPlant(int landId, int cId, HttpSession session) {
		Message result = new Message();
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
		landDAO.save(newLand);

		landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);
		ArrayList<LandView> arrayList;
//		arrayList.add(landView);
		
		JSONArray array = JSONArray.fromObject(landView);
		
		try {
			farmActionHandler.sendMessageToUser(user.toString(), new TextMessage(array.toString()));
		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("种植失败！");
			return result;
		}

		seedBag.setcNumber(seedBag.getcNumber() - 1);
		seedBagDAO.save(seedBag);

		user.setExp(user.getExp() + 2);
		user.setPrice(user.getPrice() + 1);
		user.setScore(user.getScore() + 2);
		userDAO.save(user);

		result.setCode(0);
		result.setMsg("种植成功！");
		return result;
	}

	@Override
	public Message actionKillWorm(int landId, HttpSession session) {
		Message result = new Message();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			result.setCode(-1);
			result.setMsg("用户不合法");
			return result;
		}
		LandView landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);
		if (landView == null) {
			result.setCode(-1);
			result.setMsg("该土地上不存在植物");
			return result;
		}

		if (landView.getWorm() == 0) {
			result.setCode(-1);
			result.setMsg("该植物不存在害虫");
			return result;
		}
		landView.setWorm(0);
		JSONArray array = JSONArray.fromObject(landView);
		try {
			farmActionHandler.sendMessageToUser(user.toString(), new TextMessage(array.toString()));
		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("种植失败！");
			return result;
		}

		user.setExp(user.getExp() + 2);
		user.setPrice(user.getPrice() + 1);
		user.setScore(user.getScore() + 2);
		userDAO.save(user);

		result.setCode(0);
		result.setMsg("除虫成功！");
		return result;
	}

	@Override
	public Message actionHarvest(int landId, HttpSession session) {
		Message result = new Message();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			result.setCode(-1);
			result.setMsg("用户不合法");
			return result;
		}

		LandView landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);
		if (landView == null) {
			result.setCode(-1);
			result.setMsg("该土地上不存在植物");
			return result;
		}

		landView.setGrowStep(landView.getGrowStep() + 1);
		landViewDAO.save(landView);
		JSONArray array = JSONArray.fromObject(landView);

		try {
			farmActionHandler.sendMessageToUser(user.toString(), new TextMessage(array.toString()));

		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("收获失败！");
			return result;
		}

		user.setExp(user.getExp() + landView.getExp());
		user.setPrice(user.getPrice() + landView.getPrice4UnitSale() * (landView.getOutput() - landView.getLoss()));
		user.setScore(user.getScore() + 2);
		userDAO.save(user);

		result.setCode(0);
		result.setMsg("收获成功！");
		return result;
	}

	@Override
	public Message actionCleanLand(int landId, HttpSession session) {
		Message result = new Message();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			result.setCode(-1);
			result.setMsg("用户不合法");
			return result;
		}

		LandView landView = landViewDAO.findByUIdAndLandId(user.getId(), landId);
		if (landView == null) {
			result.setCode(-1);
			result.setMsg("该土地上不存在植物");
			return result;
		}

		Land land = landDAO.findByLandIdAndUId(landId, user.getId());
		landDAO.delete(land);

		land = landDAO.findByLandIdAndUId(landId, user.getId());
		if (land != null) {
			result.setCode(-1);
			result.setMsg("除草失败");
			return result;
		}

		try {
			farmActionHandler.sendMessageToUser(user.toString(), new TextMessage("NOLAND"));

		} catch (Exception e) {
			result.setCode(-1);
			result.setMsg("除草失败！");
			return result;
		}

		user.setExp(user.getExp() + 2);
		user.setPrice(user.getPrice() + 1);
		user.setScore(user.getScore() + 2);
		userDAO.save(user);

		result.setCode(0);
		result.setMsg("除草成功！");
		return result;
	}
}