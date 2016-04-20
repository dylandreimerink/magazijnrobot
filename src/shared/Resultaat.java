package shared;

import java.util.ArrayList;

import tspSimulator.Location;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

public class Resultaat {
	private Route route;
	private double time;
	private String name;
	private ArrayList<Location> result;
	
	public Resultaat(){
		result = new ArrayList<Location>();
	}
	
	public Route getRoute(){
		return route;
	}
	
	public double getTijd(){
		return time;
	}
	
	public String getAlgorithmName(){
		return name;
	}

}
