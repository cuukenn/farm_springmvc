package cn.jxufe.service;

import javax.servlet.http.HttpSession;

import cn.jxufe.bean.Message;
import cn.jxufe.entity.User;

public interface farmService {
	/**
	 * 
	 * @param session 一个会话用来保存当前的user用户
	 * @param user 当前用户
	 * @return 返回设置用户是否成功的消息
	 */
	public Message setCurUser(HttpSession session, User user);
}
