package shared;

import java.util.ArrayList;

import tspSimulator.Location;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

public class Resultaat {
	private double time;
	private double afstand;
	private String name;
	private ArrayList<Location> result;

	public Resultaat(ArrayList<Location> result, double time) {
		// result = new ArrayList<Location>();
		this.result = result;
		this.time = time;
		this.afstand = getAfstand(result);
	}
	
	
	private double getAfstand(ArrayList<Location> p1){
		Location previousLocation = null;
		double totaleAfstand = 0;
		for (int i = 0; i < p1.size(); i++) {
			double afstand = 0;
			if(previousLocation == null){
				previousLocation = p1.get(i);
			} else {
				 afstand = calculateDistance(previousLocation, p1.get(i));
			}
			previousLocation = p1.get(i);
			totaleAfstand += afstand;
		}
		return Math.round( totaleAfstand * 100.0) / 100.0;
	}
	
	private double calculateDistance(Location locatieA , Location locatieB) {
		double temp;
		double temp1;
		if (locatieA.getLocationX() > locatieB.getLocationX()) {
			temp = locatieA.getLocationX() - locatieB.getLocationX();
		} else {
			temp = locatieB.getLocationX() - locatieA.getLocationX();
		}
		if (locatieA.getLocationY() > locatieB.getLocationY()) {
			temp1 = locatieA.getLocationY() - locatieB.getLocationY();
		} else {
			temp1 = locatieB.getLocationY() - locatieA.getLocationY();
		}
		return Math.sqrt(Math.pow(temp, 2) + Math.pow(temp1, 2));
	}
	
	public ArrayList<Location> getResult() {
		return result;
	}

	public double getTijd() {
		return time;
	}
	
	public double getDistance() {
		return afstand;
	}
	
	

	public String getAlgorithmName() {
		return name;
	}

}
