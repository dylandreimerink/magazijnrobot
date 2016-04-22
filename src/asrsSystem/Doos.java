package asrsSystem;

import java.util.ArrayList;

import shared.Product;

public class Doos {
	private int doosID;
	private double dHoogte;
	private double dLengte;
	private double dBreedte;
	private ArrayList<Product> pDoosList;
	
	public Doos(int doosID, double dHoogte, double dLengte, double dBreedte){
		this.doosID = doosID;
		this.dHoogte = dHoogte;
		this.dLengte = dLengte;
		this.dBreedte = dBreedte;
	}
	
}
