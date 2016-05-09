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
	private Result twooptResults;
	private Result nearestneighbourResults;
	private Result nearestneighbourandtwooptResult;

	long start_time;
	long stop_time;

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
		twooptResults = new Result(picklist.getPicklist(), 0);
		nearestneighbourResults = new Result(picklist.getPicklist(), 0);
		nearestneighbourandtwooptResult = new Result(picklist.getPicklist(), 0);

		/*
		 * Generate new DrawPanels
		 */
		bruteforceDrawPanel = new DrawPanel("Bruteforce", bruteforceResults, HEIGHT, WIDTH, SHOWRASTER);
		twooptDrawPanel = new DrawPanel("TwoOpt", twooptResults, HEIGHT, WIDTH, SHOWRASTER);
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
		twooptResults.setResult(picklist.getPicklist());
		nearestneighbourResults.setResult(picklist.getPicklist());
		nearestneighbourandtwooptResult.setResult(picklist.getPicklist());

		bruteforceDrawPanel.setResultaat(bruteforceResults);
		twooptDrawPanel.setResultaat(twooptResults);
		nearestneighbourDrawPanel.setResultaat(nearestneighbourResults);
		nearestneighbourandtwooptDrawPanel.setResultaat(nearestneighbourandtwooptResult);
		mainGui.repaint();
	}

	public void testAlgorithm() {

		start_time = System.nanoTime();

		bruteforce.setOnDoneListner(this);
		bruteforce.start(picklist.getListOne());

		twoopt.setOnDoneListner(this);
		twoopt.start(picklist.getListTwo());

		nearestneighbour.setOnDoneListner(this);
		nearestneighbour.start(picklist.getListThree());

		nearestneighbourandtwoopt.setOnDoneListner(this);
		nearestneighbourandtwoopt.start(picklist.getListThree());

	}

	public void BruteForceCallback() {
		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;

		bruteforceResults = bruteforce.getResult();
		bruteforceResults.setTime(Math.round(diffTime));
		bruteforceDrawPanel.setResultaat(bruteforceResults);
		mainGui.repaint();
	}

	public void TwoOptCallback() {
		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;

		twooptResults = twoopt.getResult();
		twooptResults.setTime(Math.round(diffTime));
		twooptDrawPanel.setResultaat(twooptResults);
		mainGui.repaint();
	}

	public void nearestNeighbourCallback() {
		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;

		nearestneighbourResults = nearestneighbour.getResult();
		nearestneighbourResults.setTime(Math.round(diffTime));
		nearestneighbourDrawPanel.setResultaat(nearestneighbourResults);
		mainGui.repaint();
	}

	public void nearestNeighbourAndTwoOptCallback() {
		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;

		nearestneighbourandtwooptResult = nearestneighbourandtwoopt.getResult();
		nearestneighbourandtwooptResult.setTime(Math.round(diffTime));
		nearestneighbourandtwooptDrawPanel.setResultaat(nearestneighbourandtwooptResult);
		mainGui.repaint();
	}

}
