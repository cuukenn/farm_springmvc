package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.jxufe.entity.CodeSeedType;
import cn.jxufe.service.CodeSeedTypeService;
@Controller
@RequestMapping("/codeSeedType")
	public class CodeSeedTypeController {
	    @Autowired
	    private CodeSeedTypeService codeSeedTypeService;
	    @RequestMapping(value="/data",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Iterable<CodeSeedType> data() throws Exception {
	        return codeSeedTypeService.findALl();        
	    }
	}

