package cn.jxufe.imp;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import cn.jxufe.bean.FarmAction;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.LandViewDAO;
import cn.jxufe.dao.SeedDAO;
import cn.jxufe.entity.Seed;
import cn.jxufe.entity.User;
import cn.jxufe.service.FarmService;
import cn.jxufe.view.LandView;
import cn.jxufe.websocket.FarmActionHandler;
import net.sf.json.JSONObject;
@Service
public class FarmImp implements FarmService {	
	
	
	@Autowired
	FarmActionHandler farmActionHandler;
	
	@Autowired
	LandViewDAO landViewDAO;
	
	@Autowired
	SeedDAO seedDAO;
	
//	@Override
//	public Message action(HttpSession session,FarmAction farmAction) {
//		Message result = new Message();
//		User user = (User)session.getAttribute("user");
//		try {
//			farmActionHandler.sendMessageToUser(user, farmAction.getMessageList().toString());
//		}catch(Exception e) {
//			result.setCode(-1);
//			result.setMsg("消息发送失败！");
//			return result;
//		}
//		result.setCode(0);
//	    result.setMsg("消息发送成功！");
//		return result;
//	}


	@Override
	public Message actionPlant(int landId, int cId, HttpSession session) {
		Message result = new Message();
		LandView landView= landViewDAO.findByLandId(landId);
		if(landView==null) {
			result.setCode(-1);
			result.setMsg("土地错误！");
			return result;
		}
		
		Seed seed= seedDAO.findByCId(cId);
		if(seed==null) {
			result.setCode(-1);
			result.setMsg("种子错误！");
			return result;
		}
		
		Object user = session.getAttribute("user");
		Seed seed2=new Seed();
		JSONObject obj=JSONObject.fromObject(landView);
		
		try {
			farmActionHandler.sendMessageToUser(user.toString(),new TextMessage(obj.toString()));
			
		}catch(Exception e) {
			result.setCode(-1);
			result.setMsg("种植失败！");
			return result;
		}
		result.setCode(0);
	    result.setMsg("消息成功！");
		return result;
	}


	@Override
	public Message actionKillWorm(int landId, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Message actionHarvest(int landId, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Message actionCleanLand(int landId, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}
}