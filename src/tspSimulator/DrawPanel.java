
package tspSimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import shared.Resultaat;

public class DrawPanel extends JPanel {

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int HEIGHT = (int) (screenSize.getWidth()/4) - 50;
	private int WIDTH = (int) (screenSize.getWidth()/4) - 50;
	private String algoname;
	private Resultaat resultaat;
	private int x;
	private int y;

	public DrawPanel(String algoname, Resultaat resultaat) {
		this.algoname = algoname;
		this.resultaat = resultaat;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.x = 10;
		this.y = 10;
		int afstandX = WIDTH / x;
		int afstandY = HEIGHT / y;

		for (int j = 0; j <= HEIGHT; j += afstandX) {
			g.drawLine(j, 0, j, HEIGHT);
		}
		for (int j = 0; j <= WIDTH; j += afstandY) {
			g.drawLine(0, j, WIDTH, j);
		}

		Location prevLocation = null;
		for (Location location : resultaat.getResult()) {
			if (prevLocation != null) {
				drawRoute(g, prevLocation.getLocationX(), prevLocation.getLocationY(), location.getLocationX(),
						location.getLocationY(), afstandX, afstandY);
			}
			prevLocation = location;
		}
		int x = 1;
		for (Location location : resultaat.getResult()) {
			drawProduct(g, location.getLocationX(), location.getLocationY(), afstandX, afstandY, "Product " + x);
			x++;
		}
		g.drawString(algoname, 0, HEIGHT);
	}

	private void drawProduct(Graphics g, int x, int y, int afstandX, int afstandY, String product) {
		x = afstandX * x;
		y = afstandY * y;
		double middleX = (afstandX / 2) - 7.5 + x;
		double middleY = (afstandY / 2) - 7.5 + y;
		int middleXInt = (int) middleX;
		int middleYInt = (int) middleY;
		g.setColor(Color.black);
		g.fillOval(middleXInt, middleYInt, 15, 15);
		g.setColor(Color.red);
		g.drawString(product, middleXInt + 15, middleYInt + 5);
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

	public void updateResultaat(Resultaat resultaat) {
		this.resultaat = resultaat;
	}

}
