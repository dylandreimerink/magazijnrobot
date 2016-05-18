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

import javax.swing.JProgressBar;

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
					int i = 0;
					for(Location l:list) {
						SendCommand("s;");
						sendCords(i);
						i++;
					}
					

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
	
	
	private void sendCords(int index) {
		int x = list.get(index).getLocationX();
		this.x = Integer.toString(x);
		int y = list.get(index).getLocationY();
		this.y = Integer.toString(y);
		SendCommand("o;");// o is to make clear you are sending coordinates, not a standard command
		
	}
	
	private void SendCommand(String command){

		    
		    try {
		    	
				OutputStream out = serialPort.getOutputStream();
				InputStream input = serialPort.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				
				
				boolean go = true;
				while(go){
					if(command != "o;") {
						
					out.write(command.getBytes());
					out.flush();

					
					} else if(command == "o;") {
						String tX = "X"+x+";";
						String tY = "Y"+y+";";
						out.write(tX.getBytes());
						out.write(tY.getBytes());
					}
					

					String line = "";
					if ((reader.ready()) && (line = reader.readLine()) != null)
					{
						if(line.contains("O")){
							go = false;
							//out.close();
							System.out.println("stopping");
						}

						if(line.contains("C")) {
							System.out.println("command done");
							out.close();
						}
						System.out.println(line);

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




