package tspSimulator;

import java.util.ArrayList;

import tspSimulator.TSPController;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */
public class TwoOpt implements Runnable, Algorithm {
	
	/*
	 * Declaration variables
	 */
	
	private ArrayList<Location> tour;
	
	protected Thread t = new Thread(this);
	private Result result;
	
	private TSPController onComplete;
	
	/*
	 * Functions, getters and setters
	 */
	
	@Override
	public Result calculateRoute() {

		// Get tour size
		int size = tour.size();

		// repeat until no improvement is made
		int improve = 0;

		while (improve < 100) {
			double best_distance = getAfstand(tour);

			for (int i = 0; i < size - 1; i++) {
				for (int k = i + 1; k < size; k++) {
					ArrayList<Location> new_tour;
					new_tour = twoOptSwap(tour, i, k);

					double new_distance = getAfstand(new_tour);
					Location check = new Location(0, 0);
				//	System.out.println("Improve: " + improve);
					Result tempResult = new Result(new_tour, 0);
					onComplete.setTwooptResults(tempResult);
					if (new_distance < best_distance  && check.equals(new_tour.get(0))) {
						// Improvement found so reset
						improve = 0;
						tour = new_tour;
						best_distance = new_distance;
						//System.out.println("Best distance: " + best_distance);
						
					}
				}
			}

			improve++;
		}

		Result resultaat = new Result(tour, 0);
		return resultaat;
	}

	private ArrayList<Location> twoOptSwap(ArrayList<Location> tour, int i, int k) {

		ArrayList<Location> new_tour = new ArrayList<Location>();

		int size = tour.size();
		for (int c = 0; c <= i - 1; ++c) {
			new_tour.add(tour.get(c));
		}

		// 2. take route[i] to route[k] and add them in reverse order to
		// new_route
		int dec = 0;
		for (int c = i; c <= k; ++c) {
			// new_tour.SetCity( c, tour.GetCity( k - dec ) );
			new_tour.add(tour.get(k - dec));
			dec++;
		}

		// 3. take route[k+1] to end and add them in order to new_route
		for (int c = k + 1; c < size; ++c) {
			// new_tour.SetCity( c, tour.GetCity( c ) );
			new_tour.add(tour.get(c));
		}

		return new_tour;
	}

	private double calculateDistance(Location locationA, Location locationB) {
		double temp;
		double temp1;
		if (locationA.getLocationX() > locationB.getLocationX()) {
			temp = locationA.getLocationX() - locationB.getLocationX();
		} else {
			temp = locationB.getLocationX() - locationA.getLocationX();
		}
		if (locationA.getLocationY() > locationB.getLocationY()) {
			temp1 = locationA.getLocationY() - locationB.getLocationY();
		} else {
			temp1 = locationB.getLocationY() - locationA.getLocationY();
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
		return totaleAfstand;
	}

	public Location findNearest(ArrayList<Location> picklist, Location p) {
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


	@Override
	public void callBack() {
		onComplete.TwoOptCallback();
	}
	

	@Override
	public void setOnDoneListner(TSPController listnerClass) {
		onComplete = listnerClass;
	}
	

	@Override
	public void start(ArrayList<Location> p) {
		this.tour = p;
		
		t = new Thread(this);
		t.start();
	}

	@Override
	public Result getResult() {
		return result;
	}

	@Override
	public void run() {
		this.result = calculateRoute();

		if (onComplete != null) {
			callBack();
		}

	}

}
