package lab4;

public class SimVal {

	public int lanenum;
	public int expresslanenum;
	public int expresslaneitemlimit; 
	public int startofrange; // express lane item limit
	public int endofrange; //express lane item limit
	public int customersnum;
	public int cartsizemax;
	public static void main(String[] args) {
	}
	public SimVal clone()
	{
		return new SimVal(this);
	}
	protected SimVal(int lanenum, int expresslanenum, int expresslaneitemlimit,
			int startofrange, int endofrange, int customersnum, int cartsizemax) {
		this.lanenum = lanenum;
		this.expresslanenum = expresslanenum;
		this.expresslaneitemlimit = expresslaneitemlimit;
		this.startofrange = startofrange;
		this.endofrange = endofrange;
		this.customersnum = customersnum;
		this.cartsizemax = cartsizemax;
	}
	protected SimVal(SimVal val)
	{
		this.lanenum = val.lanenum;
		this.expresslanenum = val.expresslanenum;
		this.expresslaneitemlimit = val.expresslaneitemlimit;
		this.startofrange = val.startofrange;
		this.endofrange = val.endofrange;
		this.customersnum = val.customersnum;
		this.cartsizemax = val.cartsizemax;
	}
	public SimVal()
	{
	}
	
}
//note can use .clone() method to make a hard copy