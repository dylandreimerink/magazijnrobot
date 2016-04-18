package bppSimulator;

//Authors: Mike Veltman & Dylan Reimerink (ICTM2a)

import java.awt.Graphics;

import javax.swing.JPanel;

public class SimulatiePanel extends JPanel {

	public SimulatiePanel(MainGUI parant) {
		parant.lbltijd_2.setText("55 sec");
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
	}
	
}
