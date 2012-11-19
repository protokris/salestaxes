package kdr.resume.salestaxes.tax;

import java.math.BigDecimal;

/**
 * Sales Tax
 * 
 * @author Kris Read
 */
public class SalesTax extends NickelRoundedRateTax implements Tax {

	/**
	 * Contains the tax rate for sales taxes (10%)
	 */
	private final BigDecimal taxrate = new BigDecimal(10);
	
	/**
	 * New Sales Tax
	 */
	public SalesTax() {
		super();
	}

	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.tax.RateTax#getTaxRate()
	 */
	@Override
	protected BigDecimal getTaxRate() {
		return taxrate;
	}

}
