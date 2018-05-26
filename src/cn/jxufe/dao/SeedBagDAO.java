package cn.jxufe.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.SeedBag;


public interface SeedBagDAO extends PagingAndSortingRepository<SeedBag, Long> {
	/**
	 * 
	 * 通过用户id模糊查询
	 * @param uId 用户id
	 * @param pageable 分页
	 * @return 所有的结果
	 */
	public Iterable<SeedBag> findByUId(long uId); 
	/**
	 * 
	 * 通过种子id称模糊查询
	 * @param cId 种子id
	 * @param pageable 分页
	 * @return 所有的结果
	 */
	public Page<SeedBag> findByCIdLike(int cId,Pageable pageable); 
	
	public SeedBag findByCIdAndUId(int cId,long uId);
}