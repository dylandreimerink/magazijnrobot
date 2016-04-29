package tspSimulatorv2;


public class TSPController {
	private Picklist picklist;
	private Bruteforce bruteforce;
	private TwoOpt twoopt;
	private NearestNeighbour nearestneighbour;
	private MainGUI mainGui;

	public TSPController() {
		/*
		 * Init picklist
		 */
		picklist = new Picklist();
		/*
		 * Generate new Algorithms
		 */
		bruteforce = new Bruteforce(picklist.getListOne());
		twoopt = new TwoOpt(picklist.getListTwo());
		nearestneighbour = new NearestNeighbour(picklist.getListThree());
		
		mainGui = new MainGUI();
		
		
		
		
		
	}
	
	/*
	 * Resultaat ophalen en doorsturen naar drawpanel
	 */
	
}
