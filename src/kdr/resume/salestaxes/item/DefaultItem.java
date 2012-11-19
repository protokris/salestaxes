package kdr.resume.salestaxes.item;

import kdr.resume.salestaxes.exceptions.InvalidItemException;
import kdr.resume.salestaxes.exceptions.InvalidTaxesException;
import kdr.resume.salestaxes.exceptions.SalesTaxesException;
import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.tax.list.TaxList;

/**
 * Represents an item, can be purchased and can be listed on a receipt.
 * 
 * Concrete implementation of the Item interface. 
 * 
 * @author Kris Read
 */
public class DefaultItem implements Item {
	
	/** The taxes applied to this item. */
	protected TaxList taxes = null;

	/** The name of the item. */
	protected String name = null;
	
	/** The price on the item. */
	protected Money price = null;
	
	/** The tax amount (calculated when the item is created). */
	protected Money taxAmount = null;

	/**
	 * Instantiates a new item. 
	 *
	 * @param name the name of the item
	 * @param price the price of the item
	 * @param taxes the list of taxes to be applied to this item
	 * @throws InvalidItemException thrown when parameters create invalid item
	 */
	public DefaultItem(String name, Money price, TaxList taxes) throws SalesTaxesException {

		if (price == null) price = new Money();

		if (taxes == null) throw new InvalidTaxesException("Invalid taxes supplied to item.");

		// Only the name needs to be validated; the other items validate themselves.
		this.validateName(name);
		
		this.name = name;
		this.taxes = taxes;
		this.price = price;

		// Might as well calculate the tax amount right away since we know
		// the price and have our tax list.
		this.taxAmount = this.taxes.getTaxes(this.price);
	}


	/**
	 * Validate name of the item.
	 *
	 * @param name the name to be validated
	 * @throws InvalidItemException thrown if the name is not valid.
	 */
	protected void validateName(String name) throws InvalidItemException {
		if (name == null || name.length() == 0)
			throw new InvalidItemException("Item must have a name.");
	}

	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.item.Item#getName()
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.item.Item#getPrice()
	 */
	@Override
	public Money getPrice() {
		return price;
	}

	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.item.Item#getTaxAmount()
	 */
	@Override
	public Money getTaxAmount() {
		return this.taxAmount;
	}

	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.item.Item#getPriceTaxed()
	 */
	@Override
	public Money getPriceTaxed() {
		return this.price.add(this.getTaxAmount());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("1 %s: %s\n", 
				this.getName(), 
				this.getPriceTaxed().toString());
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DefaultItem)) {
			return false;
		}
		DefaultItem other = (DefaultItem) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (price == null) {
			if (other.price != null) {
				return false;
			}
		} else if (!price.equals(other.price)) {
			return false;
		}
		return true;
	}
		
}
