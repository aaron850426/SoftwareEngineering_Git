package product;

public class shoppingCartItem {
	private int shoppingCartId;
	private String userId;
	private int auctionId;
	private String auctionName;
	private int numberOfProduct;
	private int price;
	private int total;
	private String sellerId;
	
	
	public int getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public String getAuctionName() {
		return auctionName;
	}
	public void setAuctionName(String auctionName) {
		this.auctionName = auctionName;
	}
	public int getNumberOfProduct() {
		return numberOfProduct;
	}
	public void setNumberOfProduct(int numberOfProduct) {
		this.numberOfProduct = numberOfProduct;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	
	
}
