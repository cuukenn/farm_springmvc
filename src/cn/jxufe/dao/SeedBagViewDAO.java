package cn.jxufe.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.view.SeedBagView;


public interface SeedBagViewDAO extends PagingAndSortingRepository<SeedBagView, Long> {
	/**
	 * 
	 * @param uId 用户id
	 * @return 返回所有的SeedBagView
	 */
	public Iterable<SeedBagView>  findByUId(long uId);
	
}