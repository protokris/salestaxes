package kdr.resume.salestaxes;

import static org.junit.Assert.assertEquals;
import kdr.resume.salestaxes.exceptions.SalesTaxesException;
import kdr.resume.salestaxes.item.factory.ItemFactory;
import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.receipt.DefaultReceipt;
import kdr.resume.salestaxes.receipt.Receipt;
import kdr.resume.salestaxes.store.Store;
import kdr.resume.salestaxes.tax.ImportTax;
import kdr.resume.salestaxes.tax.SalesTax;
import kdr.resume.salestaxes.tax.Tax;
import kdr.resume.salestaxes.tax.list.DefaultTaxList;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This test case outputs the correct receipt for the cases supplied in the
 * problem.
 * 
 * @author Kris Read
 */
public class InputTest {

	/**
	 * Item factories used to create items with proper tax rules
	 */
	private static ItemFactory exemptGoods = null;
	private static ItemFactory taxedGoods = null;
	private static ItemFactory importedExemptGoods = null;
	private static ItemFactory importedTaxedGoods = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		InputTest.initItemFactories();
	}
	
	/**
	 * Test input1.
	 */
	@Test
	public void testInput1() throws SalesTaxesException {
		Store store = new Store(new DefaultReceipt());
		
		store.addItem(exemptGoods.create("book", "12.49"));
		store.addItem(taxedGoods.create("music CD", "14.99"));
		store.addItem(exemptGoods.create("chocolate bar", "0.85"));

		store.purchase("book");
		store.purchase("music CD");
		store.purchase("chocolate bar");

		Receipt receipt = store.issueReceipt();
		
		assertEquals(new Money("12.49"), receipt.get(0).getPriceTaxed() );
		assertEquals(new Money("16.49"), receipt.get(1).getPriceTaxed() );
		assertEquals(new Money("0.85"), receipt.get(2).getPriceTaxed() );
		assertEquals(new Money("1.50"), receipt.getTaxes() );
		assertEquals(new Money("29.83"), receipt.getTotal() );
		
		receipt.print(System.out);
	}

	/**
	 * Test input2.
	 */
	@Test
	public void testInput2() throws SalesTaxesException {
		Store store = new Store(new DefaultReceipt());

		store.addItem(importedExemptGoods.create("imported box of chocolates", "10.00"));
		store.addItem(importedTaxedGoods.create("imported bottle of perfume", "47.50"));
		
		store.purchase("imported box of chocolates");
		store.purchase("imported bottle of perfume");
		
		Receipt receipt = store.issueReceipt();

		assertEquals(new Money("10.50"), receipt.get(0).getPriceTaxed() );
		assertEquals(new Money("54.65"), receipt.get(1).getPriceTaxed() );
		assertEquals(new Money("7.65"), receipt.getTaxes() );
		assertEquals(new Money("65.15"), receipt.getTotal() );
		
		receipt.print(System.out);
	}

	/**
	 * Test input3.
	 */
	@Test
	public void testInput3() throws SalesTaxesException {
		Store store = new Store(new DefaultReceipt());

		store.addItem(importedTaxedGoods.create("imported bottle of perfume", "27.99"));
		store.addItem(taxedGoods.create("bottle of perfume", "18.99"));
		store.addItem(exemptGoods.create("packet of headache pills", "9.75"));
		store.addItem(importedExemptGoods.create("imported box of chocolates", "11.25"));

		store.purchase("imported bottle of perfume");
		store.purchase("bottle of perfume");
		store.purchase("packet of headache pills");
		store.purchase("imported box of chocolates");
		
		Receipt receipt = store.issueReceipt();

		assertEquals( new Money("32.19"), receipt.get(0).getPriceTaxed() );
		assertEquals( new Money("20.89"), receipt.get(1).getPriceTaxed() );
		assertEquals( new Money("9.75"), receipt.get(2).getPriceTaxed() );
		assertEquals( new Money("11.85"), receipt.get(3).getPriceTaxed() );
		assertEquals( new Money("6.70"), receipt.getTaxes() );
		assertEquals( new Money("74.68"), receipt.getTotal() );
		
		receipt.print(System.out);
	}
	
	
	/**
	 * Initialize the item factories.
	 */
	protected static void initItemFactories() {

		InputTest.importedTaxedGoods = new ItemFactory(
				new DefaultTaxList(new Tax[]{
					new SalesTax(),
					new ImportTax()
				}));
		InputTest.exemptGoods = new ItemFactory(new DefaultTaxList());
		InputTest.taxedGoods = new ItemFactory(new DefaultTaxList(new SalesTax()));
		InputTest.importedExemptGoods = new ItemFactory(new DefaultTaxList(new ImportTax()));
	}

}
