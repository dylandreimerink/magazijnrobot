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

public class Console  {
	
	static JTextArea textAreaC;
	JPanel panel_3;
	private static int counter = 1;
	
	public Console() {
		// in iedere nieuwe klasse waar consoleinfo gepritn kan worden moet deze constructor worden aangeroepen.
		// vervolgens kan je overal printen door objectnaam.printLine("String") te gebruiken
	}
    public JPanel console() {
    	textAreaC = new JTextArea(7, 7);
    	textAreaC.setFont(new Font("Arial", Font.PLAIN, 12));
    	textAreaC.setEditable(false);
    	textAreaC.setColumns (5);
    	textAreaC.setLineWrap (true);
    	textAreaC.setWrapStyleWord (false); 
    	
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(textAreaC);
		panel_3.add(scrollPane);
		panel_3.add(scrollPane);
		return panel_3;
    	
	}
    
    public static void printLine(String text) {
    	if(counter > 1){
    		textAreaC.append("\n");
    	}
    	textAreaC.append(counter + ": "+text);
    	counter++;
    }
    
}
