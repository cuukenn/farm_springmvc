package cn.jxufe.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.CodeCropStatus;

public interface CodeCropStatusDAO extends PagingAndSortingRepository<CodeCropStatus, Long> {
	
}