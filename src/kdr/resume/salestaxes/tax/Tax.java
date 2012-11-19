package kdr.resume.salestaxes.tax;

import kdr.resume.salestaxes.money.Money;

/**
 * Tax interface. All taxes must implement this interface. 
 * 
 * @author Kris Read
 */
public interface Tax {

	/**
	 * Calculate and return the tax on a monetary amount.
	 *
	 * @param shelfPrice the shelf price to calculate tax on
	 * @return the tax amount
	 */
	public Money calculateTax(Money shelfPrice);
	
}
