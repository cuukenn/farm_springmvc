package cn.jxufe.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.CropsGrow;

public interface CropsGrowDAO extends PagingAndSortingRepository<CropsGrow, Long>,JpaSpecificationExecutor<CropsGrow> {

	Page<CropsGrow> findByStatus(int status, Pageable pageable);

	List<CropsGrow> findByCId(int cId);

}