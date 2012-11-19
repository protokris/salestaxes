package kdr.resume.salestaxes.exceptions;

/**
 * SalesTaxesRuntimeException
 * 
 * Subclass of RuntimeException.  Thrown in methods when an error occurs;
 * for example if a SalesTaxesException is thrown when it shouldn't. 
 * 
 * Does not need to be explicitly caught; will terminate the program. 
 * 
 * In theory it could be caught in the main method of a program. 
 * 
 * @author Kris Read
 */
@SuppressWarnings("serial")
public class SalesTaxesRuntimeException extends RuntimeException {

	public SalesTaxesRuntimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		//  Auto-generated constructor stub
	}
}
