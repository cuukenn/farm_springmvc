package cn.jxufe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("land")
public class LandController {
	@RequestMapping(value = "grid")
	public String grid() {
		return "land/grid";
	}
}