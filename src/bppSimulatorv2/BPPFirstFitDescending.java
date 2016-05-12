package bppSimulatorv2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import shared.Product;

public class BPPFirstFitDescending extends BPPFirstFit implements Comparator<Product>{

	public BPPFirstFitDescending(PickList picklist, int boxWidth, int boxHeight, int boxLength) {
		super(picklist, boxWidth, boxHeight, boxLength);
		ArrayList<Product> buff = new ArrayList<Product>();
		for(Product p : products){
			buff.add((Product)p);
		}
		products = buff;
		Collections.sort(products, this);
	}
	
	@Override
	protected void callBack(){
		for (Box box : usedBoxes) {
			for(Product p : box.getPickList().getProducts()){
				System.out.print(p.getProductId() + ", ");
			}
		}
		System.out.print(" boxes: "+ usedBoxes.size() +" \n");
		onComplete.FirstFitDescCallback();
	}
	
	@Override
	public int compare(Product o1, Product o2) {
		double size1 = o1.getHeight() * o1.getLenght() * o1.getWidth();
		double size2 = o2.getHeight() * o2.getLenght() * o2.getWidth();
		if(size1 < size2){
			return -1;
		}
		if(size2 > size1){
			return 1;
		}
		return 0;
	}
	
	

}
