package cn.jxufe.imp;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.dao.SeedBagDAO;
import cn.jxufe.dao.SeedDAO;
import cn.jxufe.dao.UserDAO;
import cn.jxufe.entity.Seed;
import cn.jxufe.entity.SeedBag;
import cn.jxufe.entity.User;
import cn.jxufe.service.SeedBagService;

@Service
public class SeedBagImp implements SeedBagService {
	@Autowired
	SeedBagDAO seedBagDAO;

	@Autowired
	UserDAO userDAO;

	@Autowired
	SeedDAO seedDAO;
	
	@Override
	/**
	 * 根据用户id模糊查询该用户的种子背包
	 * 
	 * @see cn.jxufe.service.SeedBagService
	 */
	public Iterable<SeedBag> findByUId(long uId) {
		return seedBagDAO.findByUId(uId);
	}

	/**
	 * @see cn.jxufe.service.SeedBagService
	 * 保存
	 */
	@Override
	@Transactional
	public Message save(SeedBag seedBag) {
		Message message = new Message();
		try {
			User userOld = userDAO.findById(seedBag.getuId());
			Seed seedOld = seedDAO.findByCId(seedBag.getcId());
			SeedBag seedBagOld = seedBagDAO.findByCIdAndUId(seedBag.getcId(), seedBag.getuId());
			if(seedOld==null) {
				message.setCode(-101);
				message.setMsg("不存在该种子");
				return message;
			}
			if((userOld.getPrice()-seedOld.getPrice())<0) {
				message.setCode(-102);
				message.setMsg("当前金币不足");
				return message;
			}
			if (seedBagOld!=null) {
				seedBagOld.setcNumber(seedBagOld.getcNumber() + 1);
				seedBagDAO.save(seedBagOld);
				userOld.setPrice(userOld.getPrice()-seedOld.getPrice());
				userDAO.save(userOld);
				message.setCode(0);
				message.setMsg("保存成功");
				return message;
			} else {
				seedBag.setcNumber(1);
				seedBagDAO.save(seedBag);
				message.setCode(0);
				message.setMsg("保存成功");
				return message;
			}
		} catch (Exception e) {
			message.setCode(-10);
			message.setMsg("保存失败");
		}
		return message;
	}

	/**
	 * @see cn.jxufe.service.SeedBagService
	 * 删除种子
	 */
	@Override
	public Message delete(SeedBag seedBag) {
		Message message = new Message();
		try {
			SeedBag seedBagOld = seedBagDAO.findByCIdAndUId(seedBag.getcId(), seedBag.getuId());
			if (seedBag!=null&&seedBagOld.getcNumber() > 1) {
				seedBagOld.setcNumber(seedBagOld.getcNumber() - 1);
				seedBagDAO.save(seedBagOld);
			} else {
				seedBagDAO.delete(seedBagOld);
			}
			message.setCode(0);
			message.setMsg("删除成功");
		} catch (

		Exception e) {
			message.setCode(-10);
			message.setMsg("删除失败");
		}
		return message;
	}

}
