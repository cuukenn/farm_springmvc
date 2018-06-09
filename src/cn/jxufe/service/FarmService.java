package cn.jxufe.service;
import javax.servlet.http.HttpSession;

import cn.jxufe.bean.FarmAction;
import cn.jxufe.bean.Message;

public interface FarmService {
	public Message action(long landId, HttpSession session);
	public Message actionPlant(long landId, long cId, HttpSession session);
	public Message actionKillWorm(long landId, HttpSession session);
	public Message actionHarvest(long landId, HttpSession session);
	public Message actionCleanLand(long landId, HttpSession session);
}