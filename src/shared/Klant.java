package shared;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

public class Klant {
	
	private String voorNaam;
	private String achterNaam;
	private String adres;
	private String postcode;
	private String plaats;
	
	public Klant(String voorNaam, String achterNaam, String adres, String postcode, String plaats){
		this.voorNaam = voorNaam;
		this.achterNaam = achterNaam;
		this.adres = adres;
		this.postcode = postcode;
		this.plaats = plaats;
	}

	public String getVoorNaam() {
		return voorNaam;
	}

	public void setVoorNaam(String voorNaam) {
		this.voorNaam = voorNaam;
	}

	public String getAchterNaam() {
		return achterNaam;
	}

	public void setAchterNaam(String achterNaam) {
		this.achterNaam = achterNaam;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}
	

}
