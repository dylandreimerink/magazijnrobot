package asrsSystem;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Warningfunctions {

	public void showNullpointerWarning(JFrame frame) {
		JOptionPane.showMessageDialog(frame,
			    "Selecteer eerst een order om door te gaan! (CTRL+O)",
			    "Systeem informatie",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	public void showWrongInformation(JFrame frame, String message) {
		JOptionPane.showMessageDialog(frame,
			    message,
			    "Systeem informatie",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	public void showCriticalError(JFrame frame, String message) {
		JOptionPane.showMessageDialog(frame,
			    message,
			    "Systeem informatie",
			    JOptionPane.ERROR_MESSAGE);
	}
	
	public void showConfirmationMessage(JFrame frame, String message) {
		JOptionPane.showMessageDialog(frame,
			    message,
			    "Systeem informatie", 1);
	}
	
	public void showNoRobotMessage(JFrame frame) {
		JOptionPane.showMessageDialog(frame,
			    "Robot nog niet geinitialiseerd! maak eerst een connectie!",
			    "Systeem informatie", 
			    JOptionPane.WARNING_MESSAGE);
	}
	
}
