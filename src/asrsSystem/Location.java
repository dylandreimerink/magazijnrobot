package asrsSystem;

import java.util.ArrayList;

import shared.Product;


public class Location {
	
	private int locatieX;
	private int locatieY;
	private ArrayList<Location> location = new ArrayList<Location>();

	public Location(int x, int y) {
		locatieX = x;
		locatieY = y;
	}
	
	
	
	public Location() {
		// let this stay.
	}



	public ArrayList<Location> generateLocationlist(ArrayList<Product> productlist){
		int index = 0;
		Location begin_end = new Location(1,5);
		location.add(begin_end);
		for(Product p: productlist) {
			int x = productlist.get(index).getLocationX();
			int y = productlist.get(index).getLocationY();
			Location locatie = new Location(x, y);
			location.add(locatie);
			index++;
		}
		location.add(begin_end);
		
		return location;

	}
	
	public int getLocationX() {
		return locatieX;
	}
	
	public int getLocationY() {
		return locatieY;
	}
	
	public ArrayList<Location> getLocationList() {
		return location;
	}
	
	public String toString() {
		return "Y: "+locatieY+", X: "+locatieX;
	}
	
	
	public boolean equals(Object object) {
		if (object instanceof Location) {
			if (((Location) object).getLocationX() == this.getLocationX()
					&& ((Location) object).getLocationY() == this.getLocationY()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
