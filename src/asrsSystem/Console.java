package asrsSystem;
/*
 * Authors:richard en Steven
 */
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
	private int counter = 1;
	private int counter1 = 0;
	
	public Console() {
	if(counter1 == 0) {
	textArea = new JTextArea(7, 7);
	}
	counter1++;
	textArea.setFont(new Font("Arial", Font.PLAIN, 12));
	textArea.setEditable(false);
	textArea.setColumns (5);
	textArea.setLineWrap (true);
	textArea.setWrapStyleWord (false); 
	}
    public JPanel console() {
    	
    	
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel_3.add(scrollPane);
		panel_3.add(scrollPane);
		return panel_3;
    	
	}
    
    public void printLine(String text) {
    	if(counter > 1){
    		textArea.append("\n");
    	}
    	textArea.append(counter + ": "+text);
    	counter++;
    }
    
}
