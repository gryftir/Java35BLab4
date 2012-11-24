package lab4;

import java.util.ArrayList;

public class SimulationManager {

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
	sm.runSim();

	}
	protected ArrayList<Long> runSim()
	{
		this.val.expresslaneitemlimit = this.val.startofrange;
		ArrayList<Long> values = new ArrayList<Long>();
		pb = new MyProgressBar(val);
		int sessioncount = this.val.endofrange - this.val.startofrange + 1;
		for (int index = 0; this.val.expresslaneitemlimit <= this.val.endofrange; this.val.expresslaneitemlimit++, index++)
		{
			
			SessionManager sm = new SessionManager(val);
			sm.start();
			while (!sm.isFinished())
			{
				pb.setProgress((index/sessioncount) + (sm.getProgress()/sessioncount)); //I may need to check this to make sure it works.  May be easier to run all threads simultaneously, and poll them all in a loop
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
	
}
