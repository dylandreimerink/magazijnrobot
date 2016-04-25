package tspSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import shared.Algorithm;
import shared.Resultaat;

public class FirstFitDecreasing implements Algorithm, Comparator<Location> {

	private String name;
	private ArrayList<Location> picklist;
	private DrawPanel panel;
	private Resultaat resultaat;

	public FirstFitDecreasing(String name, ArrayList<Location> picklist) {

		this.name = name;
		this.picklist = picklist;
		resultaat = new Resultaat(picklist, 0);
		panel = new DrawPanel("First Fit Decreasing", resultaat);
	}

	public void updateResultaat(ArrayList<Location> picklist) {
		this.picklist = picklist;
		resultaat = new Resultaat(picklist, 0);
		panel.updateResultaat(resultaat);
	}

	public double calculateDistance(Location locatieA, Location locatieB) {
		if (locatieA.getLocationX() > locatieB.getLocationX() && locatieA.getLocationY() > locatieB.getLocationY()) {
			return Math.sqrt(locatieA.getLocationX() - locatieB.getLocationX() + locatieA.getLocationY()
					- locatieB.getLocationY());
		} else if (locatieA.getLocationX() > locatieB.getLocationX()
				&& locatieA.getLocationY() < locatieB.getLocationY()) {
			return Math.sqrt(locatieA.getLocationX() - locatieB.getLocationX() + locatieB.getLocationY()
					- locatieA.getLocationY());
		} else if (locatieA.getLocationX() < locatieB.getLocationX()
				&& locatieA.getLocationY() > locatieB.getLocationY()) {
			return Math.sqrt(locatieB.getLocationX() - locatieA.getLocationX() + locatieA.getLocationY()
					- locatieB.getLocationY());
		} else {
			return Math.sqrt(locatieB.getLocationX() - locatieA.getLocationX() + locatieB.getLocationY()
					- locatieA.getLocationY());
		}
	}

	@Override
	public void calculateRoute() {
		ArrayList<Location> p1 = this.picklist;
		Collections.sort(p1, this);
		Location tempLocation = null;
		for (Location item : p1) {
			if (tempLocation == null) {
				tempLocation = item;
			} else {
				double dictance = this.calculateDistance(tempLocation, item);
				System.out.println(dictance);
			}
		}
		resultaat = null;
		resultaat = new Resultaat(p1, 0);
		panel.updateResultaat(resultaat);

	}

	@Override
	public Resultaat getResultaat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAlgorithmName() {
		// TODO Auto-generated method stub
		return name;
	}

	public DrawPanel getPanel() {
		return panel;
	}

	@Override
	public int compare(Location arg0, Location arg1) {
		if (arg0.getLocationX() > arg1.getLocationX() && arg0.getLocationY() > arg1.getLocationY()) {
			return 1;
		} else if (arg0.getLocationX() == arg1.getLocationX() && arg0.getLocationY() > arg1.getLocationY()
				|| arg0.getLocationX() > arg1.getLocationX() && arg0.getLocationY() == arg1.getLocationY()) {
			return 1;
		} else if (arg0.getLocationX() < arg1.getLocationX() || arg0.getLocationY() < arg1.getLocationY()) {
			return -1;
		}
		return 0;
	}
}
