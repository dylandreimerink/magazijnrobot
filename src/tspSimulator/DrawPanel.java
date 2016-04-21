
package tspSimulator;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final int HEIGHT = 400;
	private static final int WIDTH = 400;
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
		g.drawRect(startpoint, endpoint, HEIGHT, WIDTH);
	}

}
