package bppSimulator;

import java.util.ArrayList;

import shared.Product;

public class Box {
	private double hoogte;
	private double breedte;
	private double lengte;
	private ArrayList<Product> inBox;
	public Box(double hoogte, double breedte, double lengte) {
		this.hoogte = hoogte;
		this.breedte = breedte;
		this.lengte = lengte;
		inBox = new ArrayList<Product>();
	}
	
	public void addProductToBox(Product p){
		inBox.add(p);
	}
	
	public void removeFromBox(Product p){
		inBox.remove(p);
	}
	
	public ArrayList<Product> productsInBox(){
		return inBox;
	}
	
	public double getCapacity() {
		return this.breedte * this.hoogte * this.lengte;
	}
	
	public double getHoogte() {
		return hoogte;
	}
	public void setHoogte(double hoogte) {
		this.hoogte = hoogte;
	}
	public double getBreedte() {
		return breedte;
	}
	public void setBreedte(double breedte) {
		this.breedte = breedte;
	}
	public double getLengte() {
		return lengte;
	}
	public void setLengte(double lengte) {
		this.lengte = lengte;
	}
	
}