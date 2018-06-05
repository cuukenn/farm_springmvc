package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.UserLandDAO;
import cn.jxufe.entity.UserLand;
import cn.jxufe.service.UserLandService;
@Service
public class UserLandImp implements UserLandService{
	@Autowired
	UserLandDAO userLandDAO;
	/**
	 * 查看全部
	 * @see cn.jxufe.service.UserLandService
	 */
	@Override
	public EasyUIData<?> findALL(Pageable pageable) {
		Page<UserLand> page = userLandDAO.findAll(pageable);
		EasyUIData<UserLand> easyUIData = new EasyUIData<UserLand>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
		return easyUIData;
	}
	/**
	 * 保存
	 * @see cn.jxufe.service.UserLandService
	 */
	@Override
	public Message save(UserLand userLand) {
		Message message = new Message();
        try {
        	userLandDAO.save(userLand);
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
	public Message delete(UserLand userLand) {
		Message message = new Message();
        try {
        	userLandDAO.delete(userLand);
            message.setCode(0);
            message.setMsg("删除成功");
        }catch(Exception e) {
            message.setCode(-10);
            message.setMsg("删除失败");
        }
        return message;
	}
	@Override
	public Iterable<UserLand> findALL() {
		return this.userLandDAO.findAll();
	}
	@Override
	public UserLand findById(long id) {
		return this.userLandDAO.findById(id);
	}
	
	@Override
	public Iterable<UserLand> findByUId(long uId) {
		// TODO Auto-generated method stub
		return this.userLandDAO.findByUId(uId);
				
	}
}
