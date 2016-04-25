//Authors: Mike Veltman & Dylan Reimerink (ICTM2a)

package bppSimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import shared.Product;

public class SimulatiePanel extends JPanel {

	MainGUI parent;

	int productYOffset = 350;
	int boxYOffset = 100;
	Dimension productDimensions = new Dimension(50, 50);
	Dimension boxDimentions = new Dimension(50, 50);
	Dimension productTextOffset = new Dimension(-10, 0);

	public SimulatiePanel(MainGUI parant) {
		super();
		this.parent = parant;

		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
	}

	public Dimension getPreferredSize() {
		return new Dimension(250, 450);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Font font = new Font("product", Font.PLAIN, 14);
		FontMetrics metrics = g.getFontMetrics();

		g.setFont(font);
		
		for (Box b : parent.boxList) {
			System.out.println("HOI");
		}
	}
}
