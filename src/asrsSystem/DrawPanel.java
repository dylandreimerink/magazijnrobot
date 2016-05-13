package asrsSystem;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DrawPanel extends JPanel {
	
	
	
	private int magazijnSize = 5;
	private int WIDTH = 650;
	private int HEIGHT = 650;
	public ArrayList<Location> route;
	JLabel JLRoute;
	public boolean drawRoute = false;
	
	public DrawPanel() {
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		//System.out.println(WIDTH);
		//System.out.println(HEIGHT);
		int afstandX = (WIDTH / magazijnSize);
		int afstandY = (HEIGHT / magazijnSize);
		//System.out.println(afstandX);

		
			for (int j = 0; j <= HEIGHT; j += afstandX) {
				g.drawLine(j, 0, j, HEIGHT);
				System.out.println("testheigth");
			}
			for (int j = 0; j <= WIDTH; j += afstandY) {
				g.drawLine(0, j, WIDTH, j);
				System.out.println("testwidth");
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
		}
		
	}
/*
	private void drawProduct(Graphics g, int x, int y, int afstandX, int afstandY, String product) {
		int breedteStip = (int) (drawPanel.getWidth() / afstandX) / 4;
		x = afstandX * x;
		y = afstandY * y;
		double middleX = (afstandX / 2) - breedteStip/2 + x;
		double middleY = (afstandY / 2) - breedteStip/2 + y;
		int middleXInt = (int) middleX;
		int middleYInt = (int) middleY;
		g.setColor(Color.black);
		g.fillOval(middleXInt, middleYInt, breedteStip, breedteStip);
		g.setColor(Color.red);
		g.drawString(product, middleXInt + 15, middleYInt);
		
	}
	*/

	private void drawRoute(Graphics g, int beginX, int beginY, int eindX, int eindY, int afstandX, int afstandY) {
		
		int bX = (afstandX*beginX) - (afstandX/2);
		int bY = (afstandY*beginY) - (afstandY/2);
		int eX = (afstandX*eindX) - (afstandX/2);
		int eY = (afstandY*eindY) - (afstandY/2);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.setColor(Color.blue);
		g.drawLine(bX, bY, eX, eY);
		System.out.println(beginX+","+beginY+"|"+eindX+","+eindY);
		
	}
	
	public void setResult(ArrayList<Location> route) {
		drawRoute = true;
		this.route = route;
		repaint();
	}
	

}
