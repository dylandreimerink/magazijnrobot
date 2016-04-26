package tspSimulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import shared.Algorithm;
import shared.Resultaat;

public class NearestNeighbour implements Algorithm, Comparator<Location> {

	private String name;
	private ArrayList<Location> picklist;
	private DrawPanel panel;
	private Resultaat resultaat;

	public NearestNeighbour(String name, ArrayList<Location> picklist) {

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
		return Math.sqrt(Math.pow(temp,2) + Math.pow(temp1,2));
	}

	@Override
	public void calculateRoute() {
		ArrayList<Location> p1 = this.picklist;
		// Collections.sort(p1, this);
		ArrayList<Location> newArrayList = new ArrayList<Location>();
		newArrayList.add(p1.get(0));
		for(int i = 0; i< p1.size()-1; i++){ 
			Location previousLocation = newArrayList.get(i);
			Location tempLocation = null;
			for(int j = 0; j< p1.size() -1; j++){ 
				if(tempLocation == null){
					tempLocation = p1.get(j);
				} else {
					if(!newArrayList.contains(tempLocation)){
						double check = calculateDistance(previousLocation, tempLocation);
						double check1 = calculateDistance(previousLocation, p1.get(j));
						if(check > check1){
							tempLocation = p1.get(j);
						} 
					} else {
						tempLocation = p1.get(j);
					}
				}
				
			}
			newArrayList.add(tempLocation);
			previousLocation = tempLocation;
		}
		resultaat = null;
		System.out.println("Oude array:");
		for(int i = 0; i< p1.size(); i++){
			System.out.println("x: " + p1.get(i).getLocationX() + "y: " + p1.get(i).getLocationY());
		}
		System.out.println("Oude array:");
		for(int i = 0; i< newArrayList.size(); i++){
			System.out.println("x: " + newArrayList.get(i).getLocationX() + "y: " + newArrayList.get(i).getLocationY());
		}
		resultaat = new Resultaat(newArrayList, 0);
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
		} else {
			return 0;
		}

	}
}
