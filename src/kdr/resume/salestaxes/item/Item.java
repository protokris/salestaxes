package kdr.resume.salestaxes.item;

import kdr.resume.salestaxes.money.Money;

/**
 * Item interface. 
 * 
 * Implemented by DefaultItem and MockItem (mock object for testing)
 * 
 * @author Kris Read
 */
public interface Item {

	/**
	 * Gets the name of the item.
	 *
	 * @return the name
	 */
	public abstract String getName();

	/**
	 * Gets the price of the item.
	 *
	 * @return the price
	 */
	public abstract Money getPrice();

	/**
	 * Gets the tax amount.
	 *
	 * @return the tax amount
	 */
	public abstract Money getTaxAmount();

	/**
	 * Gets the price of the item after tax.
	 *
	 * @return the price taxed
	 */
	public abstract Money getPriceTaxed();

}