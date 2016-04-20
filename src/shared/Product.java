package shared;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 * 
 */

public class Product {
	private String productName;
	private int locationX;
	private int locationY;
	private double height;
	private double width;
	private double lenght;

	public Product(String productName, int locationX, int locationY, double height, double width,
			double length) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.height = height;
		this.width = width;
		this.lenght = length;
		this.productName = productName;
	}

	public Product(int productId) {
		this("Onbekend", 0, 0, 0, 0, 0);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getLocationX() {
		return locationX;
	}

	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getLenght() {
		return lenght;
	}

	public void setLenght(double lenght) {
		this.lenght = lenght;
	}
}
