package kdr.resume.salestaxes.exceptions;


/**
 * InvalidItemException.
 * 
 * Thrown when an invalid item is given as a parameter.
 *  
 * @see kdr.resume.salestaxes.exceptions.SalesTaxesException
 * @author Kris Read
 */
@SuppressWarnings("serial")
public class InvalidItemException extends SalesTaxesException {

	public InvalidItemException(String message) {
		super(message);
	}
}
