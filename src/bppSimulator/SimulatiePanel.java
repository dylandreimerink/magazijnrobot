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
		return new Dimension(250, 650);
	}
	
	int rectX = 20;
	int rectY = 20;
	int rectXOffset = 100;
	int rectYOffset = 200;
	int lineX1 = rectX;
	int lineX2 = rectXOffset + 20;
	int lineY1 = rectYOffset + 20;
	int lineY2 = rectYOffset + 20;
	int currentBox = 0;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Font font = new Font("product", Font.PLAIN, 14);
		FontMetrics metrics = g.getFontMetrics();

		g.setFont(font);
		
		for (Box b : parent.boxList) {
			double boxCapacity = b.getBreedte() * b.getHoogte() * b.getLengte();		
			
			g.setColor(Color.red);
			Rectangle box = new Rectangle(rectX, rectY, rectXOffset, rectYOffset);
			g.drawRect(box.x, box.y, box.width, box.height);
			
			for(Product p : b.getPickList().getProducts()){
				double productCapacity = p.getHeight() * p.getLenght() * p.getWidth();
				System.out.println(productCapacity);
				System.out.println(boxCapacity);
				lineY1 = (int)(200 - 200*(productCapacity/boxCapacity));
				lineY2 = lineY1;
				
				g.setColor(Color.black);
				g.drawLine(lineX1, lineY1, lineX2, lineY2);
				g.drawString(p.getProductName(), lineX1 + 5, lineY1 + 10);
				
			}
			
			currentBox = currentBox + 1;
			
			if(currentBox > 0 && currentBox < 4){
				rectX =  rectX + rectXOffset + 10;
			} else if(currentBox == 4){
				rectX = 20;
				rectY = rectY + 220;
			} else {
				rectX = rectX + rectXOffset + 10;
			}
			
//			lineX1 = rectX;
//			lineX2 = rectXOffset + 20;
//			lineY1 = rectYOffset + 20;
//			lineY2 = rectYOffset + 20;
		}
	}
}
