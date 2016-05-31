package tspSimulator;

import java.util.ArrayList;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

public class Result {
	
	/*
	 * Declaration variables
	 */
	
	private double time;
	private double distance;
	private String name;
	private ArrayList<Location> result;
	
	private boolean showPointsonly;
	
	/*
	 * Constructor 
	 */
	
	public Result(ArrayList<Location> result, double time) {
		this.result = result;
		this.time = time;
		this.distance = getAfstand(result);
		showPointsonly = false;
	}
	
	/*
	 * Functions  
	 */
	
	private double getAfstand(ArrayList<Location> p1) {
		Location previousLocation = null;
		double totalDistance = 0;
		for (int i = 0; i < p1.size(); i++) {
			double distance = 0;
			if (previousLocation == null) {
				previousLocation = p1.get(i);
			} else {
				distance = calculateDistance(previousLocation, p1.get(i));
			}
			previousLocation = p1.get(i);
			totalDistance += distance;
		}
		return Math.round(totalDistance * 100.00) / 100.00;
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
	
	/*
	 * getters and setters 
	 */
	
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
		return distance;
	}

	public void setAfstand(double afstand) {
		this.distance = afstand;
	}

	public void setResult(ArrayList<Location> result) {
		this.result = result;
		
	}

	public double getDistance() {
		return distance;
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
