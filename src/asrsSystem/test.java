package asrsSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JComboBox;
import java.awt.BorderLayout;

public class test extends JDialog implements ActionListener {
	public test() {
		getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 13, 129, 22);
		getContentPane().add(comboBox);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
