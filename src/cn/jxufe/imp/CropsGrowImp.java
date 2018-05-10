package cn.jxufe.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.CropsGrowDAO;
import cn.jxufe.entity.CropsGrow;
import cn.jxufe.entity.Seed;
import cn.jxufe.service.CropsGrowService;
@Service
public class CropsGrowImp implements CropsGrowService{
	@Autowired
	CropsGrowDAO cropsGrowDAO ;
	@Override
	public EasyUIData<?> findByStatus(int status, Pageable pageable) {
		Page<CropsGrow> page = cropsGrowDAO.findByStatus(status,pageable);
        EasyUIData<CropsGrow> easyUIData = new EasyUIData<CropsGrow>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
        return easyUIData;
	}

	@Override
	public Message save(CropsGrow cropsGrow) {
		Message message = new Message();
        try {
        	cropsGrowDAO.save(cropsGrow);
            message.setCode(0);
            message.setMsg("保存成功");
        }catch(Exception e) {
            message.setCode(-10);
            message.setMsg("保存失败");
        }
        return message;
	}

	@Override
	public Message delete(CropsGrow cropsGrow) {
		Message message = new Message();
        try {
        	cropsGrowDAO.delete(cropsGrow);
            message.setCode(0);
            message.setMsg("删除成功");
        }catch(Exception e) {
            message.setCode(-10);
            message.setMsg("删除失败");
        }
        return message;
	}

	public EasyUIData<?> findBySome(Pageable pageable) {
		// TODO Auto-generated method stub
		Page<CropsGrow> page = cropsGrowDAO.findAll(pageable);
		EasyUIData<CropsGrow> easyUIData = new EasyUIData<CropsGrow>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
		return easyUIData;
	}

	@Override
	public List<CropsGrow> findByCId(int cId) {
		return cropsGrowDAO.findByCId(cId);
	}

	

}
