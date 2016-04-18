package asrsSystem;
import java.util.ArrayList;

import Functions.*;

public class product {
	private String naam;
	private int plaats;
	private double hoogte;
	private double breedte;
	private double lengte;
	
	
	public product(String naam, int plaats, double hoogte, double breedte, double lengte){
		Database connection = new Database();
		connection.connect();
		this.naam = naam;
		this.plaats = plaats;
		this.hoogte = hoogte;
		this.breedte = breedte;
		this.lengte = lengte;
	}
	
}

