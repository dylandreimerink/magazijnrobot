package tspSimulator;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */

import java.util.ArrayList;
import java.util.Stack;

public class Bruteforce implements Algorithm, Runnable {
	
	/*
	 * Declaration variables
	 */
	
	private ArrayList<Location> picklist;
	private ArrayList<Location> shortestPicklist;
	protected Thread t = new Thread(this);
	private Result result;

	private TSPController onComplete;
	
	/*
	 * Functions, getters and setters
	 */
	
	public Result calculateRoute() {
		shortestPicklist = new ArrayList<Location>();
		for (Location l : picklist.toArray(new Location[0])) {
			shortestPicklist.add(l);
		}

		permutations(picklist, new Stack<Location>(), picklist.size());
		Result result = new Result(shortestPicklist, 0);
		return result;
	}

	private void permutations(ArrayList<Location> items,
			Stack<Location> permutation, int size) {

		if (permutation.size() == size) {
			ArrayList<Location> tempList = new ArrayList<Location>(permutation);
			Location check = new Location(0, 0);
			if (getDistance(shortestPicklist) > getDistance(tempList)
					&& check.equals(tempList.get(0))) {
				this.shortestPicklist = tempList;
			}
			onComplete.stop_time = System.nanoTime();
			double diffTime = (onComplete.stop_time - onComplete.start_time) / 1e6;
			Result tempResult = new Result(tempList, Math.round(diffTime));
			onComplete.setBruteforceResults(tempResult);

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

	private double getDistance(ArrayList<Location> p1) {
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
	public void run() {

		this.result = calculateRoute();

		if (onComplete != null) {
			callBack();
		}
	}

	public Result getResult() {
		System.out.println(result);
		return result;
	}

	public void start(ArrayList<Location> p) {
		this.picklist = p;

		t = new Thread(this);
		t.start();

	}

	public void setOnDoneListner(TSPController listnerClass) {
		onComplete = listnerClass;
	}

	public void callBack() {
		onComplete.BruteForceCallback();
	}

}
