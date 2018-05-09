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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.EasyUIDataPageRequest;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.CropsGrow;
import cn.jxufe.service.CropsGrowService;

@Controller
	@RequestMapping("cropsGrow")
	public class CropsGrowController {
	    @Autowired
	    private CropsGrowService cropsGrowService;
	    @RequestMapping(value="grid")
	    public String grid(){
	        return "cropsGrow/grid";
	    }
	    @RequestMapping(value="/gridData/{cId}",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public  EasyUIData<?> gridData(EasyUIDataPageRequest pageRequest,@PathVariable int cId,Model model){
	        List<Sort.Order> orders = new ArrayList<Sort.Order>();
	        if(pageRequest.getOrder().equals("asc")) {
	            orders.add(new Sort.Order(Direction.ASC,pageRequest.getSort()));
	        }else {
	            orders.add(new Sort.Order(Direction.DESC,pageRequest.getSort()));
	        }
	        Pageable pageable = new PageRequest(pageRequest.getPage()-1, pageRequest.getRows(), new Sort(orders)); 
	        return cropsGrowService.findByCId(cId,pageable);
	    }
	    @RequestMapping(value="save",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message save(CropsGrow cropsGrow,Model model){       
	        return cropsGrowService.save(cropsGrow);
	    }
	}