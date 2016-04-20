package asrsSystem;

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
public class Gui extends JFrame implements ActionListener {

	
	private String path;
	private ArrayList<Product> productlist;
	   
	public Gui() {
<<<<<<< HEAD
	
	
=======
<<<<<<< HEAD

=======
>>>>>>> origin/master
		
>>>>>>> origin/master
		//scherm opbouwen
		JFrame frame = new JFrame();
        this.setSize(1200,900);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AS/RS Systeem");
        getContentPane().setLayout(new BorderLayout(0, 0));
<<<<<<< HEAD
=======
		
		// initialiseer en maak menuopties.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBestand = new JMenu("Bestand");
		menuBar.add(mnBestand);
		
		
		// open order - path handler toevoegen
		JMenuItem mntmOpenOrder = new JMenuItem("Open order");
		mntmOpenOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Choosefile chooser = new Choosefile();
				path = chooser.ChooseFile();
				ParseXML order = new ParseXML(path);
				System.out.println(path);
			}
		});
		mntmOpenOrder.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnBestand.add(mntmOpenOrder);
		
		
		//open recent NOT DONE
		JMenu mnOpenRecent = new JMenu("Open recent");
		mnOpenRecent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
				
				
			}
		});

		
		
		//exit DONE
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnBestand.add(mntmExit);
		
		
		
		// bewerk artikel NOT DONE
		JMenuItem mntmBewerkArtikel = new JMenuItem("Bewerk artikel");
		mntmBewerkArtikel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnBestand.add(mntmBewerkArtikel);
		
		// bewerk artikel NOT DONE
		JMenuItem mntmBewerkArtikel = new JMenuItem("Voeg artikeltoe");
		mntmBewerkArtikel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnBestand.add(mntmBewerkArtikel);
		

>>>>>>> origin/master
        
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
        
        JButton startRobot = new JButton("start robot");
        panel_2.add(startRobot, "flowy,cell 0 0,growx,aligny top");
        
        JButton stopRobot = new JButton("stop robot");
        panel_2.add(stopRobot, "cell 0 1,growx,aligny top");
        
        JButton pauseRobot = new JButton("pauseer robot");
        panel_2.add(pauseRobot, "cell 0 2,alignx left,aligny top");
        
        JPanel panel_3 = new JPanel();
        panel_1.add(panel_3, "cell 1 0,alignx left,aligny top");
        panel_3.setLayout(new MigLayout("", "[107px]", "[][][23px]"));
        
        JButton disconnect = new JButton("disconnect");
        disconnect.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        panel_3.add(disconnect, "flowy,cell 0 0,growx,aligny top");
        
        JButton connect = new JButton("connect");
        panel_3.add(connect, "cell 0 1,growx,aligny top");
        
        JButton savePakbon = new JButton("pakbon opslaan");
        panel_3.add(savePakbon, "cell 0 2,alignx center,aligny top");
        
        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(new EmptyBorder(2, 0, 0, 0), "Console", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
        panel_1.add(panel_4, "cell 2 0,grow");
        panel_4.setLayout(new MigLayout("", "[grow]", "[grow]"));
        
        JScrollPane scrollPane = new JScrollPane();
<<<<<<< HEAD
        panel_4.add(scrollPane, "cell 0 0,grow");
        
        JPanel panel_5 = new JPanel();
        panel.add(panel_5, BorderLayout.EAST);
        panel_5.setLayout(new MigLayout("", "[]", "[]"));
        
        this.setVisible(true);
=======
        panel_4.add(scrollPane, "cell 0 0,grow");	
		
		// initialiseer en maak menuopties.
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnBestand = new JMenu("Bestand");
		menuBar.add(mnBestand);
		
		
		// open order - path handler toevoegen
		JMenuItem mntmOpenOrder = new JMenuItem("Open order");
		mntmOpenOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				path = ChooseFile();
				Bestelling bestelling = new Bestelling(path);
				productlist = bestelling.getProductList();
				System.out.println(path);
			}
		});
		mntmOpenOrder.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, InputEvent.CTRL_MASK));
		mnBestand.add(mntmOpenOrder);
		
		
		//open recent NOT DONE
		JMenu mnOpenRecent = new JMenu("Open recent");
		mnOpenRecent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnBestand.add(mnOpenRecent);
		
		
		//exit DONE
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mnBestand.add(mntmExit);
		
		
		
		// bewerk artikel NOT DONE
		JMenuItem mntmBewerkArtikel = new JMenuItem("Bewerk artikel");
		mntmBewerkArtikel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	            Artikel dialoog = new Artikel();
	            dialoog.setVisible(true);	            
			}
		});
		mnBestand.add(mntmBewerkArtikel);
        this.setVisible(true);			
>>>>>>> origin/master
	}
	
	public ArrayList<Product> getProductList() {
		return productlist;
	}
        

	//getter
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	 

}
