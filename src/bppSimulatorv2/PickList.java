package bppSimulatorv2;

import java.util.ArrayList;

import shared.Database;
import shared.Product;

public class PickList {
	ArrayList<Product> products = new ArrayList<Product>();
	
	public PickList(){
		
	}
	
	public PickList(int[] productIds) {
		Database db = new Database();
		try {
			for(Product p : db.selectAll()){
				for(int id : productIds){
					if(id == p.getProductId()){
						products.add(p);
					}
				}
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public PickList(Product[] products){
		Database db = new Database();
		try {
			for(Product p : db.selectAll()){
				for(Product pp : products){
					if(p.equals(pp)){
						this.products.add(p);
					}
				}
			}
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public PickList(ArrayList<Product> products){
		this.products = products;
	}

	public ArrayList<Product> getProducts() {
		return products;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Product p : products){
			sb.append(p.getProductName() + ", ");
		}
		return sb.toString();
	}
}
