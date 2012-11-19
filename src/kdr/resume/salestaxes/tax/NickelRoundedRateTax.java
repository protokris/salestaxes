package kdr.resume.salestaxes.tax;

import kdr.resume.salestaxes.money.Money;

/**
 * Rounded rate tax which will round to the nearest 5 cents.
 * 
 * Must be extended by a subclass that defines the tax rate. 
 * 
 * @author Kris Read
 */
public abstract class NickelRoundedRateTax extends RoundedRateTax implements Tax {
	
	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.tax.RoundedRateTax#roundTax(kdr.resume.salestaxes.money.Money)
	 */
	protected Money roundTax(Money money) {
		return money.roundUpToNearestNickel();
	}
	
}
