package asrsSystem;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import gnu.io.*;
import tspSimulatorv2.Location;

public class Connection implements Runnable{

	CommPortIdentifier info;
	String portName;
	SerialPort serialPort;
	Thread t = new Thread(this);
	Console console = new Console();
	private boolean pressedDisconnect = false;
	private boolean pressedConnect = false;
	
	
	public void openConnection(){
		
		Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
		while (ports.hasMoreElements())
		{
			info = ports.nextElement();
        	console.printLine(info.getName());			
		}
		
		portName = info.getName();
		serialPort = null;
		start();
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
	        	console.printLine("Error: Port is currently in use");				
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
		        	console.printLine("Can't access serial port");					
				}
			}
		}
		catch(Exception ex)
		{
        	console.printLine(ex.getMessage());			
		}
		finally
		{
			if(serialPort!=null)
			{
	        	console.printLine("Close serial port");				
				serialPort.close();
				t.stop();
			}
		}
	}
	public void GetPositions(ArrayList<Location> Locaties){
		
	}
}



