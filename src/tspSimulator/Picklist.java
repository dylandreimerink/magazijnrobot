package tspSimulator;

/*
 * Author: Henri van de Munt (ICTM2a)
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Picklist {

	private final int hoogte = 10;
	private final int breedte = 10;

	private ArrayList<Location> list0;
	private ArrayList<Location> list1;
	private ArrayList<Location> list2;

	public Picklist() {
		list0 = new ArrayList<Location>();
		list1 = new ArrayList<Location>();
		list2 = new ArrayList<Location>();
		int aantalProducten = (int) randomWithRange(4, 5);
		for (int z = 0; z < aantalProducten; z++) {
			int x = randomWithRange(0, breedte - 1);
			int y = randomWithRange(0, hoogte - 1);
			Location product = new Location(x, y);
			boolean check = true;
			for (Location item : list0) {
				if (item.getLocationX() == product.getLocationX() && item.getLocationY() == product.getLocationY()) {
					check = false;
				}
			}

			if (check == false) {
				z--;
			} else {
				list0.add(product);
				list1.add(product);
				list2.add(product);
			}
		}
	}

	public void generateNewPicklist() {
		ArrayList<Location> list = new ArrayList<Location>();
		int aantalProducten = (int) randomWithRange(4, 6);
		for (int z = 0; z < aantalProducten; z++) {
			int x = randomWithRange(0, breedte - 1);
			int y = randomWithRange(0, hoogte - 1);
			Location product = new Location(x, y);
			boolean check = true;
			for (Location item : list) {
				if (item.getLocationX() == product.getLocationX() && item.getLocationY() == product.getLocationY()) {
					check = false;
				}
			}
			if (check == false) {
				z--;
			} else {
				list.add(product);
			}
		}
		this.list0 = list;
		this.list1 = list;
		this.list2 = list;
	}

	public ArrayList<Location> getListOne() {
		return list0;
	}
	public ArrayList<Location> getListTwo() {
		return list1;
	}
	public ArrayList<Location> getListThree() {
		return list2;
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
		for (Location item : list0) {
			toReturn += "\n x:" + item.getLocationX() + " y:" + item.getLocationY();
		}
		return toReturn;
	}
}
