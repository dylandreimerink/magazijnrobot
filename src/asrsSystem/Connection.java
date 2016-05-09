package asrsSystem;
import java.io.OutputStream;
import java.util.Enumeration;

import gnu.io.*;

public class Connection {

	CommPortIdentifier info;
	String portName;
	SerialPort serialPort;
	
	public Connection(){
		Enumeration<CommPortIdentifier> ports = CommPortIdentifier.getPortIdentifiers();
		while (ports.hasMoreElements())
		{
			info = ports.nextElement();
			System.out.println(info.getName());
		}
		
		portName = info.getName();
		serialPort = null;
		
		
	}
	
	public void RunConnection() {
		try
		{
			CommPortIdentifier port = CommPortIdentifier.getPortIdentifier(portName);
			if(port.isCurrentlyOwned())
			{
				System.out.println("Error: Port is currently in use");
				return;
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
					
					int pin = 1;
					while(true)
					{
						System.out.println("Send command: " + Integer.toString(pin));
						
						String d = Integer.toString(pin);
						out.write(d.getBytes());
						
						pin++;
						if(pin>3)
						{
							pin = 1;
						}
						
						Thread.sleep(2000);
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
			}
		}
	}
}
