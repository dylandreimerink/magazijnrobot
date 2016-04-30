package tspSimulatorv2;

import java.util.ArrayList;

public class TSPController {
	private Picklist picklist;
	
	/*
	 * Algorithms
	 */
	private Bruteforce bruteforce;
	private TwoOpt twoopt;
	private NearestNeighbour nearestneighbour;
	
	/*
	 * DrawPanels
	 */
	private DrawPanel bruteforceDrawPanel;
	private DrawPanel twooptDrawPanel;
	private DrawPanel nearestneighbourDrawPanel;
	
	/*
	 * Results
	 */
	private Result bruteforceResults;
	private Result twooptDrawResults;
	private Result nearestneighbourResults;
	
	private MainGUI mainGui;

	public TSPController() {
		/*
		 * Init picklist
		 */
		picklist = new Picklist();
		/*
		 * Generate new Algorithms
		 */
		bruteforce = new Bruteforce();
		twoopt = new TwoOpt();
		nearestneighbour = new NearestNeighbour();
		
		/*
		 * Generate temporately Result
		 */
		bruteforceResults = new Result(picklist.getListOne(), 0);
		twooptDrawResults = new Result(picklist.getListTwo(),0);
		nearestneighbourResults = new Result(picklist.getListThree(), 0);
		
		
		/*
		 * Generate new DrawPanels
		 */
		bruteforceDrawPanel = new DrawPanel("Bruteforce",bruteforceResults);
		twooptDrawPanel = new DrawPanel("TwoOpt",twooptDrawResults);
		nearestneighbourDrawPanel = new DrawPanel("NearestNeighbour",nearestneighbourResults);
		
		/*
		 * Create MainGUI
		 */
		mainGui = new MainGUI(bruteforceDrawPanel, twooptDrawPanel, nearestneighbourDrawPanel, this);
	}
	
	public void generateNewPicklist(){
		picklist.generateNewPicklist();
		bruteforceResults.setResult(picklist.getListOne());
		twooptDrawResults.setResult(picklist.getListTwo());
		nearestneighbourResults.setResult(picklist.getListThree());
		bruteforceDrawPanel.setResultaat(bruteforceResults);
		twooptDrawPanel.setResultaat(twooptDrawResults);
		nearestneighbourDrawPanel.setResultaat(nearestneighbourResults);
	}
	
	
	public void testAlgorithm(){
		//picklist.generateNewPicklist();
		//twoopt.calculateRoute();
		//nearestneighbour.calculateRoute();
		bruteforceResults = bruteforce.calculateRoute(picklist.getListOne());
		//twooptDrawResults.setResult(picklist.getListTwo());
		nearestneighbourResults =  nearestneighbour.calculateRoute(picklist.getListThree());
		bruteforceDrawPanel.setResultaat(bruteforceResults);
		twooptDrawPanel.setResultaat(twooptDrawResults);
		nearestneighbourDrawPanel.setResultaat(nearestneighbourResults);
	}
	
	
}
