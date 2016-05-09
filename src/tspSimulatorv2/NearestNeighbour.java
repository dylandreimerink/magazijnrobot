package tspSimulatorv2;

import java.util.ArrayList;

public class NearestNeighbour implements Runnable,  Algorithm {
	
	private ArrayList<Location> picklist;
	protected Thread t = new Thread(this);
	private Result result;
	
	private TSPController onComplete;

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
	public Result calculateRoute() {
		ArrayList<Location> p1 = new ArrayList<Location>();
		
		for (Location l : picklist.toArray(new Location[0])) {
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
		picklist = newArrayList;
		Result resultaat = new Result(newArrayList, 0);
		return resultaat;
	}

	@Override
	public void callBack() {
		onComplete.nearestNeighbourCallback();
	}

	@Override
	public void setOnDoneListner(TSPController listnerClass) {
		onComplete = listnerClass;
	}

	@Override
	public void start(ArrayList<Location> p) {
		this.picklist = p;

		t = new Thread(this);
		t.start();
	
	}


	@Override
	public Result getResult() {
		System.out.println(result);
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
