package bppSimulator;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextArea;

public class MainGUI extends JFrame{
	
	public JLabel lbltijd_2;
	
	private Dimension buttonsize;
	
	public MainGUI(){
		setTitle("BPP Simulator");
		setSize(700, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addComponents();
	
		setVisible(true);
	}

	private void addComponents() {
		buttonsize = new Dimension(200, 30);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.WEST);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JButton btnStartSimulatie = new JButton("Start simulatie");
		btnStartSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnStartSimulatie.setMaximumSize(buttonsize);
		panel_1.add(btnStartSimulatie);
		
		JButton btnNewButton = new JButton("Pauzeer simulatie");
		btnNewButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnNewButton.setMaximumSize(buttonsize);
		panel_1.add(btnNewButton);
		
		JButton btnAnnuleerSimulatie = new JButton("Annuleer simulatie");
		btnAnnuleerSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAnnuleerSimulatie.setMaximumSize(buttonsize);
		panel_1.add(btnAnnuleerSimulatie);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(8, 2, 0, 0));
		
		JLabel lblAlgoritme = new JLabel("Algoritme 1");
		panel_2.add(lblAlgoritme);
		
		JLabel lbltijd = new JLabel("*tijd*");
		panel_2.add(lbltijd);
		
		JLabel lblAlgoritme_1 = new JLabel("Algoritme 2");
		panel_2.add(lblAlgoritme_1);
		
		JLabel lbltijd_1 = new JLabel("*tijd*");
		panel_2.add(lbltijd_1);
		
		JLabel lblAlgoritme_2 = new JLabel("Algoritme 3");
		panel_2.add(lblAlgoritme_2);
		
		lbltijd_2 = new JLabel("*tijd*");
		panel_2.add(lbltijd_2);
		
		JLabel lblGekozen = new JLabel("Gekozen");
		panel_2.add(lblGekozen);
		
		JLabel lblAlgoritme_3 = new JLabel("algoritme");
		panel_2.add(lblAlgoritme_3);
		
		JLabel lblEinddata = new JLabel("Einddata:");
		panel_2.add(lblEinddata);
		
		JLabel lblniks = new JLabel("");
		panel_2.add(lblniks);
		
		JLabel lblGegevens = new JLabel("Gegevens:");
		panel_2.add(lblGegevens);
		
		JLabel lblniks_1 = new JLabel("(niks)");
		panel_2.add(lblniks_1);
		
		JLabel lblGegevens_1 = new JLabel("Gegevens:");
		panel_2.add(lblGegevens_1);
		
		JLabel lblniks_2 = new JLabel("(niks)");
		panel_2.add(lblniks_2);
		
		JLabel lblGegevens_2 = new JLabel("Gegevens:");
		panel_2.add(lblGegevens_2);
		
		JLabel lblniks_3 = new JLabel("(niks)");
		panel_2.add(lblniks_3);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		SimulatiePanel panel_4 = new SimulatiePanel(this);
		panel_4.setSize(new Dimension(0, 200));
		panel_4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_3.add(panel_4);
		
		JTextArea textArea = new JTextArea();
		textArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		textArea.setRows(1);
		textArea.setColumns(1);
		panel_3.add(textArea);
	}
}
