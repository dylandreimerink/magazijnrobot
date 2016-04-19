package tspSimulator;

//Authors: Jan Willem Alejandro Casteleijn & Henri van de Munt (ICTM2a)

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

import Functions.Database;

public class MainGUI extends JFrame implements ActionListener {


	public MainGUI() {
		setTitle("TSP Simulator");
		setSize(937, 1080);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addComponents();

		setVisible(true);
	}

	private void addComponents() {
		Algorithm1 algorithm1 = new Algorithm1("Brute force");
		Algorithm2 algorithm2 = new Algorithm2("First fit");
		Algorithm3 algorithm3 = new Algorithm3("unknown");
		setLayout(new GridLayout(1, 3));
		Draw algoritme1 = new Draw("Algoritme1", 0, 0);
		add(algoritme1);
		Draw algoritme2 = new Draw("Algoritme2", 0, 0);
		add(algoritme2);
		Draw algoritme3 = new Draw("Algoritme3", 0, 0);
		add(algoritme3);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == "") {

		}
	}
}
