package tspSimulator;

/*
 * Author: Henri van de Munt (ICTM2a)
 */

public class Location {
	private int locationX;
	private int locationY;
	
	public Location(int x, int y){
		this.locationX = x;
		this.locationY = y;
	}

	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}
	
	public boolean equals(Object object){
		if(object instanceof Location){
			if(((Location) object).getLocationX() == this.getLocationX() && ((Location) object).getLocationY() == this.getLocationY()){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
}
