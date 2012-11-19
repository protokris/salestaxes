package kdr.resume.salestaxes.tax;

import java.math.BigDecimal;

/**
 * Simple implementation of Rate Tax that allows me to test the calculateTax method.
 * 
 * @author Kris Read
 */
public class MockRateTax extends RateTax {

	private BigDecimal rate = null;

	/**
	 * MockRateTax just uses the tax rate given in the constructor.
	 * 
	 * @param rate
	 */
	public MockRateTax(BigDecimal rate) {
		this.rate = rate;
	}

	@Override
	protected BigDecimal getTaxRate() {
		return rate;
	}

}
