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
	
	/**
	 * 种子id
	 */
	private int cId;
	/**
	 * 用户id
	 */
	private long uId;
	/**
	 * 种子数量
	 */
	private int cNumber;
	/**
	 * 种子描述
	 */
	private String seedCaption;
	/**
	 * 土地描述
	 */
	private String landRequireCaption;
	/**
	 * 种子生长季数
	 */
	private int harvestNum;
	/**
	 * 产量
	 */
	private int output;
	/**
	 * 价格
	 */
	private int price;
	/**
	 * 生长阶段
	 */
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
