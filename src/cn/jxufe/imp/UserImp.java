package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.UserDAO;
import cn.jxufe.entity.Seed;
import cn.jxufe.entity.User;
import cn.jxufe.service.UserService;
@Service
public class UserImp implements UserService{
	@Autowired
    UserDAO userDAO;

	@Override
	public EasyUIData<?> findByNickname(String nickname, Pageable pageable) {
		Page<User> page = userDAO.findByNickname(nickname, pageable);
        EasyUIData<User> easyUIData = new EasyUIData<User>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
        return easyUIData;
	}

	@Override
	public EasyUIData<?> findALL(Pageable pageable) {
		Page<Seed> page = userDAO.findAll(pageable);
		EasyUIData<User> easyUIData = new EasyUIData<User>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
		return easyUIData;
	}

	@Override
	public Message save(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message delete(User user) {
		// TODO Auto-generated method stub
		return null;
	}
}
