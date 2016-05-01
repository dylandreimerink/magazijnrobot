package tspSimulatorv2;

import java.util.ArrayList;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */
public class TwoOpt implements Algorithm {
	
	private ArrayList<Location> picklist;
	private ArrayList<Location> newArrayList;
	
	@Override
	public Result calculateRoute(ArrayList<Location> picklist1) {
		
		this.picklist = picklist1;
		this.newArrayList = new ArrayList<Location>();

		for (Location l : picklist.toArray(new Location[0])) {
			newArrayList.add(l);
		}

		// Get tour size
		int size = picklist.size();

		// repeat until no improvement is made
		int improve = 0;

		while (improve < 20) {
			double best_distance = getAfstand(this.picklist);

			for (int i = 0; i < size - 1; i++) {
				for (int k = i + 1; k < size; k++) {
					TwoOptSwap( i, k);

					double new_distance = getAfstand(this.newArrayList);
					if (new_distance < best_distance) {
						// Improvement found so reset
						improve = 0;
						this.picklist = this.newArrayList;
						best_distance = new_distance;
						System.out.println(best_distance);
					}
				}
			}

			improve++;

		}

		Result resultaat = new Result(picklist, 0);
		return resultaat;
	}

	private void TwoOptSwap(int i, int k) {
		double size = picklist.size();

		// 1. take route[0] to route[i-1] and add them in order to new_route
		for (int c = 0; c <= i - 1; ++c) {
			this.newArrayList.set(c, this.picklist.get(c));
		}

		// 2. take route[i] to route[k] and add them in reverse order to
		// new_route
		int dec = 0;
		for (int c = i; c <= k; ++c) {
			this.newArrayList.set(c, this.picklist.get(k - dec));
			dec++;
		}

		// 3. take route[k+1] to end and add them in order to new_route
		for (int c = k + 1; c < size; ++c) {
			newArrayList.set(c, this.picklist.get(c));
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

	public Location findNearest(ArrayList<Location> picklist, Location p) {
		// System.out.println("Locatie p:" + p.getLocationX() + " " +
		// p.getLocationY());
		Location nearest = null;
		double distance = 999999999;
		for (Location s : picklist) {
			if (this.calculateDistance(p, s) < distance) {
				distance = this.calculateDistance(p, s);
				nearest = s;
			}
		}
		return nearest;
	}

}
