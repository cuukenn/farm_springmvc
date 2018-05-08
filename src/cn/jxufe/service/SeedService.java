package cn.jxufe.service;

import org.springframework.data.domain.Pageable;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.Seed;

public interface SeedService {
	 public EasyUIData<?> findSome(int type,Pageable pageable);
	 public Message save(Seed seed);
	 public Message delete(Seed seed); 
}
