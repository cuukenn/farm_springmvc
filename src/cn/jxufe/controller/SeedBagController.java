package cn.jxufe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("seedBag")
public class SeedBagController {
	@RequestMapping(value = "grid")
	public String grid() {
		return "seedBag/grid";
	}
}