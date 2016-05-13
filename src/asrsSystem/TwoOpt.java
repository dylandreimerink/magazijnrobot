package asrsSystem;

import java.util.ArrayList;

import asrsSystem.twooptController;
import shared.Algorithm;
import shared.Resultaat;
import tspSimulator.DrawPanel;
import tspSimulatorv2.Location;
import tspSimulatorv2.Result;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */
public class TwoOpt implements Runnable{
	
	private ArrayList<asrsSystem.Location> tour;
	private ArrayList<asrsSystem.Location> optimizedTour;
	
	protected Thread t;

	public void calculateRoute(ArrayList<asrsSystem.Location> initialTour) {
		
		t = new Thread(this);
		t.start();
		
		tour = initialTour;
		// Get tour size
		int size = tour.size();

		// repeat until no improvement is made
		int improve = 0;

		while (improve < 100) {
			double best_distance = getAfstand(tour);

			for (int i = 0; i < size - 1; i++) {
				for (int k = i + 1; k < size; k++) {
					ArrayList<asrsSystem.Location> new_tour;
					new_tour = twoOptSwap(tour, i, k);

					double new_distance = getAfstand(new_tour);
					asrsSystem.Location checkStart_end = new asrsSystem.Location(1,5);
					System.out.println("Improve: " + improve);
					
					if (new_distance < best_distance  && checkStart_end.equals(new_tour.get(0)) && checkStart_end.equals(new_tour.get(new_tour.size()-1))) {
						// Improvement found so reset
						improve = 0;
						tour = new_tour;
						best_distance = new_distance;
						System.out.println("Best distance: " + best_distance);
						
					}
				}
			}

			improve++;
		}

		optimizedTour = tour;
		
	}
	
	
	public ArrayList<asrsSystem.Location> getOptimizedTour() {
		return optimizedTour;
	}
	

	private ArrayList<asrsSystem.Location> twoOptSwap(ArrayList<asrsSystem.Location> tour, int i, int k) {

		ArrayList<asrsSystem.Location> new_tour = new ArrayList<asrsSystem.Location>();

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

	private double calculateDistance(asrsSystem.Location locatieA, asrsSystem.Location locatieB) {
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

	private double getAfstand(ArrayList<asrsSystem.Location> p1) {
		asrsSystem.Location previousLocation = null;
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


	@Override
	public void run() {
		
		
	}

}
