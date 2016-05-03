package tspSimulatorv2;

import java.util.ArrayList;

public class TSPController {
	/*
	 * Dimensions
	 */
	private final int HEIGHT = 10;
	private final int WIDTH = 10;
	private final int MAXPRODUCTS = 9;
	private final int MINPRODUCTS = 9;
	private final boolean SHOWRASTER = true;

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
		picklist = new Picklist(MAXPRODUCTS, MINPRODUCTS, HEIGHT, WIDTH);
		/*
		 * Generate new Algorithms
		 */

		bruteforce = new Bruteforce();
		twoopt = new TwoOpt();
		nearestneighbour = new NearestNeighbour();
		nearestneighbourandtwoopt = new NearestNeighbourAndTwoOpt();

		/*
		 * Generate temporately Result
		 */
		bruteforceResults = new Result(picklist.getPicklist(), 0);
		twooptDrawResults = new Result(picklist.getPicklist(), 0);
		nearestneighbourResults = new Result(picklist.getPicklist(), 0);
		nearestneighbourandtwooptResult = new Result(picklist.getPicklist(), 0);

		/*
		 * Generate new DrawPanels
		 */
		bruteforceDrawPanel = new DrawPanel("Bruteforce", bruteforceResults, HEIGHT, WIDTH, SHOWRASTER);
		twooptDrawPanel = new DrawPanel("TwoOpt", twooptDrawResults, HEIGHT, WIDTH, SHOWRASTER);
		nearestneighbourDrawPanel = new DrawPanel("NearestNeighbour", nearestneighbourResults, HEIGHT, WIDTH,
				SHOWRASTER);
		nearestneighbourandtwooptDrawPanel = new DrawPanel("NearestNeighbour + TwoOpt", nearestneighbourandtwooptResult,
				HEIGHT, WIDTH, SHOWRASTER);

		/*
		 * Create MainGUI
		 */
		mainGui = new MainGUI(bruteforceDrawPanel, twooptDrawPanel, nearestneighbourDrawPanel,
				nearestneighbourandtwooptDrawPanel, this);
	}

	public void generateNewPicklist() {
		picklist.generateNewPicklist();
		bruteforceResults.setResult(picklist.getPicklist());
		twooptDrawResults.setResult(picklist.getPicklist());
		nearestneighbourResults.setResult(picklist.getPicklist());
		nearestneighbourandtwooptResult.setResult(picklist.getPicklist());

		bruteforceDrawPanel.setResultaat(bruteforceResults);
		twooptDrawPanel.setResultaat(twooptDrawResults);
		nearestneighbourDrawPanel.setResultaat(nearestneighbourResults);
		nearestneighbourandtwooptDrawPanel.setResultaat(nearestneighbourandtwooptResult);
	}

	public void testAlgorithm() {

		bruteforceResults = bruteforce.calculateRoute(picklist.getPicklist());
		twooptDrawResults = twoopt.calculateRoute(picklist.getPicklist());
		// new Thread(twoopt).start();
		nearestneighbourResults = nearestneighbour.calculateRoute(picklist.getPicklist());
		// new Thread(nearestneighbour).start();
		nearestneighbourandtwooptResult = nearestneighbourandtwoopt.calculateRoute(picklist.getPicklist());
		// new Thread(nearestneighbourandtwoopt).start();

		bruteforceDrawPanel.setResultaat(bruteforceResults);
		twooptDrawPanel.setResultaat(twooptDrawResults);
		nearestneighbourDrawPanel.setResultaat(nearestneighbourResults);
		nearestneighbourandtwooptDrawPanel.setResultaat(nearestneighbourandtwooptResult);
	}

}
