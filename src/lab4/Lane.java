package lab4;
import java.lang.Thread;
import java.util.*;

public class Lane extends Thread {

	public final boolean Express;
	private LinkedList <Customer> queue;
	
	public static void main(String[] args) {
	

	}


	protected Lane(boolean express)
	{
	this.Express = express;
	this.queue = new LinkedList<Customer>();
	}
	synchronized public void AddtoLane(Customer cust)
	{
		queue.addFirst(cust);
		
	}
//TODO synchronized function to add object
//TODO synchronized function to return current size of Queue
//TODO needs a synchronized way to update data
}
//customer.start();  
//customer.join();
//System.currentTimeMillis();