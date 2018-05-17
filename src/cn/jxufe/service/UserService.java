package cn.jxufe.service;

import org.springframework.data.domain.Pageable;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.User;

public interface UserService {
	 public EasyUIData<?> findByNickname(String nickname,Pageable pageable);
	 public EasyUIData<?> findALL(Pageable pageable);
	 public Message save(User user);
	 public Message delete(User user); 
}
