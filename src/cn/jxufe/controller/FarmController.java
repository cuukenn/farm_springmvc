package cn.jxufe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping("farm")
public class FarmController {
	/**
	 * 
	 * @return 返回结果放入到RequestMapping中
	 */
	@RequestMapping(value = "grid")
	public String grid() {
		return "farm/grid";
	}
}