package kdr.resume.salestaxes.tax;

import java.math.BigDecimal;

/**
 * Import Tax
 * 
 * @author Kris Read
 */
public class ImportTax extends NickelRoundedRateTax implements Tax {

	/**
	 * Contains the tax rate for import taxes (5%)
	 */
	private final BigDecimal taxrate = new BigDecimal(5);
	
	/**
	 * Create a new import tax.
	 */
	public ImportTax() {
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
