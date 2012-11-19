package kdr.resume.salestaxes.tax.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import kdr.resume.salestaxes.exceptions.SalesTaxesException;
import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.tax.MockTax;
import kdr.resume.salestaxes.tax.Tax;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for the class DefaultTaxList
 */
public class DefaultTaxListTest {

	/**
	 * Sample taxes.  Mocked out objects.
	 */
	private static MockTax tax1, tax2, tax3;
	
	@BeforeClass
	public static void setUp() throws SalesTaxesException {
		DefaultTaxListTest.tax1 = new MockTax(new Money("12.00"));
		DefaultTaxListTest.tax2 = new MockTax(new Money("10.50"));
		DefaultTaxListTest.tax3 = new MockTax(new Money("11.77"));
	}
	
	/**
	 * Tests constructing the default tax list from an array of taxes.
	 */
	@Test
	public void testConstructedFromTaxArray() {
		TaxList list = new DefaultTaxList(new Tax[]{
			tax1,
			tax2
		});
		assertTrue(list.contains(tax1));
		assertTrue(list.contains(tax2));
		assertFalse(list.contains(tax3));
	}
	
	/**
	 * Tests constructing the default tax list from a single tax.
	 */
	@Test 
	public void testConstructedFromTax() {
		TaxList list = new DefaultTaxList(tax1);
		assertTrue(list.contains(tax1));
		assertFalse(list.contains(tax3));
	}
	
	/**
	 * Tests creating an empty default tax list.
	 */
	@Test
	public void testConstructed() {
		TaxList list = new DefaultTaxList();
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Test getting taxes and altering the tax list.
	 */
	@Test
	public void testGetTaxes() throws SalesTaxesException {
		TaxList list = new DefaultTaxList();
		assertEquals(0, list.size());
		
		Money money = new Money("100.00");
		assertEquals(new Money("0.00"), list.getTaxes(money));
		
		list.add(tax1);
		assertEquals(1, list.size());
		assertTrue(list.contains(tax1));
		
		list.add(tax2);
		assertEquals(2, list.size());
		assertTrue(list.contains(tax2));
		
		assertEquals(new Money("22.50"), list.getTaxes(money));
		
		list.add(tax3);
		assertEquals(3, list.size());
		assertTrue(list.contains(tax3));
		
		assertEquals(new Money("34.27"), list.getTaxes(money));
			
		// duplicate adds are are okay
		list.add(tax2);
		assertEquals(4, list.size());
		assertEquals(new Money("44.77"), list.getTaxes(money));
	}

}

