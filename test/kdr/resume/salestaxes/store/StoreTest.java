package kdr.resume.salestaxes.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import kdr.resume.salestaxes.exceptions.InvalidMoneyException;
import kdr.resume.salestaxes.exceptions.ItemNotFoundException;
import kdr.resume.salestaxes.exceptions.SalesTaxesException;
import kdr.resume.salestaxes.item.Item;
import kdr.resume.salestaxes.item.MockItem;
import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.receipt.MockReceipt;
import kdr.resume.salestaxes.receipt.Receipt;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test the store class.  
 * 
 * Tests with a Mock of Receipt; Store uses a dependency injection pattern.
 * 
 * @author Kris Read
 */
public class StoreTest {

	private static Item item1 = null;

	private Store store = null;

	/**
	 * Set up a store with a mock receipt. 
	 * 
	 * This way we can test the store methods that call through to the receipt.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Before
	public void setUp() throws InvalidMoneyException {
		this.store = new Store(
				new MockReceipt(
						new Money("123.50"), 
						new Money("12.35")
					));
	}
	
	/**
	 * Mock item useful for testing.
	 * 
	 * @throws InvalidMoneyException
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws InvalidMoneyException {
		StoreTest.item1 = new MockItem("banana", 
				new Money("10.00"), 
				new Money("1.00"), 
				new Money("11.00"));

	}
	
	/**
	 * Test store constructor. Store constructor takes a Receipt; 
	 * uses dependency injection.
	 */
	@Test
	public void testStoreConstructor() {
		// already called in setUp()
		assertTrue(store.receipt != null);
		assertTrue(store.inventory != null);
		assertEquals(0, store.inventory.size());
	}

	/**
	 * Test adding an item to a store.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testAddingItemToStore() throws InvalidMoneyException {
		store.addItem(item1);
		assertEquals(1, store.inventory.size());
		assertTrue(store.inventory.containsValue(item1));
		assertTrue(store.inventory.containsKey("banana"));
	}
	
	/**
	 * Test removing an item from a store.
	 * 
	 * @throws InvalidMoneyException
	 * @throws ItemNotFoundException
	 */
	@Test
	public void testRemovingItemFromStore() throws InvalidMoneyException, ItemNotFoundException {
		store.addItem(item1);
		assertEquals(1, store.inventory.size());
		assertTrue(store.inventory.containsValue(item1));
		assertTrue(store.inventory.containsKey("banana"));
		
		store.removeItem(item1);
		assertEquals(0, store.inventory.size());
		assertFalse(store.inventory.containsValue(item1));
		assertFalse(store.inventory.containsKey("banana"));
	}
	
	/**
	 * Test what happens when we remove something that doesn't exist.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test (expected=ItemNotFoundException.class)
	public void testRemovingInvalidItem() throws SalesTaxesException {
		store.removeItem(item1);
	}
	
	
	/**
	 * Test purchase of an item after adding it to the inventory.
	 */
	@Test
	public void testPurchase() throws ItemNotFoundException, InvalidMoneyException {
		store.addItem(item1);
		store.purchase("banana");
		
		assertTrue(store.receipt.contains(item1));
		assertEquals(1, store.receipt.size());
	}
	
	/**
	 * Test that purchasing an item not in the inventory fails.
	 * 
	 * @throws SalesTaxesException
	 */
	@Test (expected=ItemNotFoundException.class)
	public void testPurchaseInvalidItem() throws SalesTaxesException {
		store.purchase("mango");
	}

	/**
	 * Test issue receipt.
	 */
	@Test
	public void testIssueReceipt() throws SalesTaxesException {

		Receipt receipt = store.issueReceipt();
		assertEquals(0, receipt.size());
		
		store.addItem(item1);
		store.purchase("banana");
		receipt = store.issueReceipt();
		
		assertEquals(1, receipt.size());
		assertTrue(receipt.contains(item1));		
		assertEquals(new Money("123.50"), receipt.getTotal());
		assertEquals(new Money("12.35"), receipt.getTaxes());
	}

}
