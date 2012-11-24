package lab4;


public class SessionManager extends Thread {

	private LaneManager lm;
	private SimVal val;
	private int customerprocessedcount;
	private long time;
	private int progress;
	private boolean finished;

	public static void main(String[] args) {

	}

	public SessionManager(SimVal val) {
		this.lm = new LaneManager(val);
		this.customerprocessedcount = 0;
	}

	public void run() {
		this.setFinished(false);
		lm.runLanes();
		while (lm.getTime() == 0) {
			setCustomerprocessedcount(lm.getCustomersprocessedcount());
			setProgress(this.getCustomerprocessedcount()/val.customersnum);
		}
		this.setFinished(true);
	}

	 protected synchronized int getCustomerprocessedcount() {
		return customerprocessedcount;
	}

	synchronized protected void setCustomerprocessedcount(int customeraddedcount) {
		this.customerprocessedcount = customeraddedcount;
	}

	protected Void doInBackground() throws Exception {
		lm.runLanes();
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
