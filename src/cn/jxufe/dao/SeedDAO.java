package cn.jxufe.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.Seed;

public interface SeedDAO extends PagingAndSortingRepository<Seed, Long> {
	public Page<Seed> findByType(int type,Pageable pageable); 
	public Page<Seed> findByLandRequirement(int landRequirement,Pageable pageable); 
}