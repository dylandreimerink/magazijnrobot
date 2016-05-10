package asrsSystem;
/*
 * Authors: richard en steven
 */
import shared.Database;

public class main {
	
	public static void main(String args[]){
	gui scherm = new gui();	
	Product_Location location = new Product_Location();	
	location.start();
	Database db = new Database();
	Thread t = new Thread(db);
	t.start();
	}
}
