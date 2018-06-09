package cn.jxufe.service;

import org.springframework.data.domain.Pageable;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.Seed;

public interface SeedService {
	/**
	 * 
	 * @param type
	 *            类型
	 * @param pageable
	 *            分页
	 * @return  所有的EasyUIData分页列表
	 */
	public EasyUIData<?> findByType(int type, Pageable pageable);

	/**
	 * 
	 * @param landRequirement
	 *            土地需求
	 * @param pageable
	 *            分页
	 * @return 所有的EasyUIData分页列表
	 */
	public EasyUIData<?> findByLand(int landRequirement, Pageable pageable);

	/**
	 * 
	 * @param caption
	 *            植物名
	 * @param pageable
	 *            分页
	 * @return 所有的EasyUIData分页列表
	 */
	public EasyUIData<?> findByCaption(String caption, Pageable pageable);

	/**
	 * 
	 * @param pageable
	 *            分页
	 * @return  所有的EasyUIData分页列表
	 */
	public EasyUIData<?> findALL(Pageable pageable);

	/**
	 * 
	 * @param seed
	 *            种子
	 * @return 保存运行结果消息状态
	 */
	public Message save(Seed seed);

	/**
	 * 
	 * @param seed
	 *            种子
	 * @return 删除运行结果消息状态
	 */
	public Message delete(Seed seed);
	
	public Seed findByCID(long cId);
}
