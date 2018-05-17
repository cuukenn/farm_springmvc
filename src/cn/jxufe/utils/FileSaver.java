package cn.jxufe.utils;

import java.io.File;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

import cn.jxufe.bean.Message;

public class FileSaver {
	public static Message save(String imagesPath,HttpServletRequest request,MultipartFile file) {
		Message message = new Message();
		if (!file.isEmpty()) {  
            try {
                String filePath = request.getSession().getServletContext().getRealPath("/") +imagesPath  
                        + file.getOriginalFilename(); 
                file.transferTo(new File(filePath));
                message.setCode(0);
                message.setMsg("保存成功");
            } catch (Exception e) {
            	e.getMessage();
                message.setCode(-1001);
                message.setMsg("保存失败");
            }  
        } 
		return message;
	}
}
