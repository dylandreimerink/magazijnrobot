package shared;

import java.util.ArrayList;
import asrsSystem.Console;
import tspSimulator.Location;
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
		if(obj instanceof Product){
			return ((Product) obj).getProductId() == this.getProductId();
		}else if(obj instanceof Location){
			return (((Location) obj).getLocationX() == this.locationX && ((Location) obj).getLocationY() == this.locationY);
		}else{
			return false;
		}
	}
	
	public String toString(){
		return "id: " + productId + ", naam: " + productName + ", location: (" + locationX + ", " + locationY + "), dimentions: " + height + "X" + width + "X" + lenght;
	}
	
	public Product(int productId) {
			
			int index = productId - 1;
			ArrayList<Product> productlist = new ArrayList<Product>();
			try {
				productlist = db.selectAll();
				
				this.productId = productId;
				this.locationX = productlist.get(index).getLocationX();
				this.locationY = productlist.get(index).getLocationY();
				this.height = productlist.get(index).getHeight();
				this.lenght = productlist.get(index).getLenght();
				this.width = productlist.get(index).getWidth();
				this.productName = productlist.get(index).getProductName();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Console.printLine("Producten niet gevonden! (vul database op met voldoende testproducten!)");
			}
			
			//this(productId, "Onbekend", 0, 0, 0, 0, 0);
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