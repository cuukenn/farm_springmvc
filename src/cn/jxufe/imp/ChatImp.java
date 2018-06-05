package cn.jxufe.imp;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;

import cn.jxufe.bean.ChatUser;
import cn.jxufe.bean.Message;
import cn.jxufe.bean.Talk;
import cn.jxufe.service.ChatService;
import cn.jxufe.service.FarmService;
import cn.jxufe.websocket.ChatActionHandler;
@Service
public class FarmImp implements FarmService {	
	
	
	@Autowired
	FarmActionHandler farmActionHandler;
	
	
	@Override
	public Message action(HttpSession session,Talk talk) {
		Message result = new Message();
		ChatUser chatUser = (User)session.getAttribute("chatUser");
		try {
			chatActionHandler.sendMessageToUser(talk.getTo(),
					new TextMessage(chatUser.getUsername()+":"+talk.getMessage()));
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