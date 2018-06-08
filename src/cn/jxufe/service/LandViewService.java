package cn.jxufe.service;

import cn.jxufe.bean.Message;
import cn.jxufe.view.LandView;

public interface LandViewService {
	public Message save(LandView landView);
	public Iterable<LandView> findALL();
	public Iterable<LandView> findByCId(long id);
	public Iterable<LandView> findByUId(long id);
}
