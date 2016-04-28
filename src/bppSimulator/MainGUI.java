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
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import shared.Bestelling;
import shared.Database;
import shared.ParseXML;
import shared.Product;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;

public class MainGUI extends JFrame implements ActionListener {

	final JFileChooser fc = new JFileChooser();

	private Dimension buttonsize;
	private JMenuItem mntmOpenPakbon;
	private SimulatiePanel simPanel;
	private BPPFirstFit firstFitAlgo;
	private BPPFirstFitDescending firstFitDescAlgo;
	private BPPBruteForce bruteForceAlgo;

	long start_time;
	long stop_time;

	PickList picklist = new PickList();
	public ArrayList<Box> boxList;
	public String console;
	JButton btnStartSimulatie;
	JButton btnPauzeerSimulatie;
	JButton btnAnnuleerSimulatie;

	JLabel lbltijdFF;
	JLabel lbltijdFFD;
	JLabel lbltijdBF;
	JLabel lblDozenFF;
	JLabel lblDozenFFD;
	JLabel lblDozenBF;

	JTextArea textArea;
	public int score1;
	JMenu mnFile;

	public boolean pauze = false;

	public MainGUI() {
		setTitle("BPP Simulator");
		setSize(960, 1032);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		boxList = new ArrayList<Box>();

		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
		fc.setDialogTitle("Open Pakbon");
		// set selected filter
		fc.setFileFilter(xmlfilter);

		addComponents();

		setVisible(true);
	}

	private void addComponents() {
		buttonsize = new Dimension(200, 30);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmOpenPakbon = new JMenuItem("Open Pakbon");
		mntmOpenPakbon.addActionListener(this);
		mnFile.add(mntmOpenPakbon);

		mnFile.addActionListener(this);

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JPanel leftPanel = new JPanel();
		panel.add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

		btnStartSimulatie = new JButton("Start simulatie");
		btnStartSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnStartSimulatie.setMaximumSize(buttonsize);
		btnStartSimulatie.addActionListener(this);
		leftPanel.add(btnStartSimulatie);

		btnAnnuleerSimulatie = new JButton("Annuleer simulatie");
		btnAnnuleerSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAnnuleerSimulatie.setMaximumSize(buttonsize);
		btnAnnuleerSimulatie.addActionListener(this);
		leftPanel.add(btnAnnuleerSimulatie);

		JPanel dataPanel = new JPanel();
		leftPanel.add(dataPanel);
		dataPanel.setLayout(new GridLayout(8, 2, 0, 0));

		JLabel lblAlgoritme = new JLabel("First Fit: ");
		dataPanel.add(lblAlgoritme);

		lbltijdFF = new JLabel("*tijd*");
		dataPanel.add(lbltijdFF);

		JLabel lblAlgoritme_1 = new JLabel("First Fit Descending: ");
		dataPanel.add(lblAlgoritme_1);

		lbltijdFFD = new JLabel("*tijd*");
		dataPanel.add(lbltijdFFD);

		JLabel lblAlgoritme_2 = new JLabel("Brute Force: ");
		dataPanel.add(lblAlgoritme_2);

		lbltijdBF = new JLabel("*tijd*");
		dataPanel.add(lbltijdBF);

		JLabel lblGekozen = new JLabel("Gekozen");
		dataPanel.add(lblGekozen);

		JLabel lblAlgoritme_3 = new JLabel("algoritme");
		dataPanel.add(lblAlgoritme_3);

		JLabel lblEinddata = new JLabel("Einddata:");
		dataPanel.add(lblEinddata);

		JLabel lblniks = new JLabel("");
		dataPanel.add(lblniks);

		JLabel lblGegevens = new JLabel("Gegevens:");
		dataPanel.add(lblGegevens);

		lblDozenFF = new JLabel("(niks)");
		dataPanel.add(lblDozenFF);

		JLabel lblGegevens_1 = new JLabel("Gegevens:");
		dataPanel.add(lblGegevens_1);

		lblDozenFFD = new JLabel("(niks)");
		dataPanel.add(lblDozenFFD);

		JLabel lblGegevens_2 = new JLabel("Gegevens:");
		dataPanel.add(lblGegevens_2);

		lblDozenBF = new JLabel("(niks)");
		dataPanel.add(lblDozenBF);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		simPanel = new SimulatiePanel(this);
		simPanel.setSize(new Dimension(0, 200));
		simPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_3.add(simPanel);

		textArea = new JTextArea();

		console = "Programma is succesvol opgestart";

		textArea = new JTextArea(7, 7);
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea.setEditable(false);
		textArea.setColumns(5);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(false);
		textArea.append(console);
		panel_3.add(scrollPane);

	}

