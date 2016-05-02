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
	private NearestNeighbourAndTwoOpt nearestneighbourandtwoopt;

	/*
	 * DrawPanels
	 */
	private DrawPanel bruteforceDrawPanel;
	private DrawPanel twooptDrawPanel;
	private DrawPanel nearestneighbourDrawPanel;
	private DrawPanel nearestneighbourandtwooptDrawPanel;
	/*
	 * Results
	 */
	private Result bruteforceResults;
	private Result twooptDrawResults;
	private Result nearestneighbourResults;
	private Result nearestneighbourandtwooptResult;

	private MainGUI mainGui;

	public TSPController() {
		/*
		 * Init picklist
		 */
		picklist = new Picklist();
		/*
		 * Generate new Algorithms
		 */
		
		(bruteforce = new Bruteforce()).start();
		twoopt = new TwoOpt();
		nearestneighbour = new NearestNeighbour();
		nearestneighbourandtwoopt = new NearestNeighbourAndTwoOpt();

		/*
		 * Generate temporately Result
		 */
		bruteforceResults = new Result(picklist.getListOne(), 0);
		twooptDrawResults = new Result(picklist.getListTwo(), 0);
		nearestneighbourResults = new Result(picklist.getListThree(), 0);
		nearestneighbourandtwooptResult = new Result(picklist.getListFour(), 0);

		/*
		 * Generate new DrawPanels
		 */
		bruteforceDrawPanel = new DrawPanel("Bruteforce", bruteforceResults);
		twooptDrawPanel = new DrawPanel("TwoOpt", twooptDrawResults);
		nearestneighbourDrawPanel = new DrawPanel("NearestNeighbour", nearestneighbourResults);
		nearestneighbourandtwooptDrawPanel = new DrawPanel("NearestNeighbour + TwoOpt",
				nearestneighbourandtwooptResult);

		/*
		 * Create MainGUI
		 */
		mainGui = new MainGUI(bruteforceDrawPanel, twooptDrawPanel, nearestneighbourDrawPanel,
				nearestneighbourandtwooptDrawPanel, this);
	}

	public void generateNewPicklist() {
		picklist.generateNewPicklist();
		bruteforceResults.setResult(picklist.getListOne());
		twooptDrawResults.setResult(picklist.getListTwo());
		nearestneighbourResults.setResult(picklist.getListThree());
		nearestneighbourandtwooptResult.setResult(picklist.getListFour());

		bruteforceDrawPanel.setResultaat(bruteforceResults);
		twooptDrawPanel.setResultaat(twooptDrawResults);
		nearestneighbourDrawPanel.setResultaat(nearestneighbourResults);
		nearestneighbourandtwooptDrawPanel.setResultaat(nearestneighbourandtwooptResult);
	}

	public void testAlgorithm() {

		bruteforceResults = bruteforce.calculateRoute(picklist.getListOne());
		twooptDrawResults = twoopt.calculateRoute(picklist.getListOne());
		//new Thread(twoopt).start();
		nearestneighbourResults = nearestneighbour.calculateRoute(picklist.getListOne());
		//new Thread(nearestneighbour).start();
		nearestneighbourandtwooptResult = nearestneighbourandtwoopt.calculateRoute(picklist.getListOne());
		//new Thread(nearestneighbourandtwoopt).start();

		bruteforceDrawPanel.setResultaat(bruteforceResults);
		twooptDrawPanel.setResultaat(twooptDrawResults);
		nearestneighbourDrawPanel.setResultaat(nearestneighbourResults);
		nearestneighbourandtwooptDrawPanel.setResultaat(nearestneighbourandtwooptResult);
	}

}
