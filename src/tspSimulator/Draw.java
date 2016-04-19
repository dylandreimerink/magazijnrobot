package tspSimulator;

import java.awt.Graphics;

import javax.swing.JPanel;



public class Draw extends JPanel {

	private static final int height = 10;
	private static final int width = 10;
	private String algoname;
	private int startpoint;
	private int endpoint;

	public Draw(String algoname, int startpoint, int endpoint) {
		this.startpoint = startpoint;
		this.endpoint = endpoint;
		this.algoname = algoname;
		
	}



	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawRect (startpoint, endpoint, 200, 200);
	}

}
