package tspSimulator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private boolean showRaster;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int height = (int) (screenSize.getWidth() / 4) - 50;
	private int width = (int) (screenSize.getWidth() / 4) - 50;
	private String algorithm;
	private Result result;
	private int x;
	private int y;

	public DrawPanel(String algorithm, Result result, int height, int widht,
			boolean showRaster) {
		this.algorithm = algorithm;
		this.result = result;
		this.x = widht;
		this.y = height;

		this.showRaster = showRaster;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int distanceX = width / x;
		int distanceY = height / y;

		if (showRaster) {
			for (int j = 0; j <= height; j += distanceX) {
				g.drawLine(j, 0, j, height);
			}
			for (int j = 0; j <= width; j += distanceY) {
				g.drawLine(0, j, width, j);
			}
		}
		Location prevLocation = null;
		for (Location location : result.getResult()) {
			if (prevLocation != null) {
				if (result.isShowPointsonly() == false) {
					drawRoute(g, prevLocation.getLocationX(),
							prevLocation.getLocationY(),
							location.getLocationX(), location.getLocationY(),
							distanceX, distanceY);
				}
			}
			prevLocation = location;
		}
		int x = 1;
		for (Location location : result.getResult()) {
			drawProduct(g, location.getLocationX(), location.getLocationY(),
					distanceX, distanceY, "Product " + x);
			x++;
		}
		g.drawString(algorithm + "           afstand: " + result.getAfstand()
				+ "           tijd: " + result.getTime() + " ms", 0, height);
	}

	private void drawProduct(Graphics g, int x, int y, int distanceX,
			int distanceY, String product) {
		int widthStip = (int) (screenSize.getWidth() / distanceX) / 4;
		x = distanceX * x;
		y = distanceY * y;
		double middleX = (distanceX / 2) - widthStip / 2 + x;
		double middleY = (distanceY / 2) - widthStip / 2 + y;
		int middleXInt = (int) middleX;
		int middleYInt = (int) middleY;
		g.setColor(Color.black);
		g.fillOval(middleXInt, middleYInt, widthStip, widthStip);
		g.setColor(Color.red);

		if (result.isShowPointsonly() == false) {
			g.drawString(product, middleXInt + 15, middleYInt);
		}
	}

	private void drawRoute(Graphics g, int beginX, int beginY, int eindX,
			int eindY, int distanceX, int distanceY) {
		beginX = (int) ((int) beginX * distanceX + distanceX / 2);
		beginY = (int) (beginY * distanceY + distanceY / 2);
		eindX = (int) (eindX * distanceX + distanceX / 2);
		eindY = (int) (eindY * distanceY + distanceY / 2);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));
		g.setColor(Color.blue);
		g.drawLine(beginX, beginY, eindX, eindY);
	}

	public void setResultaat(Result result) {
		this.result = result;
	}

	public void setShowRaster(boolean showRaster) {
		this.showRaster = showRaster;
	}

}
