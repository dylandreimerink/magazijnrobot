package bppSimulator;

import java.util.ArrayList;
import java.util.Vector;

import shared.Product;

public class BPPFirstFit implements BPPAlgorithm, Runnable {
	
	private ArrayList<Box> usedBoxes = new ArrayList<Box>();
	private ArrayList<Product> products;
	private Thread t = new Thread(this);
	
	boolean hasResult = false;
	boolean[][][] boxUnits;
	
	public BPPFirstFit(PickList picklist, int boxWidth, int boxHeight, int boxLength) {
		products = picklist.getProducts();
		boxUnits = new boolean[boxWidth][boxHeight][boxLength];
	}

	@Override
	public void start() {
		t.start();
	}

	@Override
	public void stop() {
		t.interrupt();
	}

	@Override
	public ArrayList<Box> getResult() {
		return usedBoxes;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	private boolean fitProduct(Product p){
		boolean fit = false;
		int x = (int)p.getWidth();
		int y = (int)p.getHeight();
		int z = (int)p.getLenght();
		int xOff = 0, yOff = 0, zOff = 0;
		int maxXOff = 0;
		
		
		return fit;
	}

}
