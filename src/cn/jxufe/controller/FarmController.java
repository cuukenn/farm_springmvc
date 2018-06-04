package cn.jxufe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("farm")
public class FarmController {
	@RequestMapping(value = "grid")
	public String grid() {
		return "farm/grid";
	}
}