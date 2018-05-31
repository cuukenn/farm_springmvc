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
	     * @return ��������Ϣ���뵽RequestMapping��
	     */
	    @RequestMapping(value="grid")
	    public String grid(){
	        return "cropsGrow/grid";
	    }
	    /**
	     * 
	     * @param cId ��������id�Ĳ���
	     * @param model ����MVC����ﴫ��model�������
	     * @return ��JSON��ʽ���ز��ҵĽ��List
	     */
	    @RequestMapping(value="/gridData/{cId}",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public  List<CropsGrow> gridData(@PathVariable int cId,Model model){
	        return cropsGrowService.findByCId(cId);
	    }
	    /**
	     * 
	     * @param cropsGrow ��������״̬CropsGrow����
	     * @see cn.jxufe.entity.CropsGrow
	     * @param model ����MVC����ﴫ��model�������
	     * @return ��JSON��ʽ���ر��洦�����Ϣ��Message����
	     */
	    @RequestMapping(value="save",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message save(CropsGrow cropsGrow,Model model){       
	        return cropsGrowService.save(cropsGrow);
	    }
	    /**
	     * 
	     * @param cropsGrow ��������״̬CropsGrow����
	     * @see cn.jxufe.entity.CropsGrow
	     * @param model ����MVC����ﴫ��model�������
	     * @return ��JSON��ʽ����ɾ���������Ϣ��Message����
	     */
	    @RequestMapping(value="delete",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Message delete(CropsGrow cropsGrow,Model model){     
	        return cropsGrowService.delete(cropsGrow);
	    }  
	}