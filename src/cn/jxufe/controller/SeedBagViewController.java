package cn.jxufe.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.entity.User;
import cn.jxufe.service.SeedBagViewService;

@Controller
@RequestMapping("/seedBagView")
public class SeedBagViewController {
	@Autowired
	private SeedBagViewService seedBagViewService;

	@RequestMapping(value = "gridData/{landId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Iterable<?> gridData(@PathVariable long landId, Model model, HttpSession session) {
		User user = (User) session.getAttribute("user");
		if (user == null)
			return null;
		if (landId == 0)
			return seedBagViewService.findByUId(user.getId());
		else
			return seedBagViewService.findByUIdAndLandRequireCaption(user.getId(), landId);
	}
}
