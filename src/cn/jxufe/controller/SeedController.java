package cn.jxufe.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.Seed;
import cn.jxufe.service.SeedService;

@Controller
@RequestMapping("seed")
public class SeedController {
	@Autowired
	private SeedService seedService;
	/**
	 * 
	 * @return 返回结果放入到RequestMapping中
	 */
	@RequestMapping(value = "grid")
	public String grid() {
		return "seed/grid";
	}
	/**
	 * 
	 * @param pageRequest 接收EasyUIDataPageRequest对象数据
	 * @see cn.jxufe.bean.EasyUIDataPageRequest
	 * @param caption 接收种子标题查询数据
	 * @param model 接收传到springMVC框架model层数据
	 * @return 将查询的结果EasyUIData对象以JSON格式返回
	 */
	@RequestMapping(value = "gridData", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public EasyUIData<?> gridData(EasyUIDataPageRequest pageRequest, @RequestParam(defaultValue = "") String caption,
			Model model) {
		System.out.println(caption);
		List<Sort.Order> orders = new ArrayList<Sort.Order>();
		if (pageRequest.getOrder().equals("asc")) {
			orders.add(new Sort.Order(Direction.ASC, pageRequest.getSort()));
		} else {
			orders.add(new Sort.Order(Direction.DESC, pageRequest.getSort()));
		}
		Pageable pageable = new PageRequest(pageRequest.getPage() - 1, pageRequest.getRows(), new Sort(orders));
		if (caption.equals("")) {
			return seedService.findALL(pageable);
		} else {
			return seedService.findByCaption(caption, pageable);
		}
	}
	/**
	 * 
	 * @param seed 接收Seed对象数据
	 * @see cn.jxufe.entity.Seed
	 * @param model 接收传到springMVC框架model层数据
	 * @return 将保存处理结果Message对象以JSON格式返回
	 */
	@RequestMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message save(Seed seed) {
		return seedService.save(seed);
	}
	/**
	 * 
	 * @param seed 接收Seed对象数据
	 * @see cn.jxufe.entity.Seed
	 * @param model 接收传到springMVC框架model层数据
	 * @return 将删除处理结果Message对象以JSON格式返回
	 */
	@RequestMapping(value = "delete", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message delete(Seed seed) {
		return seedService.delete(seed);
	}
}