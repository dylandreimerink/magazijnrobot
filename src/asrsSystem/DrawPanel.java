package asrsSystem;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.Product;


public class DrawPanel extends JPanel {
	
	
	
	private BufferedImage image;
	
	private int magazijnSize = 5;
	private int WIDTH = 650;
	private int HEIGHT = 650;
	private ArrayList<Location> route;
	private	ArrayList<Location> prodLoc;
	private ArrayList<Product> product;
	JLabel JLRoute;
	public boolean drawRoute = false;
	private int afstandX;
	private int afstandY;
	
	
	public DrawPanel() {
	       try {                
	           image = ImageIO.read(new File("src/img/crate.png"));
	        } catch (IOException ex) {
	             // handle exception...
	        }
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		//System.out.println(WIDTH);
		//System.out.println(HEIGHT);
		this.afstandX = (WIDTH / magazijnSize);
		this.afstandY = (HEIGHT / magazijnSize);
		//System.out.println(afstandX);

		
			for (int j = 0; j <= HEIGHT; j += afstandX) {
				g.drawLine(j, 0, j, HEIGHT);
				//System.out.println("testheigth");
			}
			for (int j = 0; j <= WIDTH; j += afstandY) {
				g.drawLine(0, j, WIDTH, j);
				//System.out.println("testwidth");
			}
		if(drawRoute == true) {
			int index = 0;
			int maxindex = route.size();
			
			for(Location loc: route) {
				if(index < maxindex-1){
					System.out.println(route);
					System.out.println("maxindex: "+maxindex);
				
					int beginX = route.get(index).getLocationX();
					int beginY = route.get(index).getLocationY();
					index++;
					int eindX = route.get(index).getLocationX();
					int eindY = route.get(index).getLocationY();
					drawRoute(g,beginX,beginY,eindX,eindY,afstandX,afstandY);
				}
			}
			drawProduct(g);
		}
		
	}
	

	private void drawProduct(Graphics g) {
		int index = 0;
		for(Product l:product) {
			int x = product.get(index).getLocationX();
			int y = product.get(index).getLocationY();
			
			int dX = (afstandX*x) - (afstandX/2);
			int dY = (afstandY*y) - (afstandY/2);
			g.drawImage(image, dX-64, dY-64, null);
			g.setColor(Color.DARK_GRAY);
			g.fillOval(dX-8,dY-8,18,18);
			g.setColor(Color.blue);
			g.drawString(product.get(index).getProductName(),dX-18,dY-18);
			index++;
			
		}
		int x = 6;
		int y = 5;
		
		int dX = (afstandX*x) - (afstandX/2);
		int dY = (afstandY*y) - (afstandY/2);
		g.setColor(Color.DARK_GRAY);
		g.fillOval(dX-8,dY-8,18,18);
		g.setColor(Color.RED);
		g.drawString("Lopende band",dX+18,dY);
	}
	
	private void drawRoute(Graphics g, int beginX, int beginY, int eindX, int eindY, int afstandX, int afstandY) {
		
		int bX = (afstandX*beginX) - (afstandX/2);
		int bY = (afstandY*beginY) - (afstandY/2);
		int eX = (afstandX*eindX) - (afstandX/2);
		int eY = (afstandY*eindY) - (afstandY/2);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.setColor(Color.DARK_GRAY);
		g.drawLine(bX, bY, eX, eY);
		System.out.println(beginX+","+beginY+"|"+eindX+","+eindY);
		
		
	}
	
	public void setResult(ArrayList<Location> route,ArrayList<Location> productLoc, ArrayList<Product> productlist) {
		drawRoute = true;
		this.route = route;
		prodLoc = productLoc;
		this.product = productlist;
		repaint();
	}
	
	public ArrayList<Location> getRoute() {
		return route;
	}
	
	public int getAfstandX() {
		return afstandX;
	}
	
	public int getAfstandY() {
		return afstandY;
	}

}
