package asrsSystem;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
/*
 * Authors: Richard en Steven, ICTM2A
 */
public class Console  {
	
	static JTextArea textAreaC;
	JPanel panel_3;
	private static int counter = 1;
	
	public Console() {
 
	}
	//deze methode maar één keer gebruiken in Gui.
    public JPanel console() {
    	textAreaC = new JTextArea(7, 7);
    	textAreaC.setFont(new Font("Arial", Font.PLAIN, 12));
    	textAreaC.setEditable(false);
    	textAreaC.setColumns (5);
    	textAreaC.setLineWrap (true);
    	textAreaC.setWrapStyleWord (false); 
    	textAreaC.setAutoscrolls(true);
    	
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(textAreaC);
		panel_3.add(scrollPane);
		panel_3.add(scrollPane);
		return panel_3;
    	
	}
    
    //gebruik deze methode om textlijnen te printen, indexcijfers worden automatisch toegevoegd
    public static void printLine(String text) {
    	if(counter > 1){
    		textAreaC.append("\n");
    	}
    	textAreaC.append(counter + ": "+text);
    	counter++;
    }
	public void printArray(ArrayList<Location> loclist) {
    	if(counter > 1){
    		textAreaC.append("\n");
    		
    	}
    	textAreaC.append(counter+": ");
    	String text = loclist.toString();
    	textAreaC.append(text);
    	
		
	}
	
	
	public void printArrayD(ArrayList<Doos> dooslist) {
    	if(counter > 1){
    		textAreaC.append("\n");
    		
    	}
    	textAreaC.append(counter+": ");
    	String text = dooslist.toString();
    	textAreaC.append(text);
	}
    
}
