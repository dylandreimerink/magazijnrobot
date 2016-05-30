package tspSimulator;

import java.util.ArrayList;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

public class Result {
	private double time;
	private double afstand;
	private String name;
	private ArrayList<Location> result;
	
	private boolean showPointsonly;

	public Result(ArrayList<Location> result, double time) {
		// result = new ArrayList<Location>();
		this.result = result;
		this.time = time;
		this.afstand = getAfstand(result);
		showPointsonly = false;
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
		return Math.round(totaleAfstand * 100.00) / 100.00;
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

	public ArrayList<Location> getResult() {
		return result;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getAfstand() {
		return afstand;
	}

	public void setAfstand(double afstand) {
		this.afstand = afstand;
	}

	public void setResult(ArrayList<Location> result) {
		this.result = result;
		
	}

	public double getDistance() {
		return afstand;
	}

	public String getAlgorithmName() {
		return name;
	}

	public boolean isShowPointsonly() {
		return showPointsonly;
	}

	public void setShowPointsonly(boolean showPointsonly) {
		this.showPointsonly = showPointsonly;
	}
	
	

}
