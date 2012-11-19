package kdr.resume.salestaxes.item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import kdr.resume.salestaxes.exceptions.InvalidItemException;
import kdr.resume.salestaxes.exceptions.InvalidTaxesException;
import kdr.resume.salestaxes.exceptions.SalesTaxesException;
import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.tax.list.MockTaxList;
import kdr.resume.salestaxes.tax.list.TaxList;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for the DefaultItem class. 
 */
public class DefaultItemTest {

	/**
	 * Mock tax list to be used to test item without worrying about tax related logic.
	 */
	protected static TaxList list = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws SalesTaxesException {
		DefaultItemTest.list = new MockTaxList(new Money("6.75"));
	}

	/**
	 * Test item constructor with valid name, price, and a mock tax list.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test
	public void testItemConstructed() throws SalesTaxesException {
		Item item = new DefaultItem("book", new Money("50.00"), DefaultItemTest.list);
		assertEquals("book", item.getName());
		assertEquals(new Money("50.00"), item.getPrice());
		assertEquals(new Money("6.75"), item.getTaxAmount());
		assertEquals(new Money("56.75"), item.getPriceTaxed());
		assertEquals(item.getPriceTaxed(), item.getPrice().add(item.getTaxAmount()));
	}

	/**
	 * Test that item constructor throws exception when null tax list supplied.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test (expected=InvalidTaxesException.class)
	public void testItemConstructorNullTaxList() throws SalesTaxesException {
		@SuppressWarnings("unused")
		Item item = new DefaultItem("book", new Money("50.00"), null);
	}

	/**
	 * Test that when an item is constructed given a null price, it gets set to 0.00
	 * 
	 * @throws SalesTaxesException
	 */
	@Test
	public void testItemConstructorNoMoney() throws SalesTaxesException {
		Item item = new DefaultItem("book", null, DefaultItemTest.list);
		assertEquals(new Money("0.00"), item.getPrice());
	}

	/**
	 * Test that when an item is constructed given a null name, it throws an exception.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test (expected=InvalidItemException.class)
	public void testItemConstructorNullName() throws SalesTaxesException {
		@SuppressWarnings("unused")
		Item item = new DefaultItem(null, new Money("50.00"), DefaultItemTest.list);
	}

	/**
	 * Test that when an item is constructed given a blank name, it throws an exception.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test (expected=InvalidItemException.class)
	public void testItemConstructorBlankName() throws SalesTaxesException {
		@SuppressWarnings("unused")
		Item item = new DefaultItem("", new Money("50.00"), DefaultItemTest.list);
	}
	
	/**
	 * Test equals object.  Method was IDE generated so this is low risk.
	 */
	@Test
	public void testEqualsObject() throws SalesTaxesException {
		Item item1 = new DefaultItem("book", new Money("0.00"), DefaultItemTest.list);
		Item item2 = new DefaultItem("book", new Money("0.00"), DefaultItemTest.list);
		assertTrue(item1.equals(item2));
		assertEquals(item1, item2);

		item1 = new DefaultItem("book", new Money("12.34"), DefaultItemTest.list);
		item2 = new DefaultItem("book", new Money("1.23"), DefaultItemTest.list);
		assertFalse(item1.equals(item2));

		item1 = new DefaultItem("book", new Money("12.34"), DefaultItemTest.list);
		item2 = new DefaultItem("back", new Money("12.34"), DefaultItemTest.list);
		assertFalse(item1.equals(item2));
	}
	
	/**
	 * Tests the toString method for the item. 
	 * 
	 * This is what gets printed on receipts. 
	 */
	@Test
	public void testToString() throws SalesTaxesException {
		Item item = new DefaultItem("book", new Money("50.00"), DefaultItemTest.list);
		String expectedString = "1 book: $56.75\n";
		assertEquals(expectedString, item.toString());
	}
	
	
	/**
	 * Hashcode was IDE generated
	 */
	@Test
	public void testHashcode() throws SalesTaxesException {
		Item item1 = new DefaultItem("book", new Money("50.00"), DefaultItemTest.list);
		Item item2 = new DefaultItem("banana", new Money("10.00"), DefaultItemTest.list);
		assertFalse(item1.hashCode() == item2.hashCode());
	}

}