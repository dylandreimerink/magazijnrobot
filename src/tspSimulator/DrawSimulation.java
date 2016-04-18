package tspSimulator;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.SingleSelectionModel;

/**
 * Authors: Jan Willem en Henri 
 * Class: ICTM2A
 */

public class DrawSimulation extends JPanel {

	public DrawSimulation() {
		this.setPreferredSize(new Dimension(700, 150));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// for (int x = 0; x < 700; x++) {
		// g.fillOval(x, 0, 80, 80);
		//
		// }
		// repaint();
	}

}
