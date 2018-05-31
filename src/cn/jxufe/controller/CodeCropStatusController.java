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
	     * @return ��JSON��ʽ���ز��ҵĽ��
	     * @throws Exception �쳣����
	     */
	    @RequestMapping(value="/data",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Iterable<CodeCropStatus> data() throws Exception {
	        return codeCropStatusService.findALl();        
	    }
	}

