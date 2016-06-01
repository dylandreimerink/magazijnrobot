package shared;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

import java.util.ArrayList;

import asrsSystem.Console;

public class Bestelling {
	private Klant klant;
	private ArrayList<Product> productList;
	private int ordernummer;

	public Bestelling(Klant klant, int ordernummer) {
		productList = new ArrayList<Product>();
		this.klant = klant;
		this.ordernummer = ordernummer;

	}

	public Klant getKlant() {
		return klant;
	}

	public int getOrdernr() {
		return ordernummer;
	}

	public void addProduct(Product product) {
		productList.add(product);
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void printPakbon() {
		Console.printLine("####################### PAKLIJST #######################");
		Console.printLine("Aan: " + klant.getVoorNaam() + klant.getAchterNaam() + ", " + klant.getAdres() + ", "
				+ klant.getPostcode() + klant.getPlaats());
		Console.printLine("Inhoud doos:");
		for (Product product : productList) {
			Console.printLine(product.getProductName());
		}
	}
}
