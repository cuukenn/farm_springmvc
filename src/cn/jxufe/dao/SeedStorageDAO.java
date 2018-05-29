package cn.jxufe.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.SeedStorage;


public interface SeedStorageDAO extends PagingAndSortingRepository<SeedStorage, Long> {
	
}