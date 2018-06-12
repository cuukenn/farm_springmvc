package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.jxufe.entity.CodeSeedType;
import cn.jxufe.service.CodeSeedTypeService;
/**
 * 处理种子类型的Controller层类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/codeSeedType")
	public class CodeSeedTypeController {
	    @Autowired
	    private CodeSeedTypeService codeSeedTypeService;
	    /**
	     * @return 将查询到的结果以JSON格式返回
	     * @throws Exception 抛出异常
	     */
	    @RequestMapping(value="/data",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Iterable<CodeSeedType> data() throws Exception {
	        return codeSeedTypeService.findALl();        
	    }
	}

