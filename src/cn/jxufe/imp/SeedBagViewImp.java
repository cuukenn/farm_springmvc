package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxufe.dao.SeedBagViewDAO;
import cn.jxufe.service.SeedBagViewService;
import cn.jxufe.view.SeedBagView;

@Service
public class SeedBagViewImp implements SeedBagViewService {
	@Autowired
	SeedBagViewDAO seedBagViewDAO;

	/**
	 * 
	 * 查询全部
	 * 
	 * @see cn.jxufe.service.SeedStorageService
	 */

	@Override
	public Iterable<SeedBagView> findByUId(long uId) {
		return seedBagViewDAO.findByUId(uId);
	}

}
