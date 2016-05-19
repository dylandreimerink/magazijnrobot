package tspSimulatorv2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class NumberOfProductsDialoog extends JDialog implements ActionListener {
	
	private TSPController controller;
	
	private boolean isOk;

	private JTextField numberOfProducts;

	private JButton annuleren;
	private JButton ok;

	public NumberOfProductsDialoog(JFrame frame, TSPController controller) {
		super(frame, true);
		this.controller = controller;
		setTitle("Selecteer het aantal producten");
		setSize(400, 100);
		setLayout(new FlowLayout());

		numberOfProducts = new JTextField(7);
		add(numberOfProducts);

		annuleren = new JButton("annuleren");
		annuleren.addActionListener(this);
		add(annuleren);

		ok = new JButton("Opslaan");
		ok.addActionListener(this);
		add(ok);
		setVisible(true);
	}

	public int getAantal() {
		if (numberOfProducts.getText() != null && !numberOfProducts.getText().equals("")) {
			return Integer.parseInt(numberOfProducts.getText().toString());
		} else {
			return 0;
		}
	}
	

	public boolean isOk() {
		return isOk;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == ok) {
			if (this.getAantal() == 0) {
				JOptionPane.showMessageDialog(this, "Je moet alle gegevens goed invullen!!");
			} else if (this.getAantal() > (controller.getHEIGHT() * controller.getWIDTH())){
				JOptionPane.showMessageDialog(this, "Het magazijn is " + controller.getHEIGHT() + " bij " + controller.getWIDTH()+ " groot,  er kunnn dus nooit meer dan " + controller.getHEIGHT() * controller.getWIDTH() + " producten ingevoerd worden!");
			} else {
				isOk = true; 
				setVisible(false);
			}
		} else {
			isOk = false;
			setVisible(false);
		}

	}
}
