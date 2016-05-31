package tspSimulator;

/*
 * Author: Henri van de Munt (ICTM2a)
 */

public class Location {
	
	/*
	 * Declaration variables
	 */
	
	private int locationX;
	private int locationY;
	
	/*
	 * Constructor 
	 */
	
	public Location(int x, int y) {
		this.locationX = x;
		this.locationY = y;
	}
	
	/*
	 * Getters and Setters
	 */

	public int getLocationX() {
		return locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	/*
	 * Equals function
	 */
	
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
	
	/*
	 * toString function
	 */

	@Override
	public String toString() {
		return locationX + " " + locationY;
	}

}
