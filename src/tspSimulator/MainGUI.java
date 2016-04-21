
package tspSimulator;

/*
 * Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)
 */

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextArea;

import shared.Database;

public class MainGUI extends JFrame implements ActionListener {

	public MainGUI() {
		setTitle("TSP Simulator");
		setSize(937, 1080);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addComponents();

		setVisible(true);
	}

	private void addComponents() {
		Picklist picklist = new Picklist();
		System.out.println(picklist.toString());

		BruteForce bf = new BruteForce("BruteForce", picklist.getList());
		FirstFit ff = new FirstFit("First Fit", picklist.getList());
		Unknown onbekend = new Unknown("Onbekend", picklist.getList());
		setLayout(new GridLayout(1, 3));

		add(bf.getPanel());
		add(ff.getPanel());
		add(onbekend.getPanel());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == "") {

		}
	}
}
