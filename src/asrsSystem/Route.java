package asrsSystem;

import java.util.ArrayList;
/*
 * Authors: Richard en Steven, ICTM2A
 */
public class Route {
	
	private ArrayList<asrsSystem.Location> optimizedTour;
	private int index = 0;
	protected Thread t;

	//tussenklasse om nog bewerkingen(algoritme bv) aan de route uit te voeren, bewerkingen zijn weggehaalt na realisatie dat
	//het niet meer uitmaakt als we maar één product per keer pakken. in deze klasse gebeurt op het moment dus niks.
	public void calculateRoute(ArrayList<asrsSystem.Location> initialTour) {
		optimizedTour = new ArrayList<asrsSystem.Location>();
		for(asrsSystem.Location l:initialTour) {
			
			
			optimizedTour.add(initialTour.get(index));
			index++;
		}
		
	}
	
	public ArrayList<asrsSystem.Location> getOptimizedTour() {
		return optimizedTour;
	}

}
