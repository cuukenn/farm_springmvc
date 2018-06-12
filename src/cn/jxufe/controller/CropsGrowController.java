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
/**
 * 处理作物生长的Controller层类
 * @author Administrator
 *
 */
@Controller
	@RequestMapping("cropsGrow")
	public class CropsGrowController {
	    @Autowired
	    private CropsGrowService cropsGrowService;
	    /**
	     * 
	     * @return 将查询的结果插入到RequestMapping中
	     */
	    @RequestMapping(value="grid")
	    public String grid(){
	        return "cropsGrow/grid";
	    }
	   /**
	    * 
	    * @param cId 接收种子ID查询的数据
	    * @param model 接收传到springMVC框架里model层数据
	    * @return 将查询到的结果以JSON格式返回
	    */
	    @RequestMapping(value="/gridData/{cId}",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public  List<CropsGrow> gridData(@PathVariable int cId,Model model){
	        return cropsGrowService.findByCId(cId);
	    }
	    /**
	     * 
	     * @param cropsGrow 接收CropsGrow对象数据
	     * @see cn.jxufe.entity.CropsGrow
	     * @param model 接收传到springMVC框架里model层数据
	     * @return 将保存数据处理结果Message对象以JSON格式返回
	     */
	    @RequestMapping(value="save",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message save(CropsGrow cropsGrow,Model model){       
	        return cropsGrowService.save(cropsGrow);
	    }
	    /**
	     * 
	     * @param cropsGrow 接收CropsGrow对象数据
	     * @see cn.jxufe.entity.CropsGrow
	     * @param model 接收传到springMVC框架里model层数据
	     * @return 将删除数据处理结果Message对象以JSON格式返回
	     */
	    @RequestMapping(value="delete",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message delete(CropsGrow cropsGrow,Model model){     
	        return cropsGrowService.delete(cropsGrow);
	    }  
	}