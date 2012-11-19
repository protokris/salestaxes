package kdr.resume.salestaxes.item.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import kdr.resume.salestaxes.exceptions.SalesTaxesException;
import kdr.resume.salestaxes.item.Item;
import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.tax.list.MockTaxList;
import kdr.resume.salestaxes.tax.list.TaxList;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * The Class ItemFactoryTest.
 */
public class ItemFactoryTest {

	private static ItemFactory ifactory = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws SalesTaxesException {
		TaxList list = new MockTaxList(new Money("5.25"));
		ItemFactoryTest.ifactory = new ItemFactory(list);
	}
	
	/**
	 * Test item factory constructor.
	 */
	@Test
	public void testItemFactory() {
		// constructor called during setUp
		assertTrue(ifactory.taxes != null);
	}

	/**
	 * Test create an item.
	 */
	@Test
	public void testCreateItemWithBigDecimalPrice() throws SalesTaxesException {
		Item item = ifactory.create("book", new BigDecimal(12.34));
		assertEquals("book", item.getName());
		assertEquals(new Money("12.34"), item.getPrice());
		assertEquals(new Money("5.25"), item.getTaxAmount());
		assertEquals(new Money("17.59"), item.getPriceTaxed());
		assertEquals(item.getPriceTaxed(), item.getTaxAmount().add(item.getPrice()));
	}

	/**
	 * Test create an item.
	 */
	@Test
	public void testCreateItemWithInvalidPrecisionBigDecimalPrice() throws SalesTaxesException {
		Item item = ifactory.create("book", new BigDecimal(12.345));
		assertEquals("book", item.getName());
		assertEquals(new Money("12.35"), item.getPrice());
		assertEquals(new Money("17.60"), item.getPriceTaxed());
	}
	
	/**
	 * Test create an item.
	 */
	@Test
	public void testCreateItemWithStringPrice() throws SalesTaxesException {
		Item item = ifactory.create("book", "12.35");
		assertEquals("book", item.getName());
		assertEquals(new Money("12.35"), item.getPrice());
		assertEquals(new Money("5.25"), item.getTaxAmount());
		assertEquals(new Money("17.60"), item.getPriceTaxed());
	}

	/**
	 * Test create an item.
	 */
	@Test
	public void testCreateItemWithMoneyPrice() throws SalesTaxesException {
		Item item = ifactory.create("book", new Money("12.35"));
		assertEquals("book", item.getName());
		assertEquals(new Money("12.35"), item.getPrice());
		assertEquals(new Money("17.60"), item.getPriceTaxed());
	}
	
}
