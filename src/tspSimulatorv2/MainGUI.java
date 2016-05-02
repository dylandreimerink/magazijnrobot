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


	public MainGUI(DrawPanel drawpanel1, DrawPanel drawpanel2, DrawPanel drawpanel3, DrawPanel drawpanel4, TSPController controller) {
		this.controller = controller;
		setTitle("TSP Simulator");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addComponents(drawpanel1, drawpanel2, drawpanel3,drawpanel4);

		setVisible(true);
	}

	private void addComponents(DrawPanel drawpanel1, DrawPanel drawpanel2, DrawPanel drawpanel3, DrawPanel drawpanel4) {
		setLayout(new GridLayout(2, 4, 0, 0));

		add(drawpanel1);
		add(drawpanel2);
		add(drawpanel3);

		JPanel knoppenPanel = new JPanel();
		add(knoppenPanel);
		knoppenPanel.setLayout(new GridLayout(4, 2, 0, 0));
		nieuwePicklist = new JButton("Nieuwe Picklist");
		nieuwePicklist.addActionListener(this);
		knoppenPanel.add(nieuwePicklist);

		testAlgorithm = new JButton("Test Algoritmes");
		testAlgorithm.addActionListener(this);
		knoppenPanel.add(testAlgorithm);
		
		
		add(drawpanel4);

		
		//hieronder is bullchit
		JPanel nearestNeighbourhahResult = new JPanel();
		JPanel nearestNeighbourResult = new JPanel();
		add(nearestNeighbourhahResult);
		add(nearestNeighbourResult);
		add(new JLabel(""));
		//hierboven is bullchit
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == nieuwePicklist) {
			controller.generateNewPicklist();
		}
		if (e.getSource() == testAlgorithm) {
			controller.testAlgorithm();
		}
		repaint();
	}

}
