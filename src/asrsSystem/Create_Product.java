package asrsSystem;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import shared.Database;

public class Create_Product extends JDialog implements ActionListener{
	
	private JLabel lblNaam;
	private JLabel lblDimensie;
	private JLabel lblCm1;
	private JLabel lblCm2;
	private JLabel lblCm3;
	private JLabel lblPositie;
	private JLabel lblX;
	private JLabel lblY;
	
	private JTextField Create_Naam;
	private JTextField txtHoogte;
	private JTextField txtLengte;
	private JTextField txtBreedte;
	private JTextField textField_X;
	private JTextField textField_Y;
	
	private JButton btnCancel;
	private JButton btnApply;
	
	private Database db;
	
	public Create_Product() {
				
		db = new Database();
        setTitle("Create");		
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		lblNaam = new JLabel("Naam:");
		lblNaam.setBounds(12, 13, 56, 16);
		getContentPane().add(lblNaam);
		
		Create_Naam = new JTextField();
		Create_Naam.setBounds(80, 10, 116, 22);
		getContentPane().add(Create_Naam);
		Create_Naam.setColumns(10);
		
		lblDimensie = new JLabel("Dimensie:");
		lblDimensie.setBounds(12, 42, 75, 16);
		getContentPane().add(lblDimensie);
		
		txtHoogte = new JTextField();
		txtHoogte.setText("Hoogte");
		txtHoogte.setBounds(80, 39, 82, 22);
		getContentPane().add(txtHoogte);
		txtHoogte.setColumns(10);
		
		lblCm1 = new JLabel("cm");
		lblCm1.setBounds(174, 42, 56, 16);
		getContentPane().add(lblCm1);
		
		txtLengte = new JTextField();
		txtLengte.setText("Lengte");
		txtLengte.setBounds(80, 71, 82, 22);
		getContentPane().add(txtLengte);
		txtLengte.setColumns(10);
		
		lblCm2 = new JLabel("cm");
		lblCm2.setBounds(174, 71, 56, 16);
		getContentPane().add(lblCm2);
		
		txtBreedte = new JTextField();
		txtBreedte.setText("Breedte");
		txtBreedte.setColumns(10);
		txtBreedte.setBounds(80, 101, 82, 22);
		getContentPane().add(txtBreedte);
		
		lblCm3 = new JLabel("cm");
		lblCm3.setBounds(174, 104, 56, 16);
		getContentPane().add(lblCm3);
		
		lblPositie = new JLabel("Positie:");
		lblPositie.setBounds(12, 136, 75, 16);
		getContentPane().add(lblPositie);
		
		lblX = new JLabel("X:");
		lblX.setBounds(80, 136, 56, 16);
		getContentPane().add(lblX);
		
		textField_X = new JTextField();
		textField_X.setBounds(99, 133, 63, 22);
		getContentPane().add(textField_X);
		textField_X.setColumns(10);
		
		lblY = new JLabel("Y:");
		lblY.setBounds(80, 165, 56, 16);
		getContentPane().add(lblY);
		
		textField_Y = new JTextField();
		textField_Y.setColumns(10);
		textField_Y.setBounds(99, 165, 63, 22);
		getContentPane().add(textField_Y);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(12, 194, 85, 25);
		getContentPane().add(btnCancel);
		
		btnApply = new JButton("Apply");
		btnApply.setBounds(109, 194, 87, 25);
		getContentPane().add(btnApply);

        btnCancel.addActionListener(this);
        btnApply.addActionListener(this);		
		
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   
        pack();
		this.setSize(new Dimension(400,300));
		this.setResizable(false);
        this.setVisible(true);
        
	}

	public void actionPerformed(ActionEvent e) {
	
        if (e.getSource() == btnApply) {
        	String naam = Create_Naam.getText();
        	try{
        	int hoogte = Integer.parseInt(txtHoogte.getText());
        	int lengte = Integer.parseInt(txtLengte.getText());
        	int breedte = Integer.parseInt(txtBreedte.getText());
        	int x = Integer.parseInt(textField_X.getText());
        	int y = Integer.parseInt(textField_Y.getText());
        	db.Create_Product(naam, hoogte, lengte, breedte, x, y);
        	JOptionPane.showMessageDialog(null, "Product is saved");        	
        	}
        	catch(NumberFormatException ne){
        		System.out.println("Dimensie & positie moeten getallen zijn");
        	}
        	
        }
        if (e.getSource() == btnCancel) {
        }
		
	}

}
