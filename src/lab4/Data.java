package lab4;

import java.util.ArrayList;

public class Data {

ArrayList<Integer> expresslanelimits;
ArrayList<Long> timingresults;
SimVal val;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	protected Data(SimVal val) {
		this.expresslanelimits = new ArrayList<Integer>();
		int rangeval = this.val.startofrange; 
		for (int index = 0; index < (this.val.endofrange - this.val.startofrange + 1); index++, rangeval++)
		{
			Integer i = rangeval;
			expresslanelimits.add(index, i);
		}
	}

	protected ArrayList<Integer> getExpresslanelimits() {
		return expresslanelimits;
	}

	protected void setExpresslanelimits(ArrayList<Integer> expresslanelimits) {
		this.expresslanelimits = expresslanelimits;
	}

	protected ArrayList<Long> getTimingresults() {
		return timingresults;
	}

	protected void setTimingresults(ArrayList<Long> timingresults) {
		this.timingresults = timingresults;
	}

}
