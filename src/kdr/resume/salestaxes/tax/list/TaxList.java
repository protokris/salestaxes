package kdr.resume.salestaxes.tax.list;

import java.util.List;

import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.tax.Tax;

/**
 * TaxList interface.
 * 
 * Implemented by DefaultTaxList and MockTaxList (mock object for testing)
 * 
 * @author Kris Read
 */
public interface TaxList extends List<Tax> {

	/**
	 * Given a shelf price, calculate the sum of all taxes to be charged.
	 *
	 * @param price the price
	 * @return the taxes
	 */
	public abstract Money getTaxes(Money price);


}