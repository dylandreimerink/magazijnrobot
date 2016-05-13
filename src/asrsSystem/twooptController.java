package asrsSystem;

import java.util.ArrayList;

import javax.swing.JPanel;

import shared.Product;

public class twooptController {
	private ArrayList<Location> initialTour;
	private ArrayList<Location> optimizedTour;
	private Console console = new Console();
	private DrawPanel drawer;
	
	public void startTwoOpt(ArrayList<Product> productlist) {
		Location location = new Location();
		initialTour = location.generateLocationlist(productlist);
		System.out.println("inittour"+initialTour);
		TwoOpt twoopt = new TwoOpt();
		twoopt.calculateRoute(initialTour);
		optimizedTour = twoopt.getOptimizedTour();
		console.printArray(optimizedTour);
		drawer.setResult(optimizedTour);
		drawer.repaint();

	}
	
	public ArrayList<Location> getOptimizedTour() {
		return optimizedTour;
	}
	
	public twooptController(JPanel container) {
		drawer = new DrawPanel();
		container.add(drawer, "cell 1 0,grow");
	}
}
