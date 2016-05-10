package asrsSystem;

import java.util.ArrayList;

import tspSimulatorv2.Picklist;
import tspSimulatorv2.Result;


public class Product_Location {
	
	private twooptController controller;
	private TwoOpt twoopt;
	private Result twooptResults;
	private Picklist picklist;

	public Product_Location(){
		controller = new twooptController();
		controller.generateNewPicklist();
		controller.testAlgorithm();
		controller.TwoOptCallback();
		controller.setTwooptResults(twooptResults);
	}
	
	public void start(){

	}
}
