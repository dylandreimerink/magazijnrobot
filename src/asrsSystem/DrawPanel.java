package asrsSystem;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


public class DrawPanel extends JPanel {
	
	
	
	private int magazijnSize = 5;
	Dimension dPanelsize;
	private int WIDTH = 600;
	private int HEIGHT = 600;
	private boolean showRaster = true;
	
	public DrawPanel(JPanel drawer) {
		dPanelsize = drawer.getSize();
		
		
		//WIDTH = (int) dPanelsize.getWidth();
		//HEIGHT = WIDTH;
		
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println(WIDTH);
		System.out.println(HEIGHT);
		int afstandX = WIDTH / magazijnSize;
		int afstandY = HEIGHT / magazijnSize;
		System.out.println(afstandX);

		if(showRaster){
			for (int j = 0; j <= HEIGHT; j += afstandX) {
				g.drawLine(j, 0, j, HEIGHT);
			}
			for (int j = 0; j <= WIDTH; j += afstandY) {
				g.drawLine(0, j, WIDTH, j);
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

	private void drawRoute(Graphics g, int beginX, int beginY, int eindX, int eindY, int afstandX, int afstandY) {
		
		beginX = (int) ((int) beginX * afstandX + afstandX / 2);
		beginY = (int) (beginY * afstandY + afstandY / 2);
		eindX = (int) (eindX * afstandX + afstandX / 2);
		eindY = (int) (eindY * afstandY + afstandY / 2);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.setColor(Color.blue);
		g.drawLine(beginX, beginY, eindX, eindY);
		
	}
	*/

}
