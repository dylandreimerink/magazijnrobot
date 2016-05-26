package asrsSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.border.MatteBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
public class gui extends JFrame implements ActionListener {

	
	private JMenuItem mntmOpenOrder;
	private JMenuItem mntmExit;
	private JMenuItem mntmBewerkArtikel;
	private JMenuItem mntmVoegToe;
	private JMenuItem mntmDeleteArtikel;
	private JMenuItem mntmGenRoute;
	JButton startRobot;
	JButton pauseRobot;
	JButton stopRobot;
	JButton connect;
	JButton disconnect;
	JButton genRoute;
	JLabel orderNr;
	JLabel kNaam;
	JLabel kAdres;

	
	Controller controller;
	ChooseOrder chooser = new ChooseOrder();
	Console console = new Console();
	JPanel container = new JPanel();
	ProgressBar PBar;
	Warningfunctions warning = new Warningfunctions();
	private JLabel lblOrderinfoselecteerEenOrder;
	private Component horizontalStrut;
	private Component horizontalStrut_2;
	private JPanel panel_6;
	private JPanel drawerContainer;
	private JLabel lblNewLabel;
	private JPanel panel_7;
	private JLabel lblDoosEen;
	private JLabel lblDoosTwee;
	private JLabel lblDoosinfo;
	private Image image;
	private JFrame frame;
	
	
	public gui() {
		addComponents();
	}
	
	private void addComponents() {

		Console console = new Console();
		Toolkit kit = Toolkit.getDefaultToolkit();
	
		image = kit.createImage("src/img/smallCrate.png");
		

		//scherm opbouwen
		frame = new JFrame();
        this.setSize(1200,900);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AS/RS Systeem");
        this.setIconImage(image);
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
		
		mntmGenRoute = new JMenuItem("Genereer route");
		mntmGenRoute.addActionListener(this);
		mntmGenRoute.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, InputEvent.CTRL_MASK));
		mnBestand.add(mntmGenRoute);
		
		// bewerk artikel NOT DONE
		mntmBewerkArtikel = new JMenuItem("Bewerk artikel");
		mntmBewerkArtikel.addActionListener(this);
		mnBestand.add(mntmBewerkArtikel);
		mntmBewerkArtikel.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, InputEvent.CTRL_MASK));
		
		// bewerk artikel NOT DONE
		mntmVoegToe = new JMenuItem("Voeg artikel toe");
		mntmVoegToe.addActionListener(this);
		mnBestand.add(mntmVoegToe);
		mntmVoegToe.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, InputEvent.CTRL_MASK));


        
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(new BorderLayout(0, 0));
        
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
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
        
        genRoute = new JButton("genereer route");
        genRoute.addActionListener(this);
        panel_3.add(genRoute, "cell 0 2,alignx center,aligny top");
        
        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(new EmptyBorder(2, 0, 0, 0), "Console", TitledBorder.LEFT, TitledBorder.TOP, null, Color.DARK_GRAY));
        panel_1.add(panel_4, "cell 2 0,grow");
        
        panel_4.add(console.console());
        console.printLine("Programma is succesvol opgestart!");
        console.printLine("Selecteer een order om te beginnen!");
        panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
        
        
        JPanel panel_5 = new JPanel();
        panel_5.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
        panel.add(panel_5, BorderLayout.EAST);
        panel_5.setLayout(new MigLayout("", "[grow][]", "[grow][][]"));
        
        panel_7 = new JPanel();
        panel_7.setBorder(new MatteBorder(0, 0, 0, 1, (Color) new Color(0, 0, 0)));
        panel_5.add(panel_7, "cell 0 0,grow");
        panel_7.setLayout(new MigLayout("", "[][][]", "[][][][]"));
        
        lblDoosinfo = new JLabel("Doosinfo:");
        panel_7.add(lblDoosinfo, "cell 1 1");
        
        lblDoosEen = new JLabel("Doos 1:");
        panel_7.add(lblDoosEen, "cell 0 2");
        
        lblDoosTwee = new JLabel("Doos 2:");
        panel_7.add(lblDoosTwee, "cell 0 3");
        container.setBorder(new EmptyBorder(0, 0, 0, 0));
        

        panel_5.add(container, "cell 1 0,aligny top");
        
		container.setLayout(new MigLayout("", "[10px][]", "[][10px][][][][][][][][]"));
		orderNr = new JLabel("Ordernummer: onbekend");
		kNaam = new JLabel("Naam: onbekend");
		kAdres = new JLabel("Adres: onbekend");
		
		lblOrderinfoselecteerEenOrder = new JLabel("Orderinfo (selecteer een order om te beginnen):");
		container.add(lblOrderinfoselecteerEenOrder, "cell 1 0");
		
		container.add(orderNr, "cell 1 2");
		container.add(kAdres, "cell 1 4");
		container.add(kNaam, "cell 1 3");
		
		horizontalStrut = Box.createHorizontalStrut(300);
		container.add(horizontalStrut, "cell 0 5 2 1,growy");
		
		horizontalStrut_2 = Box.createHorizontalStrut(300);
		panel_5.add(horizontalStrut_2, "cell 0 2,alignx center,aligny bottom");
		
		drawerContainer = new JPanel();
		drawerContainer.setBorder(new MatteBorder(1, 1, 1, 0, (Color) new Color(0, 0, 0)));
		panel.add(drawerContainer, BorderLayout.CENTER);
        drawerContainer.setLayout(new MigLayout("", "[10px][grow]", "[354px,grow][]"));
        
        controller = new Controller(drawerContainer);
        PBar = new ProgressBar();
      
        drawerContainer.add(PBar.getProgressBar(), "cell 1 1,grow");
        
        setVisible(true);
	}
	
	//actionlisteners
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mntmOpenOrder) {
			
			chooser.ChooseFile();
			String ordernr = chooser.getOrderNr();
			orderNr.setText("Ordernummer: "+ordernr);
			String Naam = chooser.getKlantInfo();
			kNaam.setText("Naam: "+Naam);
			String adres = chooser.getAdres();
			kAdres.setText("Adres: "+adres);
			lblOrderinfoselecteerEenOrder.setText("Orderinfo:");
		}
		if(e.getSource()== mntmExit) {
			System.exit(0);
		}
		if(e.getSource()== mntmBewerkArtikel) {
			Edit_Product edit = new Edit_Product();
		}
		if(e.getSource()== mntmVoegToe) {
			Create_Product creator = new Create_Product();
		}
		if(e.getSource()== mntmDeleteArtikel) {
			// todo code here
		}
		if(e.getSource()== connect) {
			controller.Connect();
		}
		if(e.getSource() == startRobot){
			controller.StartRobot();
		}
		if(e.getSource()== disconnect) {
			controller.Disconnect();
		}
		if(e.getSource()== genRoute) {
			if(chooser.getProductList()== null) {
				warning.showNullpointerWarning(frame);
			} else {
			controller.startRoute(chooser.getProductList(),panel_7);
			setVisible(true);
			}
		}
		if(e.getSource() == stopRobot) {
			controller.stopRobot();
		}
		if(e.getSource() == pauseRobot) {
			
		}
		if(e.getSource() == mntmGenRoute) {
			if(chooser.getProductList()== null) {
				warning.showNullpointerWarning(frame);
			} else {
			controller.startRoute(chooser.getProductList(),panel_7);
			setVisible(true);
			}
		}
	}


}
