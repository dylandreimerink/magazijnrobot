package asrsSystem;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console {
	
	JTextArea textArea;
	JPanel panel_3;
	public String console;
	
    public JPanel caller() {
    	
     
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		textArea = new JTextArea(7, 7);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea.setEditable(false);
		textArea.setColumns (5);
		textArea.setLineWrap (true);
		textArea.setWrapStyleWord (false); 
		console = "programma is succesvol opgestart";
		textArea.append(console);
		panel_3.add(scrollPane);
		
		return panel_3;
	}
}
