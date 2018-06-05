package cn.jxufe.service;
import javax.servlet.http.HttpSession;

import cn.jxufe.bean.FarmAction;
import cn.jxufe.bean.Message;

public interface FarmService {
	public Message action(HttpSession session,FarmAction farmAction);
}