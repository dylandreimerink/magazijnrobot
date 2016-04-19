package asrsSystem;
import java.util.ArrayList;

import Functions.*;
import Functions.Product;

public class Order {

	private String path="";
	private ArrayList<Product> product;
	
	
	public Order(String path) {
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
