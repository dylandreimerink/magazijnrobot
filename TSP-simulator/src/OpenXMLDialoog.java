import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class OpenXMLDialoog extends JDialog implements ActionListener{
	
	private boolean isOk;
	private JFileChooser fileChooser;
	
	private void initialize(){
		setTitle("Selecteer Order");
		setSize(780, 458);
		try {
			this.setIconImage(ImageIO.read(new File("src/tux2.png")));
		} catch (IOException exc) {
			exc.printStackTrace();
		}
		

		FileNameExtensionFilter xmlfilter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(xmlfilter);
		fileChooser.setDialogTitle("Open schedule file");
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		getContentPane().add(fileChooser);
		
		this.setVisible(true);
	}
	
	
	
	public OpenXMLDialoog(JFrame frame){
		super(frame, true);
		initialize();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    // user selects a file
		}	
	}
	
}
