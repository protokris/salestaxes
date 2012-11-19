package kdr.resume.salestaxes.tax;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import kdr.resume.salestaxes.exceptions.SalesTaxesException;
import kdr.resume.salestaxes.money.Money;

import org.junit.Test;

/**
 * Testing the tax classes [including ImportTax, SalesTax, 
 * RoundedRateTax (abstract), RateTax (abstract), NickelRoundedRateTax (abstract)].
 */
public class TaxTest {

	/**
	 * Testing a Mock Rate Tax lets me test the calculateTax algorithm without
	 * worrying about rounding etc. 
	 * 
	 * @throws SalesTaxesException
	 */
	@Test
	public void testRateTaxCalculation() throws SalesTaxesException {
		Tax tax = new MockRateTax(new BigDecimal(10));
		Money amount = tax.calculateTax(new Money("100.00"));
		assertEquals(new Money("10.00"), amount );

		tax = new MockRateTax(new BigDecimal(5));
		amount = tax.calculateTax(new Money("300.00"));
		assertEquals(new Money("15.00"), amount );
	}

	/**
	 * Simple test of the tax rate on a rate based tax class.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test
	public void testSetTaxRate() throws SalesTaxesException {
		SalesTax tax = new SalesTax();
		assertEquals(BigDecimal.valueOf(10), tax.getTaxRate());
	}
	
	/**
	 * Test cases where rounding should not happen on sales tax calcs.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test
	public void testSalesTaxUnroundedAmount() throws SalesTaxesException {
		Tax tax = new SalesTax();
		Money amount = tax.calculateTax(new Money("100.00"));
		assertEquals(new Money("10.00"), amount );

		amount = tax.calculateTax(new Money("101.00"));
		assertEquals(new Money("10.10"), amount );
	}

	/**
	 * Test cases where rounding to nearest nickel on sales tax calcs.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test
	public void testSalesTaxRoundedAmount() throws SalesTaxesException {
		Tax tax = new SalesTax();
		Money amount = tax.calculateTax(new Money("100.01"));
		assertEquals(new Money("10.05"), amount );

		amount = tax.calculateTax(new Money("100.10"));
		assertEquals(new Money("10.05"), amount );
	}

	/**
	 * Test cases where rounding should not happen on import tax calcs.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test
	public void testImportTaxUnroundedAmount() throws SalesTaxesException {
		Tax tax = new ImportTax();
		Money amount = tax.calculateTax(new Money("100.00"));
		assertEquals(new Money("5.00"), amount );

		amount = tax.calculateTax(new Money("101.00"));
		assertEquals(new Money("5.05"), amount );
	}

	/**
	 * Test rounding to nearest nickel on import tax calcs.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test
	public void testImportTaxRoundedAmount() throws SalesTaxesException {
		Tax tax = new ImportTax();
		Money amount = tax.calculateTax(new Money("100.01"));
		assertEquals(new Money("5.05"), amount );

		amount = tax.calculateTax(new Money("100.10"));
		assertEquals(new Money("5.05"), amount );
	}
	
	/**
	 * Test calculate sales tax - some scenarios used during TDD.
	 */
	@Test
	public void testCalculateSalesTaxScenarios() throws SalesTaxesException {
		Tax tax = new SalesTax();
		Money taxes = tax.calculateTax(new Money("12.01"));
		assertEquals("$1.25", taxes.toString());

		taxes = tax.calculateTax(new Money("12.00"));
		assertEquals("$1.20", taxes.toString());

		taxes = tax.calculateTax(new Money("12.10"));
		assertEquals("$1.25", taxes.toString());
		
		taxes = tax.calculateTax(new Money("12.49"));
		assertEquals("$1.25", taxes.toString());

		taxes = tax.calculateTax(new Money("12.51"));
		assertEquals("$1.30", taxes.toString());
	}

}
