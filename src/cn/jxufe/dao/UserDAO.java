package cn.jxufe.dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.User;


public interface UserDAO extends PagingAndSortingRepository<User, Long> {
	public Page<User> findByNicknameLike(String nickname,Pageable pageable); 
}