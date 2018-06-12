package cn.jxufe.dao;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.jxufe.entity.CodeLandRequire;

public interface CodeLandRequireDAO extends PagingAndSortingRepository<CodeLandRequire, Long> {	
	public CodeLandRequire findByCode( int code);
}