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
import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import shared.Bestelling;
import shared.Database;
import shared.ParseXML;
import shared.Product;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;

public class MainGUI extends JFrame implements ActionListener{
	
	final JFileChooser fc = new JFileChooser();
	
	private Dimension buttonsize;
	private JMenuItem mntmOpenPakbon;
	private SimulatiePanel simPanel;
	private BPPFirstFit firstFitAlgo;
	private BPPFirstFitDescending firstFitDescAlgo;
	
	public JLabel lbltijd_2;
	PickList picklist;
	public ArrayList<Box> boxList;
	public String console;
	JButton btnStartSimulatie;
	JButton btnPauzeerSimulatie;
	JButton btnAnnuleerSimulatie;
	JTextArea textArea;
	public int score1;
	JMenu mnFile;
	
	
	

	public MainGUI() {
		setTitle("BPP Simulator");
		setSize(700, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		boxList = new ArrayList<Box>();


		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter(
			     "xml files (*.xml)", "xml");
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

		btnPauzeerSimulatie = new JButton("Pauzeer simulatie");
		btnPauzeerSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnPauzeerSimulatie.setMaximumSize(buttonsize);
		btnPauzeerSimulatie.addActionListener(this);
		leftPanel.add(btnPauzeerSimulatie);

		btnAnnuleerSimulatie = new JButton("Annuleer simulatie");
		btnAnnuleerSimulatie.setAlignmentX(Component.RIGHT_ALIGNMENT);
		btnAnnuleerSimulatie.setMaximumSize(buttonsize);
		btnAnnuleerSimulatie.addActionListener(this);
		leftPanel.add(btnAnnuleerSimulatie);

		JPanel dataPanel = new JPanel();
		leftPanel.add(dataPanel);
		dataPanel.setLayout(new GridLayout(8, 2, 0, 0));

		JLabel lblAlgoritme = new JLabel("Algoritme 1");
		dataPanel.add(lblAlgoritme);
		
		JLabel lbltijd = new JLabel("Efficientie: " + score1 + "%");
		dataPanel.add(lbltijd);

		JLabel lblAlgoritme_1 = new JLabel("Algoritme 2");
		dataPanel.add(lblAlgoritme_1);

		JLabel lbltijd_1 = new JLabel("*efficientie*");
		dataPanel.add(lbltijd_1);

		JLabel lblAlgoritme_2 = new JLabel("Algoritme 3");
		dataPanel.add(lblAlgoritme_2);

		lbltijd_2 = new JLabel("*efficientie*");
		dataPanel.add(lbltijd_2);

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

		JLabel lblniks_1 = new JLabel("(niks)");
		dataPanel.add(lblniks_1);

		JLabel lblGegevens_1 = new JLabel("Gegevens:");
		dataPanel.add(lblGegevens_1);

		JLabel lblniks_2 = new JLabel("(niks)");
		dataPanel.add(lblniks_2);

		JLabel lblGegevens_2 = new JLabel("Gegevens:");
		dataPanel.add(lblGegevens_2);

		JLabel lblniks_3 = new JLabel("(niks)");
		dataPanel.add(lblniks_3);

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
		textArea.setColumns (5);
		textArea.setLineWrap (true);
		textArea.setWrapStyleWord (false); 
		textArea.append(console);
		panel_3.add(scrollPane);

	}
	
	public void FirstFitCallback(){
		boxList = firstFitAlgo.getResult();
		simPanel.repaint();
		firstFitAlgo = null;
	}
	
	public void FirstFitDescCallback(){
		boxList = firstFitDescAlgo.getResult();
		simPanel.repaint();
		firstFitDescAlgo = null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mntmOpenPakbon){
			int response = fc.showOpenDialog(this);
			if(response == JFileChooser.APPROVE_OPTION){
				Bestelling order = new ParseXML(fc.getSelectedFile().getAbsolutePath()).getBestelling();
				int[] ids = new int[order.getProductList().toArray().length];
				int i = 0;
				for(Product p : order.getProductList()){
					ids[i] = p.getProductId();
					i++;
				}
				picklist = new PickList(ids);
			}
		}
		
		
		if (e.getSource() == btnStartSimulatie) {
			console = "\nSimulatie aan het starten..";
			if(firstFitAlgo == null){
				firstFitAlgo = new BPPFirstFit(picklist, 3, 3, 3);
				firstFitAlgo.setOnDoneListner(this);
			}
			if(firstFitDescAlgo == null){
				firstFitDescAlgo = new BPPFirstFitDescending(picklist, 3, 3, 3);
				firstFitDescAlgo.setOnDoneListner(this);
			}
			//firstFitAlgo.start();
			firstFitDescAlgo.start();
			textArea.append(console);
			textArea.setCaretPosition(textArea.getDocument().getLength());
			
		} else if (e.getSource() == btnPauzeerSimulatie) {
			console = "\nSimulatie aan het pauzeren..";
			firstFitAlgo.pauze();
			textArea.append(console);
			textArea.setCaretPosition(textArea.getDocument().getLength());
		} else if (e.getSource() == btnAnnuleerSimulatie) {
			console = "\nSimulatie aan het annuleren..";
			firstFitAlgo.stop();
			textArea.append(console);
			textArea.setCaretPosition(textArea.getDocument().getLength());
		}
	}	
}
