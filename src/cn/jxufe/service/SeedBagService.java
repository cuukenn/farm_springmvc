package cn.jxufe.service;

import org.springframework.data.domain.Pageable;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.SeedBag;

public interface SeedBagService {
	/**
	 * 
	 * @param id
	 *            用户id
	 * @param pageable
	 *            分页
	 * @return 所有的EasyUIData分页列表
	 */
	public Iterable<SeedBag> findByUId(long uId);
	/**
	 * 
	 * @param uId
	 *           种子id
	 * @param pageable
	 *            分页
	 * @return 所有的EasyUIData分页列表
	 */
	public EasyUIData<?> findByCIdLike(int cId, Pageable pageable);
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
