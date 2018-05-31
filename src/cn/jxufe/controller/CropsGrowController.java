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
	    /**
	     * 
	     * @return 将返回消息放入到RequestMapping中
	     */
	    @RequestMapping(value="grid")
	    public String grid(){
	        return "cropsGrow/grid";
	    }
	    /**
	     * 
	     * @param cId 接收种子id的参数
	     * @param model 接收MVC框架里传到model层的数据
	     * @return 以JSON格式返回查找的结果List
	     */
	    @RequestMapping(value="/gridData/{cId}",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public  List<CropsGrow> gridData(@PathVariable int cId,Model model){
	        return cropsGrowService.findByCId(cId);
	    }
	    /**
	     * 
	     * @param cropsGrow 接收种子状态CropsGrow对象
	     * @see cn.jxufe.entity.CropsGrow
	     * @param model 接收MVC框架里传到model层的数据
	     * @return 以JSON格式返回保存处理的消息体Message对象
	     */
	    @RequestMapping(value="save",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message save(CropsGrow cropsGrow,Model model){       
	        return cropsGrowService.save(cropsGrow);
	    }
	    /**
	     * 
	     * @param cropsGrow 接收种子状态CropsGrow对象
	     * @see cn.jxufe.entity.CropsGrow
	     * @param model 接收MVC框架里传到model层的数据
	     * @return 以JSON格式返回删除处理的消息体Message对象
	     */
	    @RequestMapping(value="delete",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message delete(CropsGrow cropsGrow,Model model){     
	        return cropsGrowService.delete(cropsGrow);
	    }  
	}