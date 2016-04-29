package tspSimulatorv2;
/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

import java.util.ArrayList;

public interface Algorithm {

	public Result calculateRoute(ArrayList<Location> picklist);
	
}
