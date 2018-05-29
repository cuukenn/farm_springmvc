package cn.jxufe.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.view.SeedBagView;


public interface SeedBagViewDAO extends PagingAndSortingRepository<SeedBagView, Long> {
	public Iterable<SeedBagView>  findByUId(long uId);
	
}