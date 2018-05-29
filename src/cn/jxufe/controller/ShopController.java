package cn.jxufe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.Message;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.entity.User;
import cn.jxufe.service.SeedBagService;
import cn.jxufe.service.SeedService;

@Controller
@RequestMapping("shop")
public class ShopController {
	/**
	 * SeedBagService接口对象
	 * 
	 * @see cn.jxufe.service.SeedBagService
	 */
	@Autowired
	private SeedBagService seedBagService;

	@Autowired
	private SeedService seedService;
	
	@RequestMapping(value = "grid")
	public String grid() {
		return "shop/grid";
	}

	@RequestMapping(value = "gridData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<?> gridData(HttpSession session,Model model) {
			long id=-1l;
			User user=(User)session.getAttribute("user");
			if(user!=null)id=user.getId();
			return seedBagService.findByUId(id);
	}
	@RequestMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message save(@RequestBody SeedBag seedBag, HttpSession session,Model model) {
		User user=(User)session.getAttribute("user");
		if(user!=null) {
			seedBag.setuId(user.getId());
			if(seedService.findByCID(seedBag.getcId())!=null) {
				return seedBagService.save(seedBag);
			}
			else {
				Message message=new Message();
				message.setCode(-101);
				message.setMsg("种子不存在");
				return message;
			}
			
		}
		else {
			Message message=new Message();
			message.setCode(-100);
			message.setMsg("未登录");
			return message;
		}
		
	}

	@RequestMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message delete(@RequestBody SeedBag seedBag, HttpSession session,Model model) {
		User user=(User)session.getAttribute("user");
		if(user!=null) {
			seedBag.setuId(user.getId());
			return seedBagService.delete(seedBag);
		}
		else {
			Message message=new Message();
			message.setCode(-100);
			message.setMsg("未登录");
			return message;
		}
		
	}

}
