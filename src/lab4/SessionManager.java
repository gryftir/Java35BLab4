package lab4;


public class SessionManager extends Thread {

	private LaneManager lm;
	private SimVal val;
	private int customerprocessedcount;
	private long time;
	private int progress;
	private boolean finished;

	public static void main(String[] args) {
		SimVal val = new SimVal();
		val.cartsizemax = 20;
		val.customersnum = 10;
		val.expresslaneitemlimit = 7;
		val.expresslanenum = 2;
		val.lanenum = 10;
		val.startofrange = 5;
		val.endofrange = 10;
		SessionManager sm = new SessionManager(val);
		sm.start();
		
	}

	public SessionManager(SimVal val) {
		this.lm = new LaneManager(val);
		this.customerprocessedcount = 0;
		this.val = val;
	}

	public void run() {
		this.setFinished(false);
		lm.start();
		while (lm.getTime() == 0) {
			setCustomerprocessedcount(lm.getCustomersprocessedcount());
			setProgress((int)(getCustomerprocessedcount()/(float)this.val.customersnum * 100));
		}
		this.setTime(lm.getTime());
		this.setFinished(true);
	}

	 protected synchronized int getCustomerprocessedcount() {
		return customerprocessedcount;
	}

	synchronized protected void setCustomerprocessedcount(int customeraddedcount) {
		this.customerprocessedcount = customeraddedcount;
	}

	protected Void doInBackground() throws Exception {
		lm.start();
		while (lm.getTime() == 0) {
	
				setCustomerprocessedcount(lm.getCustomersprocessedcount());
				setProgress(this.getCustomerprocessedcount()/val.customersnum);
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

	protected synchronized int getProgress() {
		return progress;
	}

	protected synchronized void setProgress(int progress) {
		this.progress = progress;
	}

	protected synchronized boolean isFinished() {
		return finished;
	}

	protected synchronized void setFinished(boolean finished) {
		this.finished = finished;
	}
	

}
