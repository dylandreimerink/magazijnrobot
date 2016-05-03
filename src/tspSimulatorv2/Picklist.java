package tspSimulatorv2;

import java.util.ArrayList;

public class Picklist {

	private int hoogte;
	private int breedte;

	private int minimaalAantalproducten;
	private int maximaalAantalproducten;



	private ArrayList<Location> picklist;
	// private ArrayList<Location> list1;
	// private ArrayList<Location> list2;
	// private ArrayList<Location> list3;
	// private ArrayList<Location> list4;

	public Picklist(int minimaalAantalproducten, int maximaalAantalproducten, int hoogte, int breedte) {
		this.minimaalAantalproducten = minimaalAantalproducten;
		this.maximaalAantalproducten = maximaalAantalproducten;
		this.hoogte = hoogte;
		this.breedte = breedte;
		
		generateNewPicklist();
	}

	public void generateNewPicklist() {
		picklist = new ArrayList<Location>();
		// list1 = new ArrayList<Location>();
		// list2 = new ArrayList<Location>();
		// list3 = new ArrayList<Location>();
		// list4 = new ArrayList<Location>();
		int aantalProducten = (int) randomWithRange(minimaalAantalproducten, maximaalAantalproducten);

		Location produc1t = new Location(0, 0);
		picklist.add(produc1t);
		// list1.add(produc1t);
		// list2.add(produc1t);
		// list3.add(produc1t);
		// list4.add(produc1t);
		for (int z = 0; z < aantalProducten; z++) {
			int x = randomWithRange(0, this.breedte - 1);
			int y = randomWithRange(0, this.hoogte - 1);
			Location product = new Location(x, y);
			boolean check = true;
			for (Location item : picklist) {
				if (item.getLocationX() == product.getLocationX() && item.getLocationY() == product.getLocationY()) {
					check = false;
				}
			}

			if (check == false) {
				z--;
			} else {
				picklist.add(product);
				// list1.add(product);
				// list2.add(product);
				// list3.add(product);
				// list4.add(product);
			}
		}
	}

	public ArrayList<Location> getPicklist() {
		return picklist;
	}

	// public ArrayList<Location> getListOne() {
	// return list1;
	// }
	//
	// public ArrayList<Location> getListTwo() {
	// return list2;
	// }
	//
	// public ArrayList<Location> getListThree() {
	// return list3;
	// }
	//
	// public ArrayList<Location> getListFour() {
	// return list4;
	// }

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
		for (Location item : picklist) {
			toReturn += "\n x:" + item.getLocationX() + " y:" + item.getLocationY();
		}
		return toReturn;
	}
}