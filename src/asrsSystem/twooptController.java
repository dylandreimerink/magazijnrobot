package asrsSystem;

import java.util.ArrayList;

import shared.Product;

public class twooptController {
	private ArrayList<Location> initialTour;
	private ArrayList<Location> optimizedTour;
	private Console console = new Console();
	
	public void startTwoOpt(ArrayList<Product> productlist) {
		Location location = new Location();
		initialTour = location.generateLocationlist(productlist);
		System.out.println("inittour"+initialTour);
		TwoOpt twoopt = new TwoOpt();
		twoopt.calculateRoute(initialTour);
		optimizedTour = twoopt.getOptimizedTour();
		console.printArray(optimizedTour);
	}
	
	public ArrayList<Location> getOptimizedTour() {
		return optimizedTour;
	}

}
