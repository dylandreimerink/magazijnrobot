
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
	private JButton nieuwePicklist;
	private JButton testAlgorithm;

	private BruteForce bf;
	private NearestNeighbourhaha ff;
	private NearestNeighbour ffd;

	private JLabel BruteForceTime;
	private JLabel NearestNeighbourhahTime;
	private JLabel NearestNeighbourTime;
	
	private JLabel BruteForceDistance;
	private JLabel NearestNeighbourhahDistance;
	private JLabel NearestNeighbourDistance;

	public MainGUI() {
		setTitle("TSP Simulator");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addComponents();

		setVisible(true);
	}

	private void addComponents() {
		picklist = new Picklist();
		picklist1 = new Picklist();
		picklist2 = new Picklist();
		// System.out.println(picklist.toString());

		bf = new BruteForce("BruteForce", picklist.getListOne());
		ff = new NearestNeighbourhaha("Onbekend", picklist.getListTwo());
		ffd = new NearestNeighbour("Nearest Neighbour", picklist.getListThree());
		setLayout(new GridLayout(2, 4, 0, 0));

		add(bf.getPanel());
		add(ff.getPanel());
		add(ffd.getPanel());

		JPanel knoppenPanel = new JPanel();
		add(knoppenPanel);
		knoppenPanel.setLayout(new GridLayout(4, 1, 0, 0));
		nieuwePicklist = new JButton("Nieuwe Picklist");
		nieuwePicklist.addActionListener(this);
		knoppenPanel.add(nieuwePicklist);

		testAlgorithm = new JButton("Test Algoritmes");
		testAlgorithm.addActionListener(this);
		knoppenPanel.add(testAlgorithm);

		JPanel BruteForceResult = new JPanel();
		JPanel NearestNeighbourhahResult = new JPanel();
		JPanel NearestNeighbourResult = new JPanel();
		add(BruteForceResult);
		add(NearestNeighbourhahResult);
		add(NearestNeighbourResult);
		BruteForceResult.setLayout(new GridLayout(2, 1, 0, 0));
		NearestNeighbourhahResult.setLayout(new GridLayout(2, 1, 0, 0));
		NearestNeighbourResult.setLayout(new GridLayout(2, 1, 0, 0));
		
		BruteForceTime = new JLabel("Tijd: " + Double.toString(bf.getResultaat().getTijd()));
		NearestNeighbourhahTime = new JLabel("Tijd: " + Double.toString(ff.getResultaat().getTijd()));
		NearestNeighbourTime = new JLabel("Tijd: " + Double.toString(ffd.getResultaat().getTijd()));
		BruteForceResult.add(BruteForceTime);
		NearestNeighbourhahResult.add(NearestNeighbourhahTime);
		NearestNeighbourResult.add(NearestNeighbourTime);
		
		BruteForceDistance = new JLabel("Afstand: " + Double.toString(bf.getResultaat().getDistance()));
		NearestNeighbourhahDistance = new JLabel("Afstand: " + Double.toString(ff.getResultaat().getDistance()));
		NearestNeighbourDistance = new JLabel("Afstand: " + Double.toString(ffd.getResultaat().getDistance()));
		BruteForceResult.add(BruteForceDistance);
		NearestNeighbourhahResult.add(NearestNeighbourhahDistance);
		NearestNeighbourResult.add(NearestNeighbourDistance);

		add(new JLabel(""));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nieuwePicklist) {
			this.picklist.generateNewPicklist();
			// System.out.println(this.picklist.toString());
			bf.updateResultaat(this.picklist.getListOne());
			ff.updateResultaat(this.picklist.getListTwo());
			ffd.updateResultaat(this.picklist.getListThree());
			repaint();
		}
		if (e.getSource() == testAlgorithm) {
			ffd.calculateRoute();
			repaint();
		}
	}
}
