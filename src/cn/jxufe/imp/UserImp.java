package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.UserDAO;
import cn.jxufe.entity.User;
import cn.jxufe.service.UserService;
@Service
public class UserImp implements UserService{
	@Autowired
    UserDAO userDAO;
	/**
	 * 
	 * 通过昵称模糊查询
	 * @see cn.jxufe.service.UserService
	 */
	@Override
	public EasyUIData<?> findByNickname(String nickname, Pageable pageable) {
		Page<User> page = userDAO.findByNicknameLike(nickname, pageable);
        EasyUIData<User> easyUIData = new EasyUIData<User>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
        return easyUIData;
	}
	/**
	 * 查看全部
	 * @see cn.jxufe.service.UserService
	 */
	@Override
	public EasyUIData<?> findALL(Pageable pageable) {
		Page<User> page = userDAO.findAll(pageable);
		EasyUIData<User> easyUIData = new EasyUIData<User>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
		return easyUIData;
	}
	/**
	 * 保存
	 * @see cn.jxufe.service.UserService
	 */
	@Override
	public Message save(User user) {
		Message message = new Message();
        try {
        	userDAO.save(user);
            message.setCode(0);
            message.setMsg("保存成功");
        }catch(Exception e) {
            message.setCode(-10);
            message.setMsg("保存失败");
        }
        return message;
	}
	/**
	 * 删除
	 * @see cn.jxufe.service.UserService
	 */
	@Override
	public Message delete(User user) {
		Message message = new Message();
        try {
        	userDAO.delete(user);
            message.setCode(0);
            message.setMsg("删除成功");
        }catch(Exception e) {
            message.setCode(-10);
            message.setMsg("删除失败");
        }
        return message;
	}
}
