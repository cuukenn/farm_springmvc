package cn.jxufe.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.User;
import cn.jxufe.entity.UserLand;


public interface UserLandDAO extends PagingAndSortingRepository<UserLand, Long> {
	/**
	 * 
	 * 通过昵称模糊查询
	 * @param uId 昵称
	 * @param pageable 分页
	 * @return 所有的结果
	 */
	public Page<UserLand> findByUId(long uId,Pageable pageable); 
	
	public UserLand findById(long id);
	public  Iterable<UserLand> findByUId(long uId); 
	
}