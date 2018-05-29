package cn.jxufe.service;

import javax.servlet.http.HttpSession;

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
	public EasyUIData<?> findByNicknameLike(String nickname, Pageable pageable);

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
	
	/**
	 * 
	 * @param pageable
	 *            分页
	 * @return 所有的EasyUIData分页列表
	 */
	public  Iterable<User> findALL();
	
	/**
	 * 
	 * @param session 一个会话用来保存当前的user用户
	 * @param user 当前用户
	 * @return 返回设置用户是否成功的消息
	 */
	public Message setCurUser(HttpSession session, User user);
	
	public  User findById(long id);
}
