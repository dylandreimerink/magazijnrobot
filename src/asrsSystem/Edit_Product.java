package asrsSystem;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
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
	
	private JTextField textField;
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
	private int productlengte;
	private JComboBox product;
	
	public Edit_Product() {
		
        setTitle("Edit");		
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		db = new Database();
		
		try {
			producten = db.Get_Productnames();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to connect productarray to array in db");
		}

//		System.out.println(Arrays.toString(producten));
		
		productlengte = producten.length;
		
		product = new JComboBox(producten);		
		product.addActionListener(this);
		product.setBounds(12, 13, 129, 22);
		getContentPane().add(product);
		
		lblNaam = new JLabel("Naam:");
		lblNaam.setBounds(12, 42, 56, 16);
		getContentPane().add(lblNaam);
		
		textField = new JTextField();
		textField.setBounds(85, 39, 116, 22);
		getContentPane().add(textField);
		textField.setColumns(10);
		
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
		
		lblCm1 = new JLabel("cm");
		lblCm1.setBounds(204, 71, 56, 16);
		getContentPane().add(lblCm1);
		
		lblCm2 = new JLabel("cm");
		lblCm2.setBounds(204, 103, 56, 16);
		getContentPane().add(lblCm2);
		
		lblCm3 = new JLabel("cm");
		lblCm3.setBounds(204, 138, 56, 16);
		getContentPane().add(lblCm3);
		
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
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(36, 202, 75, 25);
		getContentPane().add(btnCancel);
		
		btnApply = new JButton("Apply");
		btnApply.setBounds(133, 202, 68, 25);
		getContentPane().add(btnApply);
		
		button = new JButton("New button");
		button.setBounds(0, 0, 0, 0);
		getContentPane().add(button);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(228, 202, 68, 25);
		getContentPane().add(btnDelete);
		
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);   
        pack();
		this.setSize(new Dimension(400,300));
		this.setResizable(false);
        this.setVisible(true);		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
