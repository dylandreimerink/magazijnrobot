package asrsSystem;
/*
 * Authors: richard en steven
 */
import java.util.ArrayList;

import shared.Product;

public class Doos {
	private ArrayList<Product> productList = new ArrayList<Product>();
	public int doosId;
	
	public Doos(int doosId){
		this.doosId = doosId;
	}
	
	public void addProduct(Product p){
		productList.add(p);
	}

	public ArrayList<Product> getProductList(){
		return productList;
	}
	
	
	public static ArrayList<Doos> generateDoosList(ArrayList<Product> productlist) {
		ArrayList<Doos> doosList = new ArrayList<Doos>();
		Doos doos1 = new Doos(1);
		Doos doos2 = new Doos(2);
		int i = 0;
		for(Product p : productlist){
			if(i % 2 == 1){
				doos1.addProduct(p);
			}else{
				doos2.addProduct(p);
			}
			i++;
		}
		doosList.add(doos1);
		doosList.add(doos2);
		
		System.out.println(doosList);
		return doosList;
		
	}
	
	public String toString() {
		return "Doos" +doosId+": "+productList.toString()+", ";
		
	}
	
}
