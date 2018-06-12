package cn.jxufe.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.view.LandView;

public interface LandViewDAO extends PagingAndSortingRepository<LandView, Long> {

	/**
	 * 
	 * @param uId 用户id
	 * @param landId 土地id
	 * @return 返回landView
	 */
	public LandView findByUIdAndLandId(long uId, long landId);
	/**
	 * 
	 * @param cId 种子id
	 * @return 返回landView
	 */
	public Iterable<LandView> findByCId(long cId);

	public Iterable<LandView> findByUId(long uId);

}