package bppSimulator;

import java.util.ArrayList;
import java.util.Stack;

import shared.Product;

public class BPPBruteForce implements BPPAlgorithm, Runnable {

	protected ArrayList<Box> usedBoxes = new ArrayList<Box>();
	protected ArrayList<Box> bestBoxes = new ArrayList<Box>();
	protected ArrayList<Product> products;
	protected Thread t = new Thread(this);

	protected boolean waiting = false;
	protected boolean stop = false;

	boolean hasResult = false;
	boolean[][][] boxUnits;

	MainGUI onComplete;

	public BPPBruteForce(PickList picklist, int boxWidth, int boxHeight, int boxLength) {
		products = picklist.getProducts();
		boxUnits = new boolean[boxWidth][boxHeight][boxLength];
	}

	public boolean getPauzed() {
		return waiting;
	}

	public void setOnDoneListner(MainGUI listnerClass) {
		onComplete = listnerClass;
	}

	@Override
	public void start() {
		if (waiting) {
			waiting = false;
		} else {
			t.start();
		}
	}

	@Override
	public void stop() {
		System.out.println("stopping brute force");
		stop = true;
		
	}

	public void pauze() {
		waiting = true;
	}

	@Override
	public ArrayList<Box> getResult() {
		return bestBoxes;
	}

	public void permutations(ArrayList<Product> items, Stack<Product> permutation, int size) {

		if (permutation.size() == size) {

			ArrayList<Product> boxlist = new ArrayList<Product>();
			for (Product p : permutation.toArray(new Product[0])) {
				while (waiting) {

				}
				double usedSpace = 0;

				for (Product pp : boxlist) {
					usedSpace += pp.getHeight() * pp.getLenght() * pp.getWidth();
				}

				usedSpace += p.getHeight() * p.getLenght() * p.getWidth();
				if ((boxUnits.length * boxUnits[0].length * boxUnits[0][0].length) > usedSpace && fitProduct(p)) {
					boxlist.add(p);
				} else {

					usedBoxes.add(
							new Box(boxUnits.length, boxUnits[0].length, boxUnits[0][0].length, new PickList(boxlist)));
					boxlist = new ArrayList<Product>();
					boxlist.add(p);
					boxUnits = new boolean[boxUnits.length][boxUnits[0].length][boxUnits[0][0].length];
				}
			}
			usedBoxes.add(new Box(boxUnits.length, boxUnits[0].length, boxUnits[0][0].length, new PickList(boxlist)));
			checkBoxes(usedBoxes);
			usedBoxes = new ArrayList<Box>();
		}

		if(stop){
			return;
		}
		
		/* items available for permutation */
		Product[] availableItems = items.toArray(new Product[0]);
		for (Product i : availableItems) {
			/* add current item */
			permutation.push(i);

			/* remove item from available item set */
			items.remove(i);

			/* pass it on for next permutation */
			permutations(items, permutation, size);

			/* pop and put the removed item back */
			items.add(permutation.pop());
		}
	}

	private void checkBoxes(ArrayList<Box> used) {

		if (bestBoxes.size() > used.size()) {

			bestBoxes = used;
		} else if (bestBoxes.size() == 0) {
			bestBoxes = used;
		}
	}

	@Override
	public void run() {

		permutations(products, new Stack<Product>(), products.size());


		if (onComplete != null) {
			callBack();
		}
	}

	protected void callBack() {
		onComplete.BruteForceCallback();
	}

	private boolean fitProduct(Product p) {
		boolean fit = false;
		boolean thisTry = true;
		int productWidth = (int) p.getWidth();
		int productHeight = (int) p.getHeight();
		int productLength = (int) p.getLenght();
		int xOff = 0, yOff = 0, zOff = 0;
		int maxXOff = boxUnits.length - productWidth;
		int maxYOff = boxUnits[0].length - productHeight;
		int maxZOff = boxUnits[0][0].length - productLength;

		boolean[] turned = { false, false, false };
		if (productWidth == productLength) {
			turned[2] = true;
		}
		if (productWidth == productHeight) {
			turned[1] = true;
		}
		if (productLength == productHeight) {
			turned[0] = true;
		}

		do {
			thisTry = true;
			for (int x = xOff; x < (xOff + productWidth); x++) {
				for (int y = yOff; y < (yOff + productHeight); y++) {
					for (int z = zOff; z < (zOff + productLength); z++) {
						if (boxUnits[x][y][z]) {
							thisTry = false;
							x = (xOff + productWidth);
							y = (yOff + productHeight);
							z = (zOff + productLength);
						}
					}
				}
			}
			if (!thisTry) {
				if (xOff < maxXOff) {
					xOff++;
				} else if (yOff < maxYOff) {
					yOff++;
					xOff = 0;
				} else if (zOff < maxZOff) {
					zOff++;
					yOff = 0;
					xOff = 0;
				} else {
					if (turned[0] && turned[1] && turned[2]) {
						break;
					} else {
						if (!turned[2] && productWidth != productLength) {
							int buff = productWidth;
							productWidth = productLength;
							productLength = buff;
							turned[2] = true;
						} else if (!turned[1] && productWidth != productHeight) {
							int buff = productWidth;
							productWidth = productHeight;
							productHeight = buff;
							turned[1] = true;
							if (productWidth != productLength)
								turned[2] = false;
						} else if (!turned[0] && productLength != productHeight) {
							int buff = productLength;
							productLength = productHeight;
							productHeight = buff;
							turned[0] = true;
							if (productWidth != productHeight)
								turned[1] = false;
							if (productWidth != productLength)
								turned[2] = false;
						}
						if (productWidth == productLength) {
							turned[2] = true;
						}
						if (productWidth == productHeight) {
							turned[1] = true;
						}
						if (productLength == productHeight) {
							turned[0] = true;
						}
						xOff = 0;
						yOff = 0;
						zOff = 0;
						maxXOff = boxUnits.length - productWidth;
						maxYOff = boxUnits[0].length - productHeight;
						maxZOff = boxUnits[0][0].length - productLength;
					}
				}
			}
		} while (!thisTry);

		if (thisTry) {
			fit = true;

			for (int x = xOff; x < (xOff + productWidth); x++) {
				for (int y = yOff; y < (yOff + productHeight); y++) {
					for (int z = zOff; z < (zOff + productLength); z++) {
						boxUnits[x][y][z] = true;
					}
				}
			}
		}

		return fit;
	}

}
