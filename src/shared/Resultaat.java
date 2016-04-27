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

	public Resultaat(ArrayList<Location> result, double time, double afstand) {
		// result = new ArrayList<Location>();
		this.result = result;
		this.time = time;
		this.afstand = afstand;
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
