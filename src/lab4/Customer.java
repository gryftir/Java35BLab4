package lab4;
import java.lang.Thread;

public class Customer extends Thread {

	private Cart cart;
	private double starttime;
	private double totaltime;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Customer(int itemnum)
	{
		this.cart = new Cart(itemnum);
	}

	public synchronized double getStarttime() {
		return starttime;
	}

	public synchronized void setStarttime(double starttime) {
		this.starttime = starttime;
	}

	public synchronized double getTotaltime() {
		return totaltime;
	}

	public synchronized void setTotaltime(double totaltime) {
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
