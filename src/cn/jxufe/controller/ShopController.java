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
	/**
	 * SeedService接口对象
	 * @see cn.jxufe.service.SeedService
	 */
	@Autowired
	private SeedService seedService;
	/**
	 * 
	 * @return 返回消息放入到RequestMapping中
	 */
	@RequestMapping(value = "grid")
	public String grid() {
		return "shop/grid";
	}
	/**
	 * 
	 * @param session 接收HttpSession对象
	 * @param model 接收MVC框架传到model层的数据
	 * @return 以JSON格式返回查找SeedBag对象
	 */
	@RequestMapping(value = "gridData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<?> gridData(HttpSession session,Model model) {
			long id=-1l;
			User user=(User)session.getAttribute("user");
			if(user!=null)id=user.getId();
			return seedBagService.findByUId(id);
	}
	/**
	 * 
	 * @param seedBag 接收种子收纳袋SeedBag对象
	 * @see cn.jxufe.entity.SeedBag
	 * @param session 接收HttpSession对象
	 * @param model 接收MVC框架传到model层的数据
	 * @return 以JSON格式返回保存seedBag数据的处理消息体类Message对象
	 */
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
				message.setMsg("所购种子不存在");
				return message;
			}
			
		}
		else {
			Message message=new Message();
			message.setCode(-100);
			message.setMsg("购买失败");
			return message;
		}
		
	}
/**
 * 
 * @param seedBag 接收种子收纳袋SeedBag对象
 * @see cn.jxufe.entity.SeedBag
 * @param session 接收HttpSession对象
 * @param model 接收MVC框架传到model层的数据
 * @return 以JSON格式返回删除seedBag数据的处理消息体类Message对象
 */
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
			message.setMsg("删除失败");
			return message;
		}
		
	}

}
