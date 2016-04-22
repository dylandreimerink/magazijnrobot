package asrsSystem;

import shared.Database;

public class main {
	Console console = new Console();
	public static void main(String args[]){
	gui scherm = new gui();	
	
	Database db = new Database();
	Thread t = new Thread(db);
	t.start();
	

	}
}
