package asrsSystem;
/*
 * Authors: richard en steven
 */
import java.util.ArrayList;

import shared.Product;

public class Doos {
	private int doosID;
	private ArrayList<Doos> doosList;
	
	public Doos(int doosID){
		this.doosID = doosID;
	}
	
	public Doos() {
		//let this stay
	}
	
	public void setMaxInDoos(int value) {
	}
	
	public int getDoosId() {
		return doosID;
	}
	
	public ArrayList<Doos> generateDoosList(ArrayList<Product> productlist) {
		doosList = new ArrayList<Doos>();
			int aantal = productlist.size()/2;
			int doosId = 1;
			for(int i=0; i <= aantal; i++) {
				Doos doos = new Doos(doosId);
				doosList.add(doos);
			}
			doosId++;
			for(int i=aantal; i< productlist.size()-1; i++) {
				Doos doos = new Doos(doosId);
				doosList.add(doos);
			}		
		return doosList;
	}
	
	public String toString() {
		return "Doos: "+doosID+", ";
		
	}
	
}
