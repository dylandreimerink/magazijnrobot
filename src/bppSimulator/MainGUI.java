package bppSimulator;

//Authors: Mike Veltman & Dylan Reimerink (ICTM2a)

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

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
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import shared.Bestelling;
import shared.ParseXML;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class MainGUI extends JFrame implements ActionListener {

	final JFileChooser fc = new JFileChooser();

	private Dimension buttonsize;
	private JMenuItem mntmOpenPakbon;
	private SimulatiePanel simPanelFF;
	private SimulatiePanel simPanelFFD;
	private SimulatiePanel simPanelBF;
	
	private BPPFirstFit firstFitAlgo;
	private BPPFirstFitDescending firstFitDescAlgo;
	private BPPBruteForce bruteForceAlgo;
	
	int FFTime = 0, FFDTime = 0, BFTime = 0;
	int FFDozen = 0, FFDDozen = 0, BFDozen = 0;

	long start_time;
	long stop_time;

	PickList picklist = new PickList();
	
	public ArrayList<Box> boxListFF;
	public ArrayList<Box> boxListFFD;
	public ArrayList<Box> boxListBF;
	
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
	JLabel lblGekozenAlgoritme;

	JTextArea textArea;
	public int score1;
	JMenu mnFile;

	public boolean pauze = false;

	public MainGUI() {
		setTitle("BPP Simulator");
		setSize(960, 1032);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		boxListFF = new ArrayList<Box>();
		boxListFFD = new ArrayList<Box>();
		boxListBF = new ArrayList<Box>();

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

		btnAnnuleerSimulatie = new JButton("Herstart simulatie");
		btnAnnuleerSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAnnuleerSimulatie.setMaximumSize(buttonsize);
		btnAnnuleerSimulatie.addActionListener(this);
		leftPanel.add(btnAnnuleerSimulatie);

		JPanel dataPanel = new JPanel();
		dataPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		leftPanel.add(dataPanel);
		dataPanel.setLayout(new GridLayout(8, 2, 0, 0));

		JLabel lblAlgoritme = new JLabel("First Fit: ");
		lblAlgoritme.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblAlgoritme);

		lbltijdFF = new JLabel("*tijd*");
		lbltijdFF.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lbltijdFF);

		JLabel lblAlgoritme_1 = new JLabel("First Fit Decreasing: ");
		lblAlgoritme_1.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblAlgoritme_1);

		lbltijdFFD = new JLabel("*tijd*");
		lbltijdFFD.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lbltijdFFD);

		JLabel lblAlgoritme_2 = new JLabel("Brute Force: ");
		lblAlgoritme_2.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblAlgoritme_2);

		lbltijdBF = new JLabel("*tijd*");
		lbltijdBF.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lbltijdBF);

		JLabel lblGekozen = new JLabel("Gekozen");
		lblGekozen.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblGekozen);

		lblGekozenAlgoritme = new JLabel("algoritme");
		lblGekozenAlgoritme.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblGekozenAlgoritme);

		JLabel lblEinddata = new JLabel("Einddata:");
		lblEinddata.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblEinddata);

		JLabel lblniks = new JLabel("");
		lblniks.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblniks);

		JLabel lblGegevens = new JLabel("Gegevens:");
		lblGegevens.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblGegevens);

		lblDozenFF = new JLabel("(niks)");
		lblDozenFF.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblDozenFF);

		JLabel lblGegevens_1 = new JLabel("Gegevens:");
		lblGegevens_1.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblGegevens_1);

		lblDozenFFD = new JLabel("(niks)");
		lblDozenFFD.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblDozenFFD);

		JLabel lblGegevens_2 = new JLabel("Gegevens:");
		lblGegevens_2.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblGegevens_2);

		lblDozenBF = new JLabel("(niks)");
		lblDozenBF.setHorizontalAlignment(SwingConstants.CENTER);
		dataPanel.add(lblDozenBF);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));

		JTabbedPane tabbedPane = new JTabbedPane();
		
		simPanelFF = new SimulatiePanel();
		simPanelFF.setSize(new Dimension(0, 200));
		simPanelFF.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tabbedPane.add("First Fit", simPanelFF);
		
		simPanelFFD = new SimulatiePanel();
		simPanelFFD.setSize(new Dimension(0, 200));
		simPanelFFD.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tabbedPane.add("First Fit Decreasing", simPanelFFD);
		
		simPanelBF = new SimulatiePanel();
		simPanelBF.setSize(new Dimension(0, 200));
		simPanelBF.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		tabbedPane.add("Brute Force", simPanelBF);
		
		panel_3.add(tabbedPane);

		textArea = new JTextArea();

		console = "Programma is succesvol opgestart";
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
				panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
				textArea = new JTextArea(13, 7);
				JScrollPane scrollPane = new JScrollPane(textArea);
				panel_1.add(scrollPane);
				textArea.setFont(new Font("Arial", Font.PLAIN, 12));
				textArea.setEditable(false);
				textArea.setColumns(5);
				textArea.setLineWrap(true);
				textArea.setWrapStyleWord(false);
				textArea.append(console);

	}

	public void FirstFitCallback() {
		
		boxListFF = firstFitAlgo.getResult();
		lblDozenFF.setText("Dozen: " + boxListFF.size());
		FFDozen = boxListFF.size();
		
		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;
		
		lbltijdFF.setText(Math.round(diffTime) + " ms");
		FFTime = (int)Math.round(diffTime);
		
		simPanelFF.setBoxList(boxListFF);
		simPanelFF.repaint();
		
		appendConsoleText("\nFirst Fit klaar");
		
		firstFitAlgo = null;
	}

	public void FirstFitDescCallback() {
		

		boxListFFD = firstFitDescAlgo.getResult();
		lblDozenFFD.setText("Dozen: " + boxListFFD.size());
		FFDDozen = boxListFFD.size();
		
		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;

		lbltijdFFD.setText(Math.round(diffTime) + " ms");
		FFDTime = (int) Math.round(diffTime);

		simPanelFFD.setBoxList(boxListFFD);
		simPanelFFD.repaint();

		appendConsoleText("\nFirst Fit Decreasing klaar");

		firstFitDescAlgo = null;		

	}

	public void BruteForceCallback() {
		boxListBF = bruteForceAlgo.getResult();
		lblDozenBF.setText("Dozen: " + boxListBF.size());
		BFDozen = boxListBF.size();
		
		stop_time = System.nanoTime();
		double diffTime = (stop_time - start_time) / 1e6;
		
		lbltijdBF.setText(Math.round(diffTime) + " ms");
		BFTime = (int) Math.round(diffTime);
		
		simPanelBF.setBoxList(boxListBF);
		simPanelBF.repaint();
		
		appendConsoleText("\nBrute Force klaar");
		
		bruteForceAlgo = null;
	
		lbltijdFF.setText(FFTime + " ms");
		lbltijdFFD.setText(FFDTime + " ms");
		lbltijdBF.setText(BFTime + " ms");
		
		if(FFDozen < FFDDozen && FFDozen < BFDozen){
			lblGekozenAlgoritme.setText("First Fit");
		}else if(FFDDozen < FFDozen && FFDDozen < BFDozen){
			lblGekozenAlgoritme.setText("First Fit Decreasing");
		}else if(BFDozen < FFDDozen && BFDozen < FFDozen){
			System.out.println("BF: " + BFDozen + ", FF: " + FFDDozen + ", FFD: " + FFDDozen);
			lblGekozenAlgoritme.setText("Brute Force");
		}else{
			System.out.println("dozen gelijk");
			if(FFTime <= FFDTime && FFTime <= BFTime){
				lblGekozenAlgoritme.setText("First Fit");
			}else if(FFDTime <= FFTime && FFDTime <= BFTime){
				lblGekozenAlgoritme.setText("First Fit Decreasing");
			}else{
				lblGekozenAlgoritme.setText("Brute Force");
			}
		}
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

			if (firstFitDescAlgo == null) {
				firstFitDescAlgo = new BPPFirstFitDescending(picklist, 4, 4, 4);
				firstFitDescAlgo.setOnDoneListner(this);
			}

			if (bruteForceAlgo == null) {
				bruteForceAlgo = new BPPBruteForce(picklist, 4, 4, 4);
				bruteForceAlgo.setOnDoneListner(this);
			}
			
			start_time = System.nanoTime();
			appendConsoleText("\nStarten First Fit");
			firstFitAlgo.start();
			appendConsoleText("\nStarten First Fit Decreasing");
			firstFitDescAlgo.start();
			appendConsoleText("\nStarten Brute Force");
			bruteForceAlgo.start();
			
			new Thread(new Runnable() {
				public void run() {
					while ((System.nanoTime() - start_time) < (3000 * 1e6)) {

					}
					if (bruteForceAlgo != null) {
						bruteForceAlgo.stop();
					}
				}
			}).start();

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