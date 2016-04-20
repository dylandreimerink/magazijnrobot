package bppSimulator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;

//Authors: Mike Veltman & Dylan Reimerink (ICTM2a)

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;

import shared.Product;

public class SimulatiePanel extends JPanel {

	MainGUI parent;
	
	int productYOffset = 350;
	Dimension productDimentions = new Dimension(50, 50);
	Dimension productTextOffset = new Dimension(-10, 0);
	
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
        
        int xOffset = 0;
        Font font = new Font("product", Font.PLAIN , 14);
        FontMetrics metrics = g.getFontMetrics();
   
        g.setFont(font);

        for(Product p : parent.productList){
        	
        	Rectangle rect = new Rectangle(xOffset, productYOffset,  metrics.stringWidth(p.getProductName()) + 20, productDimentions.height);
        	
        	g.fillRect( rect.x, rect.y, rect.width, rect.height);
        	g.setColor(Color.white);
            int y = ((rect.height - metrics.getHeight()) / 2) - metrics.getAscent();
        	g.drawString(p.getProductName(), rect.x + 6, (int)(rect.y + y + (rect.getHeight()/2)));
        	g.setColor(Color.black);
        	xOffset += rect.width + 10;
        }
    }  
	
}
