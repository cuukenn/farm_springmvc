package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.entity.CodeLandRequire;
import cn.jxufe.service.CodeLandRequireService;
/**
 * 处理作物土地需求的Controller层类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/codeLandRequire")
	public class CodeLandRequireController {
	    @Autowired
	    private CodeLandRequireService codeLandRequireService;
	    /**
	     * @return 将查询到的结果以JSON格式返回
	     * @throws Exception 抛出异常
	     */
	    @RequestMapping(value="/data",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Iterable<CodeLandRequire> data() throws Exception {
	        return codeLandRequireService.findALl();        
	    }
	}

