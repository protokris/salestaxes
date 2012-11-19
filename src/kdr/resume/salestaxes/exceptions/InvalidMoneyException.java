package kdr.resume.salestaxes.exceptions;


/**
 * InvalidMoneyException.
 *
 * Thrown when a money is constructed with invalid input.
 * 
 * @see kdr.resume.salestaxes.exceptions.SalesTaxesException
 * @author Kris Read
 */
@SuppressWarnings("serial")
public class InvalidMoneyException extends SalesTaxesException {

	public InvalidMoneyException(String arg0) {
		super(arg0);
		//  Auto-generated constructor stub
	}

	public InvalidMoneyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		//  Auto-generated constructor stub
	}
}
