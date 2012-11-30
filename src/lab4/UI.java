package lab4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
public class UI extends JPanel implements ActionListener,  PropertyChangeListener {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField lanenum = new JTextField("10");
	private JTextField expresslanenum = new JTextField("2");
	private JTextField startofrange = new JTextField("5");
	private JTextField endofrange = new JTextField("9");
	private JTextField customersnum = new JTextField("10");
	private JTextField cartsizemax = new JTextField("50");
	private JButton runSimulation = new JButton("Run Simulation");
	private JTextField error = new JTextField("");
	private SimVal val;
	private JPanel p1;
	private JPanel p2;
	private JFrame jf;
	private Business biz;
	private JProgressBar pb = new JProgressBar(0,100);
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
		pb.setStringPainted(true);
		pb.setBorderPainted(false);
		pb.setVisible(true);
		pb.setSize(400, 30);
		pb.setPreferredSize(new Dimension (300, 20));
		add(runSimulation, BorderLayout.EAST);
		add(pb, BorderLayout.WEST);
		setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		error.setVisible(true);
		add(error, BorderLayout.WEST);
		p1.setPreferredSize(new Dimension(500,230));
		setPreferredSize(new Dimension(500,70));
		jf = new JFrame("Simulation");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel content = new JPanel(new BorderLayout());
		content.add(p1, BorderLayout.CENTER);
		content.add(this, BorderLayout.SOUTH);
		//content.add(pb, BorderLayout.SOUTH);
		//this.setPreferredSize(new Dimension(750,250));
		jf.setContentPane(content);
		jf.pack();
		jf.setSize(750,250);
		jf.setVisible(true);
		jf.setTitle("Lane Size Simulator");
		runSimulation.addActionListener(this);
	}

public void actionPerformed(ActionEvent e)
{
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
	biz = new Business(val, this);
	
	//biz.getsim().addPropertyChangeListener(this);
	//jf.setVisible(false);
	biz.run();
	while (!biz.isFinished())
	{
	}
	
	ArrayList<Long> results = this.biz.getResults();
	ArrayList<Integer> limits = this.biz.getLimits();
	int i =  JOptionPane.showOptionDialog(this.jf, new DataGraph(), "Test" , JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object [ ] { "End Program" }, 1);
	System.exit(0);
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

@Override
public void propertyChange(PropertyChangeEvent evt) {
	// TODO Auto-generated method stub
	System.out.println("progress");
	pb.setValue(biz.getsim().sessionprogress());
	pb.paint(pb.getGraphics());
}
protected void setProgress()
{
	pb.setValue(biz.getsim().sessionprogress());
	pb.paint(pb.getGraphics());
	//pb.repaint();
}
	
	

}
//require less express lanes than total lanes, express lane num be zero