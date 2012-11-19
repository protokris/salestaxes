package kdr.resume.salestaxes.receipt;

import static org.junit.Assert.assertEquals;
import kdr.resume.salestaxes.exceptions.SalesTaxesException;
import kdr.resume.salestaxes.item.Item;
import kdr.resume.salestaxes.item.MockItem;
import kdr.resume.salestaxes.money.Money;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testing the DefaultReceipt object.
 * 
 * Uses mock items so that item logic is not included in the tests.
 * 
 */
public class DefaultReceiptTest {

	private static Item item1, item2, item3 = null;
	
	@BeforeClass
	public static void setUpBeforeClass() throws SalesTaxesException {
		DefaultReceiptTest.item1 = new MockItem("americano", new Money("2.75"), new Money("0.50"), new Money("3.25"));
		DefaultReceiptTest.item2 = new MockItem("latte", new Money("5.00"), new Money("1.00"), new Money("6.00"));
		DefaultReceiptTest.item3 = new MockItem("espresso", new Money("1.33"), new Money("0.07"), new Money("1.40"));
	}
	
	/**
	 * Test getting the taxes owed on the receipt.
	 */
	@Test
	public void testGetTaxesOwed() throws SalesTaxesException {
		Receipt receipt = new DefaultReceipt();

		receipt.add(item1);
		assertEquals(new Money("0.50"), receipt.getTaxes());
		assertEquals(item1.getTaxAmount(), receipt.getTaxes());
		
		receipt.add(item2);
		assertEquals(new Money("1.50"), receipt.getTaxes());
		
		receipt.remove(item2);
		assertEquals(new Money("0.50"), receipt.getTaxes());
	}

	/**
	 * Test getting the total owed on the receipt.
	 */
	@Test
	public void testGetTotalOwed() throws SalesTaxesException {
		Receipt receipt = new DefaultReceipt();

		receipt.add(item1);
		assertEquals(new Money("3.25"), receipt.getTotal());
		assertEquals(item1.getPriceTaxed(), receipt.getTotal());
		
		receipt.add(item2);
		assertEquals(new Money("9.25"), receipt.getTotal());
		
		receipt.remove(item1);
		assertEquals(new Money("6.00"), receipt.getTotal());
	}

	/**
	 * Test the string created when we print the totals.
	 */
	@Test
	public void testPrintTotals() {
		DefaultReceipt receipt = new DefaultReceipt();
		receipt.add(item1);
		receipt.add(item2);
		receipt.add(item3);
		
		String expectedString = "Sales Taxes: $1.57\nTotal: $10.65\n";
		assertEquals(expectedString, receipt.printTotals());
	}
	
	/**
	 * Test the string created when we print the items.
	 * 
	 * Defers to just calling toString on each (mock) item. 
	 */
	@Test
	public void testPrintItems() {
		DefaultReceipt receipt = new DefaultReceipt();
		receipt.add(item1);
		receipt.add(item2);
		receipt.add(item3);

		// the mock just returns the name
		String expectedString = "americanolatteespresso";
		assertEquals(expectedString, receipt.printItems());
	}
	
}
