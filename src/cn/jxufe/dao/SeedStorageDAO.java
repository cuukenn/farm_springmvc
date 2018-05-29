package cn.jxufe.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.view.SeedStorageView;


public interface SeedStorageDAO extends PagingAndSortingRepository<SeedStorageView, Long> {
	
}