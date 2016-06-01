package asrsSystem;
/*
 * Authors: Richard en Steven, ICTM2A
 */
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import shared.*;
public class ChooseOrder {
	
	private String path;
	private ArrayList<shared.Product> productlist;
	ParseXML order;
	
	
	//gebruik deze methode om JFilechooser te openen, word via klasse parseXML doorgestuurd naar database,
	public void ChooseFile() {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("kies uw XML file");
	    chooser.addChoosableFileFilter(new FileNameExtensionFilter("xml Documents", "xml") );
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    File selectedFile = chooser.getSelectedFile();
	      this.path = selectedFile.getAbsolutePath();
	      Console.printLine("xml bestand geselecteerd. path: "+path);
	      Console.printLine("parsing xml file...");
	      order = new ParseXML(path);
	      this.productlist = order.getProductList();
	      System.out.println(productlist);
	      Console.printLine("parsing compleet! geselecteerde producten:" + productlist);
	      
	      
	      
	    } else {
	    	Console.printLine("geen order geselecteerd! selecteer een order om het programma te gebruiken!");
	    }
	    
	    
	}
	

	
	public String getKlantInfo() {
		return ""+order.getBestelling().getKlant().getVoorNaam()+" "+order.getBestelling().getKlant().getAchterNaam();
	}
	
	public String getAdres() {
		return ""+order.getBestelling().getKlant().getAdres();
	}
	
	public String getOrderNr() {
		return ""+order.getBestelling().getOrdernr();
	}
	//wanneer JFilechooser is aangeroepen en uitgevoerd kan je met deze getter de volledige productlijst meekrijgen
	public ArrayList<shared.Product> getProductList() {
		return productlist;
	}
	  
}	
