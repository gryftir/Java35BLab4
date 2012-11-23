package lab4;
import java.lang.Thread;

public class Customer extends Thread {

	private Cart cart;
	private long starttime;
	private long totaltime;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Customer(int itemnum)
	{
		this.cart = new Cart(itemnum);
	}

	public synchronized long getStarttime() {
		return starttime;
	}

	public synchronized void setStarttime(long starttime) {
		this.starttime = starttime;
	}

	public synchronized long getTotaltime() {
		return totaltime;
	}

	public synchronized void setTotaltime(long totaltime) {
		this.totaltime = totaltime;
	}
	public int size()
	{
		return cart.size();
	}
	
	public void run()
	{
		try{
			Thread.sleep(500 * cart.size());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		this.setTotaltime(System.currentTimeMillis() - this.getStarttime());
	}	
}
