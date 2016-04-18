import java.util.ArrayList;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 * 
 */

public class Bestelling {
	private int ordernumber;
	private String date, firstname, surname, address, postcode;
	private ArrayList<Product> productList;

	public Bestelling(int ordernumber, String date, String firstname, String surname, String address, String postcode) {
		this.ordernumber = ordernumber;
		this.date = date;
		this.firstname = firstname;
		this.surname = surname;
		this.address = address;
		this.postcode = postcode;

		productList = new ArrayList();
	}

	public void addOrder(Product product) {
		productList.add(product);
	}
	
	

	@Override
	public String toString() {
		return "Bestelling [ordernumber=" + ordernumber + ", date=" + date + ", firstname=" + firstname + ", surname="
				+ surname + ", address=" + address + ", postcode=" + postcode + ", productList=" + productList + "]";
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

}
