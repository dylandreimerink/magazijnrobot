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

import com.sun.glass.events.KeyEvent;
import com.sun.xml.internal.ws.api.Component;

import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

import Functions.*;
import Functions.Product;
public class Gui extends JFrame implements ActionListener {

	
	private String path;
	private ArrayList<Product> productlist;
	   
	public Gui() {
		
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
			}
		});
		mnBestand.add(mntmBewerkArtikel);
		
		//scherm opbouwen
		JFrame frame = new JFrame();
        this.setSize(1200,900);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AS/RS Systeem");
        getContentPane().setLayout(new FlowLayout());
        
        this.setVisible(true);
	}
	
	public ArrayList<Product> getProductList() {
		return productlist;
	}
        

	//filechooser functie, geeft path van het gekozen bestand terug, path moet nog op een andere manier verwerkt worden.
	public String ChooseFile() {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setCurrentDirectory(new java.io.File("."));
	    chooser.setDialogTitle("kies uw XML file");
	    chooser.addChoosableFileFilter(new FileNameExtensionFilter("xml Documents", "xml") );
	    chooser.setAcceptAllFileFilterUsed(false);
	    if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    File selectedFile = chooser.getSelectedFile();
	      return("" + selectedFile.getAbsolutePath());
	    } else {
	      return("No Selection ");
	    }
	  }
	


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	 

}
