package cn.jxufe.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;

@Entity
@Table(name="T_Land")
public class UserLand  extends EntityID{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private long uId;
    private int landType;
    private int status;
    private int cId;
    private int worm;
    private int landOutput;
    private Date plantTime;
    private int landCode;
	public long getuId() {
		return uId;
	}
	public void setuId(long uId) {
		this.uId = uId;
	}
	public int getLandType() {
		return landType;
	}
	public void setLandType(int landType) {
		this.landType = landType;
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
	public int getLandOutput() {
		return landOutput;
	}
	public void setLandOutput(int landOutput) {
		this.landOutput = landOutput;
	}
	public Date getPlantTime() {
		return plantTime;
	}
	public void setPlantTime(Date plantTime) {
		this.plantTime = plantTime;
	}
	public int getLandCode() {
		return landCode;
	}
	public void setLandCode(int landCode) {
		this.landCode = landCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
