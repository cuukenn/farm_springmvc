package cn.jxufe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.Message;
import cn.jxufe.entity.User;
import cn.jxufe.service.FarmService;
import cn.jxufe.service.UserService;

@Controller
@RequestMapping("farm")
public class FarmController {
	@Autowired
	private UserService userService;
	/**
	 * FarmService接口对象
	 * 
	 * @see cn.jxufe.service.FarmService
	 */
	@Autowired
	private FarmService farmService;

	@RequestMapping(value = "grid")
	public String grid() {
		return "farm/grid";
	}

	@RequestMapping(value = "getUser", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> getUser() {
		return (List<User>) userService.findALL(null);
	}

	/**
	 * 
	 * @param session
	 *            接收前台的HttpSession信息
	 * @param user
	 *            接收选择的用户信息
	 * @return 将当前的用户信息放入session
	 */
	@RequestMapping(value = "setCurUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message setCurUser(HttpSession session, @RequestBody User user) {
		return farmService.setCurUser(session, user);
	}
}