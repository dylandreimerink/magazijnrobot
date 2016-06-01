package asrsSystem;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import shared.Product;
/*
 * Authors: Richard en Steven, ICTM2A
 */

public class DrawPanel extends JPanel {
	
	
	
	private BufferedImage image;
	private BufferedImage robotImage;
	private int magazijnSize = 5;
	private int WIDTH = 650;
	private int HEIGHT = 650;
	private ArrayList<Location> route;
	private ArrayList<Product> product;
	private JLabel JLRoute;
	public boolean drawRoute = false;
	private int afstandX;
	private int afstandY;
	private int counter = 0;
	private Warningfunctions warning = new Warningfunctions();
	
	
	
	
	public DrawPanel() {
			// in constructor images declareren, afstand tussen magazijnvakken declareren.
	       try {                
	           image = ImageIO.read(new File("src/img/crate.png"));
	           robotImage = ImageIO.read(new File("src/img/robot.png"));
	        } catch (IOException ex) {
	             warning.showCriticalError(null, "afbeelding lezen error! controleer de img folder!");
	        }
	       
			afstandX = (WIDTH / magazijnSize);
			afstandY = (HEIGHT / magazijnSize);
			
			
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);

			
			// afstandx en y staat voor de afstand tussen magazijncoordinaten, 
			//bv: tussen vakje 3 en 4 zitten 100 x coords
			for (int j = 0; j <= HEIGHT; j += afstandX) {
				g.drawLine(j, 0, j, HEIGHT);
				
			}
			for (int j = 0; j <= WIDTH; j += afstandY) {
				g.drawLine(0, j, WIDTH, j);
			
			}
		
			//route tekenen als drawroute == true, if not, overslaan
		if(drawRoute == true) {

			int index = 0;
			int maxindex = route.size();
			
			for(Location loc: route) {
				if(index < maxindex-1){

					
					int beginX = route.get(index).getLocationX();
					int beginY = route.get(index).getLocationY();
					index++;
					int eindX = route.get(index).getLocationX();
					int eindY = route.get(index).getLocationY();
					drawRoute(g,beginX,beginY,eindX,eindY,afstandX,afstandY);
				}
			}
			//als drawroute == true, dan word(en) ook de producten en robot getekend
			drawProduct(g);
			drawRobot(g, afstandX, afstandY, counter);
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
		
		
		
	}
	//drawrobotimage aan de hand van een gegeven counter.
	private void drawRobot(Graphics g, int afstandX, int afstandY, int counter) {
		if(counter == 0) {
			int x = route.get(counter).getLocationX();
			int y = route.get(counter).getLocationY();
			int bX = (afstandX*x) - (afstandX/2);
			int bY = (afstandY*y) - (afstandY/2);
			g.drawImage(robotImage, bX-32, bY-32, null);
			repaint();
		}else{
			int x = route.get(counter-1).getLocationX();
			int y = route.get(counter-1).getLocationY();
			int bX = (afstandX*x) - (afstandX/2);
			int bY = (afstandY*y) - (afstandY/2);
			g.drawImage(robotImage, bX-32, bY-32, null);
			repaint();
		}
			

	}
	
	// methode om resultaat van de gegenereerde route door te geven aan deze klasse.
	public void setResult(ArrayList<Location> route,ArrayList<Location> productLoc, ArrayList<Product> productlist) {
		drawRoute = true;
		this.route = route;
		this.product = productlist;
		repaint();
	}
	
	//word aangeroepen in controller, verhoogt de robotcounter wanneer deze word aangeroepen.
	//aan de hand van deze counter bepaald de robotImage bij welk product hij nu moet zijn.
	public void setRobotCounter() {
		if(counter < route.size()) {
			counter++;
		}else{
			counter = route.size();
		}
	}


}
