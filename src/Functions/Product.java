package Functions;
/**
 * Authors: Jan Willem en Henri
 * Class: ICTM2A
 * 
 */

public class Product {
	private int productId;
	private int locationX;
	private int locationY;

	public Product(int productId, int locationX, int locationY) {
		this.productId = productId;
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	public Product(int productId) {
		this(productId, 0, 0);
	}

	public int getProductId() {
		return productId;
	}
	
	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}
	
	
}
