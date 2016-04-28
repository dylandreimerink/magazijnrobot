package shared;

/*
 * Authors: Jan Willem Alejandro Casteleijn (ICTM2a)
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Testing Code for the Database Connection
 * By Jan Willem 
 */

import javax.swing.JOptionPane;

public class Database implements Runnable {
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/kbs2";
	private String user;
	private String passwd;

	public Database() {
		this.user = "root";
		this.passwd = "";
	}

	public void run() {
		try {
			connect();
			System.out.println("This database connection is a new Thread");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			System.out.println("Connecting....");
			//JOptionPane.showMessageDialog(null, "connection succesful");
			System.out.println("Connected.");
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public ArrayList<Product> selectAll() throws Exception {
		connect(); // Don't know if the connection has to be established
					// everytime when a Query is executed??
		preparedStatement = connect.prepareStatement("SELECT * FROM product");
		ResultSet rs;

		rs = preparedStatement.executeQuery(); // read this variable to
												// determine the
												// location of
			
		ArrayList<Product> productList = new ArrayList<Product>();
		while (rs.next()) {
			productList.add(new Product(rs.getInt("productId"), rs.getString("productNaam"), rs.getInt("locatieX"), rs.getInt("locatieY"), rs.getInt("pHoogte"), rs.getInt("pBreedte"), rs.getInt("pLengte")));
		}
		return productList;

	}

	public void insert() throws Exception {
		connect(); // Don't know if the connection has to be established
					// everytime when a Query is executed??
		preparedStatement = connect.prepareStatement("insert into DBNAME values (default, ?, ?, ?, ? , ?, ?)");

		/*
		 * Example Statements
		 */
		preparedStatement.setString(1, "Test");
		preparedStatement.setString(2, "TestEmail");
		preparedStatement.setString(3, "TestWebpage");
		preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
		preparedStatement.setString(5, "TestSummary");
		preparedStatement.setString(6, "TestComment");
		preparedStatement.executeUpdate();
	}

	public void update(String naam, int hoogte, int lengte, int breedte, int x, int y){
	}

	public void delete() throws Exception {

	}
	
	public void Create_Product(String naam, int hoogte, int lengte , int breedte, int x, int y){
		try{
			connect();
			preparedStatement = connect.prepareStatement("INSERT INTO product (productNaam, pLengte, pBreedte, pHoogte, locatieX, locatieY, voorraad) VALUES ('"+naam+"', '"+lengte+"', '"+breedte+"', '"+hoogte+"', '"+x+"', '"+y+"', 2)");
			preparedStatement.executeUpdate();
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public String[] Get_Productnames() throws Exception{
			connect();
			preparedStatement = connect.prepareStatement("SELECT productNaam FROM product");
			ResultSet rs;

			rs = preparedStatement.executeQuery();
			
			ArrayList<String> ProductNamen = new ArrayList<String>();
			while (rs.next()) {
				ProductNamen.add(rs.getString("productNaam"));
			}
			String[] Producten = ProductNamen.toArray(new String[ProductNamen.size()]);
			return Producten;			
	}
	
	public ArrayList<String> Edit_Product(String productnaam){
		ArrayList<String> ProductInformatie = new ArrayList<String>();
		try{
			connect();
			preparedStatement = connect.prepareStatement("SELECT * FROM product WHERE productNaam = '"+productnaam+"'");
			ResultSet rs;
	
			rs = preparedStatement.executeQuery();
			
			
			while (rs.next()){
			ProductInformatie.add(rs.getString("productNaam"));
			ProductInformatie.add(rs.getString("pBreedte"));
			ProductInformatie.add(rs.getString("pHoogte"));
			ProductInformatie.add(rs.getString("pLengte"));
			ProductInformatie.add(rs.getString("locatieX"));
			ProductInformatie.add(rs.getString("locatieY"));
			ProductInformatie.add(rs.getString("productId"));
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		return ProductInformatie;		
	}

}