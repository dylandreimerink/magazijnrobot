package asrsSystem;
/*
 * Authors: Richard en Steven, ICTM2A
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import gnu.io.*;
import shared.Product;

//Import de benodigde io's en utils.

public class Robot implements Runnable{

	CommPortIdentifier info;
	String portName;
	SerialPort serialPort;
	Thread t = new Thread(this);
	private ArrayList <Location> list;
	private ArrayList <Doos> boxlist;
	private String x;
	private String y;
	OutputStream out;
	InputStream input;
	Controller controller;
	
	public void openConnection(ArrayList <Location> list, ArrayList <Doos> boxlist, Controller controller){
		this.controller = controller;
		this.boxlist = boxlist;
		this.list = list;
		Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
		while (ports.hasMoreElements())
		{
			info = ports.nextElement();
        	System.out.println("arduino poort: "+info.getName());			
		}
		
		if(info.getName()== null){
			System.out.println("Geen poort aangesloten.");
		}
		else{
			portName = info.getName();			
		}
		// Haalt de COM poort op en zet het in de variabele portName.
		serialPort = null;
		
		}
	
	public void start(){
		System.out.println(t.getState());
		if(t.getState() != Thread.State.NEW){
			t.destroy();
			t = new Thread(this);
		}
		t.start();
		//Start een nieuwe Thread.
	}
		
	public void run() {
		try
		{
			CommPortIdentifier port = CommPortIdentifier.getPortIdentifier(portName);
			if(port.isCurrentlyOwned())
			{
	        	System.out.println("Error: Port is currently in use");
	        	//Controleerd of de COM poort op dit moment in gebruik is.
	 
			}
			if(port!=null)
			{
				CommPort commPort = port.open(portName, 2000);

				if(commPort instanceof SerialPort)
				{
					serialPort = (SerialPort) commPort;
					serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
					serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
					
					OutputStream out = serialPort.getOutputStream();
					//de variabele out zorgt ervoor dat gegevens naar de arduino verstuurd kunnen worden.
					int i = 0;
					System.out.println("start sending arraylist");
					for(Location l:list) {
						int x = list.get(i).getLocationX();
						int y = list.get(i).getLocationY();
						//Zet de coördinaten x & y in de juiste variabelen per product.
						for(Doos d : boxlist){
							for(Product p : d.getProductList()){
								System.out.println(p);
								if(p.getLocationX() == x && p.getLocationY() == y){
									if(d.doosId == 1){
										sendCords(x, y, true, true);
									}else{
										sendCords(x, y, true, false);
									}
								}
							}
						}
						
						i++;
					}
					sendCords(1, 5, false, true);

				}
				else
				{
		        	System.out.println("Can't access serial port");					
				}
			}
		}
		catch(Exception ex)
		{
        	System.out.println(ex.getMessage());			
		}
		finally
		{
			if(serialPort!=null)
			{
	        	System.out.println("Close serial port");	
	        	try {
					out.close();
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				serialPort.close();
				t.stop();
				//Stopt de Thread en sluit de serialPort.
			}
		}
	}
	
	private void sendCords(int x, int y, boolean pick, boolean left) {
		this.x = Integer.toString(x);
		this.y = Integer.toString(y);
		String line = "";
		
		if(x == 6 && y == 5){
			return;
		}
		
		System.out.println("X: " + this.x);
		System.out.println("Y: " + this.y);
		
	    try {
	    	
			out = serialPort.getOutputStream();
			input = serialPort.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
					
			boolean go = true;
			while(go){
				out.write('S');
				out.flush();
				System.out.println("sending S XY");
				if(hasOK(reader)){
					go = false;
				}
				//Versturen van het S commando totdat de Arduino een reactie geeft.
			}
			go = true;
			String tX = "X"+this.x + ";";
			String tY = "Y"+this.y + ";";
			while(go){
					out.write(tX.getBytes());
					out.flush();
					System.out.println("sending X");
					if(hasOK(reader)){
						go = false;
					}
					//Verstuurd de X coördinaten.
			}
			go = true;
			
			while(go){
					out.write(tY.getBytes());
					System.out.println("sending Y");
					out.flush();
					if(hasOK(reader)){
						go = false;
					}
					//Verstuurd de Y coördinaten.
			}
			
			go = true;
			
			while(go){
				if ((reader.ready()) && (line = reader.readLine()) != null)
				{
					if(line.contains("C")) {
						System.out.println("command done");
						controller.updateRobotLocation();
						
						go = false;
						//Als commando C wordt verstuurd rijdt de robot terug naar zijn beginpunt, en wordt
						// de visuele robot locatie geupdatet.
					}
				}
			}

			if(pick){
				go = true;
				
				while(go){
					out.write('S');
					out.flush();
					System.out.println("sending S BPP");
					if(hasOK(reader)){
						go = false;
					}
				}
				
				go = true;
				
				while(go){
					char dir;
					if(left){
						dir = 'L';
					}else{
						dir = 'R';
					}
					
					out.write(dir);
					out.flush();
					System.out.println("sending " + dir);
					if(hasOK(reader)){
						go = false;
					}
				}
				
				go = true;
				
				while(go){
					if ((reader.ready()) && (line = reader.readLine()) != null)
					{
						if(line.contains("C")) {
							System.out.println("command done");
							//out.close();
							go = false;
						}
					}
				}
				
				go = true;
				
				while(go){
					out.write('S');
					out.flush();
					System.out.println("sending S P");
					if(hasOK(reader)){
						go = false;
					}
				}
				
				go = true;
				
				while(go){
					out.write('P');
					out.flush();
					System.out.println("sending P");
					if(hasOK(reader)){
						go = false;
					}
				}
				
				go = true;
				
				while(go){
					if ((reader.ready()) && (line = reader.readLine()) != null)
					{
						if(line.contains("C")) {
							System.out.println("command done");
							//out.close();
							go = false;
						}
					}
				}
				
				go = true;
				
				while(go){
					out.write('B');
					out.flush();
					System.out.println("sending B");
					if(hasOK(reader)){
						go = false;
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	    try {
			t.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	} 
	boolean hasOK(BufferedReader reader) throws IOException{
		String line;
		if ((reader.ready()))
		{
			System.out.println("Ready");
			line = reader.readLine();
			System.out.println(line);
			if(line.contains("O")){
				System.out.println(line);
				System.out.println("OK received");
				return true;
			}
		}
		return false;
	}
}



