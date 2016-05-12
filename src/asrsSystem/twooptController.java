package asrsSystem;

import java.util.ArrayList;

public class twooptController {
	private ArrayList<Location> location;
	DrawPanel routepanel;
	
	public void start() {
		
		routepanel = new DrawPanel();
	}

	public DrawPanel getPanel() {
		return routepanel;
	}

}
