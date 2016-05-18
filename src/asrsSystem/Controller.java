package asrsSystem;

import java.util.ArrayList;

import javax.swing.JPanel;

import shared.Product;

public class Controller {
	private ArrayList<Location> initialTour;
	private ArrayList<Location> optimizedTour;
	private Console console = new Console();
	private DrawPanel drawer;
	private Robot robot;
	
	public void startRoute(ArrayList<Product> productlist) {
		Location location = new Location();
		initialTour = location.generateLocationlist(productlist);
		System.out.println("inittour"+initialTour);
		Route route = new Route();
		route.calculateRoute(initialTour);
		optimizedTour = route.getOptimizedTour();
		console.printArray(optimizedTour);
		drawer.setResult(optimizedTour, initialTour, productlist);
		drawer.repaint();

	}
	
	public ArrayList<Location> getOptimizedTour() {
		return optimizedTour;
	}
	
	public Controller(JPanel container) {
		drawer = new DrawPanel();
		container.add(drawer, "cell 1 0,grow");
	}
	
	public void Connect() {
		robot = new Robot();
		robot.openConnection(optimizedTour);
	}
	
	public void Disconnect() {
		robot.t.stop();
	}
	
	public void StartRobot() {
		robot.start();
	}
}
