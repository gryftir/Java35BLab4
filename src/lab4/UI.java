package lab4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
public class UI extends JPanel implements ActionListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField lanenum = new JTextField();
	private JTextField expresslanenum = new JTextField("");
	private JTextField startofrange = new JTextField("");
	private JTextField endofrange = new JTextField("");
	private JTextField customersnum = new JTextField("");
	private JTextField cartsizemax = new JTextField("");
	private JButton runSimulation = new JButton("Run Simulation");
	private JTextField error = new JTextField("");
	private SimVal val;
	private JPanel p1;
	//private JPanel p2;
	private JFrame jf;
	private Business biz;
	private boolean finished = false;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() 
		{
			public void run() 
			{
				UI ui = new UI();
			}
		});

	}
	
	public UI ()
	{
		super(new BorderLayout());
		p1 = new JPanel(new GridLayout(6,2));
		p1.add(new JLabel("Total Number of Lanes"));
		p1.add(lanenum);
		p1.add(new JLabel("Number of Express Lanes:"));
		p1.add(expresslanenum);
		p1.add(new JLabel("Numeric Beginning of Express Lane Item Limit Range"));
		p1.add(startofrange);
		p1.add(new JLabel("Numeric End of Express Lane Item Limit Range"));
		p1.add(endofrange);
		p1.add(new JLabel("Number of Customers"));
		p1.add(customersnum);
		p1.add(new JLabel("Maximum Number of Items in Cart"));
		p1.add(cartsizemax);
		error.setEditable(false);
		runSimulation.setPreferredSize(new Dimension(200, 40));
		add(runSimulation, BorderLayout.EAST);
		error.setVisible(true);
		add(error, BorderLayout.WEST);
		p1.setPreferredSize(new Dimension(500,230));
		setPreferredSize(new Dimension(500,70));
		jf = new JFrame();
		jf.add(p1, BorderLayout.CENTER);
		jf.add(this, BorderLayout.SOUTH);
		//this.setPreferredSize(new Dimension(750,250));
		jf.pack();
		jf.setSize(750,250);
		jf.setVisible(true);
		jf.setTitle("Lane Size Simulator");
		runSimulation.addActionListener(this);
	}

	public UI (JFrame j)
	{
		super(new BorderLayout());
		p1 = new JPanel(new GridLayout(6,2));
		p1.add(new JLabel("Total Number of Lanes"));
		p1.add(lanenum);
		p1.add(new JLabel("Number of Express Lanes:"));
		p1.add(expresslanenum);
		p1.add(new JLabel("Numeric Beginning of Express Lane Item Limit Range"));
		p1.add(startofrange);
		p1.add(new JLabel("Numeric End of Express Lane Item Limit Range"));
		p1.add(endofrange);
		p1.add(new JLabel("Number of Customers"));
		p1.add(customersnum);
		p1.add(new JLabel("Maximum Number of Items in Cart"));
		p1.add(cartsizemax);
		error.setEditable(false);
		runSimulation.setPreferredSize(new Dimension(200, 40));
		add(runSimulation, BorderLayout.EAST);
		error.setVisible(true);
		add(error, BorderLayout.WEST);
		p1.setPreferredSize(new Dimension(500,230));
		setPreferredSize(new Dimension(500,70));
		jf = j;
		jf.add(p1, BorderLayout.CENTER);
		jf.add(this, BorderLayout.SOUTH);
		//this.setPreferredSize(new Dimension(750,250));
		jf.pack();
		jf.setSize(750,250);
		jf.setVisible(true);
		jf.setTitle("Lane Size Simulator");
		runSimulation.addActionListener(this);
	}
	
public void actionPerformed(ActionEvent e)
{
	jf.setVisible(false);
	this.setFinished(true);
	SimVal num = new SimVal();
	num.lanenum = Integer.parseInt(lanenum.getText());
	num.expresslanenum = Integer.parseInt(expresslanenum.getText());
	num.startofrange = Integer.parseInt(startofrange.getText());
	num.endofrange = Integer.parseInt(endofrange.getText());
	num.customersnum = Integer.parseInt(customersnum.getText());
	num.cartsizemax = Integer.parseInt(cartsizemax.getText());
	val = new SimVal(num);
	/*System.out.println(val.lanenum);
	System.out.println(val.expresslanenum);
	System.out.println(val.startofrange); 
	System.out.println(val.endofrange); 
	System.out.println(val.customersnum); 
	System.out.println(val.cartsizemax); */
	biz = new Business(val);
	//pb = new MyProgressBar();
	biz.run();
	//while (!biz.isFinished())
	//{
	//	pb.setProgress(biz.getProgress());
	//}
	ArrayList<Long> results = this.biz.getTimingresults();
	ArrayList<Integer> limits = this.biz.getLimits();
	int size = limits.size();
	int fastestindex = this.biz.indexoffastestlane();
	int index;
	for (index = 0; index < fastestindex; index++)
	{
		System.out.println("Express Lane Item Limit: " + limits.get(index) + " Completion time of session in Milliseconds: " + results.get(index));
	}
	System.out.println("		Fastest Session");
	System.out.println("-------------------------------------------");
	System.out.println("Express Lane Item Limit: " + limits.get(index) + " Completion time of session in Milliseconds: " + results.get(index));
	System.out.println("-------------------------------------------");
	for (index++; index < size; index++)
	{
		System.out.println("Express Lane Item Limit: " + limits.get(index) + " Completion time of session in Milliseconds: " + results.get(index));
	}
	System.out.println("Program Finished");
	//System.exit(0);
}
	protected int getProgress()
	{
		return this.biz.getProgress();
		
	}

	protected boolean isFinished() {
		return finished;
	}

	protected void setFinished(boolean finished) {
		this.finished = finished;
	}

	
	

}
//require less express lanes than total lanes, express lane num be zero