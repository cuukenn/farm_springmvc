package cn.jxufe.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.User;


public interface UserDAO extends PagingAndSortingRepository<User, Long> {
	/**
	 * 
	 * 通过昵称模糊查询
	 * @param nickname 昵称
	 * @param pageable 分页
	 * @return 所有的结果
	 */
	public Page<User> findByNicknameLike(String nickname,Pageable pageable); 
}