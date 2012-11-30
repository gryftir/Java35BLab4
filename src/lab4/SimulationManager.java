package lab4;

import java.util.ArrayList;

import javax.swing.SwingWorker;

public class SimulationManager extends SwingWorker<ArrayList<Long>, Void>{

	private MyProgressBar pb;
	private SimVal val;
	private ArrayList<SessionManager> smary;
	private ArrayList<Long> values;
	private boolean finished;
	
	public static void main(String[] args) {
		SimVal val = new SimVal();
		val.cartsizemax = 20;
		val.customersnum = 20;
		val.expresslaneitemlimit = 7;
		val.expresslanenum = 2;
		val.lanenum = 10;
		val.startofrange = 9;
		val.endofrange = 10;
	SimulationManager sm = new SimulationManager(val);
	sm.runSim();
	for (int i = 0; i < sm.values.size(); i++)
	{ 
		System.out.println (sm.values.get(i));
	}

	}
	protected void runSim()
	{
		this.val.expresslaneitemlimit = this.val.startofrange;
		
		for (int index = 0; this.val.expresslaneitemlimit <= this.val.endofrange; this.val.expresslaneitemlimit++, index++)
		{
			//note if I do this concurrently, clone val edit: done
			smary.add(index, new SessionManager(val.clone()));
			smary.get(index).start();
		}
		while (!this.allsessionfinished())
		{	
			/*try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//I may need to check this to make sure it works.  May be easier to run all threads simultaneously, and poll them all in a loop
			//System.out.println("current progress is: " + sm.getProgress());
		}
		for (int index = 0; index < smary.size(); index++)
		{
			Long l =smary.get(index).getTime();
			values.add(index, l);
		}	
		this.setFinished(true);
		
	}
	public SimulationManager(SimVal val)
	{
		this.val = val;
		this.smary = new ArrayList<SessionManager>();
		this.values = new ArrayList<Long>();
		this.finished = false;
		
	}
	@Override
	protected ArrayList<Long> doInBackground() throws Exception {
		this.runSim();
		return null;
	}
	public boolean allsessionfinished()
	{
		boolean finished = true;
		int index = 0;
		while ((index < smary.size()) && finished == true)
		{
			if (!(smary.get(index).isFinished()))
			{
				finished = false;
			}
			index++;
		}
			return finished;
	}
	 int sessionprogress()
	{
		int progress = 0;
		for (int index = 0; index < smary.size(); index++)
		{
			progress += smary.get(index).getProgress();
		}
		return progress/smary.size();
	}
	protected ArrayList<Long> getValues() {
		return values;
	}
	protected void setValues(ArrayList<Long> values) {
		this.values = values;
	}
	protected boolean isFinished() {
		return finished;
	}
	protected void setFinished(boolean finished) {
		this.finished = finished;
	}
	synchronized protected void setProg(int number)
	{
		pb.setProgress(number);
	}
}
