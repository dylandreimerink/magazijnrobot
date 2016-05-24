package asrsSystem;

import javax.swing.JProgressBar;

public class ProgressBar {
	private JProgressBar progressBar;
	
	

	public void createProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
	}
	
	public JProgressBar getProgressBar() {
		createProgressBar();
		return progressBar;
		
	}
	
	public void updatePBar(int value) {
		if(value > 0 && value <= 100) {
			progressBar.setValue(value);
		}
	}
}
