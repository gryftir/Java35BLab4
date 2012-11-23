package lab4;

import javax.swing.*;
import java.awt.*;

public class SessionManager extends SwingWorker<Void, Void> {

	private LaneManager lm;
	private SimVal val;
	private int customeraddedcount;
	private long time;

	public static void main(String[] args) {

	}

	public SessionManager(SimVal val) {
		this.lm = new LaneManager(val);
		this.customeraddedcount = 0;
	}

	public long runLanes() {
		lm.runLanes();
		while (lm.getTime() == 0) {
			setCustomeraddedcount(lm.getCustomersaddedcount());
		}
		return lm.getTime();
	}

	 protected synchronized int getCustomeraddedcount() {
		return customeraddedcount;
	}

	synchronized protected void setCustomeraddedcount(int customeraddedcount) {
		this.customeraddedcount = customeraddedcount;
	}

	protected Void doInBackground() throws Exception {
		lm.runLanes();
		while (lm.getTime() == 0) {
	
				setCustomeraddedcount(lm.getCustomersaddedcount());
				setProgress(this.getCustomeraddedcount()/val.customersnum);
			}
		setTime(lm.getTime());
		return null;
	}

	protected synchronized long getTime() {
		return time;
	}

	protected synchronized void setTime(long time) {
		this.time = time;
	}

}
