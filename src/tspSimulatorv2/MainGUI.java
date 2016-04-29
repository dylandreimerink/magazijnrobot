package tspSimulatorv2;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainGUI extends JFrame implements ActionListener {
	
	private TSPController controller;

	private JButton nieuwePicklist;
	private JButton testAlgorithm;

	private JLabel bruteForceTime;
	private JLabel nearestNeighbourhahTime;
	private JLabel nearestNeighbourTime;

	private JLabel bruteForceDistance;
	private JLabel nearestNeighbourhahDistance;
	private JLabel nearestNeighbourDistance;

	public MainGUI(DrawPanel drawpanel1, DrawPanel drawpanel2, DrawPanel drawpanel3, TSPController controller) {
		this.controller = controller;
		setTitle("TSP Simulator");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addComponents(drawpanel1,drawpanel2,drawpanel3);

		setVisible(true);
	}

	private void addComponents(DrawPanel drawpanel1, DrawPanel drawpanel2, DrawPanel drawpanel3) {
		setLayout(new GridLayout(2, 4, 0, 0));
		
		add(drawpanel1);
		add(drawpanel2);
		add(drawpanel3);
		
		
		JPanel knoppenPanel = new JPanel();
		add(knoppenPanel);
		knoppenPanel.setLayout(new GridLayout(4, 1, 0, 0));
		nieuwePicklist = new JButton("Nieuwe Picklist");
		nieuwePicklist.addActionListener(this);
		knoppenPanel.add(nieuwePicklist);

		testAlgorithm = new JButton("Test Algoritmes");
		testAlgorithm.addActionListener(this);
		knoppenPanel.add(testAlgorithm);

		JPanel bruteForceResult = new JPanel();
		JPanel nearestNeighbourhahResult = new JPanel();
		JPanel nearestNeighbourResult = new JPanel();
		add(bruteForceResult);
		add(nearestNeighbourhahResult);
		add(nearestNeighbourResult);
		bruteForceResult.setLayout(new GridLayout(2, 1, 0, 0));
		nearestNeighbourhahResult.setLayout(new GridLayout(2, 1, 0, 0));
		nearestNeighbourResult.setLayout(new GridLayout(2, 1, 0, 0));

		add(new JLabel(""));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nieuwePicklist) {
			controller.generateNewPicklist();
			
		}
		if (e.getSource() == testAlgorithm) {

		}
		repaint();
	}

}
