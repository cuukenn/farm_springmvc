package cn.jxufe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	public Message actionPlant(int landId, int cId, HttpSession session,Model model) {
		return farmService.actionPlant(landId, cId, session);
	}
	
	@RequestMapping(value = "killWorm", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionKillWorm(int landId, HttpSession session,Model model) {
		return farmService.actionKillWorm(landId, session);
	} 
	
	@RequestMapping(value = "harvest", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionHarvest(int landId, HttpSession session,Model model) {
		return farmService.actionHarvest(landId, session);
	} 
	
	
	@RequestMapping(value = "cleanLand", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message actionCleanLand(int landId, HttpSession session,Model model) {
		return farmService.actionCleanLand(landId, session);
	} 
	
}