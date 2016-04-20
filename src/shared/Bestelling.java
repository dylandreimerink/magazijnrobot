package shared;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 * 
 */

public class Bestelling {
	private Klant klant;
	private ArrayList<Product> productList;

	public Bestelling(Klant klant) {
		productList = new ArrayList();
		this.klant = klant;
		
	}
	
	public Klant getKlant(){
		return klant;
	}
	
	public void addProduct(Product product) {
		productList.add(product);
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void printPakbon() {
		System.out.println("####################### PAKLIJST #######################");
		System.out.println(
				"Aan: " + klant.getVoorNaam() + klant.getAchterNaam() + ", " + klant.getAdres() + ", " + klant.getPostcode() + klant.getPlaats());
		System.out.println("Inhoud doos:");
		for (Product product : productList) {
		    System.out.println(product.getProductName());
		}
	}
}
