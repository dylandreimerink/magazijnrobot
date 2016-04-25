package asrsSystem;
/*
 * Authors: Richard en Steven
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import com.sun.corba.se.impl.orbutil.graph.Node;
import com.sun.glass.events.KeyEvent;
import com.sun.xml.internal.ws.api.Component;

import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import shared.*;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import shared.Bestelling;
import shared.Product;

import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JScrollPane;
public class gui extends JFrame implements ActionListener {

	
	private JMenuItem mntmOpenOrder;
	private JMenuItem mntmExit;
	private JMenuItem mntmBewerkArtikel;
	private JMenuItem mntmVoegToe;
	private JMenuItem mntmDeleteArtikel;
	JButton startRobot;
	JButton pauseRobot;
	JButton stopRobot;
	JButton connect;
	JButton disconnect;
	JButton savePakbon;
	
	
	public gui() {
		addComponents();
	}
	
	ChooseOrder chooser = new ChooseOrder();
	Console console = new Console();
	Pakbon pakbon = new Pakbon(1);
	private void addComponents() {
		
		

		//scherm opbouwen
		JFrame frame = new JFrame();
        this.setSize(1200,900);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AS/RS Systeem");
        getContentPane().setLayout(new BorderLayout(0, 0));


        
		// initialiseer en maak menuopties.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBestand = new JMenu("Bestand");
		menuBar.add(mnBestand);
		
		
		// open order - path handler toevoegen
		mntmOpenOrder = new JMenuItem("Open order");
		mntmOpenOrder.addActionListener(this);
		mntmOpenOrder.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnBestand.add(mntmOpenOrder);
		
		
		//open recent NOT DONE
		JMenu mnOpenRecent = new JMenu("Open recent");
		mnOpenRecent.addActionListener(this);

		
		
		//exit DONE
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(this);
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnBestand.add(mntmExit);
		
		
		
		// bewerk artikel NOT DONE
		mntmBewerkArtikel = new JMenuItem("Bewerk artikel");
		mntmBewerkArtikel.addActionListener(this);
		mnBestand.add(mntmBewerkArtikel);
		
		mntmDeleteArtikel = new JMenuItem("Delete artikel");
		mntmDeleteArtikel.addActionListener(this);
		mnBestand.add(mntmDeleteArtikel);
		
		// bewerk artikel NOT DONE
		mntmVoegToe = new JMenuItem("Voeg artikel toe");
		mntmVoegToe.addActionListener(this);
		mnBestand.add(mntmVoegToe);
		


        
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, Color.DARK_GRAY, Color.LIGHT_GRAY, Color.DARK_GRAY, Color.LIGHT_GRAY));
        panel.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new MigLayout("", "[115px][121px][grow]", "[91px,grow]"));
        
        JPanel panel_2 = new JPanel();
        panel_1.add(panel_2, "cell 0 0,alignx left,aligny top");
        panel_2.setLayout(new MigLayout("", "[101px]", "[23px][23px][23px]"));
        
        startRobot = new JButton("start robot");
        startRobot.addActionListener(this);
        panel_2.add(startRobot, "flowy,cell 0 0,growx,aligny top");
        
        stopRobot = new JButton("stop robot");
        stopRobot.addActionListener(this);
        panel_2.add(stopRobot, "cell 0 1,growx,aligny top");
        
        pauseRobot = new JButton("pauseer robot");
        pauseRobot.addActionListener(this);
        panel_2.add(pauseRobot, "cell 0 2,alignx left,aligny top");
        
        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3, "cell 1 0,alignx left,aligny top");
        panel_3.setLayout(new MigLayout("", "[107px]", "[][][23px]"));
        
        disconnect = new JButton("disconnect");
        disconnect.addActionListener(this);
        panel_3.add(disconnect, "flowy,cell 0 0,growx,aligny top");
        
        connect = new JButton("connect");
        connect.addActionListener(this);
        panel_3.add(connect, "cell 0 1,growx,aligny top");
        
        savePakbon = new JButton("pakbon opslaan");
        savePakbon.addActionListener(this);
        panel_3.add(savePakbon, "cell 0 2,alignx center,aligny top");
        
        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(new EmptyBorder(2, 0, 0, 0), "Console", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
        panel_1.add(panel_4, "cell 2 0,grow");
        
        panel_4.add(console.console());
        console.printLine("Programma is succesvol opgestart!");
        console.printLine("Selecteer een order om te beginnen!");
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
        
        
        JPanel panel_5 = new JPanel();
        panel.add(panel_5, BorderLayout.EAST);
        panel_5.setLayout(new MigLayout("", "[]", "[]"));
        
        panel_5.add(pakbon.panel());
        
        this.setVisible(true);
        if(chooser.selected == true) {
        	pakbon.GenerateOrderInfo(chooser.getProductList());
        }
        
		
        
	}
	
	//actionlisteners
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mntmOpenOrder) {
			
			chooser.ChooseFile();
		}
		if(e.getSource()== mntmExit) {
			System.exit(0);
		}
		if(e.getSource()== mntmBewerkArtikel) {
			// todo code here
		}
		if(e.getSource()== mntmVoegToe) {
			Create_Product creator = new Create_Product();
		}
		if(e.getSource()== mntmDeleteArtikel) {
			// todo code here
		}
		
	}


}
