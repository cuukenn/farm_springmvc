package cn.jxufe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * 处理种子收纳袋的Controller层类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("seedBag")
public class SeedBagController {
	/**
	 * 
	 * @return 返回结果放入到RequestMapping中
	 */
	@RequestMapping(value = "grid")
	public String grid() {
		return "seedBag/grid";
	}
}