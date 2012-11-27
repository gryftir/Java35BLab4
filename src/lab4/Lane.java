package lab4;

import java.lang.Thread;
import java.util.*;

public class Lane extends Thread {

	public final boolean Express;
	private LinkedList<Customer> queue;
	private long totallanetime;
	private int currentitems;
	private int customerprocessedcount;
	private boolean stop = false;

	public static void main(String[] args) {
		
	}

	protected Lane(boolean express) {
		this.Express = express;
		this.queue = new LinkedList<Customer>();
		this.totallanetime = 0;
		this.customerprocessedcount = 0;
	}

	synchronized public void AddtoLane(Customer cust) {
		cust.setStarttime(System.currentTimeMillis());
		setCurrentItems(getCurrentItems() + cust.size());
		queue.addFirst(cust);
	}

	synchronized protected long getTotallanetime() {
		return totallanetime;
	}

	synchronized protected void setTotallanetime(long d) {
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
	long printtime = c.getTotaltime();
	this.setTotallanetime(this.getTotallanetime() + printtime);
	System.out.println("time is: "+ printtime);
	if (c.getTotaltime() <= 0)
	{
		System.out.println("error with processing total time from a customer.");
	}
	this.setCustomerprocessedcount(this.getCustomerprocessedcount() + 1);
	c = null;
}
public void run()
{
		System.out.println("starting Lane");
		while(!stop)
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

protected boolean isExpress() {
	return Express;
}

protected synchronized int getCustomerprocessedcount() {
	return customerprocessedcount;
}

protected synchronized void setCustomerprocessedcount(int customerprocessedcount) {
	this.customerprocessedcount = customerprocessedcount;
}

protected void setStop(boolean stop) {
	this.stop = stop;
}
}
// customer.start();
// customer.join();
// System.currentTimeMillis();