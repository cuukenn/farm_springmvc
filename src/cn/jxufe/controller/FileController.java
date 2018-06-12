package cn.jxufe.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import cn.jxufe.bean.Message;
import cn.jxufe.utils.FileSaver;
/**
 * 处理文件上传的Controller层类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("file")
public class FileController {
	/**
	 * 
	 * @param request 接收HttpServletRequest对象数据
	 * @param uploadFile 接收上传文件的MultipartFile对象数据
	 * @return 将图像保存处理结果Message对象以JSON格式返回
	 */
	@RequestMapping(value = "saveHeadImg", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Message saveHeadImg(HttpServletRequest request, @RequestParam("filePathName") MultipartFile uploadFile) {
		return FileSaver.save("images/headImages/", request, uploadFile);
	}
}