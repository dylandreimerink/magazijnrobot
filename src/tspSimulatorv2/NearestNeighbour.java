package tspSimulatorv2;

import java.util.ArrayList;

public class NearestNeighbour implements Algorithm {

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
		System.out.println("Locatie p:" + p.getLocationX() + " " + p.getLocationY());
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
	public Result calculateRoute(ArrayList<Location> picklist) {
		ArrayList<Location> p1 = picklist;
	
		// Collections.sort(p1, this);
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
		System.out.println("Oude array:");
		for (int i = 0; i < p1.size(); i++) {
			System.out.println("x: " + p1.get(i).getLocationX() + "y: " + p1.get(i).getLocationY());
		}
		System.out.println("Nieuwe array:");
		for (int i = 0; i < newArrayList.size(); i++) {
			System.out.println("x: " + newArrayList.get(i).getLocationX() + "y: " + newArrayList.get(i).getLocationY());
		}
		picklist = newArrayList;
		Result resultaat = new Result(newArrayList, 0);
		return resultaat;
	}
}
