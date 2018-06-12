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

	@Override
	public Iterable<SeedBagView> findByUIdAndLandRequireCaption(long uId, long landId) {
		int ldCode = (int) (landId - 1) / 6;
		String landRQ = "";
		switch (ldCode) {
		case 0:
			landRQ = "金土地";
			break;
		case 1:
			landRQ = "黄土地";
			break;
		case 2:
			landRQ = "红土地";
			break;
		case 3:
			landRQ = "黑土地";
			break;
		default:
			break;
		}

		return seedBagViewDAO.findByUIdAndLandRequireCaption(uId, landRQ);
	}

}
