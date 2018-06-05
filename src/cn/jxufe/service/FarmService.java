package cn.jxufe.service;
import javax.servlet.http.HttpSession;

import cn.jxufe.bean.FarmAction;
import cn.jxufe.bean.Message;

public interface FarmService {
//	public Message action(HttpSession session,FarmAction farmAction);
	public Message actionPlant(long uId, int cId, HttpSession session);
	public Message actionKillWorm(int landId, HttpSession session);
	public Message actionHarvest(int landId, HttpSession session);
	public Message actionCleanLand(int landId, HttpSession session);
}