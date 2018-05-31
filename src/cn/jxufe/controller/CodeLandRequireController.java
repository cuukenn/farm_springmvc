package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.entity.CodeLandRequire;
import cn.jxufe.service.CodeLandRequireService;
@Controller
@RequestMapping("/codeLandRequire")
	public class CodeLandRequireController {
	    @Autowired
	    private CodeLandRequireService codeLandRequireService;
	    /**
	     * 
	     * @return 以JSON格式返回查找的结果
	     * @throws Exception 异常处理
	     */
	    @RequestMapping(value="/data",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Iterable<CodeLandRequire> data() throws Exception {
	        return codeLandRequireService.findALl();        
	    }
	}

