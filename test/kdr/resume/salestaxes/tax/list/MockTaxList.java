package kdr.resume.salestaxes.tax.list;

import java.util.ArrayList;

import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.tax.Tax;

/**
 * Simple mock of a tax list. 
 * 
 * Still extends ArrayList (no need to test/mock that) but
 * the getTaxes method just returns the value supplied to the constructor
 * rather than doing any calculations etc. 
 * 
 * @author Kris Read
 */
@SuppressWarnings("serial")
public class MockTaxList extends ArrayList<Tax> implements TaxList {

	private Money taxes = null;

	public MockTaxList(Money taxes) {
		this.taxes = taxes;
	}
	
	@Override
	public Money getTaxes(Money price) {
		return this.taxes;
	}

}
