package lab4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
	private JPanel p2;
	private JFrame jf;
	Business biz;
	private MyProgressBar pb;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	UI u = new UI();

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

public void actionPerformed(ActionEvent e)
{
	SimVal num = new SimVal();
	num.lanenum = Integer.parseInt(lanenum.getText());
	num.expresslanenum = Integer.parseInt(expresslanenum.getText());
	num.startofrange = Integer.parseInt(startofrange.getText());
	num.endofrange = Integer.parseInt(endofrange.getText());
	num.customersnum = Integer.parseInt(customersnum.getText());
	num.cartsizemax = Integer.parseInt(cartsizemax.getText());
	val = num;
	biz = new Business(val);
	jf.setVisible(false);
	biz.run();
}
	

	
	

}
//require less express lanes than total lanes, express lane num be zero