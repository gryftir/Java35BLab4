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
		sm.runSim();
		while(sm.allsessionfinished())
		{
		}
		this.data.setTimingresults(sm.getValues());
		this.setFinished(true);
		return;
	}
	protected Business(SimVal val) {
		this.val = val;
		this.sm = new SimulationManager(val);
		this.data = new Data(val);
	}
	protected boolean isFinished() {
		return finished;
	}
	protected void setFinished(boolean finished) {
		this.finished = finished;
	}
	

}
