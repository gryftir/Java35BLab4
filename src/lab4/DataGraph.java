package lab4;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DataGraph extends JPanel {

	ArrayList<Long> results;
	ArrayList<Long> limits;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public DataGraph()
	{
		this.setPreferredSize(new Dimension(800,800));
	}

	protected DataGraph(ArrayList<Long> results, ArrayList<Long> limits) {
		this.results = results;
		this.limits = limits;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawString("Banner",  0,  40);
	}

}
