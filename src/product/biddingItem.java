package product;

public class biddingItem {
	private int bidId;
	private String category;
	private String name;
	private String description;
	private String SellerId;
	private int priceMin;
	private int priceMax;
	private int priceIncrease;
	private int priceNow;
	private String endTime;
	private String status;
	private String buyerId;
	private String picturePath;
	
	
	public int getBidId() {
		return bidId;
	}
	public void setBidId(int bidId) {
		this.bidId = bidId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSellerId() {
		return SellerId;
	}
	public void setSellerId(String sellerId) {
		SellerId = sellerId;
	}
	public int getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(int priceMin) {
		this.priceMin = priceMin;
	}
	public int getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(int priceMax) {
		this.priceMax = priceMax;
	}
	public int getPriceIncrease() {
		return priceIncrease;
	}
	public void setPriceIncrease(int priceIncrease) {
		this.priceIncrease = priceIncrease;
	}
	public int getPriceNow() {
		return priceNow;
	}
	public void setPriceNow(int priceNow) {
		this.priceNow = priceNow;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getPicturePath() {
		return picturePath;
	}
	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
	
	
	
	
}
