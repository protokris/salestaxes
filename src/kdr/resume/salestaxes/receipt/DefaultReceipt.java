package kdr.resume.salestaxes.receipt;

import java.io.PrintStream;
import java.util.ArrayList;

import kdr.resume.salestaxes.item.Item;
import kdr.resume.salestaxes.money.Money;

/**
 * DefaultReceipt is an implementation of the Receipt interface. 
 * 
 * Represents a receipt for items purchased.
 * 
 * Mainly the receipt holds Items and can be printed.
 * 
 * @author Kris Read
 */
@SuppressWarnings("serial")
public class DefaultReceipt extends ArrayList<Item> implements Receipt {

	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.receipt.Receipt#getTotal()
	 */
	@Override
	public Money getTotal() {
		Money total = new Money();
		for (Item item : this) {
			total = total.add(item.getTaxAmount());
			total = total.add(item.getPrice());
		}
		return total;
	}

	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.receipt.Receipt#getTaxes()
	 */
	@Override
	public Money getTaxes() {
		Money taxes = new Money();
		for (Item item : this) {
            taxes = taxes.add(item.getTaxAmount());
		}
		return taxes;
	}
	
	/**
	 * Gets the string format total and tax subototal.
	 *
	 * @return the string
	 */
	protected String printTotals() {
		// This uses the Java 5 printf format features.
		String taxes = String.format("%s: %s\n",
				Messages.getString("Receipt.SalesTaxSubtotalPrompt"), 
				this.getTaxes().toString());

		String total = String.format("%s: %s\n",
				Messages.getString("Receipt.TotalPrompt"), 
				this.getTotal().toString());

		return taxes+total;
	}

	/**
	 * Gets the string format for all the items in the receipt.
	 *
	 * @return the string
	 */
	protected String printItems() {
		StringBuffer buffer = new StringBuffer();
		for (Item item : this) {
			buffer.append(item.toString());
		}
		return buffer.toString();
	}

	/* (non-Javadoc)
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString() {
		return printItems() + printTotals();
	}
	
	/* (non-Javadoc)
	 * @see kdr.resume.salestaxes.receipt.Receipt#print(java.io.PrintStream)
	 */
	@Override
	public void print(PrintStream stream) {
		stream.println(this.toString());
	}
	
}
