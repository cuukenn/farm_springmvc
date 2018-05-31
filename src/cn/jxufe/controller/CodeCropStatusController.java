package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.entity.CodeCropStatus;
import cn.jxufe.service.CodeCropStatusService;
@Controller
@RequestMapping("/codeCropStatus")
	public class CodeCropStatusController {
	    @Autowired
	    private CodeCropStatusService codeCropStatusService;
	    /**
	     * 
	     * @return 以JSON格式返回查找的结果
	     * @throws Exception 异常处理
	     */
	    @RequestMapping(value="/data",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Iterable<CodeCropStatus> data() throws Exception {
	        return codeCropStatusService.findALl();        
	    }
	}

