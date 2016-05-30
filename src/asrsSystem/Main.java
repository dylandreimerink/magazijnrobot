package asrsSystem;
/*
 * Authors: richard en steven
 */
import shared.Database;

public class Main {
	
	public static void main(String args[]){
		new gui();	
		Database db = new Database();
		Thread t = new Thread(db);
		t.start();
	}
}
