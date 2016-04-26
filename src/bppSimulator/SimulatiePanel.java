//Authors: Mike Veltman & Dylan Reimerink (ICTM2a)

package bppSimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.synth.SynthSeparatorUI;

import shared.Product;

public class SimulatiePanel extends JPanel {

	MainGUI parent;

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
		return new Dimension(300, 650);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int rectX = 20;
		int rectY = 10;
		int rectXOffset = 150;
		int rectYOffset = 700;
		int lineX1 = rectX;
		int lineX2 = rectXOffset + 20;
		int lineY1 = rectYOffset + 10;
		int lineY2 = rectYOffset + 10;
		int currentBox = 0;
		int efficiency;
		int totalEfficiency;
		int listEfficiency;
		int unusedX1 = 0;
		int unusedY1 = 0;
		int oldY1 = 0;

		Font font = new Font("product", Font.PLAIN, 14);
		FontMetrics metrics = g.getFontMetrics();

		g.setFont(font);

		for (Box b : parent.boxList) {
			double boxCapacity = b.getBreedte() * b.getHoogte() * b.getLengte();
			totalEfficiency = 100;

			g.setColor(Color.red);
			Rectangle box = new Rectangle(rectX, rectY - 1, rectXOffset, rectYOffset);
			g.drawRect(box.x, box.y, box.width, box.height);

			for (Product p : b.getPickList().getProducts()) {
				double productCapacity = p.getHeight() * p.getLenght() * p.getWidth();
				double fillPercentage = 100 * (productCapacity / boxCapacity);
				

				lineY1 = (int) (lineY2 - 800 * (productCapacity / boxCapacity));
				lineY2 = lineY1;

				DecimalFormat df = new DecimalFormat("#.0");

				g.setColor(Color.black);
				g.drawLine(lineX1, lineY1, lineX2, lineY2);
				g.drawString(p.getProductName() + df.format(fillPercentage) + "%", lineX1 + 2, lineY1 + 14);

				System.out.println(lineY1);

				unusedX1 = lineX1;
				unusedY1 = lineY1;
				oldY1 = lineY1;
			}

			currentBox = currentBox + 1;
			g.setColor(Color.red);
			g.drawString("Doos " + currentBox, rectX + 50, rectY + rectYOffset + 16);

			g.setColor(Color.lightGray);
			Rectangle unused = new Rectangle(rectX + 1, rectY, rectXOffset - 1, lineY1 - 10);
			g.fillRect(unused.x, unused.y, unused.width, unused.height);

			if (currentBox > 0) {
				rectX = rectX + rectXOffset + 10;
				lineX1 = rectX;
				lineX2 = rectXOffset + lineX1;
				lineY1 = rectYOffset + 10;
				lineY2 = rectYOffset + 10;
			}

		}

	}

}
