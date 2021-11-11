package Main;

public class Shoe {
	private String shoeId, shoeName, shoeDescription;
	private int shoePrice;

	public Shoe(String shoeId, String shoeName, String shoeDescription, int shoePrice) {
		this.shoeId = shoeId;
		this.shoeName = shoeName;
		this.shoeDescription = shoeDescription;
		this.shoePrice = shoePrice;
	}

	public String getShoeId() {
		return shoeId;
	}

	public String getShoeName() {
		return shoeName;
	}

	public String getShoeDescription() {
		return shoeDescription;
	}

	public int getShoePrice() {
		return shoePrice;
	}

	public void setShoeId(String shoeId) {
		this.shoeId = shoeId;
	}

	public void setShoeName(String shoeName) {
		this.shoeName = shoeName;
	}

	public void setShoeDescription(String shoeDescription) {
		this.shoeDescription = shoeDescription;
	}

	public void setShoePrice(int shoePrice) {
		this.shoePrice = shoePrice;
	}

}
