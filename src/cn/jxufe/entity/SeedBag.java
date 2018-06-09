package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;

@Entity
@Table(name = "T_SeedBag")
public class SeedBag extends EntityID {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 用户id
	 */
	private long uId;
	/**
	 * 种子id
	 */
	private long cId;
	/**
	 * 种子数量
	 */
	private int cNumber=0;
	public long getuId() {
		return uId;
	}
	public void setuId(long uId) {
		this.uId = uId;
	}
	public long getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getcNumber() {
		return cNumber;
	}
	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}
}
