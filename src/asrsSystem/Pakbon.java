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
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel addComponents() {
		JPanel container = new JPanel();
		
		return container;
	}
	
	
	public void GenerateOrderInfo(ArrayList<shared.Product> productlist) {
		for(Product object: productlist) {
			
		}
	}
	
}
