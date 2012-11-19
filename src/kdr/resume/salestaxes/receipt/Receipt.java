package kdr.resume.salestaxes.receipt;

import java.io.PrintStream;
import java.util.List;

import kdr.resume.salestaxes.item.Item;
import kdr.resume.salestaxes.money.Money;

/**
 * Receipt interface. 
 * 
 * Implemented by DefaultReceipt and MockReceipt (mock object for testing)
 * 
 * @author Kris Read
 */
public interface Receipt extends List<Item> {

	/**
	 * Gets the total for the receipt.
	 *
	 * @return the total
	 */
	public abstract Money getTotal();

	/**
	 * Gets the taxes on the receipt.
	 *
	 * @return the taxes
	 */
	public abstract Money getTaxes();

	/**
	 * Convenience method to print receipts to a printstream.
	 * 
	 * @param stream Receipt will be output on this stream
	 */
	public abstract void print(PrintStream stream);

}