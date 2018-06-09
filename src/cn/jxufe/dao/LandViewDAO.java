package cn.jxufe.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.view.LandView;

public interface LandViewDAO extends PagingAndSortingRepository<LandView, Long> {

	public LandView findByUIdAndLandId(long uId, long landId);

	public Iterable<LandView> findByCId(long cId);

	public Iterable<LandView> findByUId(long uId);

}