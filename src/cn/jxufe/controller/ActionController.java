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

@Controller
@RequestMapping("action")
public class ActionController {
	@Autowired
	private FarmService farmService;

	@RequestMapping(value = "grid")
	public String grid() {
		return "action/grid";
	}

	@RequestMapping(value = "plant", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionPlant(@RequestBody FarmRequest farmRequest, HttpSession session, Model model) {
		return farmService.actionPlant(farmRequest.getLandId(), farmRequest.getcId(), session);
	}

	@RequestMapping(value = "killWorm", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionKillWorm(@RequestBody FarmRequest farmRequest,  HttpSession session, Model model) {
		return farmService.actionKillWorm(farmRequest.getLandId(), session);
	}

	@RequestMapping(value = "harvest", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionHarvest(@RequestBody FarmRequest farmRequest,  HttpSession session, Model model) {
		return farmService.actionHarvest(farmRequest.getLandId(), session);
	}

	@RequestMapping(value = "cleanLand", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionCleanLand(@RequestBody FarmRequest farmRequest,  HttpSession session, Model model) {
		return farmService.actionCleanLand(farmRequest.getLandId(), session);
	}

}