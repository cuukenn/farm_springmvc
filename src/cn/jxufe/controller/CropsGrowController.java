package cn.jxufe.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	    public  List<CropsGrow> gridData(@PathVariable int cId,Model model){
	        return cropsGrowService.findByCId(cId);
	    }
	    @RequestMapping(value="save",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message save(CropsGrow cropsGrow,Model model){       
	        return cropsGrowService.save(cropsGrow);
	    }
	    @RequestMapping(value="delete",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message delete(CropsGrow cropsGrow,Model model){     
	        return cropsGrowService.delete(cropsGrow);
	    }  
	}