package asrsSystem;
/*
 * Authors: richard en steven
 */

import java.util.ArrayList;

import javax.swing.JPanel;

import shared.*;
import javax.swing.JLabel;

public class Pakbon {
	ChooseOrder order = new ChooseOrder();
	Console console = new Console();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel addPakbonGui() {
		JPanel container = new JPanel();
		console.printLine("testing");
		return container;
	}
	
	
	public void GenerateOrderInfo(ArrayList<shared.Product> productlist) {
		for(Product object: productlist) {
			
		}
	}
	
}
