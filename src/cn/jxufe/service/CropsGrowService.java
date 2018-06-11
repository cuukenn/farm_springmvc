package cn.jxufe.service;

import org.springframework.data.domain.Pageable;

import cn.jxufe.bean.EasyUIData;
import cn.jxufe.bean.Message;
import cn.jxufe.entity.CropsGrow;

public interface CropsGrowService {
	/**
	 * 
	 * @param status 状态
	 * @param pageable 分页
	 * @return 所有的EasyUIData分页列表
	 */
	 public EasyUIData<?> findByStatus(int status,Pageable pageable);
	 /**
	  * 
	  * @param pageable 分页
	  * @return 所有的EasyUIData分页列表
	  */
	 public EasyUIData<?> findBySome(Pageable pageable);
	 /**
	  * 
	  * @param cId 植物id
	  * @return 所有的植物
	  */
	 public java.util.List<CropsGrow> findByCId(int cId);
	 /**
	  * 
	  * @param cropsGrow 植物
	  * @return 运行结果消息状态
	  */
	 public Message save(CropsGrow cropsGrow);
	 /**
	  * 
	  * @param cropsGrow 植物
	  * @return 运行结果消息状态
	  */
	 public Message delete(CropsGrow cropsGrow); 
	 
	 public CropsGrow findNextCrops(long cId,int growStep); 
	 
	 public CropsGrow findFirstCrops(long cId); 
}
