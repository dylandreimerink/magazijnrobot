package asrsSystem;
import java.util.ArrayList;

import Functions.Database;

public class product {

	private ArrayList<product> product;
	
	public product(){
		Database connection = new Database();
		connection.connect();
	}
	
}
