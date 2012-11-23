package lab4;

import java.util.ArrayList;
import java.util.Random;

public class LaneManager {

	Boolean run;
	SimVal val;
	CustomerFactory factory;
	ArrayList<Lane> laneAry;
	private Random generator;

	// TODO: random short intervals to produce customers.
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public LaneManager(SimVal val) {
		this.val = val;
		factory = new CustomerFactory(this.val);
		laneAry = new ArrayList<Lane>();
		this.buildLanes();
		this.generator = new Random();

	}

	private void buildLanes() {

		int i;
		for (i = 0; i < this.val.expresslanenum; i++) {
			laneAry.add(i, new Lane(true, run));
		}
		for (i = this.val.expresslanenum; i < this.val.lanenum; i++) {
			laneAry.add(i, new Lane(false, run));
		}
	}

	public long runLanes() {
		long delay;
		long start;
		int random;
		for (int i = 0; i < this.val.customersnum; i++) {
			start = System.currentTimeMillis();
			random = this.generator.nextInt(this.val.cartsizemax) + 1;
			delay = random * 100;
			while (start + delay > System.currentTimeMillis()) {

			}
			addCustomer();
		}
		run = false;
		long time = 0;
		for (int i = 0; i < laneAry.size(); i++) {
			time = +laneAry.get(i).getTotallanetime();
		}
		return time;
	}

	private void addCustomer() {
		int index;
		Customer c = factory.makeCustomer();
		int smallestnumberofitems;
		int currentitems;
		int lanetoaddto = -1;
		if (this.val.expresslanenum > 0)  // there are express lanes 
		{
			if (c.size() <= this.val.expresslaneitemlimit) { //goes into express lane
				lanetoaddto = 0;
				smallestnumberofitems = laneAry.get(0).getCurrentItems();
				for (index = 1; index < this.val.expresslanenum; index++) {
					currentitems = laneAry.get(index).getCurrentItems();
					if (currentitems < smallestnumberofitems)
					{
						smallestnumberofitems = currentitems;
						lanetoaddto = index;
					}
					
				}
			}
			else //doesn't go into express lane
			{
				smallestnumberofitems = laneAry.get(this.val.expresslanenum).getCurrentItems();
				lanetoaddto = this.val.expresslanenum;
				for (index = this.val.expresslanenum + 1; index < this.val.lanenum; index++) {
					currentitems = laneAry.get(index).getCurrentItems();
					if (currentitems < smallestnumberofitems)
					{
						smallestnumberofitems = currentitems;
						lanetoaddto = index;
					}
				}
			}
		}
		else //no express lanes
		{
			lanetoaddto = 0;
			smallestnumberofitems = laneAry.get(0).getCurrentItems();
			for (index = 1; index < this.val.lanenum; index++) {
				currentitems = laneAry.get(index).getCurrentItems();
				if (currentitems < smallestnumberofitems)
				{
					smallestnumberofitems = currentitems;
					lanetoaddto = index;
				}
			}
		}
		if (lanetoaddto == -1)
		{
			System.out.println("error with lane manager add customer logic");
			return;
		}
		else
			laneAry.get(lanetoaddto).AddtoLane(c);
	return;	
	}

}
