package cn.jxufe.service;

import cn.jxufe.view.SeedBagView;

public interface SeedBagViewService {
	/**
	 * @param uId
	 * 			用户标识
	 * @return 查询的SeedBagView对象
	 */
	public Iterable<SeedBagView> findByUId(long uId);
	/**
	 * 
	 * @param uId 用户ID
	 * @param landId 土地ID
	 * @return 查询的SeedBagView对象
	 */
	
	public Iterable<SeedBagView> findByUIdAndLandRequireCaption(long uId,long landId);
}
