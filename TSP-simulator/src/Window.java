import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.imageio.ImageIO;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Canvas;

public class Window {

	private JFrame frame;

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

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu fileButton = new JMenu("File");
		menuBar.add(fileButton);

		JMenuItem openOrderButton = new JMenuItem("Open Order");
		fileButton.add(openOrderButton);

		JMenuItem exitButton = new JMenuItem("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		fileButton.add(exitButton);

		JMenu algorithmButton = new JMenu("Algorithm");
		menuBar.add(algorithmButton);

		JRadioButtonMenuItem algoOne = new JRadioButtonMenuItem("Volledige enumeratie");
		algorithmButton.add(algoOne);

		JRadioButtonMenuItem algoTwo = new JRadioButtonMenuItem("Simpel Gretig");
		algorithmButton.add(algoTwo);

		JRadioButtonMenuItem algoThree = new JRadioButtonMenuItem("-");
		algorithmButton.add(algoThree);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.X_AXIS));
		
		DrawSimulation drawsimulation = new DrawSimulation();
		frame.add(drawsimulation);
	}

}
