package asrsSystem;

import java.util.ArrayList;

import shared.Product;
import tspSimulatorv2.Picklist;
import tspSimulatorv2.Result;


public class Location {
	
	private int locatieX;
	private int locatieY;
	private ArrayList<Location> location;

	public Location(int x, int y) {
		locatieX = x;
		locatieY = y;
	}
	
	
	
	public void generateLocationlist(ArrayList<Product> productlist){
		int index = 0;
		for(Product p: productlist) {
			int x = productlist.get(index).getLocationX();
			int y = productlist.get(index).getLocationY();
			Location locatie = new Location(x, y);
			location.add(locatie);
		}

	}
	
	public ArrayList<Location> getLocationList() {
		return location;
	}
	
	public String toString() {
		return "locY: "+locatieY+", locX: "+locatieX;
	}
}
