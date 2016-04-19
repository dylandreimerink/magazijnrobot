package asrsSystem;
import java.util.ArrayList;

import Functions.*;

public class order {
    
	private String path="";
	private ArrayList<Product> product;
	
	
	public order(String path) {
		this.path = path;
		Bestelling order = new Bestelling(this.path);
		product = new ArrayList();
		product = order.getProductList();
	}
	// getter
	public ArrayList<Product> getProductList() {
		return product;
	}
}
