package asrsSystem;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
	private ImageIcon image;
	
	public void startRoute(ArrayList<Product> productlist, JPanel panel) {
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
		updateDoosinfo(panel);

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
	
	public void updateDoosinfo(JPanel panel) {
		image = new ImageIcon("src/smallCrate.png");
		
		int counter = 1;
		for(Doos l:doosList) {
			
			if(l.getDoosId() == 1) {
				JLabel imageIcon = new JLabel(image);
				panel.add(imageIcon,"cell "+counter+" 2");
				System.out.println(l.getDoosId());
			
			}
		}
		counter = 1;
		for(Doos l:doosList)
			if(l.getDoosId() == 2) {
				JLabel imageIcon = new JLabel(image);
				panel.add(imageIcon, "cell "+counter+" 3");
				System.out.println(l.getDoosId());
			}
			counter++;
			
			
		}
	}

