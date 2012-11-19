package kdr.resume.salestaxes.receipt;

import java.io.PrintStream;
import java.util.ArrayList;

import kdr.resume.salestaxes.item.Item;
import kdr.resume.salestaxes.money.Money;

/**
 * Mock of receipt so I can test Store.  
 * 
 * This is useful when using a dependency injection pattern. See StoreTest.java
 * 
 * @author Kris Read
 */
@SuppressWarnings("serial")
public class MockReceipt extends ArrayList<Item> implements Receipt {

	private Money total = null;
	private Money taxes = null;
	
	/**
	 * MockReceipt just takes total and taxes in constructor so they can be returned later.
	 * 
	 * @param total
	 * @param taxes
	 */
	public MockReceipt(Money total, Money taxes) {
		this.total = total;
		this.taxes = taxes;
	}

	@Override
	public Money getTotal() {
		return total;
	}

	@Override
	public Money getTaxes() {
		return taxes;
	}

	@Override
	public void print(PrintStream stream) {
	}
}
