package tspSimulator;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */

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

	/**
	 * Launch the application. Create new object of Database Start new Thread
	 * for Database Connection
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					//Database db = new Database("host", "user", "ww");
					//Thread t = new Thread(db);
					//t.start();
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
			// parseXML();
		}
		if (e.getSource() == exitButton) {
			System.exit(0);
		}

	}

}
