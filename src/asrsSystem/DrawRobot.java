package asrsSystem;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class DrawRobot extends DrawPanel {
	private BufferedImage image;
	private Graphics g;
	private ArrayList<Location> route = null;
	private int afstandX = 0;
	private int afstandY = 0;
	private int index = 0;
	
	public void updateRobotImage() {
		int beginX;
		int beginY;
		int eindX;
		int eindY;
		if(index < route.size()-1) {
			beginX = route.get(index).getLocationX();
			beginY = route.get(index).getLocationY();
			eindX = route.get(index+1).getLocationX();
			eindY = route.get(index+1).getLocationY();
			
			int bX = (afstandX*beginX) - (afstandX/2);
			int bY = (afstandY*beginY) - (afstandY/2);
			int eX = (afstandX*eindX) - (afstandX/2);
			int eY = (afstandY*eindY) - (afstandY/2);
			g.drawImage(image, bX-32, bY-32,null);
		}
		
				
	}
	
	public void createImage(Graphics g) {
		afstandX = getAfstandX();
		afstandY = getAfstandY();
		route = getRoute();
		this.g = g;
	       try {                
	           image = ImageIO.read(new File("src/img/robot.png"));
	        } catch (IOException ex) {
	             // handle exception...
	        } 
	}
}
