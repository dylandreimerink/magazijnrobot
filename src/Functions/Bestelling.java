package Functions;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
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
	private int ordernumber;
	private String date, firstname, surname, address, postcode, plaats;
	private ArrayList<Product> productList;

	public Bestelling(String path) {
		productList = new ArrayList();
		try {
			File inputFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			System.out.println("Ordernummer: " + doc.getElementsByTagName("ordernummer").item(0).getTextContent());
			int ordernummer = Integer.parseInt(doc.getElementsByTagName("ordernummer").item(0).getTextContent());
			System.out.println("Datum: " + doc.getElementsByTagName("datum").item(0).getTextContent());
			String datum = doc.getElementsByTagName("datum").item(0).getTextContent();
			NodeList nListKlant = doc.getElementsByTagName("klant");
			for (int tempKlant = 0; tempKlant < nListKlant.getLength(); tempKlant++) {
				Node nNodeKlant = nListKlant.item(tempKlant);
				System.out.println("Current Element :" + nNodeKlant.getNodeName());
				if (nNodeKlant.getNodeType() == Node.ELEMENT_NODE) {
					Element eElementKlant = (Element) nNodeKlant;
					System.out.println(
							"Voornaam: " + eElementKlant.getElementsByTagName("voornaam").item(0).getTextContent());
					System.out.println(
							"Achternaam: " + eElementKlant.getElementsByTagName("achternaam").item(0).getTextContent());
					System.out
							.println("Adres: " + eElementKlant.getElementsByTagName("adres").item(0).getTextContent());
					System.out.println(
							"Postcode: " + eElementKlant.getElementsByTagName("postcode").item(0).getTextContent());
					System.out.println(
							"Plaats: " + eElementKlant.getElementsByTagName("plaats").item(0).getTextContent());
					this.firstname = eElementKlant.getElementsByTagName("voornaam").item(0).getTextContent();
					this.surname = eElementKlant.getElementsByTagName("achternaam").item(0).getTextContent();
					this.address = eElementKlant.getElementsByTagName("adres").item(0).getTextContent();
					this.postcode = eElementKlant.getElementsByTagName("postcode").item(0).getTextContent();
					this.plaats = eElementKlant.getElementsByTagName("plaats").item(0).getTextContent();
				}
			}
			NodeList nListArtiekelnr = doc.getElementsByTagName("artikelnr");

			for (int tempArtiekelnr = 0; tempArtiekelnr < nListArtiekelnr.getLength(); tempArtiekelnr++) {
				System.out.println(
						"artikelnr: " + doc.getElementsByTagName("artikelnr").item(tempArtiekelnr).getTextContent());
				Product product = new Product(
						Integer.parseInt(doc.getElementsByTagName("artikelnr").item(tempArtiekelnr).getTextContent()));
				productList.add(product);
			}
		} catch (Exception f) {
			System.out.println("Wrong syntax xml file");
		}
	}

	public void addProduct(Product product) {
		productList.add(product);
	}

	public int getOrdernumber() {
		return ordernumber;
	}

	public String getDate() {
		return date;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getSurname() {
		return surname;
	}

	public String getAddress() {
		return address;
	}

	public String getPostcode() {
		return postcode;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void printPakbon() {
		System.out.println("####################### PAKLIJST #######################");
		System.out.println(
				"Aan: " + this.firstname + this.surname + ", " + this.address + ", " + this.postcode + this.plaats);
		System.out.println("Inhoud doos:");
//		for (String item : someList) {
//		    System.out.println(item);
//		}
	}
}
