
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
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
				try{
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
							System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
							System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
							System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
							System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

						}
					}
					System.out.println("----------------------------");
				} catch (Exception f) {
						f.printStackTrace();
				}
				
				
			} else if (result == JFileChooser.CANCEL_OPTION) {
			    System.out.println("Cancel was selected");
			}
			
			
		}
		
		if (e.getSource() == exitButton) {
			System.exit(0);
		}
		
	}

}

