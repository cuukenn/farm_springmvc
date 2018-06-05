package cn.jxufe.service;

import org.springframework.data.domain.Pageable;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.UserLand;

public interface UserLandService {

	/**
	 * 
	 * @param uId 用户的id
	 * @return 所有的UserLand结果
	 */
	public Iterable<UserLand> findByUId(long uId);

	/**
	 * 
	 * @param pageable
	 *            分页
	 * @return 所有的EasyUIData分页列表
	 */
	public EasyUIData<?> findALL(Pageable pageable);

	/**
	 * 
	 * @param user
	 *            用户
	 * @return 保存运行结果消息状态
	 */
	public Message save(UserLand userLand);

	/**
	 * 
	 * @param user 用户
	 * @return 删除运行结果消息状态
	 */
	public Message delete(UserLand userLand);
	
	/**
	 * 
	 * @param pageable
	 *            分页
	 * @return 所有的UserLand结果
	 */
	public  Iterable<UserLand> findALL();
	
	public  UserLand  findById(long id);
}
