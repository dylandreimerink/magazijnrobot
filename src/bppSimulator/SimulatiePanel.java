package bppSimulator;

import java.awt.Color;
import java.awt.Dimension;

//Authors: Mike Veltman & Dylan Reimerink (ICTM2a)

import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Functions.Product;

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
        
        for(Product p : parent.productList){
    	   g.fillRect( xOffset, productYOffset, productDimentions.width, productDimentions.height);
    	   g.setColor(Color.white);
    	   g.drawString("test", productTextOffset.width + xOffset + (productDimentions.width / 2), productTextOffset.height + productYOffset + (productDimentions.height / 2));
    	   g.setColor(Color.black);
    	   xOffset += productDimentions.width + 10;
        }
    }  
	
}
