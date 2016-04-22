package asrsSystem;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import shared.*
public class Choosefile {
	private String path;
	private Bestelling bestelling;
	
	public String ChooseFile() {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("kies uw XML file");
	    chooser.addChoosableFileFilter(new FileNameExtensionFilter("xml Documents", "xml") );
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    File selectedFile = chooser.getSelectedFile();
	      return("" + selectedFile.getAbsolutePath());
	      this.path = selectedFile.getAbsolutePath();
	      ParseXML order = new ParseXML(path);
	      bestelling = order.getBestelling();
	      System.out.println(bestelling);
	    } else {
	      return("No Selection ");
	    }
	    
	  }
}
