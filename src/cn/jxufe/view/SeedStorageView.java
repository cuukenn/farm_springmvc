package cn.jxufe.view;

import javax.persistence.Entity;
import javax.persistence.Table;

import cn.jxufe.bean.EntityID;

@Entity
@Table(name = "V_SeedStorage")
public class SeedStorageView extends EntityID {
	private static final long serialVersionUID = 1L;
	/**
	 * 种子id
	 */
	private int cId;
	/**
	 * 种子状态
	 */
	private String caption;
	/**
	 * 收获季度
	 */
	private int harvestNum;
	/**
	 * 植物等级
	 */
	private int cropLevel;
	/**
	 * 类型
	 */
	private int type;
	/**
	 * 经验
	 */
	private int exp;
	/**
	 * 成熟时间
	 */
	private int matureTime;
	/**
	 * 输出
	 */
	private int output;
	/**
	 * 价格
	 */
	private int price;
	private int price4UnitSale;
	/**
	 * 土地需求
	 */
	private int landRequirement;
	/**
	 * 分数
	 */
	private int score;
	/**
	 * 详细信息
	 */
	private String tip;
	/**
	 * 宽度
	 */
	private int width;
	/**
	 * 高度
	 */
	private int height;
	/**
	 * x偏移量
	 */
	private int offsetX;
	/**
	 * y偏移量
	 */
	private int offsetY;
	/**
	 * 土地描述
	 */
	private String landRequireCaption;
	/**
	 * 种子类型描述
	 */
	private String seedTypeCaption;
	/**
	 * 成熟步骤
	 */
	private int growStep;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getHarvestNum() {
		return harvestNum;
	}
	public void setHarvestNum(int harvestNum) {
		this.harvestNum = harvestNum;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public String getTip() {
		return tip;
	}
	public void setTip(String tip) {
		this.tip = tip;
	}
	public String getLandRequireCaption() {
		return landRequireCaption;
	}
	public void setLandRequireCaption(String landRequireCaption) {
		this.landRequireCaption = landRequireCaption;
	}
	
	public String getSeedTypeCaption() {
		return seedTypeCaption;
	}
	public void setSeedTypeCaption(String seedTypeCaption) {
		this.seedTypeCaption = seedTypeCaption;
	}
	public int getCropLevel() {
		return cropLevel;
	}
	public void setCropLevel(int cropLevel) {
		this.cropLevel = cropLevel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getMatureTime() {
		return matureTime;
	}
	public void setMatureTime(int matureTime) {
		this.matureTime = matureTime;
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
	public int getPrice4UnitSale() {
		return price4UnitSale;
	}
	public void setPrice4UnitSale(int price4UnitSale) {
		this.price4UnitSale = price4UnitSale;
	}
	public int getLandRequirement() {
		return landRequirement;
	}
	public void setLandRequirement(int landRequirement) {
		this.landRequirement = landRequirement;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
	public int getGrowStep() {
		return growStep;
	}
	public void setGrowStep(int growStep) {
		this.growStep = growStep;
	}
	
}
