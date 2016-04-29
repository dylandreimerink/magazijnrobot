package tspSimulatorv2;


public class TSPController {
	private Picklist picklist;
	private Bruteforce bruteforce;
	private TwoOpt twoopt;
	private NearestNeighbour nearestneighbour;
	private MainGUI maingGui;

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
		
		maingGui = new MainGUI();
		
		
		
		
	}
	/*
	 * Resultaat ophalen en doorsturen naar drawpanel
	 */
	
}
