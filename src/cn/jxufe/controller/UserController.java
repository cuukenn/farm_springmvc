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

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 
	 * @return 返回结果放入到RequestMapping中
	 */
	@RequestMapping(value = "grid")
	public String grid() {
		return "user/grid";
	}
	/**
	 * 
	 * @return 返回结果放入到RequestMapping中
	 */
	@RequestMapping(value = "userSelect")
	public String selectGrid() {
		return "user/userSelect";
	}
	/**
	 * 设置当前用户到session的
	 * @param session 接收HttpSession对象数据
	 * @param user 接收User对象数据
	 * @see cn.jxufe.entity.User
	 * @return 将处理结果Message对象以JSON格式返回
	 */
	@RequestMapping(value = "setCurUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message setCurUser(HttpSession session, @RequestBody User user) {
		return userService.setCurUser(session, user);
	}
	/**
	 * 从session获取用户信息
	 * @param session 接收HttpSession对象数据
	 * @return 将处理结果User对象以JSON格式返回
	 */
	@RequestMapping(value = "getCurUser", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User getCurUser(HttpSession session) {
		User userOld=(User)session.getAttribute("user");
		if(userOld==null)return new User();
		User userNew=userService.findById(userOld.getId());
		if(userNew!=null)session.setAttribute("user",userNew);
		return userNew;
	}
	/**
	 * 
	 * @param pageRequest 接收EasyUIDataPageRequest对象数据
	 * @see cn.jxufe.bean.EasyUIDataPageRequest
	 * @param nickname 接收昵称查询的数据
	 * @param model 接收传到springMVC框架model层数据
	 * @return 将查询的结果EasyUIData对象以JSON格式返回
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
	 * @param model 接收传到springMVC框架model层数据
	 * @return 将查询的结果以JSON格式返回
	 */
	@RequestMapping(value = "gridDataALL", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<User> gridData(Model model) {
		return userService.findALL();
	}
/**
 * 
 * @param user 接收User对象数据
 * @see cn.jxufe.entity.User
 * @param model 接收传到springMVC框架model层数据
 * @return 将保存处理结果Message对象以JSON格式返回
 */
	@RequestMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message save(User user, Model model) {
		return userService.save(user);
	}
	/**
	 * 
	 * @param user 接收User对象数据
	 * @see cn.jxufe.entity.User
	 * @param model 接收传到springMVC框架model层数据
	 * @return 将删除处理结果Message对象以JSON格式返回
	 */
	@RequestMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message delete(User user, Model model) {
		return userService.delete(user);
	}

}