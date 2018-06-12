package cn.jxufe.service;

import cn.jxufe.bean.Message;
import cn.jxufe.entity.SeedBag;

public interface SeedBagService {
	/**
	 * 
	 * @param uId 用户id
	 * @return 所有的EasyUIData分页列表
	 */
	public Iterable<SeedBag> findByUId(long uId);
	/**
	 * 
	 * @param seedBag
	 *            用户背包信息
	 * @return 保存运行结果消息状态
	 */
	public Message save(SeedBag seedBag);

	/**
	 * 
	 * @param seedBag
	 *            种子信息
	 * @return 删除运行结果消息状态
	 */
	public Message delete(SeedBag seedBag);
}
