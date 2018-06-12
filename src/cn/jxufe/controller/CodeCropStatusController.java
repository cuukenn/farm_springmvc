package cn.jxufe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jxufe.entity.CodeCropStatus;
import cn.jxufe.service.CodeCropStatusService;
/**
 * 处理作物生长状态的Controller层类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/codeCropStatus")
	public class CodeCropStatusController {
	    @Autowired
	    private CodeCropStatusService codeCropStatusService;
	    /**
	     * @return 将查询到的结果以JSON格式返回
	     * @throws Exception 抛出异常
	     */
	    @RequestMapping(value="/data",produces=MediaType.APPLICATION_JSON_VALUE)
	    @ResponseBody
	    public Iterable<CodeCropStatus> data() throws Exception {
	        return codeCropStatusService.findALl();        
	    }
	}

