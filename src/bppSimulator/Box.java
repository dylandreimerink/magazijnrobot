package bppSimulator;

public class Box {
	//Dimentions of the box
	private double height;
	private double width;
	private double length;
	
	//instance of picklist the box is in
	private PickList inBox;
	public Box(double hoogte, double breedte, double lengte, PickList list) {
		this.height = hoogte;
		this.width = breedte;
		this.length = lengte;
		this.inBox = list;
		
	}

	public PickList getPickList(){
		return inBox;
	}
	
	public double getCapacity() {
		return this.width * this.height * this.length;
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
	public double getLength() {
		return length;
	}
	public void setLenth(double length) {
		this.length = length;
	}
	
}
