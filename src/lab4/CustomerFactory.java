package lab4;
import java.util.Random;

public class CustomerFactory {

	private SimVal values;
	private Random generator;
	
	public static void main(String[] args) {

	}

	public CustomerFactory(SimVal val) {
		this.values = val;
		this.generator =  new Random();
	}
	

	protected Customer makeCustomer()
	{
		return new Customer(this.generator.nextInt(values.cartsizemax) + 1);
	}
}
