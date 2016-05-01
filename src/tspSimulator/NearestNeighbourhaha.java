
package tspSimulator;

import java.util.ArrayList;

import shared.Algorithm;
import shared.Resultaat;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */

public class NearestNeighbourhaha implements Algorithm {

	private String name;
	private ArrayList<Location> picklist;
	private ArrayList<Location> newPicklist;
	private DrawPanel panel;
	private Resultaat resultaat;

	public NearestNeighbourhaha(String name, ArrayList<Location> picklist) {
		this.name = name;
		this.picklist = picklist;
		resultaat = new Resultaat(picklist, 0);

		panel = new DrawPanel(name, resultaat);
		newPicklist = new  ArrayList<Location>();
		
		for (Location l : picklist.toArray(new Location[0])) {
			newPicklist.add(l);
		}

	}

	public void updateResultaat(ArrayList<Location> picklist) {
		this.picklist = picklist;
		resultaat = new Resultaat(picklist, 0);
		panel.updateResultaat(resultaat);
	}

	@Override
	public void calculateRoute() {
		// Get tour size
		int size = picklist.size();

		// repeat until no improvement is made
		int improve = 0;
		System.out.println("Improvements: " + improve);

		while (improve < 20) {
			double best_distance = getAfstand(picklist);
			System.out.println("HOI" + best_distance);

			for (int i = 0; i < size - 1; i++) {
				for (int k = i + 1; k < size; k++) {
					this.TwoOptSwap(i, k);

					double new_distance = getAfstand(newPicklist);
					
					if (new_distance < best_distance) {
						// Improvement found so reset
						improve = 0;
						picklist = newPicklist;
						best_distance = new_distance;
					}
				}
			}

			improve++;
		}

		resultaat = new Resultaat(newPicklist, 0);
		System.out.println(resultaat.getResult());
		panel.updateResultaat(resultaat);
	}

	private void TwoOptSwap(int i, int k) {
		double size = picklist.size();

		// 1. take route[0] to route[i-1] and add them in order to new_route
		for (int c = 0; c <= i - 1; ++c) {
			newPicklist.set(c, this.picklist.get(c));
		}

		// 2. take route[i] to route[k] and add them in reverse order to
		// new_route
		int dec = 0;
		for (int c = i; c <= k; ++c) {
			newPicklist.set(c, this.picklist.get(k - dec));
			dec++;
		}

		// 3. take route[k+1] to end and add them in order to new_route
		for (int c = k + 1; c < size; ++c) {
			newPicklist.set(c, this.picklist.get(c));
		}
	}

	private double calculateDistance(Location locatieA, Location locatieB) {
		double temp;
		double temp1;
		if (locatieA.getLocationX() > locatieB.getLocationX()) {
			temp = locatieA.getLocationX() - locatieB.getLocationX();
		} else {
			temp = locatieB.getLocationX() - locatieA.getLocationX();
		}
		if (locatieA.getLocationY() > locatieB.getLocationY()) {
			temp1 = locatieA.getLocationY() - locatieB.getLocationY();
		} else {
			temp1 = locatieB.getLocationY() - locatieA.getLocationY();
		}
		return Math.sqrt(Math.pow(temp, 2) + Math.pow(temp1, 2));
	}

	private double getAfstand(ArrayList<Location> p1) {
		Location previousLocation = null;
		double totaleAfstand = 0;
		for (int i = 0; i < p1.size(); i++) {
			double afstand = 0;
			if (previousLocation == null) {
				previousLocation = p1.get(i);
			} else {
				afstand = calculateDistance(previousLocation, p1.get(i));
			}
			previousLocation = p1.get(i);
			totaleAfstand += afstand;
		}
		return Math.round(totaleAfstand * 100.0) / 100.0;
	}

	@Override
	public Resultaat getResultaat() {
		return this.resultaat;
	}

	@Override
	public String getAlgorithmName() {
		// TODO Auto-generated method stub
		return null;
	}

	public DrawPanel getPanel() {
		return panel;
	}
}
