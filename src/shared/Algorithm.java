package shared;

import java.util.ArrayList;

import tspSimulator.DrawPanel;
import tspSimulator.Location;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

public interface Algorithm {

	public void calculateRoute();
	
	public Resultaat getResultaat();
	
	public String getAlgorithmName();
	
	public DrawPanel getPanel();
}
