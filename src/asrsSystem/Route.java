package asrsSystem;

import java.util.ArrayList;

import asrsSystem.Controller;
import shared.Algorithm;
import shared.Resultaat;
import tspSimulator.DrawPanel;
import tspSimulatorv2.Location;
import tspSimulatorv2.Result;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */
public class Route {
	
	private ArrayList<asrsSystem.Location> tour;
	private ArrayList<asrsSystem.Location> optimizedTour;
	private int index = 0;
	protected Thread t;

	public void calculateRoute(ArrayList<asrsSystem.Location> initialTour) {
		optimizedTour = new ArrayList<asrsSystem.Location>();
		asrsSystem.Location begin_eind = new asrsSystem.Location(6,5);
		for(asrsSystem.Location l:initialTour) {
			
			optimizedTour.add(begin_eind);
			optimizedTour.add(initialTour.get(index));
			index++;
		}
		optimizedTour.add(begin_eind);
	}
	
	public ArrayList<asrsSystem.Location> getOptimizedTour() {
		return optimizedTour;
	}

}
