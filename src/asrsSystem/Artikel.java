package asrsSystem;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class Artikel extends JDialog implements ActionListener {
	//test
	private JButton create;
	private JButton edit;
	private JButton delete;
	
	public Artikel(JFrame frame){		
		create = new JButton("Create Artikel");
		edit = new JButton("Edit Artikel");
		delete = new JButton ("Delete Artikel");
		add(create);
		add(edit);
		add(delete);
		create.addActionListener(this);
		edit.addActionListener(this);
		delete.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == create){
			//Vervolg
		}
		else if (e.getSource() == edit){
			//Vervolg
		}
		else if (e.getSource() == delete){
			//Vervolg
		}
	}

}
