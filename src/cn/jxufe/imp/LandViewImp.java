package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;

import cn.jxufe.bean.Message;
import cn.jxufe.dao.LandViewDAO;
import cn.jxufe.service.LandViewService;
import cn.jxufe.view.LandView;

public class LandViewImp  implements LandViewService {

	@Autowired
	LandViewDAO landViewDAO;
	
	@Override
	public Message save(LandView landView) {
		Message message = new Message();
        try {
        	landViewDAO.save(landView);
            message.setCode(0);
            message.setMsg("保存成功");
        }catch(Exception e) {
            message.setCode(-10);
            message.setMsg("保存失败");
        }
        return message;
	}

}
