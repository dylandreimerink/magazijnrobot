package tspSimulatorv2;


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
		bruteforce = new Bruteforce(picklist.getListOne());
		twoopt = new TwoOpt(picklist.getListTwo());
		nearestneighbour = new NearestNeighbour(picklist.getListThree());
		
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
		bruteforceResults = new Result(picklist.getListOne(), 0);
		twooptDrawResults = new Result(picklist.getListTwo(),0);
		nearestneighbourResults = new Result(picklist.getListThree(), 0);

	}
	
}
