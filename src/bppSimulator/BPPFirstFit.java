package bppSimulator;

import java.util.ArrayList;

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
		ArrayList<Product> boxlist = new ArrayList<Product>();
		for(Product p : products){
			if(fitProduct(p)){
				System.out.println("product fit: " + p.toString());
				boxlist.add(p);
			}else{
				//add boxlist after sync
				usedBoxes.add(new Box(boxUnits.length, boxUnits[0].length, boxUnits[0][0].length));
				boxlist = new ArrayList<Product>();
			}
		}
		
	}
	
	private boolean fitProduct(Product p){
		boolean fit = false;
		boolean thisTry = true;
		int productWidth = (int)p.getWidth();
		int productHeight = (int)p.getHeight();
		int productLength = (int)p.getLenght();
		int xOff = 0, yOff = 0, zOff = 0;
		int maxXOff = boxUnits.length - productWidth;
		int maxYOff = boxUnits[0].length - productHeight;
		int maxZOff = boxUnits[0][0].length - productLength;
		
		do{
			for(int x = xOff; x < (xOff + productWidth); x++){
				for(int y = yOff; y < (yOff + productHeight); y++){
					for(int z = zOff; z < (zOff + productLength); z++){
						if(!boxUnits[x][y][z]){
							thisTry = false;
							x = (xOff + productWidth);
							y = (yOff + productHeight);
							z = (zOff + productLength);
						}
					}
				}
			}
			if(!thisTry){
				if(xOff < maxXOff){
					xOff++;
				}else if(yOff < maxYOff){
					yOff++;
					xOff = 0;
				}else if(zOff < maxZOff){
					zOff++;
					yOff = 0;
					xOff = 0;
				}else{
					break;
				}
			}
		}while(!thisTry);
		
		if(thisTry){
			fit = true;
			
			for(int x = xOff; x < (xOff + productWidth); x++){
				for(int y = yOff; y < (yOff + productHeight); y++){
					for(int z = zOff; z < (zOff + productLength); z++){
						boxUnits[x][y][z] = true;
					}
				}
			}
		}
		
		return fit;
	}

}
