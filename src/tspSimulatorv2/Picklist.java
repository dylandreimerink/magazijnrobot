package tspSimulatorv2;

import java.util.ArrayList;

import tspSimulator.Location;

public class Picklist {

	private final int hoogte = 10;
	private final int breedte = 10;

	private ArrayList<Location> list1;
	private ArrayList<Location> list2;
	private ArrayList<Location> list3;

	public Picklist() {
		generateNewPicklist();
	}

	public void generateNewPicklist() {
		list1 = new ArrayList<Location>();
		list2 = new ArrayList<Location>();
		list3 = new ArrayList<Location>();
		int aantalProducten = (int) randomWithRange(9, 10);
		Location produc1t = new Location(0, 0);
		list1.add(produc1t);
		list2.add(produc1t);
		list3.add(produc1t);
		for (int z = 0; z < aantalProducten; z++) {
			int x = randomWithRange(0, breedte - 1);
			int y = randomWithRange(0, hoogte - 1);
			Location product = new Location(x, y);
			boolean check = true;
			for (Location item : list1) {
				if (item.getLocationX() == product.getLocationX() && item.getLocationY() == product.getLocationY()) {
					check = false;
				}
			}

			if (check == false) {
				z--;
			} else {
				list1.add(product);
				list2.add(product);
				list3.add(product);
			}
		}
	}

	public ArrayList<Location> getListOne() {
		return list1;
	}

	public ArrayList<Location> getListTwo() {
		return list2;
	}

	public ArrayList<Location> getListThree() {
		return list3;
	}

	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}

	public int getHoogte() {
		return hoogte;
	}

	public int getBreedte() {
		return breedte;
	}

	@Override
	public String toString() {
		String toReturn = "";
		for (Location item : list1) {
			toReturn += "\n x:" + item.getLocationX() + " y:" + item.getLocationY();
		}
		return toReturn;
	}
}