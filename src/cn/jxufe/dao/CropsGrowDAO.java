package cn.jxufe.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.CropsGrow;
import cn.jxufe.entity.Seed;

public interface CropsGrowDAO extends PagingAndSortingRepository<CropsGrow, Long> {

	Page<CropsGrow> findByStatus(int status, Pageable pageable);
	
}