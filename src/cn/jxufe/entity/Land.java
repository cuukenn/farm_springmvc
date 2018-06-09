package cn.jxufe.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;

@Entity
@Table(name = "T_Land")
public class Land extends EntityID {
	private long uId;
	private long landId;
	private int status = 1;
	private long cId;
	private int worm;
	private int loss;
	private int curHarvestNum = 1;
	private Date plantTime = new Date(new java.util.Date().getTime());
	public long getuId() {
		return uId;
	}
	public void setuId(long uId) {
		this.uId = uId;
	}
	public long getLandId() {
		return landId;
	}
	public void setLandId(long landId) {
		this.landId = landId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getcId() {
		return cId;
	}
	public void setcId(long cId) {
		this.cId = cId;
	}
	public int getWorm() {
		return worm;
	}
	public void setWorm(int worm) {
		this.worm = worm;
	}
	public int getLoss() {
		return loss;
	}
	public void setLoss(int loss) {
		this.loss = loss;
	}
	public int getCurHarvestNum() {
		return curHarvestNum;
	}
	public void setCurHarvestNum(int curHarvestNum) {
		this.curHarvestNum = curHarvestNum;
	}
	public Date getPlantTime() {
		return plantTime;
	}
	
	

}
