
package tspSimulator;

import java.util.ArrayList;
import java.util.Stack;

import bppSimulator.Box;
import bppSimulator.PickList;
import shared.Algorithm;
import shared.Product;
import shared.Resultaat;
import shared.Route;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */

public class BruteForce implements Algorithm {

	private String name;
	private ArrayList<Location> picklist;
	private ArrayList<Location> shortestPicklist;
	private DrawPanel panel;
	private Resultaat resultaat;

	public BruteForce(String name, ArrayList<Location> picklist) {
		this.name = name;
		this.picklist = picklist;
		resultaat = new Resultaat(picklist, 0);

		panel = new DrawPanel(name, resultaat);
		shortestPicklist = new ArrayList<Location>();
		for (Location l : picklist.toArray(new Location[0])) {
			shortestPicklist.add(l);
		}
	}

	public void updateResultaat(ArrayList<Location> picklist) {
		this.picklist = picklist;
		System.out.println("Meegekregen picklist" + picklist);
		System.out.println("Oude shortest picklist " + shortestPicklist);
		shortestPicklist = new ArrayList<Location>();
		for (Location l : picklist.toArray(new Location[0])) {
			shortestPicklist.add(l);
		}
		System.out.println("Nieuwe shortest picklist" + shortestPicklist);
		resultaat = new Resultaat(picklist, 0);
		
		panel.updateResultaat(resultaat);
	}

	private void permutations(ArrayList<Location> items, Stack<Location> permutation, int size) {

		if (permutation.size() == size) {
			
			ArrayList<Location> tempList = new ArrayList<Location>(permutation);
			
			Location check = new Location(0,0);
			if (getAfstand(shortestPicklist) > getAfstand(tempList) && check.equals(tempList.get(0))) {
				this.shortestPicklist = tempList;

			}
		}

		/* items available for permutation */
		Location[] availableItems = items.toArray(new Location[0]);
		for (Location i : availableItems) {
			/* add current item */
			permutation.push(i);

			/* remove item from available item set */
			items.remove(i);

			/* pass it on for next permutation */
			permutations(items, permutation, size);

			/* pop and put the removed item back */
			items.add(permutation.pop());
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
	public void calculateRoute() {
		
		permutations(picklist, new Stack<Location>(), picklist.size());
		resultaat = new Resultaat(shortestPicklist, 0);
		System.out.println(resultaat.getResult());
		panel.updateResultaat(resultaat);
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
