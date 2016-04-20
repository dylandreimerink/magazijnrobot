package bppSimulator;

//Authors: Mike Veltman & Dylan Reimerink (ICTM2a)

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

import shared.Product;

import java.awt.Font;

public class MainGUI extends JFrame implements ActionListener {

	public JLabel lbltijd_2;
	public ArrayList<Product> productList;
	public String console;
	JButton btnStartSimulatie;
	JButton btnPauzeerSimulatie;
	JButton btnAnnuleerSimulatie;
	JTextArea textArea;
	JMenu mnFile;

	private Dimension buttonsize;

	public MainGUI() {
		setTitle("BPP Simulator");
		setSize(700, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		productList = new ArrayList<Product>();

		// TODO: remove code between comments when done testing
		productList.add(new Product(0, 2, 3, 10, 15, 10));
		productList.add(new Product(1, 2, 4, 10, 20, 10));
		productList.add(new Product(2, 2, 5, 10, 25, 10));
		// end comment

		addComponents();

		setVisible(true);
	}

	private void addComponents() {
		buttonsize = new Dimension(200, 30);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		mnFile.addActionListener(this);

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

		btnStartSimulatie = new JButton("Start simulatie");
		btnStartSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnStartSimulatie.setMaximumSize(buttonsize);
		btnStartSimulatie.addActionListener(this);
		panel_1.add(btnStartSimulatie);

		btnPauzeerSimulatie = new JButton("Pauzeer simulatie");
		btnPauzeerSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnPauzeerSimulatie.setMaximumSize(buttonsize);
		btnPauzeerSimulatie.addActionListener(this);
		panel_1.add(btnPauzeerSimulatie);

		btnAnnuleerSimulatie = new JButton("Annuleer simulatie");
		btnAnnuleerSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAnnuleerSimulatie.setMaximumSize(buttonsize);
		btnAnnuleerSimulatie.addActionListener(this);
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

		console = "Programma is succesvol opgestart";

		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea.setEditable(false);
		textArea.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		textArea.setRows(1);
		textArea.setColumns(1);
		textArea.append(console);
		panel_3.add(textArea);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == mnFile){
			
		}
		
		if (ae.getSource() == btnStartSimulatie) {
			console = "\nSimulatie aan het starten..";
			textArea.append(console);
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} else if (ae.getSource() == btnPauzeerSimulatie) {
			console = "\nSimulatie aan het pauzeren..";
			textArea.append(console);
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} else if (ae.getSource() == btnAnnuleerSimulatie) {
			console = "\nSimulatie aan het annuleren..";
			textArea.append(console);
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} else {
			System.out.println("WTF");
		}

	}
}
