package tspSimulator;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 * Authors: Jan Willem en Henri Class: ICTM2A
 */

public class Window extends JFrame implements ActionListener {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu fileButton;
	private JMenuItem openOrderButton;
	private JMenuItem exitButton;

	private JButton start;
	private JTextField textfieldHoogte;
	private JTextField textfieldBreedte;
	private JLabel labelHoogte;
	private JLabel labelBreedte;

	/**
	 * Launch the application. Create new object of Database Start new Thread
	 * for Database Connection
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					// Database db = new Database("host", "user", "ww");
					// Thread t = new Thread(db);
					// t.start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Window() {
		frame = new JFrame();
		frame.setTitle("TSP Simulator");
		frame.setBounds(100, 100, 1920 / 2, 1020 / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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

		start = new JButton("Start");
		start.addActionListener(this);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		getContentPane().add(start);

		labelHoogte = new JLabel("Hoogte");
		getContentPane().add(labelHoogte);
		textfieldHoogte = new JTextField(10);
		getContentPane().add(textfieldHoogte);
		labelBreedte = new JLabel("Breedte");
		getContentPane().add(labelBreedte);
		textfieldBreedte = new JTextField(10);
		getContentPane().add(textfieldBreedte);
		frame.setVisible(true);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == openOrderButton) {
			// parseXML();
		}
		if (e.getSource() == exitButton) {
			System.exit(0);
		}
		if (e.getSource() == start) {

		}

	}

}
