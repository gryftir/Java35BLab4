package lab4;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;



@SuppressWarnings("serial")
public class MyProgressBar extends JPanel implements PropertyChangeListener {

	private JFrame jf;

	private JProgressBar progressBar;
	private Task task;
	
	
	private class Task extends SwingWorker<Void, Void> 
	{
		int prog = 0;
		// Main task. Executed in background thread.
		public Void doInBackground() 
		{
			// Initialize progress property.
			setProgress(0);
			while (prog < 100) 
			{
				try{
					Thread.sleep(10);
				}
				catch (InterruptedException ignore)
				{
					
				}
				setProgress(getProg());
			}
			setProgress(100);
			return null;
		}
		public void done()
		{
			
		}
		protected int getProg() {
			return prog;
		}
		protected void setProg(int prog) {
			this.prog = prog;
		}
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	protected MyProgressBar()
	{
		super(new BorderLayout());
		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		progressBar.setStringPainted(true);
		progressBar.setVisible(true);
		

		//add(new JScrollPane(taskOutput), BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
	}
	public void propertyChange(PropertyChangeEvent evt) 
	{
		if ("progress" == evt.getPropertyName()) 
		{
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			if (progress == 100)
			{
				//delay approxximately 10 seconds, then hide the progress bar
				//long start = System.currentTimeMillis();
				//while (start + 10000 < System.currentTimeMillis())
				//{
					
				//}
				//this.setVis(false);
			}
		}
	}
	protected void runProgressBar(JFrame jframe)
	{
	
		// Create and set up the content pane.
		JPanel jp = new JPanel();
		this.jf = jframe;
		this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jp.add(progressBar, BorderLayout.PAGE_START);
		jp.setVisible(true);
		jp.setOpaque(true);
		jf.setSize(400,100);
		jf.add(jp);
		jp.setVisible(true);
		//jf.pack();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
	}
	
	protected int getProgress() {
		if (task != null)
		{
		return task.getProg();
		}
		else return -1;
	}
	protected void setProgress(int progress) {
	if (task != null)
		{
			task.setProg(progress);
		}
		//progressBar.setValue(progress);
	//	progressBar.repaint();
	//	jf.repaint();
	//	this.repaint();
	}
		public void setVis(boolean aFlag)
		{
			this.jf.setVisible(aFlag);
		}
		
	
}
