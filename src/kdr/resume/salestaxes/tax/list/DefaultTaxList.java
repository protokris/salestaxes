package kdr.resume.salestaxes.tax.list;

import java.util.ArrayList;
import java.util.Arrays;

import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.tax.Tax;

/**
 * Concrete implementation of TaxList
 * 
 *  DefaultTaxList is an ArrayList of Taxes.  
 *  
 *  This class allows an item to have many taxes applied to it in sequence. 
 *  
 *  Taxes can be added or removed from the list at runtime. Better to extend
 *  ArrayList this way so that we can isolate bulk tax operations (like summing
 *  all of the tax amounts). 
 *  
 *  @author Kris Read
 */
@SuppressWarnings("serial")
public class DefaultTaxList extends ArrayList<Tax> implements TaxList {

	/**
	 * Convenience constructor so that each tax doesn't need to be added.
	 * 
	 * @param taxes
	 */
	public DefaultTaxList(Tax[] taxes) {
		super();
		this.addAll(Arrays.asList(taxes));
	}
	
	/**
	 * Convenience constructor to add a single tax.
	 * 
	 * @param tax
	 */
	public DefaultTaxList(Tax tax) {
		super();
		this.add(tax);
	}
	
	/**
	 * Constructor for empty tax list.
	 */
	public DefaultTaxList() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.tax.list.TaxList#getTaxes(kdr.resume.salestaxes.money.Money)
	 */
	@Override
	public Money getTaxes(Money price) {
		Money taxes = new Money();
		for (Tax tax : this) {
			taxes = taxes.add(tax.calculateTax(price));
		}
		return taxes;
	}	
}
