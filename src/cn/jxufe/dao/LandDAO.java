package cn.jxufe.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.Land;
public interface LandDAO extends PagingAndSortingRepository<Land, Long> {
	public Land findByLandId(int landId);
	public Land findByLandIdAndUId(int landId,long uId);
}