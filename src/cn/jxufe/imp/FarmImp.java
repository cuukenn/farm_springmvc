package cn.jxufe.imp;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import cn.jxufe.bean.Message;
import cn.jxufe.entity.User;
import cn.jxufe.service.FarmService;
@Service
public class FarmImp implements FarmService{
	/**
	 * 
	 */
	@Override
	public Message setCurUser(HttpSession session, User user) {
		Message message = new Message();
        try {
        	session.setAttribute("user", user);
            message.setCode(0);
            message.setMsg("设置用户成功");
        }catch(Exception e) {
            message.setCode(-1005);
            message.setMsg("设置用户失败");
        }
        return message;
	}
	
}
