package shared;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

import java.util.ArrayList;

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
		System.out.println("####################### PAKLIJST #######################");
		System.out.println("Aan: " + klant.getVoorNaam() + klant.getAchterNaam() + ", " + klant.getAdres() + ", "
				+ klant.getPostcode() + klant.getPlaats());
		System.out.println("Inhoud doos:");
		for (Product product : productList) {
			System.out.println(product.getProductName());
		}
	}
}
