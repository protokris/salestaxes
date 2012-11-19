package kdr.resume.salestaxes.item.factory;

import java.math.BigDecimal;

import kdr.resume.salestaxes.exceptions.InvalidItemException;
import kdr.resume.salestaxes.exceptions.InvalidMoneyException;
import kdr.resume.salestaxes.exceptions.SalesTaxesException;
import kdr.resume.salestaxes.item.DefaultItem;
import kdr.resume.salestaxes.item.Item;
import kdr.resume.salestaxes.money.Money;
import kdr.resume.salestaxes.tax.list.TaxList;

/**
 * A factory for creating Item objects.  Makes it far easier to create 
 * many items when you know they will have the same taxes.
 * 
 * @author Kris Read
 */
public class ItemFactory {
	
	/** The taxes to apply to all items made with this factory. */
	protected TaxList taxes;
	
	/**
	 * Instantiates a new item factory.
	 *
	 * @param taxes the taxes to apply to all items created with this factory
	 */
	public ItemFactory(TaxList taxes) {
		this.taxes = taxes;
	}
		
	/**
	 * Creates a new item with the given name and price.
	 *
	 * @param name the name
	 * @param price the price
	 * @return the item
	 * 
	 * @throws InvalidItemException  throws if the name is invalid (e.g. blank)
	 * @throws InvalidMoneyException throws if the price is invalid (e.g. negative)
	 */
	public Item create(String name, String price) throws SalesTaxesException {
		return new DefaultItem(name, new Money(price), this.taxes);
	}

	/**
	 * Creates a new item with the given name and price.
	 *
	 * @param name the name
	 * @param price the price
	 * @return the item
	 * 
	 * @throws InvalidItemException  throws if the name is invalid (e.g. blank)
	 * @throws InvalidMoneyException throws if the price is invalid (e.g. negative)
	 */
	public Item create(String name, Money price) throws SalesTaxesException {
		return new DefaultItem(name, price, this.taxes);
	}

	/**
	 * Creates a new item with the given name and price.
	 *
	 * @param name the name
	 * @param price the price
	 * @return the item
	 * 
	 * @throws InvalidItemException  throws if the name is invalid (e.g. blank)
	 * @throws InvalidMoneyException throws if the price is invalid (e.g. negative)
	 */
	public Item create(String name, BigDecimal price) throws SalesTaxesException {
		return new DefaultItem(name, new Money(price), this.taxes);
	}

}
