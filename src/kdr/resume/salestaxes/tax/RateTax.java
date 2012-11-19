package kdr.resume.salestaxes.tax;

import java.math.BigDecimal;

import kdr.resume.salestaxes.money.Money;

/**
 * Abstract tax which is based on simple multiplication by a tax rate.
 * 
 * The subclass must define the rate via defining the abstract method getTaxRate()
 * 
 * @author Kris Read
 */
public abstract class RateTax implements Tax {

	/**
	 * Gets the tax rate to be used by this tax.
	 * 
	 * @return the tax rate as an integer (5 = 5%)
	 */
	protected abstract BigDecimal getTaxRate();
	
	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.tax.Tax#calculateTax(kdr.resume.salestaxes.money.Money)
	 */
	@Override
	public Money calculateTax(Money shelfPrice) {
		Money money = new Money();
		BigDecimal realRate = this.getTaxRate().movePointLeft(2);
  	    money = shelfPrice.multiply(realRate);
		return money;
	}	
}
