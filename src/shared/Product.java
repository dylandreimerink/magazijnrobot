package shared;

import java.util.ArrayList;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

public class Product {
	private String productName;
	private int productId;
	private int locationX;
	private int locationY;
	private double height;
	private double width;
	private double lenght;
	Database db = new Database();

	public Product(int productId, String productName, int locationX, int locationY, double height, double width,
			double length) {
		this.productId = productId;
		this.locationX = locationX;
		this.locationY = locationY;
		this.height = height;
		this.width = width;
		this.lenght = length;
		this.productName = productName;
	}

	@Override
	public boolean equals(Object obj) {
		return ((Product) obj).getProductId() == this.getProductId();
	}
	
	public String toString(){
		return "id: " + productId + ", naam: " + productName + ", location: (" + locationX + ", " + locationY + "), dimentions: " + height + "X" + width + "X" + lenght;
	}
	
	public Product(int productId) {
		ArrayList<Product> productlist = new ArrayList<Product>();
		try {
			productlist = db.selectAll();
			
			this.productId = productId;
			this.locationX = productlist.get(productId).getLocationX();
			this.locationY = productlist.get(productId).getLocationY();
			this.height = productlist.get(productId).getHeight();
			this.lenght = productlist.get(productId).getLenght();
			this.width = productlist.get(productId).getWidth();
			this.productName = productlist.get(productId).getProductName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

	public int getProductId(){
		return productId;
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
