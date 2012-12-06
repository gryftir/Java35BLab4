package lab4;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DataGraph extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Long> results;
	ArrayList<Integer> limits;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Long> results = new ArrayList<Long>();
		ArrayList<Integer> values = new ArrayList<Integer>();
		results.add(new Long(500));
		results.add(new Long(600));
		results.add(new Long(400));
		results.add(new Long(800));
		values.add(new Integer(5));
		values.add(new Integer(6));
		values.add(new Integer(7));
		values.add(new Integer(8));
		DataGraph dg = new DataGraph(results, values);
		int i =  JOptionPane.showOptionDialog(null , dg, "Test" , JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new Object [ ] { "End Program" }, 1);
		System.out.println(i);
	}
	public DataGraph()
	{
		this.setPreferredSize(new Dimension(1200,800));
	}

	protected DataGraph(ArrayList<Long> results, ArrayList<Integer> limits) {
		this();
		this.results = results;
		this.limits = limits;
	}
	protected void paintComponent(Graphics g) {
		this.setBackground(Color.white);
		 this.setOpaque(true);
		super.paintComponent(g);
		Dimension dim = this.getSize();
		double width = dim.getWidth();
		double height = dim.getHeight();
		int elements = results.size();
		double elementsize = width/elements - 1;
		int maxindex = this.findmaxindex();
		int minindex = this.findminindex();
		double max = results.get(maxindex);
		

		int offset = (int)20; 
		  Graphics2D g2d = (Graphics2D)g;
	        // g2d.drawString("123", 10, 10);
		 this.setBackground(Color.blue);
		 this.setOpaque(true);
		  for (int i = 0; i < elements; i++)
		  {
			 
			  if (i == minindex)
			  {
				g.setColor(Color.RED);    
				  g2d.drawLine(offset +  (int)((elementsize * i)), 0, offset +  (int)((elementsize * i)), (int)height - 40);
				  g.setColor(Color.BLACK);
				  g2d.drawString(limits.get(i).toString() + " items in cart, FASTEST",offset +  (int)((elementsize * i)),  (int)(height - 20));
				  
				  g2d.drawString(results.get(i).toString()+ " speed in miliseconds", offset  + (int)((elementsize * i)) ,  (int)(height - 2));
				  
			  }
			  else
			  {
	
				  
				  
				  
				  g2d.drawString(results.get(i).toString() + " speed in miliseconds", offset + (int)((elementsize * i)) ,  (int)(height - 2));
				  g2d.drawString(limits.get(i).toString() + " items in cart", offset +  (int)((elementsize * i)),  (int)(height - 20));
				  g.setColor(Color.BLUE);
				  g2d.drawLine(offset + (int)((elementsize * i)), 0, offset +  (int)((elementsize * i)), (int)height - 40);
				  g.setColor(Color.BLACK);
			  }
		  }
		  for (int i = 0; i < elements - 1; i++)
		  {
	         g2d.drawLine(offset + (int)(elementsize * i), (int)(height - (results.get(i)/max * height)),offset +  (int)((elementsize * (i + 1))), (int)(height - (results.get(i + 1)/max * height)));
	        // System.out.println((int)((elementsize * (i + 1))) + " " + (int)(results.get(i + 1)% (int)height) );
		  }
		 // g2d.drawString("Red Line indicates fasted ")
	
		 
	}
	protected int findmaxindex()
	{
		ArrayList<Long> results = this.results;
		int size = results.size();
		Long maxval = results.get(0);
		int maxvalindex = 0;
		for (int index = 1; index < size; index++)
		{
			if (results.get(index) > maxval)
			{
				maxval = results.get(index);
				maxvalindex = index;
			}
		}
		return maxvalindex;
	}
	protected int findminindex()
	{
		ArrayList<Long> results = this.results;
		int size = results.size();
		Long minval = results.get(0);
		int minvalindex = 0;
		for (int index = 1; index < size; index++)
		{
			if (results.get(index) < minval)
			{
				minval = results.get(index);
				minvalindex = index;
			}
		}
		return minvalindex;
	}

}
