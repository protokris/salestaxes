package kdr.resume.salestaxes.store;

import java.util.HashMap;

import kdr.resume.salestaxes.exceptions.ItemNotFoundException;
import kdr.resume.salestaxes.item.Item;
import kdr.resume.salestaxes.receipt.Receipt;

/**
 * Store is used to make purchases and generate a receipt.
 * 
 * It could be extended with more store like functionality later.
 * 
 * @author Kris Read
 */
public class Store {
	
	/** The receipt. */
	protected Receipt receipt = null;
	
	/**
	 * Inventory of items available for purchase
	 */
	protected HashMap<String, Item> inventory = null;
	
	/**
	 * Instantiates a new store.
	 */
	public Store(Receipt receipt) {
	  this.receipt = receipt;	
	  this.inventory = new HashMap<String, Item>();
	}

	/**
	 * Purchase an item by name.
	 *
	 * @param itemName the name of the item to be purchased
	 */
	public void purchase(String itemName) throws ItemNotFoundException {
		if (!inventory.containsKey(itemName)) {
			throw new ItemNotFoundException("The item \"" + itemName + "\" is not for sale.");
		}
		this.receipt.add(this.inventory.get(itemName));
	}

	/**
	 * Add an item to the store. Overwrites if the item by that name exists.
	 * 
	 * @param item the item
	 */
	public void addItem(Item item) {
		this.inventory.put(item.getName(), item);
	}

	/**
	 * Remove an item from the store
	 * 
	 * @param item the item
	 */
	public void removeItem(Item item) throws ItemNotFoundException {
		if (!inventory.containsKey(item.getName())) {
			throw new ItemNotFoundException("The item \"" + item.getName() + "\" is not for sale.");
		}
		this.inventory.remove(item.getName());
	}
	
	/**
	 * Issue receipt.
	 *
	 * @return the receipt
	 */
	public Receipt issueReceipt() {
		return this.receipt;
	}
	
}
