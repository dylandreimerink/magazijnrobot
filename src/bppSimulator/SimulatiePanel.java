package bppSimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class SimulatiePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MainGUI parent;
	
	public SimulatiePanel(MainGUI parant) {
		super();
		this.parent = parant; 
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
	}

    public Dimension getPreferredSize() {
        return new Dimension(250,450);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // Draw Text
        g.drawString("This is my custom Panel!",10,20);
    }  
	
}
