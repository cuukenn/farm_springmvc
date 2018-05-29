package cn.jxufe.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.dao.SeedStorageDAO;
import cn.jxufe.entity.SeedStorage;
import cn.jxufe.service.SeedStorageService;
@Service
public class SeedStorageImp implements SeedStorageService{
	@Autowired
	SeedStorageDAO seedStorageDAO;
	/**
	 * 
	 * 查询全部
	 * @see cn.jxufe.service.SeedStorageService
	 */

	@Override
	public EasyUIData<?> findALL(Pageable pageable) {
		Page<SeedStorage> page = seedStorageDAO.findAll(pageable);
		EasyUIData<SeedStorage> easyUIData = new EasyUIData<SeedStorage>();
        easyUIData.setTotal(page.getTotalElements());
        easyUIData.setRows(page.getContent());
		return easyUIData;
	}
	
}
