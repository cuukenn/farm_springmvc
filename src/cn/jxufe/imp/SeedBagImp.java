package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.SeedBagDAO;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.service.SeedBagService;
@Service
public class SeedBagImp implements SeedBagService{
	@Autowired
	SeedBagDAO seedBagDAO;
	@Override
	/**
	 * 根据用户id模糊查询该用户的种子背包
	 * @see cn.jxufe.service.SeedBagService
	 */
	public EasyUIData<?> findByUIdLike(int uId, Pageable pageable) {
		Page<SeedBag> page = seedBagDAO.findByUIdLike(uId, pageable);
        EasyUIData<SeedBag> easyUIData = new EasyUIData<SeedBag>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
        return easyUIData;
	}
	/**
	 * 根据种子id模糊查询该用户的种子背包
	 * @see cn.jxufe.service.SeedBagService
	 */
	@Override
	public EasyUIData<?> findByCIdLike(int cId, Pageable pageable) {
		Page<SeedBag> page = seedBagDAO.findByUIdLike(cId, pageable);
        EasyUIData<SeedBag> easyUIData = new EasyUIData<SeedBag>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
        return easyUIData;
	}
	/**
	 * 保存
	 */
	@Override
	public Message save(SeedBag seedBag) {
		Message message = new Message();
        try {
        	seedBagDAO.save(seedBag);
            message.setCode(0);
            message.setMsg("保存成功");
        }catch(Exception e) {
            message.setCode(-10);
            message.setMsg("保存失败");
        }
        return message;
	}
	/**
	 * 删除种子
	 */
	@Override
	public Message delete(SeedBag seedBag) {
		Message message = new Message();
        try {
        	seedBagDAO.delete(seedBag);
            message.setCode(0);
            message.setMsg("删除成功");
        }catch(Exception e) {
            message.setCode(-10);
            message.setMsg("删除失败");
        }
        return message;
	}
	
	

}
