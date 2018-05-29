package cn.jxufe.view;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;

@Entity
@Table(name = "V_SeedBag")
public class SeedBagView extends EntityID {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int cId;
	private long uId;
	private int cNumber;
	private String seedCaption;
	private String landRequireCaption;
	private int harvestNum;
	private int output;
	private int price;
	private int growStep;
	
	public int getGrowStep() {
		return growStep;
	}
	public void setGrowStep(int growStep) {
		this.growStep = growStep;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public long getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public int getcNumber() {
		return cNumber;
	}
	public void setcNumber(int cNumber) {
		this.cNumber = cNumber;
	}
	public String getSeedCaption() {
		return seedCaption;
	}
	public void setSeedCaption(String seedCaption) {
		this.seedCaption = seedCaption;
	}
	public String getLandRequireCaption() {
		return landRequireCaption;
	}
	public void setLandRequireCaption(String landRequireCaption) {
		this.landRequireCaption = landRequireCaption;
	}
	public int getHarvestNum() {
		return harvestNum;
	}
	public void setHarvestNum(int harvestNum) {
		this.harvestNum = harvestNum;
	}
	public int getOutput() {
		return output;
	}
	public void setOutput(int output) {
		this.output = output;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
