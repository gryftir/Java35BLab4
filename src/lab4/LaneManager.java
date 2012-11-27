package lab4;

import java.util.ArrayList;
import java.util.Random;

public class LaneManager extends Thread {

	private SimVal val;
	private CustomerFactory factory;
	private ArrayList<Lane> laneAry;
	private Random generator;
	private long time;
	private int customersprocessedcount;

	// TODO: random short intervals to produce customers.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimVal val = new SimVal();
		val.cartsizemax = 20;
		val.customersnum = 10;
		val.expresslaneitemlimit = 7;
		val.expresslanenum = 2;
		val.lanenum = 10;
		val.startofrange = 5;
		val.endofrange = 10;
		LaneManager lm = new LaneManager(val);
		lm.run();
		while (lm.time == 0)
		{
			
		}
		System.out.println("return result from running lanes is: " + lm.time);
	}

	public LaneManager(SimVal val) {
		this.val = val;
		factory = new CustomerFactory(this.val);
		laneAry = new ArrayList<Lane>();
		this.buildLanes();
		this.generator = new Random();
		this.customersprocessedcount = 0;

	}

	private void buildLanes() {

		int i;
		for (i = 0; i < this.val.expresslanenum; i++) {
			laneAry.add(i, new Lane(true));
		}
		for (i = this.val.expresslanenum; i < this.val.lanenum; i++) {
			laneAry.add(i, new Lane(false));
		}
	}
	 synchronized public int getLanesProccessedCount()
	 {
		 int size = laneAry.size();
		 int count = 0;
		 for (int i = 0; i <  size; i++)
		 {
			 count += laneAry.get(i).getCustomerprocessedcount();
		 }
		 return count;
	 }
	public void run() {
		long delay;
		long start;
		int random;
		for (int i = 0; i < laneAry.size(); i++)
		{
			laneAry.get(i).start();
		}
		for (int i = 0; i < this.val.customersnum; i++) {
			start = System.currentTimeMillis();
			random = this.generator.nextInt(this.val.cartsizemax) + 1;
			delay = random * 20;
			while (start + delay > System.currentTimeMillis()) {
			this.setCustomersprocessedcount(this.getLanesProccessedCount());	
			}
			addCustomer(i);
			System.out.println("Customer: " + i);
		}
		boolean allfinished = false;
		while (!allfinished)
		{
			int count = this.val.lanenum;
			for (int j = this.val.lanenum - 1; j >= 0; j--)
			{
				this.setCustomersprocessedcount(this.getLanesProccessedCount());
				if (laneAry.get(j).QueueEmpty())
				{
					count--;
				}
			}
			if (count == 0)
				allfinished = true;
		}
		long time = 0;
		for (int i = 0; i < laneAry.size(); i++) {
			time += laneAry.get(i).getTotallanetime();
			laneAry.get(i).setStop(true);
		}
		this.time = time;
		
		return;
	}

	private void addCustomer(int customernum) {
		int index;
		Customer c = factory.makeCustomer();
		c.setCustomernumber(customernum);
		int smallestnumberofitems;
		int currentitems;
		int lanetoaddto = -1;
		if (this.val.expresslanenum > 0) // there are express lanes
		{
			if (c.size() <= this.val.expresslaneitemlimit) { // goes into
																// express lane
				lanetoaddto = 0;
				smallestnumberofitems = laneAry.get(0).getCurrentItems();
				for (index = 1; index < this.val.expresslanenum; index++) {
					currentitems = laneAry.get(index).getCurrentItems();
					if (currentitems < smallestnumberofitems) {
						smallestnumberofitems = currentitems;
						lanetoaddto = index;
					}

				}
			} else // doesn't go into express lane
			{
				smallestnumberofitems = laneAry.get(this.val.expresslanenum)
						.getCurrentItems();
				lanetoaddto = this.val.expresslanenum;
				for (index = this.val.expresslanenum + 1; index < this.val.lanenum; index++) {
					currentitems = laneAry.get(index).getCurrentItems();
					if (currentitems < smallestnumberofitems) {
						smallestnumberofitems = currentitems;
						lanetoaddto = index;
					}
				}
			}
		} else // no express lanes
		{
			lanetoaddto = 0;
			smallestnumberofitems = laneAry.get(0).getCurrentItems();
			for (index = 1; index < this.val.lanenum; index++) {
				currentitems = laneAry.get(index).getCurrentItems();
				if (currentitems < smallestnumberofitems) {
					smallestnumberofitems = currentitems;
					lanetoaddto = index;
				}
			}
		}
		if (lanetoaddto == -1) {
			System.out.println("error with lane manager add customer logic");
			return;
		} else
			laneAry.get(lanetoaddto).AddtoLane(c);
		return;
	}

	protected long getTime() {
		return time;
	}

	protected void setTime(long time) {
		this.time = time;
	}

	synchronized protected int getCustomersprocessedcount() {
		return customersprocessedcount;
	}

	synchronized protected void setCustomersprocessedcount(int customersaddedcount) {
		this.customersprocessedcount = customersaddedcount;
	}
	protected void stopLanes()
	{
		for (int index = 0; index < laneAry.size(); index++)
			laneAry.get(index).setStop(true);
	}

}
