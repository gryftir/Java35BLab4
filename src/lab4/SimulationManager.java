package lab4;

import java.util.ArrayList;

import javax.swing.SwingWorker;

public class SimulationManager extends SwingWorker<ArrayList<Long>, Void>{

	MyProgressBar pb;
	SimVal val;

	
	public static void main(String[] args) {
		SimVal val = new SimVal();
		val.cartsizemax = 20;
		val.customersnum = 10;
		val.expresslaneitemlimit = 7;
		val.expresslanenum = 2;
		val.lanenum = 10;
		val.startofrange = 5;
		val.endofrange = 10;
	SimulationManager sm = new SimulationManager(val);
	ArrayList<Long> list =  sm.runSim();
	for (int i = 0; i < list.size(); i++)
	{ 
		System.out.println (list.get(i));
	}

	}
	protected ArrayList<Long> runSim()
	{
		this.val.expresslaneitemlimit = this.val.startofrange;
		ArrayList<Long> values = new ArrayList<Long>();
		pb = new MyProgressBar();
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				pb.runProgressBar();
			}
		});
		int sessioncount = this.val.endofrange - this.val.startofrange + 1;
		for (int index = 0; this.val.expresslaneitemlimit <= this.val.endofrange; this.val.expresslaneitemlimit++, index++)
		{
			//note if I do this concurrently, clone val edit: done
			SessionManager sm = new SessionManager(val.clone());
			sm.start();
			while (!sm.isFinished())
			{
				System.out.println ("index: " + index + " sm.getProgress(): " + sm.getProgress() + " " + sessioncount);
				pb.setProgress((int)( (index/(float)sessioncount * 100) + (sm.getProgress()/(float)sessioncount  ) )); //I may need to check this to make sure it works.  May be easier to run all threads simultaneously, and poll them all in a loop
				//System.out.println("current progress is: " + sm.getProgress());
			}
			Long l = new Long(sm.getTime());
			values.add(index, l);	
			sm = null;
		}
		return values;
	}
	public SimulationManager(SimVal val)
	{
		this.val = val;
		
	}
	@Override
	protected ArrayList<Long> doInBackground() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
