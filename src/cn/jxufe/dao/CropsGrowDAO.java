package cn.jxufe.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.CropsGrow;

public interface CropsGrowDAO extends PagingAndSortingRepository<CropsGrow, Long> {
	
}