package cn.jxufe.service;

import org.springframework.data.domain.Pageable;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.User;

public interface UserService {
	/**
	 * 
	 * @param nickname
	 *            昵称
	 * @param pageable
	 *            分页
	 * @return 所有的EasyUIData分页列表
	 */
	public EasyUIData<?> findByNickname(String nickname, Pageable pageable);

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
	public Message save(User user);

	/**
	 * 
	 * @param user
	 *            用户
	 * @return 删除运行结果消息状态
	 */
	public Message delete(User user);
}
