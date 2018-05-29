package cn.jxufe.service;

import org.springframework.data.domain.Pageable;

import cn.jxufe.bean.EasyUIData;

public interface SeedStorageService {
	/**
	 * 
	 * @param pageable
	 *            分页
	 * @return 所有的EasyUIData分页列表
	 */
	public EasyUIData<?> findALL(Pageable pageable);
}
