package tspSimulator;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.SingleSelectionModel;

/**
 * Authors: Jan Willem en Henri 
 * Class: ICTM2A
 */

public class DrawPanel extends JPanel {
	

	
	public DrawPanel() {
		this.setPreferredSize(new Dimension(700, 150));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
	}

}
