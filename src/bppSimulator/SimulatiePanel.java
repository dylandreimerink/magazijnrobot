//Authors: Mike Veltman & Dylan Reimerink (ICTM2a)

package bppSimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import shared.Product;

public class SimulatiePanel extends JPanel {

	ArrayList<Box> boxlist;

	Dimension productDimensions = new Dimension(50, 50);
	Dimension boxDimentions = new Dimension(50, 50);
	Dimension productTextOffset = new Dimension(-10, 0);

	public SimulatiePanel() {
		super();
		boxlist = new ArrayList<Box>();
		
		setBorder(BorderFactory.createLineBorder(Color.black));
		setVisible(true);
	}
	
	public void setBoxList(ArrayList<Box> boxlist){
		this.boxlist = boxlist;
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
		Font font = new Font("product", Font.PLAIN, 14);
		g.getFontMetrics();

		g.setFont(font);

		for (Box b : boxlist) {
			double boxCapacity = b.getBreedte() * b.getHoogte() * b.getLengte();
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
				g.drawString(p.getProductName() + " " + df.format(fillPercentage) + "%", lineX1 + 2, lineY1 + 14);

				System.out.println(lineY1);
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