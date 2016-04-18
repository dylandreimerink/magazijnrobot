package Functions;

import java.sql.*;

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
	static final String DB_URL = "jdbc:mysql://henrivandemunt.nl/bernar1q_magazijnrobot";	
	private String user;
	private String passwd;

	public Database() {
		this.user = "bernar1q_magrobo";
		this.passwd = "Welkom01";
	}

	public void run() {
		try {
			//connect();
			System.out.println("This database connection is a new Thread");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connect() {
		try {
			System.out.println("Connecting....");
			JOptionPane.showMessageDialog(null, "connection succesful");
			System.out.println("Connected.");
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(DB_URL,user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void select() throws Exception {
		connect(); // Don't know if the connection has to be established
					// everytime when a Query is executed??
		preparedStatement = connect.prepareStatement("SELECT * FROM product");
		resultSet = preparedStatement.executeQuery(); // read this variable to
														// determine the
														// location of
														// products(X, Y POS)
		System.out.println(resultSet);
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

	public void update() throws Exception {

	}

	public void delete() throws Exception {

	}

}
