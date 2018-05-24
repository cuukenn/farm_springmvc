package cn.jxufe.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.CropsGrow;

public interface CropsGrowDAO extends PagingAndSortingRepository<CropsGrow, Long> {

	Page<CropsGrow> findByStatus(int status, Pageable pageable);

	List<CropsGrow> findByCId(int cId);

}