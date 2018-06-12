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
	 * @param userLand 用户土地对象
	 *           
	 * @return 保存运行结果消息状态
	 */
	public Message save(UserLand userLand);

	/**
	 * 
	 * @param userLand 用户土地对象
	 * @return 删除运行结果消息状态
	 */
	public Message delete(UserLand userLand);
	
	/**
	 * 
	 * @return 所有的UserLand结果
	 */
	public  Iterable<UserLand> findALL();
	/**
	 * 
	 * @param id 用户土地id
	 * @return 查询结果UserLand对象返回
	 */ 
	public  UserLand  findById(long id);
}
