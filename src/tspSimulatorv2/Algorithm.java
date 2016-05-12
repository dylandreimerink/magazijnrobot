package tspSimulatorv2;
/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

import java.util.ArrayList;

public interface Algorithm {

	public Result calculateRoute();
	
	public void callBack();
	
	public void setOnDoneListner(TSPController listnerClass);
	
	public void start(ArrayList<Location> p);
	
	public Result getResult();
	
	public void run();
}
