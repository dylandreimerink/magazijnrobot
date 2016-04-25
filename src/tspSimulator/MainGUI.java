
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

	private Picklist picklist;
	private Picklist picklist1;
	private Picklist picklist2;
	private JButton test;
	private JButton test2;

	private BruteForce bf;
	private FirstFit ff;
	private FirstFitDecreasing ffd;

	public MainGUI() {
		setTitle("TSP Simulator");
		setSize(937, 1080);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addComponents();

		setVisible(true);
	}

	private void addComponents() {
		picklist = new Picklist();
		picklist1 = new Picklist();
		picklist2 = new Picklist();
		//System.out.println(picklist.toString());


		bf = new BruteForce("BruteForce", picklist.getList());
		ff = new FirstFit("First Fit", picklist.getList());
		ffd = new FirstFitDecreasing("Onbekend", picklist.getList());
		setLayout(new GridLayout(1, 4, 0, 0));
		
		
		

		add(bf.getPanel());
		add(ff.getPanel());
		add(ffd.getPanel());

		test = new JButton("Test");
		test.addActionListener(this);
		add(test);
		
		test2 = new JButton("Test2");
		test2.addActionListener(this);
		add(test2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == test) {
			this.picklist.generateNewPicklist();
			System.out.println(this.picklist.toString());
			bf.updateResultaat(this.picklist.getList());
			ff.updateResultaat(this.picklist.getList());
			ffd.updateResultaat(this.picklist.getList());
			repaint();
		}
		if(e.getSource() == test2){
			ffd.calculateRoute();
			//ffd.updateResultaat(this.picklist.getList());
			repaint();
		}
	}
}
