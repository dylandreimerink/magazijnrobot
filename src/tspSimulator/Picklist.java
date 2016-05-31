package tspSimulator;

import java.util.ArrayList;

public class Picklist {
	
	/*
	 * Declaration variables
	 */
	
	private int height;
	private int width;

	private int minimumOfProducts;
	private int maximumOfProducts;

	private ArrayList<Location> picklist;
	private ArrayList<Location> list1;
	private ArrayList<Location> list2;
	private ArrayList<Location> list3;
	private ArrayList<Location> list4;
	
	/*
	 * Constructor
	 */
	
	public Picklist(int minimaalAantalproducten, int maximaalAantalproducten, int hoogte, int breedte) {
		this.minimumOfProducts = minimaalAantalproducten;
		this.maximumOfProducts = maximaalAantalproducten;
		this.height = hoogte;
		this.width = breedte;

		generateNewPicklist();
	}
	

	/*
	 * Functions
	 */

	public void generateNewPicklist() {
		picklist = new ArrayList<Location>();
		list1 = new ArrayList<Location>();
		list2 = new ArrayList<Location>();
		list3 = new ArrayList<Location>();
		list4 = new ArrayList<Location>();
		int numberOfProducts = (int) randomWithRange(minimumOfProducts, maximumOfProducts);

		Location firstProduct = new Location(0, 0);
		picklist.add(firstProduct);
		list1.add(firstProduct);
		list2.add(firstProduct);
		list3.add(firstProduct);
		list4.add(firstProduct);
		for (int z = 0; z < numberOfProducts; z++) {
			int x = randomWithRange(0, this.width - 1);
			int y = randomWithRange(0, this.height - 1);
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
				list1.add(product);
				list2.add(product);
				list3.add(product);
				list4.add(product);
			}
		}
	}
	
	private int randomWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int) (Math.random() * range) + min;
	}

	/*
	 *  getters and setters
	 */
	
	public ArrayList<Location> getPicklist() {
		return picklist;
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

	public ArrayList<Location> getListFour() {
		return list4;
	}

	public int getHoogte() {
		return height;
	}

	public int getBreedte() {
		return width;
	}

	public void setMinimaalAantalproducten(int minimumOfProducts) {
		this.minimumOfProducts = minimumOfProducts;
	}

	public void setMaximaalAantalproducten(int maximumOfProducts) {
		this.maximumOfProducts = maximumOfProducts;
	}
	
	/*
	 * toString function
	 */

	@Override
	public String toString() {
		String toReturn = "";
		for (Location item : picklist) {
			toReturn += "\n x:" + item.getLocationX() + " y:" + item.getLocationY();
		}
		return toReturn;
	}
}