import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawSimulation extends JPanel {

	public DrawSimulation() {
		 this.setPreferredSize(new Dimension(700, 150));
	}
	
	 public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.fillOval(0, 0, 80, 80);
	 }
	
}	

