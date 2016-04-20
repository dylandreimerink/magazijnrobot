package shared;

/*
 * Author: Henri van de Munt
 */
import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ParseXML {

	private Klant klant;
	private Bestelling bestelling;
	
	public ParseXML(String path) {
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
					 String firstname = eElementKlant.getElementsByTagName("voornaam").item(0).getTextContent();
					 String surname = eElementKlant.getElementsByTagName("achternaam").item(0).getTextContent();
					 String address = eElementKlant.getElementsByTagName("adres").item(0).getTextContent();
					 String postcode = eElementKlant.getElementsByTagName("postcode").item(0).getTextContent();
					 String plaats = eElementKlant.getElementsByTagName("plaats").item(0).getTextContent();
					 this.klant = new Klant(firstname ,surname , address, postcode, plaats);
				}
			}
			NodeList nListArtiekelnr = doc.getElementsByTagName("artikelnr");
			this.bestelling = new Bestelling(this.klant);
			for (int tempArtiekelnr = 0; tempArtiekelnr < nListArtiekelnr.getLength(); tempArtiekelnr++) {
				System.out.println(
						"artikelnr: " + doc.getElementsByTagName("artikelnr").item(tempArtiekelnr).getTextContent());
				Product product = new Product(
						Integer.parseInt(doc.getElementsByTagName("artikelnr").item(tempArtiekelnr).getTextContent()));
				this.bestelling.addProduct(product);
			}
		} catch (Exception f) {
			System.out.println("Wrong syntax xml file");
		}
	}

	public void setKlant(Klant klant) {
		this.klant = klant;
	}

	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	}
	
	

}
