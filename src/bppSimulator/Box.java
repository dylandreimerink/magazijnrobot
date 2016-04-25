package bppSimulator;

import java.util.ArrayList;

import shared.Product;

public class Box {
	private double hoogte;
	private double breedte;
	private double lengte;
	private PickList inBox;
	public Box(double hoogte, double breedte, double lengte, PickList list) {
		this.hoogte = hoogte;
		this.breedte = breedte;
		this.lengte = lengte;
		this.inBox = list;
		
	}

	public PickList getPickList(){
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
