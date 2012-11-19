package kdr.resume.salestaxes.tax;

import kdr.resume.salestaxes.money.Money;

/**
 * Creating a mock object "dummy" tax so I can test other classes
 * without worrying about whether the Tax works. 
 * 
 * Just returns what you pass the constructor. 
 * 
 * @author Kris Read
 */
public class MockTax implements Tax {

	private Money toBeReturned = null;
	
	public MockTax(Money toBeReturned) {
		this.toBeReturned = toBeReturned;
	}

	@Override
	public Money calculateTax(Money shelfPrice) {
		return toBeReturned;
	}

}
