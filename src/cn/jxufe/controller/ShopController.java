package cn.jxufe.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.entity.User;
import cn.jxufe.service.SeedBagService;

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


	@RequestMapping(value = "grid")
	public String grid() {
		return "shop/grid";
	}

	@RequestMapping(value = "gridData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public EasyUIData<?> gridData(EasyUIDataPageRequest pageRequest, HttpSession session,
			Model model) {
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		if (pageRequest.getOrder().equals("asc")) {
			orders.add(new Sort.Order(Direction.ASC, pageRequest.getSort()));
		} else {
			orders.add(new Sort.Order(Direction.DESC, pageRequest.getSort()));
		}
		Pageable pageable = new PageRequest(pageRequest.getPage() - 1, pageRequest.getRows(), new Sort(orders));
		
			return seedBagService.findByUIdLike(((User)session.getAttribute("user")).getId(), pageable);
	}
	@RequestMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message save(SeedBag seedBag, Model model) {
		return seedBagService.save(seedBag);
	}

	@RequestMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message delete(SeedBag seedBag, Model model) {
		return seedBagService.delete(seedBag);
	}

}
