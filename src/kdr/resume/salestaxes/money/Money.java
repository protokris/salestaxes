package kdr.resume.salestaxes.money;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

import kdr.resume.salestaxes.exceptions.InvalidMoneyException;
import kdr.resume.salestaxes.exceptions.SalesTaxesRuntimeException;

/**
 * Implementation of a Money class. Used to hold a monetary amount. 
 * Based on BigDecimal internally and handles things like rounding, 
 * formatting, etc.
 * 
 * BigDecimal cannot be subclassed and so this class is built with composition :(
 * 
 * @author Kris Read
 */
public class Money {
	
	/** constant to round to nearest nickel. */
	protected static final BigDecimal NICKEL_ROUNDING_FACTOR = new BigDecimal(20);

	/** Internal representation of the amount. Defaults to $0.00 */
	protected BigDecimal value = new BigDecimal(0.00);

    /**
     * Instantiates a new money. Takes a string (for convenience). 
     *
     * @param value the value
     * @throws InvalidMoneyException thrown if the string amount is not valid
     */
    public Money(String value) throws InvalidMoneyException {
		try {
			if (value.startsWith("$")) value = value.substring(1);
			this.value = new BigDecimal(value);
			this.value = this.value.setScale(2, RoundingMode.HALF_UP);
			this.validateMoney();
		} catch (NumberFormatException e) {
			throw new InvalidMoneyException("Money created with invalid string: " + value, e);
		}
	}

	/**
	 * Instantiates a new money. BigDecimal is taken for convenience but
	 * the value will be rounded HALF_UP to 2 decimal places. 
	 *
	 * @param value the value
	 * @throws InvalidMoneyException thrown if the BigDecimal value is not valid
	 */
	public Money(BigDecimal value) throws InvalidMoneyException  {
		// Rounding is prudent since BigDecimal could have been created from a double
		value = value.setScale(2, RoundingMode.HALF_UP);
		this.value = value;
		this.validateMoney();
	}

	/**
	 * Instantiates a new money with value $0.00.
	 */
	public Money() {
	}
	
	/**
	 * Validate this money object and throw an exception if invalid.
	 *
	 * @throws InvalidMoneyException the invalid money exception
	 */
	protected void validateMoney() throws InvalidMoneyException {
		if (value.compareTo(BigDecimal.valueOf(0)) < 0) {
			throw new InvalidMoneyException("Money must be positive.");
		}
		try {
			// This checks if there is precision beyond 2 decimal places. 
			// If so, throw an exception. 
			this.value.movePointRight(2).intValueExact();
		} catch (ArithmeticException e) {
			throw new InvalidMoneyException("Money must not have fractional cents.", e);
		}
	}
	
	/**
	 * Instantiates a new money based on an existing (presumably valid) money.
	 * 
	 * Copy-constructor for convenience.
	 *
	 * @param money the money
	 */
	public Money(Money money) {
		this.value = money.toBigDecimal();
	}
	
	/**
	 * Gets the value as a BigDecimal. 
	 *
	 * @return the value
	 */
	protected BigDecimal toBigDecimal() {
		return this.value;
	}
		
	/**
	 * Adds an amount to the amount represented by this object.
	 *
	 * @param amount the amount to add
	 */
	public Money add(Money amount) {
		try {
			BigDecimal result = this.value.add(amount.toBigDecimal());
			return new Money(result);
		} catch (InvalidMoneyException e) {
			throw new SalesTaxesRuntimeException("Invalid money exception adding two valid moneys: ", e);
		}
	}
	
	/**
	 * Multiply money by a BigDecimal multiplier. 
	 * 
	 * @param multiplier number to multiply by
	 * @return multiplied money
	 */
	public Money multiply(BigDecimal multiplier) {
		try {
			BigDecimal result = this.value.multiply(multiplier);
			result = result.setScale(2, RoundingMode.UP);
			return new Money(result);
		} catch (InvalidMoneyException e) {
			throw new SalesTaxesRuntimeException("Invalid money exception multiplying a valid money: ", e);
		}
	}

	
	/**
	 * Rounds up the value of this money object to the nearest 5 cents.
	 * 
	 * @return rounded money object
	 */
	public Money roundUpToNearestNickel() {
		Money money;
		try {
			money = new Money(this.roundUpWithFactor(this.value, Money.NICKEL_ROUNDING_FACTOR));
		} catch (InvalidMoneyException e) {
			// This should not happen because we just rounded a valid money.
			throw new SalesTaxesRuntimeException("Invalid money exception rounding up to nearest nickel.", e);
		}
		return money;
	}

	/**
	 * Round up tax based on a factor. 
	 * 
	 * Currently only supports rounding up to nearest nickel.
	 * 
	 * @param value the value to round
	 * @param factor the factor to round by (e.g. NICKEL_ROUNDING_FACTOR)
	 * @return the tax after rounding
	 */
	protected BigDecimal roundUpWithFactor(BigDecimal value, BigDecimal factor) {
		// Algorithm is for 5 cents:  (Ceiling(value * 20))/20
		BigDecimal rounded = value.multiply(factor);
		rounded = rounded.setScale(0, RoundingMode.CEILING);
		rounded = rounded.divide(factor);
		return rounded;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		NumberFormat formatter = NumberFormat.getCurrencyInstance();
		return formatter.format(this.value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof Money)) {
			return false;
		}
		Money other = (Money) obj;
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else {
			// need to use compareTo here because BigDecimals
			// are stubborn; 0.0 != 0
			if (value.compareTo(other.value) != 0) {
				return false;
			}
		}
		return true;
	}
}