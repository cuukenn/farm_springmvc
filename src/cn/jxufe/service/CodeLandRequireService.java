package cn.jxufe.service;

import cn.jxufe.entity.CodeLandRequire;

public interface CodeLandRequireService {
	/**
	 * 
	 * @return 所有的CodeLandRequire
	 */
	public Iterable<CodeLandRequire> findALl();
	
	public boolean isLandTypeSame(long landId,int landRequirementCode);
}
