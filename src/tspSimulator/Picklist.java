package tspSimulator;

/*
 * Author: Henri van de Munt (ICTM2a)
 */

import java.util.ArrayList;

public class Picklist {

	private final int hoogte = 10;
	private final int breedte = 10;

	private ArrayList<Location> list;

	public Picklist() {
		list = new ArrayList<Location>();
		int aantalProducten = (int) randomWithRange(4, 6);
		for (int z = 0; z < aantalProducten; z++) {
			int x = randomWithRange(0, breedte);
			int y = randomWithRange(0, hoogte);
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
	}

	public ArrayList<Location> getList() {
		return list;
	}

	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}

	@Override
	public String toString() {
		String toReturn = "";
		for (Location item : list) {
			toReturn += "\n x:" + item.getLocationX() + " y:" + item.getLocationY();
		}
		return toReturn;
	}
}
