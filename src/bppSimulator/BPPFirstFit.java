package bppSimulator;

import java.util.ArrayList;
import shared.Product;

public class BPPFirstFit implements BPPAlgorithm, Runnable {

	protected ArrayList<Box> usedBoxes = new ArrayList<Box>();
	protected ArrayList<Product> products;
	protected Thread t = new Thread(this);

	protected boolean waiting = false;

	boolean hasResult = false;
	boolean[][][] boxUnits;

	MainGUI onComplete;

	public BPPFirstFit(PickList picklist, int boxWidth, int boxHeight, int boxLength) {
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
		t.interrupt();
	}

	public void pauze() {
		waiting = true;
	}

	@Override
	public ArrayList<Box> getResult() {
		return usedBoxes;
	}

	@Override
	public void run() {
		System.out.println(products);
		ArrayList<Product> boxlist = new ArrayList<Product>();
		for (Product p : products) {
			while (waiting) {
				System.out.println("waiting for input");
			}
			double usedSpace = 0;

			for (Product pp : boxlist) {
				usedSpace += pp.getHeight() * pp.getLenght() * pp.getWidth();
			}

			usedSpace += p.getHeight() * p.getLenght() * p.getWidth();
			if ((boxUnits.length * boxUnits[0].length * boxUnits[0][0].length) > usedSpace && fitProduct(p)) {
				System.out.println("product fit: " + p.toString());
				boxlist.add(p);
			} else {
				System.out.println("product doent fit " + p.getWidth() + ", " + p.getHeight() + ", " + p.getLenght());
				usedBoxes.add(
						new Box(boxUnits.length, boxUnits[0].length, boxUnits[0][0].length, new PickList(boxlist)));
				boxlist = new ArrayList<Product>();
				boxlist.add(p);
				boxUnits = new boolean[boxUnits.length][boxUnits[0].length][boxUnits[0][0].length];
			}
		}
		usedBoxes.add(new Box(boxUnits.length, boxUnits[0].length, boxUnits[0][0].length, new PickList(boxlist)));

		for (Box box : usedBoxes) {
			System.out.println(box.getPickList());
		}
		if (onComplete != null) {
			callBack();
		}
	}

	protected void callBack() {
		onComplete.FirstFitCallback();
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
							System.out.println("turning on z axis");
							int buff = productWidth;
							productWidth = productLength;
							productLength = buff;
							turned[2] = true;
						} else if (!turned[1] && productWidth != productHeight) {
							System.out.println("turning on y axis");
							int buff = productWidth;
							productWidth = productHeight;
							productHeight = buff;
							turned[1] = true;
							if (productWidth != productLength)
								turned[2] = false;
						} else if (!turned[0] && productLength != productHeight) {
							System.out.println("turning on x axis");
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
