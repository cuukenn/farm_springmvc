package cn.jxufe.entity;

import java.sql.Date;

import cn.jxufe.bean.EntityID;

public class Land extends EntityID {
	private int uid;
	private int landId;
	private int status;
	private int cId;
	private int worm;
	private int loss;
	private int curHarvestNum;
	private Date plantTime=new Date(new java.util.Date().getTime());

	
	public int getCurHarvestNum() {
		return curHarvestNum;
	}

	public void setCurHarvestNum(int curHarvestNum) {
		this.curHarvestNum = curHarvestNum;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getLandId() {
		return landId;
	}

	public void setLandId(int landId) {
		this.landId = landId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
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

	public Date getPlantTime() {
		return plantTime;
	}

	public void setPlantTime(Date plantTime) {
		this.plantTime = plantTime;
	}

}
