package cn.jxufe.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;

@Entity
@Table(name="T_CropsGrow")
public class CropsGrow  extends EntityID{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private int cId;
    private long growTime;
    private int growStep;
    private String growCaption;
    private float insect;
    private int width;
    private int height;
    private int offsetX; 
    private int offsetY;
    private int status;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public long getGrowTime() {
		return growTime;
	}
	public void setGrowTime(long growTime) {
		this.growTime = growTime;
	}
	public int getGrowStep() {
		return growStep;
	}
	public void setGrowStep(int growStep) {
		this.growStep = growStep;
	}
	public String getGrowCaption() {
		return growCaption;
	}
	public void setGrowCaption(String growCaption) {
		this.growCaption = growCaption;
	}
	public float getInsect() {
		return insect;
	}
	public void setInsect(float insect) {
		this.insect = insect;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getOffsetX() {
		return offsetX;
	}
	public void setOffsetX(int offsetX) {
		this.offsetX = offsetX;
	}
	public int getOffsetY() {
		return offsetY;
	}
	public void setOffsetY(int offsetY) {
		this.offsetY = offsetY;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
   
}