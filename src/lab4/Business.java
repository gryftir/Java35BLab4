package lab4;

import java.util.ArrayList;

public class Business extends Thread {

	private SimVal val;
	private SimulationManager sm;
	private boolean finished = false;
	private Data data;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimVal val = new SimVal();
		val.cartsizemax = 20;
		val.customersnum = 10;
		val.expresslaneitemlimit = 7;
		val.expresslanenum = 2;
		val.lanenum = 10;
		val.startofrange = 9;
		val.endofrange = 10;
		Business b = new Business(val);
		b.run();
		//for (int index = 0; index < l.size(); index++)
		//{
		//	System.out.println(l.get(index));
		//}
	}
	public void run ()
	{
		sm.execute();
		while(!sm.isFinished())
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.data.setTimingresults(sm.getValues());
		this.setFinished(true);
		return;
	}
	protected Business(SimVal val) {
		this.val = val;
		this.sm = new SimulationManager(this.val);
		this.data = new Data(val);
	}
	protected boolean isFinished() {
		return finished;
	}
	protected void setFinished(boolean finished) {
		this.finished = finished;
	}
	protected ArrayList<Long> getResults()
	{
		return this.data.getTimingresults();
	}
	protected ArrayList<Integer> getLimits()
	{
		return this.data.getExpresslanelimits();
	}
	protected int indexoffastestlane()
	{
		ArrayList<Long> results = this.data.getTimingresults();
		int size = results.size();
		Long minval = results.get(0);
		int minvalindex = 0;
		for (int index = 1; index < size; index++)
		{
			if (results.get(index) < minval)
			{
				minval = results.get(index);
				minvalindex = index;
			}
		}
		return minvalindex;
	}
	protected ArrayList<Integer> getExpresslanelimits() {
		return this.data.getExpresslanelimits();
	}


	protected ArrayList<Long> getTimingresults() {
		return this.data.getTimingresults();
	}
	protected int getProgress() {
		return sm.getProgress();
	}

}
