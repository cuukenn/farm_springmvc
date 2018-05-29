package cn.jxufe.service;

import cn.jxufe.view.SeedBagView;

public interface SeedBagViewService {
	/**
	 * @param uID
	 * 			用户标识
	 * @param pageable
	 *            分页
	 * @return 对应用户的EasyUIData分页列表
	 */
	public Iterable<SeedBagView> findByUId(long uId);
}
