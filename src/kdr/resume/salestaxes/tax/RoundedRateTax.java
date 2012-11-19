package kdr.resume.salestaxes.tax;

import kdr.resume.salestaxes.money.Money;

/**
 * Rounded version of the RateTax. 
 * 
 * The subclass must implement the rounding via abstract method roundTax(Money m)
 * 
 * @author Kris
 *
 */
public abstract class RoundedRateTax extends RateTax implements Tax {
	
	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.tax.Tax#calculateTax(kdr.resume.salestaxes.money.Money)
	 */
	@Override
	public Money calculateTax(Money shelfPrice) {
		return roundTax(super.calculateTax(shelfPrice));
	}
	
	/**
	 * Implement this method to determine how we will round the tax.
	 * 
	 * @param money to be rounded
	 * @return the rounded amount
	 */
	protected abstract Money roundTax(Money money);
}
