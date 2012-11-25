package lab4;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Insets;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.*;



@SuppressWarnings("serial")
public class MyProgressBar extends JPanel implements PropertyChangeListener {

	private JPanel jp;

	private JProgressBar progressBar;
	private JTextArea taskOutput;
	private Task task;
	
	
	class Task extends SwingWorker<Void, Void> 
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
					Thread.sleep(1000);
				}
				catch (InterruptedException ignore)
				{
					
				}
				setProgress(getProg());
			}
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
		
		taskOutput = new JTextArea(5, 20);
		taskOutput.setMargin(new Insets(5, 5, 5, 5));
		taskOutput.setEditable(false);

		//add(new JScrollPane(taskOutput), BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		
	}
	public void propertyChange(PropertyChangeEvent evt) 
	{
		if ("progress" == evt.getPropertyName()) 
		{
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
			taskOutput.append(String.format("Completed %d%% of task.\n", task
					.getProgress()));
		}
		}
	protected void runProgressBar()
	{
	
		// Create and set up the content pane.
		task = new Task();
		task.addPropertyChangeListener(this);
		task.execute();
		jp = new JPanel();
		JFrame jf = new JFrame("Progress Bar");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jp.add(progressBar, BorderLayout.PAGE_START);
		//jp.pack();
		jp.setVisible(true);
		jp.setOpaque(true);
		jf.setSize(200,100);
		jf.add(jp);
		//jf.pack();
		jf.setVisible(true);
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		
	}
	
	protected int getProgress() {
		return task.getProg();
	}
	protected void setProgress(int progress) {
		task.setProg(progress);
	}
		
	
}
