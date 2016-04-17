import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import java.net.URL;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Window extends JFrame implements ActionListener {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu fileButton;
	private JMenuItem openOrderButton;
	private JMenuItem exitButton;
	private JMenu algorithmButton;
	private JRadioButtonMenuItem algoOne;
	private JRadioButtonMenuItem algoTwo;
	private JRadioButtonMenuItem algoThree;
	private DrawSimulation drawsimulation;
	private JFileChooser fileChooser;
	private JButton open;

	/**
	 * Launch the application. Create new object of Database Start new Thread
	 * for Database Connection
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					Database db = new Database("host", "user", "ww");
					db.start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Window() {
		initialize();

	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("TSP Simulator");
		frame.setBounds(100, 100, 909, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			frame.setIconImage(ImageIO.read(new File("src/tux2.png")));
		} catch (IOException exc) {
			exc.printStackTrace();
		}

		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		fileButton = new JMenu("File");
		menuBar.add(fileButton);

		openOrderButton = new JMenuItem("Open Order");
		openOrderButton.addActionListener(this);
		fileButton.add(openOrderButton);

		exitButton = new JMenuItem("Exit");
		exitButton.addActionListener(this);
		fileButton.add(exitButton);

		algorithmButton = new JMenu("Algorithm");
		menuBar.add(algorithmButton);

		algoOne = new JRadioButtonMenuItem("Volledige enumeratie");
		algorithmButton.add(algoOne);

		algoTwo = new JRadioButtonMenuItem("Simpel Gretig");
		algorithmButton.add(algoTwo);

		algoThree = new JRadioButtonMenuItem("-");
		algorithmButton.add(algoThree);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));

		drawsimulation = new DrawSimulation();
		frame.add(drawsimulation);
	}

	private void parseXML() {
		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
		open = new JButton("Open");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(xmlfilter);
		fileChooser.setDialogTitle("Open XML file");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				File inputFile = new File(fileChooser.getSelectedFile().toString());
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(inputFile);
				doc.getDocumentElement().normalize();
				System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
				System.out.println("Ordernummer: " + doc.getElementsByTagName("ordernummer").item(0).getTextContent());
				System.out.println("Datum: " + doc.getElementsByTagName("datum").item(0).getTextContent());
				NodeList nListKlant = doc.getElementsByTagName("klant");
				System.out.println("----------------------------");
				for (int tempKlant = 0; tempKlant < nListKlant.getLength(); tempKlant++) {
					Node nNodeKlant = nListKlant.item(tempKlant);
					System.out.println("Current Element :" + nNodeKlant.getNodeName());
					if (nNodeKlant.getNodeType() == Node.ELEMENT_NODE) {
						Element eElementKlant = (Element) nNodeKlant;
						System.out.println(
								"Voornaam: " + eElementKlant.getElementsByTagName("voornaam").item(0).getTextContent());
						System.out.println("Achternaam: "
								+ eElementKlant.getElementsByTagName("achternaam").item(0).getTextContent());
						System.out.println(
								"Adres: " + eElementKlant.getElementsByTagName("adres").item(0).getTextContent());
						System.out.println(
								"Postcode: " + eElementKlant.getElementsByTagName("postcode").item(0).getTextContent());
						System.out.println(
								"Plaats: " + eElementKlant.getElementsByTagName("plaats").item(0).getTextContent());
					}
				}
				NodeList nListArtiekelnr = doc.getElementsByTagName("artikelnr");
				for (int tempArtiekelnr = 0; tempArtiekelnr < nListArtiekelnr.getLength(); tempArtiekelnr++) {
					System.out.println("artikelnr: "
							+ doc.getElementsByTagName("artikelnr").item(tempArtiekelnr).getTextContent());
				}
			} catch (Exception f) {
				System.out.println("Wrong syntax xml file");
			}
		} else if (result == JFileChooser.CANCEL_OPTION) {
			System.out.println("Cancel was selected");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openOrderButton) {
			FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
			open = new JButton("Open");
			fileChooser = new JFileChooser();
			fileChooser.setFileFilter(xmlfilter);
			fileChooser.setDialogTitle("Open schedule file");
			fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					String selectedFile = fileChooser.getSelectedFile().toString();
					File fXmlFile = new File(selectedFile);
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);
					doc.getDocumentElement().normalize();
					System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					NodeList nList = doc.getElementsByTagName("staff");
					System.out.println("----------------------------");
					for (int temp = 0; temp < nList.getLength(); temp++) {

						Node nNode = nList.item(temp);

						System.out.println("\nCurrent Element :" + nNode.getNodeName());

						if (nNode.getNodeType() == Node.ELEMENT_NODE) {

							Element eElement = (Element) nNode;

							System.out.println("Staff id : " + eElement.getAttribute("id"));
							System.out.println("First Name : "
									+ eElement.getElementsByTagName("firstname").item(0).getTextContent());
							System.out.println("Last Name : "
									+ eElement.getElementsByTagName("lastname").item(0).getTextContent());
							System.out.println("Nick Name : "
									+ eElement.getElementsByTagName("nickname").item(0).getTextContent());
							System.out.println(
									"Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

						}
					}
					System.out.println("----------------------------");
				} catch (Exception f) {
					f.printStackTrace();
				}

			} else if (result == JFileChooser.CANCEL_OPTION) {
				System.out.println("Cancel was selected");
			}

			parseXML();
		}

		if (e.getSource() == exitButton) {
			System.exit(0);
		}

	}

}
