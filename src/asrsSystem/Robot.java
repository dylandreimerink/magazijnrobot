package asrsSystem;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Enumeration;

import gnu.io.*;

public class Robot implements Runnable{

	CommPortIdentifier info;
	String portName;
	SerialPort serialPort;
	Thread t = new Thread(this);
	private boolean pressedDisconnect = false;
	private boolean pressedConnect = false;
	private ArrayList <Location> list;
	private String x;
	private String y;
	
	public void openConnection(ArrayList <Location> list){
		
		this.list = list;
		Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
		while (ports.hasMoreElements())
		{
			info = ports.nextElement();
        	System.out.println("arduino poort: "+info.getName());			
		}
		
		portName = info.getName();
		serialPort = null;
		
		}
	
	public void start(){
		t.start();
	}
	
	public void setpressedDisconnect(boolean value) {
		this.pressedDisconnect = value;
	}
	
	public void run() {
		try
		{
			CommPortIdentifier port = CommPortIdentifier.getPortIdentifier(portName);
			if(port.isCurrentlyOwned())
			{
	        	System.out.println("Error: Port is currently in use");		
	 
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
					SendCordinates(list);
//					int pin = 1;
//					while(true)
//					{
//						if (pressedDisconnect == false) {
//			        	console.printLine("Send command: " + Integer.toString(pin));						
//						String d = Integer.toString(pin);
//						out.write(d.getBytes());
//						
//						pin++;
//						if(pin>3)
//						{
//							pin = 1;
//						}
//						
//						t.sleep(150);
//						} 
//					else if (pressedDisconnect == true) {
//							break;
//						}
//						
//					}
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
				serialPort.close();
				t.stop();
			}
		}
	}
	
	/*private void openSerialPort(){
		if(serialPort instanceof SerialPort)
			return;
		
		try {
			CommPortIdentifier port = CommPortIdentifier.getPortIdentifier(portName);
			if(port.isCurrentlyOwned())
			{
				System.out.println("Error: Port is currently in use");				
			}
			if(port!=null)
			{
				CommPort commPort = port.open(portName, 2000);
				
				if(commPort instanceof SerialPort)
				{
					serialPort = (SerialPort) commPort;
					serialPort.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
					serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
				}
			}
		} catch (NoSuchPortException | PortInUseException | UnsupportedCommOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public void SendCordinates(ArrayList <Location> list){
		for(int i = 0; i < list.size(); i++) {   
		    int intx = list.get(i).getLocationX();
		    int inty = list.get(i).getLocationY();
		    System.out.println("intX: " + intx);
		    System.out.println("intY: " + inty);		    
		    x = Integer.toString(intx);
		    y = Integer.toString(inty);		    
		    System.out.println("X: " + x);
		    System.out.println("Y: " + y);
		    
		    try {
		    	
				OutputStream out = serialPort.getOutputStream();
				InputStream input = serialPort.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				String xString = "X" + x + ";";
				String yString = "Y" + y + ";";
				String zString = "Z1;";
				
				boolean go = true;
				while(go){
					out.write(xString.getBytes());
					out.write(yString.getBytes());
					out.write(zString.getBytes());
					out.flush();
					String line = "";
					if ((reader.ready()) && (line = reader.readLine()) != null)
					{
						if(line.contains("OK")){
							go = false;
							out.close();
							System.out.println("stopping");
						}
						System.out.println("Line:"+line);
					}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				t.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 		
	}
}



