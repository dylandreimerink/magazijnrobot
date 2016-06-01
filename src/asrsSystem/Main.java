package asrsSystem;
/*
 * Authors: richard en steven, ICTM2A
 */
import shared.Database;

public class Main {
	
	public static void main(String args[]){
		//gui aanroepen, database connectie in nieuwe thread starten
		new gui();	
		Database db = new Database();
		Thread t = new Thread(db);
		t.start();
	}
}
