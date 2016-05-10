package asrsSystem;

import tspSimulatorv2.Picklist;
import tspSimulatorv2.Result;
import asrsSystem.TwoOpt;

public class twooptController {
	private TwoOpt twoopt;
	private Result twooptResults;
	private Picklist picklist;

	public twooptController() {
		picklist = new Picklist(5, 5, 10, 10);

		twoopt = new TwoOpt();
		twooptResults = new Result(picklist.getPicklist(), 0);

	}
public void generateNewPicklist() {			
		picklist.generateNewPicklist();
		//System.out.println(picklist);
 		twooptResults.setResult(picklist.getPicklist());
		twooptResults.setShowPointsonly(true);
}
public void testAlgorithm() {
	twoopt.setOnDoneListner(this);
	twoopt.start(picklist.getPicklist());
	//System.out.println(picklist.getPicklist());
}

public void TwoOptCallback() {
	twooptResults = twoopt.getResult();
	//System.out.println(twooptResults);
}

public void setTwooptResults(Result twooptResults) {
	this.twooptResults = twooptResults;
	//System.out.println("nieuw; " + twooptResults);

}


}