	public void FirstFitCallback() {
		appendConsoleText("\nStarten First Fit");
		
		boxList = firstFitAlgo.getResult();
		lblDozenFF.setText("Dozen: " + boxList.size());
		
		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;
		
		lbltijdFF.setText(Math.round(diffTime) + " ms");
		simPanel.repaint();
		
		appendConsoleText("\nFirst Fit klaar");
		
		firstFitAlgo = null;
		
		while ((System.nanoTime() - stop_time) < (3000 * 1e6)) {

		}
		
		start_time = System.nanoTime();
		firstFitDescAlgo.start();
	}

	public void FirstFitDescCallback() {
		appendConsoleText("\nStarten First Fit Decreasing");

		boxList = firstFitDescAlgo.getResult();
		lblDozenFFD.setText("Dozen: " + boxList.size());

		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;

		lbltijdFFD.setText(Math.round(diffTime) + " ms");

		simPanel.repaint();

		appendConsoleText("\nFirst Fit Decreasing klaar");

		firstFitDescAlgo = null;

		while ((System.nanoTime() - stop_time) < (3000 * 1e6)) {

		}

		start_time = System.nanoTime();
		appendConsoleText("\nStarten Brute Force");
		
		bruteForceAlgo.start();
		
		while ((System.nanoTime() - start_time) < (5000 * 1e6)) {

		}
		
		if (bruteForceAlgo != null) {
			bruteForceAlgo.stop();
		}
	}

	public void BruteForceCallback() {
		boxList = bruteForceAlgo.getResult();
		lblDozenBF.setText("Dozen: " + boxList.size());
		
		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;
		
		lbltijdBF.setText(Math.round(diffTime) + " ms");
		
		simPanel.repaint();
		
		appendConsoleText("\nBrute Force klaar");
		
		bruteForceAlgo = null;

		appendConsoleText("\nSimulatie voltooid");
	}

	public void appendConsoleText(String text) {
		textArea.append(text);
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmOpenPakbon) {
			int response = fc.showOpenDialog(this);
			if (response == JFileChooser.APPROVE_OPTION) {
				Bestelling order = new ParseXML(fc.getSelectedFile().getAbsolutePath()).getBestelling();
				picklist = new PickList(order.getProductList());
				appendConsoleText("\nXml pakbon successvol geladen");
			}
		}

		if (e.getSource() == btnStartSimulatie) {

			if (picklist.getProducts().size() == 0) {
				appendConsoleText("\nLaad eerst een xml pakbon voor je de simulatie start");
				return;
			}

			lbltijdFF.setText("*wachten*");
			lbltijdFFD.setText("*wachten*");
			lbltijdBF.setText("*wachten*");
			appendConsoleText("\nSimulatie aan het starten..");
			if (firstFitAlgo == null) {
				firstFitAlgo = new BPPFirstFit(picklist, 4, 4, 4);
				firstFitAlgo.setOnDoneListner(this);
			}
			start_time = System.nanoTime();
			firstFitAlgo.start();

			if (firstFitDescAlgo == null) {
				firstFitDescAlgo = new BPPFirstFitDescending(picklist, 4, 4, 4);
				firstFitDescAlgo.setOnDoneListner(this);
			}

			if (bruteForceAlgo == null) {
				bruteForceAlgo = new BPPBruteForce(picklist, 4, 4, 4);
				bruteForceAlgo.setOnDoneListner(this);
			}

		} else if (e.getSource() == btnAnnuleerSimulatie) {
			new MainGUI().textArea.setText(textArea.getText());
			this.setVisible(false);

			console = "\nSimulatie aan het annuleren..";
			if (firstFitAlgo != null)
				firstFitAlgo.stop();
			if (firstFitDescAlgo != null)
				firstFitDescAlgo.stop();
			if (bruteForceAlgo != null)
				bruteForceAlgo.stop();
		}
	}
}