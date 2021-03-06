package tspSimulator;

import java.util.ArrayList;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */
public class NearestNeighbourAndTwoOpt implements Runnable, Algorithm {
	
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
		
        ArrayList<Location> p1 = new ArrayList<Location>();
		
		for (Location l : tour.toArray(new Location[0])) {
			p1.add(l);
		}
		ArrayList<Location> newArrayList = new ArrayList<Location>();
		newArrayList.add(p1.get(0));
		p1.remove(0);
		int q = 0;
		while (!p1.isEmpty()) {
			Location neareast = findNearest(p1, newArrayList.get(q));
			newArrayList.add(neareast);
			p1.remove(neareast);
			q++;
		}
		tour = newArrayList;
		Result nearestNeighbour = new Result(newArrayList, 0);


		tour = new ArrayList<Location>();

		for (Location l : nearestNeighbour.getResult().toArray(new Location[0])) {
			tour.add(l);
		}

		// Get tour size
		int size = tour.size();

		// repeat until no improvement is made
		int improve = 0;

		while (improve < 500) {
			double best_distance = getAfstand(tour);

			for (int i = 0; i < size - 1; i++) {
				for (int k = i + 1; k < size; k++) {
					ArrayList<Location> new_tour;
					new_tour = twoOptSwap(tour, i, k);

					double new_distance = getAfstand(new_tour);
					Location check = new Location(0, 0);

					if (new_distance < best_distance && check.equals(new_tour.get(0))) {
						// Improvement found so reset
						improve = 0;
						tour = new_tour;
						best_distance = new_distance;
						Result tempResult = new Result(new_tour, 0);
						onComplete.setNearestneighbourandtwooptResult(tempResult);
					}
				}
			}

			improve++;
		}

		Result resultaat = new Result(tour, 0);
		if (resultaat.getAfstand() > nearestNeighbour.getAfstand()) {
			return nearestNeighbour;
		} else {
			return resultaat;
		}
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
		return totaleAfstand;
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

	@Override
	public void run() {
		this.result = calculateRoute();

		if (onComplete != null) {
			callBack();
		}
	}

	public Result getResult() {
		return result;
	}

	public void start(ArrayList<Location> p) {
		this.tour = p;

		t = new Thread(this);
		t.start();
	
	}

	public void setOnDoneListner(TSPController listnerClass) {
		onComplete = listnerClass;
	}

	public void callBack() {
		onComplete.nearestNeighbourAndTwoOptCallback();
	}

}
