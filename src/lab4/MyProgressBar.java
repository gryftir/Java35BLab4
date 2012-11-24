package lab4;

import javax.swing.*;

public class MyProgressBar extends JPanel {

	private int progress;
	private double progresspercent;
	private SimVal val;
	private JProgressBar progressBar;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	protected MyProgressBar(SimVal val)
	{
		super(new java.awt.BorderLayout());
		this.val = val;
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		JPanel panel = new JPanel();
		panel.add(progressBar);

		add(panel, java.awt.BorderLayout.PAGE_START);
		
	}
	protected void runProgressBar()
	{
		JFrame frame = new JFrame("ProgressBar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = this;
		newContentPane.setOpaque(true);
		frame.setContentPane(newContentPane);
		frame.pack();
		frame.setVisible(true);
	}
	protected synchronized int getProgress() {
		return progress;
	}
	protected synchronized void setProgress(int progress) {
		this.progress = progress;
	}
	
}
