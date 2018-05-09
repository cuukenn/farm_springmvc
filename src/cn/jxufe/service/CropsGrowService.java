package cn.jxufe.service;

import org.springframework.data.domain.Pageable;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.CropsGrow;

public interface CropsGrowService {
	 public EasyUIData<?> findByStatus(int status,Pageable pageable);
	 public EasyUIData<?> findBySome(Pageable pageable);
	 public Message save(CropsGrow cropsGrow);
	 public Message delete(CropsGrow cropsGrow); 
}
