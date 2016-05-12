package asrsSystem;
/*
 * Authors: richard en steven
 */
import shared.Database;

public class main {
	
	public static void main(String args[]){
	gui scherm = new gui();	
	//Location location = new Location();	
	//location.start();
	Database db = new Database();
	Thread t = new Thread(db);
	t.start();
	}
}
