package asrsSystem;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JProgressBar;

import shared.Product;

public class Controller {
	private ArrayList<Location> initialTour;
	private ArrayList<Location> optimizedTour;
	private ArrayList<Doos> doosList;
	private Console console = new Console();
	private DrawPanel drawer;
	private Robot robot;
	private JProgressBar progressBar;
	
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
		Doos doos = new Doos();
		doosList = doos.generateDoosList(productlist);
		console.printArrayD(doosList);

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
	
	public void createProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
	}
	
	public JProgressBar getProgressBar() {
		return progressBar;
		
	}
	
	public void updatePBar(int value) {
		if(value > 0 && value <= 100) {
			progressBar.setValue(value);
		}
	}
}
