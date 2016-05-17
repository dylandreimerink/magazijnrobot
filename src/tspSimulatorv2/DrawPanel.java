package tspSimulatorv2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private boolean showRaster;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int height = (int) (screenSize.getWidth() / 4) - 50;
	private int width = (int) (screenSize.getWidth() / 4) - 50;
	private String algoname;
	private Result resultaat;
	private int x;
	private int y;

	public DrawPanel(String algoname, Result resultaat, int hoogte, int breedte, boolean showRaster) {
		this.algoname = algoname;
		this.resultaat = resultaat;
		this.x = breedte;
		this.y = hoogte;

		this.showRaster = showRaster;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int afstandX = width / x;
		int afstandY = height / y;

		if (showRaster) {
			for (int j = 0; j <= height; j += afstandX) {
				g.drawLine(j, 0, j, height);
			}
			for (int j = 0; j <= width; j += afstandY) {
				g.drawLine(0, j, width, j);
			}
		}
		Location prevLocation = null;
		for (Location location : resultaat.getResult()) {
			if (prevLocation != null) {
				if (resultaat.isShowPointsonly() == false) {
					drawRoute(g, prevLocation.getLocationX(), prevLocation.getLocationY(), location.getLocationX(),
							location.getLocationY(), afstandX, afstandY);
				}
			}
			prevLocation = location;
		}
		int x = 1;
		for (Location location : resultaat.getResult()) {
			drawProduct(g, location.getLocationX(), location.getLocationY(), afstandX, afstandY, "Product " + x);
			x++;
		}
		g.drawString(algoname + "           afstand: " + resultaat.getAfstand() + "           tijd: "
				+ resultaat.getTime() + " ms", 0, height);
	}

	private void drawProduct(Graphics g, int x, int y, int afstandX, int afstandY, String product) {
		int breedteStip = (int) (screenSize.getWidth() / afstandX) / 4;
		x = afstandX * x;
		y = afstandY * y;
		double middleX = (afstandX / 2) - breedteStip / 2 + x;
		double middleY = (afstandY / 2) - breedteStip / 2 + y;
		int middleXInt = (int) middleX;
		int middleYInt = (int) middleY;
		g.setColor(Color.black);
		g.fillOval(middleXInt, middleYInt, breedteStip, breedteStip);
		g.setColor(Color.red);

		if (resultaat.isShowPointsonly() == false) {
			g.drawString(product, middleXInt + 15, middleYInt);
		}
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

	public void setResultaat(Result resultaat) {
		this.resultaat = resultaat;
	}

	public void setShowRaster(boolean showRaster) {
		this.showRaster = showRaster;
	}
	
	
}
