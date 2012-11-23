package lab4;

import java.lang.Thread;
import java.util.*;

public class Lane extends Thread {

	public final boolean Express;
	private LinkedList<Customer> queue;
	private double totallanetime;
	private int currentitems;
	private Boolean run;

	public static void main(String[] args) {

	}

	protected Lane(boolean express, Boolean run) {
		this.Express = express;
		this.queue = new LinkedList<Customer>();
		this.run = run;
		this.totallanetime = 0;
	}

	synchronized public void AddtoLane(Customer cust) {
		cust.setStarttime(System.currentTimeMillis());
		setCurrentItems(getCurrentItems() + cust.size());
		queue.addFirst(cust);
	}

	synchronized protected double getTotallanetime() {
		return totallanetime;
	}

	synchronized protected void setTotallanetime(double d) {
		this.totallanetime = d;
	}

	synchronized int getCurrentItems() {
		return currentitems;
	}

	synchronized void setCurrentItems(int num) {
		this.currentitems = num;
	}
synchronized void processCustomer()
{
	Customer c = this.pollLast();
	c.start();
	try
	{
		c.join();
	}
	catch (InterruptedException e)
	{
		e.printStackTrace();
	}
	this.setCurrentItems(this.getCurrentItems() - c.size());
	this.setTotallanetime(this.getTotallanetime() + c.getTotaltime());
	c = null;
}
public void run()
{
		while(this.run)
		{
			if (!this.QueueEmpty())
			{
				processCustomer();
			}
		}
}
synchronized public boolean QueueEmpty()
{
	return this.queue.isEmpty();
}
synchronized public Customer pollLast()
{
	return this.queue.pollLast();
}
}
// customer.start();
// customer.join();
// System.currentTimeMillis();