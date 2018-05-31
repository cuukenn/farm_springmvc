package cn.jxufe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.User;
import cn.jxufe.service.UserService;

/**
 *用户管理里的 MVC框架里controller层类
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	/**
	 * UserService接口对象
	 * 
	 * @see cn.jxufe.service.UserService
	 */
	private UserService userService;
	@RequestMapping(value = "grid")
	/**
	 * 
	 * @return 返回对象放入到RequestMapping当中
	 */
	public String grid() {
		return "user/grid";
	}
	
	@RequestMapping(value = "userSelect")
	public String selectGrid() {
		return "user/userSelect";
	}
	
	/**
	 * 
	 * @param session
	 *           接收浏览器的HttpSession对象
	 * @param user
	 *            接收用户User对象
	 * @return 把用户信息放入session用JSON的格式返回处理提示消息Message类对象
	 */
	@RequestMapping(value = "setCurUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message setCurUser(HttpSession session, @RequestBody User user) {
		return userService.setCurUser(session, user);
	}
	
	/**
	 * 
	 * @param session 接收浏览器的HttpSession对象
	 * @param user 接收用户User对象
	 * @return 用JSON的格式返回设置的用户User类对象
	 */
	@RequestMapping(value = "getCurUser", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User setCurUser(HttpSession session) {
		User userOld=(User)session.getAttribute("user");
		User userNew=userService.findById(userOld.getId());
		if(userNew!=null)session.setAttribute("user",userNew);
		return userNew;
	}
	
	/**
	 * @param pageRequest 接收前台传来的分页请求
	 * @see EasyUIDataPageRequest
	 * @param nickname 接收昵称查找的参数
	 * @param model MVC框架的model层参数
	 * @return 返回EasyUIData表并以JSON格式返回
	 */
	@RequestMapping(value = "gridData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public EasyUIData<?> gridData(EasyUIDataPageRequest pageRequest, @RequestParam(defaultValue = "") String nickname,
			Model model) {
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		if (pageRequest.getOrder().equals("asc")) {
			orders.add(new Sort.Order(Direction.ASC, pageRequest.getSort()));
		} else {
			orders.add(new Sort.Order(Direction.DESC, pageRequest.getSort()));
		}
		Pageable pageable = new PageRequest(pageRequest.getPage() - 1, pageRequest.getRows(), new Sort(orders));
		if (nickname.equals("")) {
			return userService.findALL(pageable);
		} else {
			return userService.findByNicknameLike(nickname, pageable);
		}
	}
	/**
	 * 
	 * @param model 接收MVC框架的model层数据
	 * @return 返回查找的结果并以JSON的格式返回
	 */
	@RequestMapping(value = "gridDataALL", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<User> gridData(Model model) {
		return userService.findALL();
	}

	/**
	 * 
	 * @param user 接收User类对象
	 * @see cn.jxufe.entity.User
	 * @param model 接收MVC框架传到model层数据
	 * @return 返回保存处理后消息体Message的对象
	 */
	@RequestMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message save(User user, Model model) {
		return userService.save(user);
	}

	/**
	 * @param user 接收User类对象
	 * @see cn.jxufe.entity.User
	 * @param model 接收MVC框架传到model层数据
	 * @return 返回删除处理后消息体Message的对象
	 */
	@RequestMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message delete(User user, Model model) {
		return userService.delete(user);
	}

}