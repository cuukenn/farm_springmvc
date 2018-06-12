package cn.jxufe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.FarmRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.service.FarmService;
/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("action")
public class ActionController {
	@Autowired
	private FarmService farmService;

	/**
	 * 
	 * @return 返回结果放入到RequestMapping中
	 */
	@RequestMapping(value = "grid")
	public String grid() {
		return "action/grid";
	}

	/**
	 * 处理播种请求
	 * @param farmRequest 接收操作农场请求对象
	 * @see cn.jxufe.bean.FarmRequest
	 * @param session 接收HttpSession对象
	 * @param model 接收传到springMVC框架model层数据
	 * @return JSON格式返回处理结果Message对象
	 */
	@RequestMapping(value = "plant", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionPlant(@RequestBody FarmRequest farmRequest, HttpSession session, Model model) {
		return farmService.actionPlant(farmRequest.getLandId(), farmRequest.getcId(), session);
	}

	/**
	 * 处理杀虫请求
	 * @param farmRequest 接收操作农场请求对象
	 * @see cn.jxufe.bean.FarmRequest
	 * @param session 接收HttpSession对象
	 * @param model 接收传到springMVC框架model层数据
	 * @return JSON格式返回处理结果Message对象
	 */
	@RequestMapping(value = "killWorm", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionKillWorm(@RequestBody FarmRequest farmRequest, HttpSession session, Model model) {
		return farmService.actionKillWorm(farmRequest.getLandId(), session);
	}

	/**
	 * 处理收获请求
	 * @param farmRequest 接收操作农场请求对象
	 * @see cn.jxufe.bean.FarmRequest
	 * @param session 接收HttpSession对象
	 * @param model 接收传到springMVC框架model层数据
	 * @return JSON格式返回处理结果Message对象
	 */
	@RequestMapping(value = "harvest", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionHarvest(@RequestBody FarmRequest farmRequest, HttpSession session, Model model) {
		return farmService.actionHarvest(farmRequest.getLandId(), session);
	}

	/**
	 * 处理除枯草请求
	 * @param farmRequest 接收操作农场请求对象
	 * @see cn.jxufe.bean.FarmRequest
	 * @param session 接收HttpSession对象
	 * @param model 接收传到springMVC框架model层数据
	 * @return JSON格式返回处理结果Message对象
	 */
	@RequestMapping(value = "cleanLand", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionCleanLand(@RequestBody FarmRequest farmRequest, HttpSession session, Model model) {
		return farmService.actionCleanLand(farmRequest.getLandId(), session);
	}

}