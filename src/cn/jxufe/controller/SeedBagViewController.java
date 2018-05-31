package cn.jxufe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.entity.User;
import cn.jxufe.service.SeedBagViewService;

@Controller
@RequestMapping("/seedBagView")
public class SeedBagViewController {
	@Autowired
	private SeedBagViewService seedBagViewService;
	/**
	 * 
	 * @param pageRequest接收分页请求参数
	 * @see cn.jxufe.bean.EasyUIDataPageRequest
	 * @param model 接收MVC框架传到model层参数
	 * @param session 接收HttpSession对象
	 * @return 以JSON格式返回EasyUIData对象
	 */
	@RequestMapping(value = "gridData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<?> gridData(EasyUIDataPageRequest pageRequest, Model model,HttpSession session) {
		User user=(User)session.getAttribute("user");
		if(user==null)return null;
		return seedBagViewService.findByUId(user.getId());
	}
}
