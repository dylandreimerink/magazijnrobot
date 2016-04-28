package asrsSystem;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import shared.Database;

public class Edit_Product extends JDialog implements ActionListener{

	private JLabel lblNaam;
	private JLabel lblDimensie;
	private JLabel lblCm1;
	private JLabel lblCm2;
	private JLabel lblCm3;
	private JLabel lblPlaats;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel combobox;
	
	private JTextField naam;
	private JTextField txtHoogte;
	private JTextField txtLengte;
	private JTextField txtBreedte;
	private JTextField TextField_X;
	private JTextField TextField_Y;
	
	private JButton btnCancel;
	private JButton btnApply;
	private JButton button;
	private JButton btnDelete;
	
	private Database db;
	
	private String [] producten;
	private String productnaam;
	private int productId;
	private JComboBox product;
	
	Console console = new Console();

//	Voegt de benodigde buttons toe om het scherm te kunnen maken.	
	
	public Edit_Product() {
		
        setTitle("Edit");		
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		db = new Database();

//	Database klasse is nodig voor het wijzigen van producten en verwijderen.		
		
		try {
			producten = db.Get_Productnames();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			console.printLine("database error: productenlijst kon niet opgehaalt worden");
		}

//	Haalt alle productnamen op van de database	
		
		combobox = new JLabel("Select:");
		combobox.setBounds(12, 13, 56, 16);
		getContentPane().add(combobox);
		
		product = new JComboBox(producten);		
		product.setBounds(85, 13, 116, 22);
		getContentPane().add(product);

//	Productnamen worden toegevoegd aan de combobox.		
		
		lblNaam = new JLabel("Naam:");
		lblNaam.setBounds(12, 42, 56, 16);
		getContentPane().add(lblNaam);
		
		naam = new JTextField();
		naam.setBounds(85, 39, 116, 22);
		getContentPane().add(naam);
		naam.setColumns(10);

//	Mogelijkheid om het productnaam te wijzigen.			
		
		lblDimensie = new JLabel("Dimensie:");
		lblDimensie.setBounds(12, 71, 77, 16);
		getContentPane().add(lblDimensie);
		
		txtHoogte = new JTextField();
		txtHoogte.setText("Hoogte");
		txtHoogte.setBounds(85, 68, 116, 22);
		getContentPane().add(txtHoogte);
		txtHoogte.setColumns(10);
		
		txtLengte = new JTextField();
		txtLengte.setText("Lengte");
		txtLengte.setColumns(10);
		txtLengte.setBounds(85, 100, 116, 22);
		getContentPane().add(txtLengte);
		
		txtBreedte = new JTextField();
		txtBreedte.setText("Breedte");
		txtBreedte.setColumns(10);
		txtBreedte.setBounds(85, 135, 116, 22);
		getContentPane().add(txtBreedte);
		
		lblCm1 = new JLabel("Hoogte (cm)");
		lblCm1.setBounds(204, 71, 75, 16);
		getContentPane().add(lblCm1);
		
		lblCm2 = new JLabel("Lengte (cm)");
		lblCm2.setBounds(204, 103, 75, 16);
		getContentPane().add(lblCm2);
		
		lblCm3 = new JLabel("Breedte (cm)");
		lblCm3.setBounds(204, 138, 75, 16);
		getContentPane().add(lblCm3);

//	Mogelijkheid om dimensies van het product te wijzigen.		
		
		lblPlaats = new JLabel("Plaats:");
		lblPlaats.setBounds(12, 170, 56, 16);
		getContentPane().add(lblPlaats);
		
		lblX = new JLabel("X:");
		lblX.setBounds(85, 170, 26, 16);
		getContentPane().add(lblX);
		
		TextField_X = new JTextField();
		TextField_X.setBounds(106, 167, 35, 22);
		getContentPane().add(TextField_X);
		TextField_X.setColumns(10);
		
		lblY = new JLabel("Y:");
		lblY.setBounds(146, 170, 26, 16);
		getContentPane().add(lblY);
		
		TextField_Y = new JTextField();
		TextField_Y.setColumns(10);
		TextField_Y.setBounds(166, 167, 35, 22);
		getContentPane().add(TextField_Y);
	
//	Mogelijkheid om X & Y as te wijzigen.		
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(36, 202, 75, 25);
		getContentPane().add(btnCancel);
		
		btnApply = new JButton("Apply");
		btnApply.setBounds(133, 202, 68, 25);
		getContentPane().add(btnApply);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(228, 202, 75, 25);
		getContentPane().add(btnDelete);

//	Benodigde knoppen voor het annuleren, toepassen en verwijderen van producten.		
		
		product.addActionListener(this);
		btnApply.addActionListener(this);
		btnCancel.addActionListener(this);
		btnDelete.addActionListener(this);
		
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   
        pack();
		this.setSize(new Dimension(400,300));
		this.setResizable(false);
        this.setVisible(true);		
	}

	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == product) {       
//	Als een productnaam geselecteerd is in de combobox wordt onderstaand uitgevoerd.        	
        	
        	JComboBox jcmbType = (JComboBox) e.getSource();
        	String cmbType = (String) jcmbType.getSelectedItem();
        	db.Edit_Product(cmbType);

//	Haalt het geselecteerde productnaam op van de combobox en haalt vervolgens in de database klasse alle gegevens op.       	
        	
        	ArrayList<String> info = db.Edit_Product(cmbType);
        	
//	Zet de gegevens in een ArrayList.        	
        	
        	productId = Integer.parseInt(info.get(6));
 
//	Haalt het productId op van de ArrayList en zet het in de variabele productId.        	
        	
        	for(int i=0; i<info.size(); i++){

//	Een for loop om alle gegevens op de juiste plek te krijgen.        		
        		
        		String Productinformatie = info.get(i);

//	Gaat de ArrayList langs om alle informatie op de juiste plek te zetten.        		
        		
        		if(i == 0){
                	naam.setText(Productinformatie);
        		}
        		else if(i == 1){
        			txtBreedte.setText(Productinformatie);
        		}
        		else if(i == 2){
        			txtHoogte.setText(Productinformatie);
        		}
        		else if(i == 3){
        			txtLengte.setText(Productinformatie);
        		}
        		else if(i == 4){
        			TextField_X.setText(Productinformatie);
        		}
        		else if(i == 5){
        			TextField_Y.setText(Productinformatie);
        		}
//	Zet de tekst op de juiste plekken.        		
        	}
        }
        if(e.getSource() == btnApply){        	
// Wanneer er op de knop Apply is gedrukt wordt onderstaande uitgevoerd. 
        	
        	productnaam = naam.getText();
        	
        	try{
        	int hoogte = Integer.parseInt(txtHoogte.getText());
        	int lengte = Integer.parseInt(txtLengte.getText());
        	int breedte = Integer.parseInt(txtBreedte.getText());
        	int x = Integer.parseInt(TextField_X.getText());
        	int y = Integer.parseInt(TextField_Y.getText());
        	
// Productinformatie wordt in variabelen opgeslagen om vervolgens doorgegeven te worden aan de database klasse.        	
        	
        	db.update(productId, productnaam, hoogte, lengte, breedte, x, y);
        	
// Update de productinformatie in de database klasse.   
        	
        	console.printLine("Product "+productnaam+" is succesvol opgeslagen");
        	JOptionPane.showMessageDialog(null, "Product is opgeslagen");        	
        	}
        	catch(NumberFormatException ne){
        		console.printLine("dimensies en locaties moeten getallen zijn!");
        	}
//	Wanneer het gelukt is krijgt de gebruiker een saved melding en anders een foutmelding.        	
        }
        if (e.getSource() == btnCancel) {        	
//	Wanneer er op de knop Cancel wordt gedrukt wordt onderstaande uitgevoerd.    
        	
        	dispose();        	
        }
        if (e.getSource() == btnDelete){
        	db.delete(productId);
        	console.printLine("Product met productId "+productId+" is succesvol verwijderd!");
        	JOptionPane.showMessageDialog(null, "Product is verwijderd");        	
        }
	}
}
