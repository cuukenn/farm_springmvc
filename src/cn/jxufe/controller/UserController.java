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
 *�û�������� MVC�����controller����
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	/**
	 * UserService�ӿڶ���
	 * 
	 * @see cn.jxufe.service.UserService
	 */
	private UserService userService;
	@RequestMapping(value = "grid")
	/**
	 * 
	 * @return ���ض�����뵽RequestMapping����
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
	 *           �����������HttpSession����
	 * @param user
	 *            �����û�User����
	 * @return ���û���Ϣ����session��JSON�ĸ�ʽ���ش�����ʾ��ϢMessage�����
	 */
	@RequestMapping(value = "setCurUser", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message setCurUser(HttpSession session, @RequestBody User user) {
		return userService.setCurUser(session, user);
	}
	
	/**
	 * 
	 * @param session �����������HttpSession����
	 * @param user �����û�User����
	 * @return ��JSON�ĸ�ʽ�������õ��û�User�����
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
	 * @param pageRequest ����ǰ̨�����ķ�ҳ����
	 * @see EasyUIDataPageRequest
	 * @param nickname �����ǳƲ��ҵĲ���
	 * @param model MVC��ܵ�model�����
	 * @return ����EasyUIData����JSON��ʽ����
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
	 * @param model ����MVC��ܵ�model������
	 * @return ���ز��ҵĽ������JSON�ĸ�ʽ����
	 */
	@RequestMapping(value = "gridDataALL", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<User> gridData(Model model) {
		return userService.findALL();
	}

	/**
	 * 
	 * @param user ����User�����
	 * @see cn.jxufe.entity.User
	 * @param model ����MVC��ܴ���model������
	 * @return ���ر��洦�����Ϣ��Message�Ķ���
	 */
	@RequestMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message save(User user, Model model) {
		return userService.save(user);
	}

	/**
	 * @param user ����User�����
	 * @see cn.jxufe.entity.User
	 * @param model ����MVC��ܴ���model������
	 * @return ����ɾ���������Ϣ��Message�Ķ���
	 */
	@RequestMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message delete(User user, Model model) {
		return userService.delete(user);
	}

}