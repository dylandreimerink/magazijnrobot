/*
 * Henri & Jan Willem
 */
package tspSimulator;

import java.awt.Graphics;

import javax.swing.JPanel;



public class DrawPanel extends JPanel {

	private static final int HEIGHT = 10;
	private static final int WIDTH = 10;
	private String algoname;
	private int startpoint;
	private int endpoint;

	public DrawPanel(String algoname, int startpoint, int endpoint) {
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
