package lab4;

import java.util.ArrayList;

public class Cart {
	private ArrayList<Item> cartitems;

	public static void main(String[] args) {

	}

	public Cart(int itemnum) {
		this.cartitems = new ArrayList<Item>();
		for (int i = 0; i < itemnum; i++) {
			this.cartitems.add(new Item());
		}
	}
	public int size()
	{
		return cartitems.size();
	}
}
