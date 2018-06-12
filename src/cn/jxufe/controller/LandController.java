package cn.jxufe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.entity.User;
import cn.jxufe.service.LandViewService;

@Controller
@RequestMapping("land")
public class LandController {
	@Autowired
	private LandViewService landViewService;
/**
 * 
 * @return 返回结果放入到RequestMapping中
 */
	@RequestMapping(value = "grid")
	public String grid() {
		return "land/grid";
	}
/**
 * @param session 接收HttpSession对象数据
 * @return 将session当中的用户信息查询结果以JSON格式返回
 */
	@RequestMapping(value = "gridViewData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<?> gridData(HttpSession session) {
		User user=(User)session.getAttribute("user");
		if(user==null)return null;
		return landViewService.findByUId(user.getId());
	}
}