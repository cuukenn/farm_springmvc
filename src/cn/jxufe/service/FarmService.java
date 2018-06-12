package cn.jxufe.service;

import javax.servlet.http.HttpSession;

import cn.jxufe.bean.FarmAction;
import cn.jxufe.bean.Message;

public interface FarmService {
	/**
	 * 
	 * @param landId 土地id
	 * @param session  会话
	 * @return 后台前台是否同步一直
	 */
	public Message action(long landId, HttpSession session);

	/**
	 * 
	 * @param landId 土地id
	 * @param cId 种子id
	 * @param session 会话
	 * @return 是否播种了种子的消息
	 */
	public Message actionPlant(long landId, long cId, HttpSession session);

	/**
	 * 
	 * @param landId 土地id
	 * @param session 会话
	 * @return 是否成功杀虫的消息
	 */
	public Message actionKillWorm(long landId, HttpSession session);

	/**
	 * 
	 * @param landId 土地id
	 * @param session 会话
	 * @return 是否成功收获的消息
	 */
	public Message actionHarvest(long landId, HttpSession session);

	/**
	 * 
	 * @param landId 土地id
	 * @param session 会话
	 * @return 是否成功除草的消息
	 */
	public Message actionCleanLand(long landId, HttpSession session);
	/**
	 * 
	 * @param landId 土地id
	 * @param cId 种子id
	 * @param session 会话
	 * @return 是否成功除草的消息
	 */
	public Message plantTansition(long landId, long cId, HttpSession session);
	/**
	 * 
	 * @param landId 土地id
	 * @param session 会话
	 * @return 是否成功杀虫的消息
	 */
	public Message killWormTansition(long landId, HttpSession session);
	/**
	 * 
	 * @param landId 土地id
	 * @param session 会话
	 * @return 是否成功收获事物的消息
	 */
	public Message harvestTansition(long landId, HttpSession session);

	/**
	 * 
	 * @param landId 土地id
	 * @param session 会话
	 * @return 是否成功除草事物的消息
	 */
	public Message cleanLandTansition(long landId, HttpSession session);
}