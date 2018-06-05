package cn.jxufe.imp;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.FarmAction;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.User;
import cn.jxufe.service.FarmService;
import cn.jxufe.websocket.FarmActionHandler;
@Service
public class FarmImp implements FarmService {	
	
	
	@Autowired
	FarmActionHandler farmActionHandler;
	
	
	@Override
	public Message action(HttpSession session,FarmAction farmAction) {
		Message result = new Message();
		User user = (User)session.getAttribute("user");
		try {
//			farmActionHandler.sendMessageToUser(user, farmAction.getMessageList().toString());
		}catch(Exception e) {
			result.setCode(-1);
			result.setMsg("消息发送失败！");
			return result;
		}
		result.setCode(0);
	    result.setMsg("消息发送成功！");
		return result;
	}
}