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
		for (int index = 0; this.val.expresslaneitemlimit <= this.val.endofrange; this.val.expresslaneitemlimit++, index++)
		{
			SessionManager sm = new SessionManager(val);
			long num = sm.runLanes();
			values.add(index, new Long(num));
		}
		return values;
	}
	public SimulationManager(SimVal val)
	{
		this.val = val;
	}
	
}
