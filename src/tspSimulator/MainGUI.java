package tspSimulator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainGUI extends JFrame implements ActionListener {

	private TSPController controller;

	private JButton newPicklist;
	private JButton testAlgorithm;
	private JButton rasterOn;
	private JButton rasterOff;
	private JButton numberOfProducts;

	public MainGUI(DrawPanel drawpanel1, DrawPanel drawpanel2, DrawPanel drawpanel3, DrawPanel drawpanel4,
			TSPController controller) {
		this.controller = controller;
		setTitle("TSP Simulator");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		addComponents(drawpanel1, drawpanel2, drawpanel3, drawpanel4);

		setVisible(true);
	}

	private void addComponents(DrawPanel drawpanel1, DrawPanel drawpanel2, DrawPanel drawpanel3, DrawPanel drawpanel4) {
		setLayout(new GridLayout(2, 4, 0, 0));

		add(drawpanel1);
		add(drawpanel2);
		add(drawpanel3);

		JPanel buttonPanel = new JPanel();
		add(buttonPanel);
		buttonPanel.setLayout(new GridLayout(5, 1, 0, 0));
		newPicklist = new JButton("Nieuwe Picklist");
		newPicklist.addActionListener(this);
		buttonPanel.add(newPicklist);

		testAlgorithm = new JButton("Test Algoritmes");
		testAlgorithm.addActionListener(this);
		buttonPanel.add(testAlgorithm);

		rasterOn = new JButton("Raster on");
		rasterOn.addActionListener(this);
		buttonPanel.add(rasterOn);

		rasterOff = new JButton("Raster off");
		rasterOff.addActionListener(this);
		buttonPanel.add(rasterOff);

		numberOfProducts = new JButton("Selecteer aantal Producten");
		numberOfProducts.addActionListener(this);
		buttonPanel.add(numberOfProducts);

		add(drawpanel4);

		// hieronder is bullchit om gritlayout op te vullen
		for(int i = 0; i <= 2; i++){
			add(new JLabel(""));
		}
		// hierboven is bullchit om gritlayout op te vullen

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newPicklist) {
			controller.generateNewPicklist();
		}
		if (e.getSource() == testAlgorithm) {
			controller.testAlgorithm();
		}
		if (e.getSource() == rasterOn) {
			controller.setRaster(true);
		}
		if (e.getSource() == rasterOff) {
			controller.setRaster(false);
		}
		if (e.getSource() == numberOfProducts) {
			NumberOfProductsDialoog dialoog = new NumberOfProductsDialoog(this, controller);
			if (dialoog.isOk()) {
				int aantal = dialoog.getAantal();

				JOptionPane.showMessageDialog(this,
						"Als er een nieuwe picklist gegenereerd wordt zal deze " + aantal + " producten bevatten.");
				aantal--;
				controller.setMaxproducts(aantal);
				controller.setMinproducts(aantal);
			}
		}
	}

}
