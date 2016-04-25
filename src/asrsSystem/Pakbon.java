package asrsSystem;
/*
 * Authors: richard en steven
 */

import java.util.ArrayList;

import javax.swing.JPanel;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import shared.*;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;



public class Pakbon {
	JLabel[] productInfo = new JLabel[0];
	ChooseOrder order = new ChooseOrder();
	JPanel container = new JPanel();
	int b=0;
	int row = 7;
	/**
	 * @wbp.parser.entryPoint
	 */
	public Pakbon(int i) { 
		
		container.setLayout(new MigLayout("", "[10px][]", "[10px][][][][][][][][]"));
		JLabel orderNr = new JLabel("Ordernummer:");
		JLabel kNaam = new JLabel("Naam:");
		JLabel kArdres = new JLabel("Adres:");
		JLabel kBestelling = new JLabel("Bestelling:");
		
		container.add(kBestelling, "cell 1 5");
		container.add(orderNr, "cell 1 1");
		container.add(kArdres, "cell 1 3");
		container.add(kNaam, "cell 1 2");
		
		
	}
	
	public Pakbon() {
		
	}
	
	public JPanel panel() {
		return container;
	}
	
	
	public void GenerateOrderInfo(ArrayList<shared.Product> productlist) {
		for(Product object: productlist) {
			int artikelnr = productlist.get(b).getProductId();
			b++;
			String artikelnrN = Integer.toString(artikelnr);
			
			JLabel Artikel = new JLabel(artikelnrN);
			container.add(Artikel, "cell 1 "+row);
			
			System.out.println(artikelnrN);
			row++;
		}
	}
	
}
