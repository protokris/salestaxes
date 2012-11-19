package kdr.resume.salestaxes.money;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.math.BigDecimal;
import java.text.NumberFormat;
import kdr.resume.salestaxes.exceptions.InvalidMoneyException;

import org.junit.Test;

/**
 * Tests for the Money class.
 */
public class MoneyTest {

	/**
	 * Test constructing money from a String.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testConstructorMoneyFromString() throws InvalidMoneyException {
		Money money = new Money("18.99");
		assertEquals(BigDecimal.valueOf(18.99), money.value);

		money = new Money("$18.51");
		assertEquals(BigDecimal.valueOf(18.51), money.value);
	}

	/**
	 * Test that Money objects can't be created from negative numbers in strings.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test (expected=InvalidMoneyException.class)
	public void testConstructorMoneyFromNegativeString() throws InvalidMoneyException {
		@SuppressWarnings("unused")
		Money money = new Money("-23.45");
	}

	/**
	 * Test that money objects can't be created from invalid non-numeric strings.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test (expected=InvalidMoneyException.class)
	public void testConstructorMoneyFromNonNumericString() throws InvalidMoneyException {
		@SuppressWarnings("unused")
		// Can he swing, from a web? No he can't, he's a pig.
		Money money = new Money("spider pig");
	}
	
	/**
	 * Test validation on Money precision amount.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test (expected=InvalidMoneyException.class)
	public void testValidateMoneyPrecision() throws InvalidMoneyException {
		Money money = new Money("100.00");
		money.value = new BigDecimal("100.001");
		money.validateMoney();
	}

	/**
	 * Test constructing money from a big decimal.
	 * 
	 * This is a tricky case because BigDecimal's constructor takes so many things,
	 * including doubles which can cause strange things.  E.g. 
	 * 
	 * new BigDecimal(1.45) <-- contains value 1.44999999999999 etc.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testConstrcutorMoneyFromBigDecimal() throws InvalidMoneyException {
		Money money = new Money(BigDecimal.valueOf(102.78));
		assertEquals(BigDecimal.valueOf(102.78), money.value);

		money = new Money(BigDecimal.valueOf(102.7800001));
		assertEquals(BigDecimal.valueOf(102.78), money.value);

		money = new Money(BigDecimal.valueOf(102.7799999));
		assertEquals(BigDecimal.valueOf(102.78), money.value);
	}

	/**
	 * Test that money objects can't be created from negative BigDecimals.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test (expected=InvalidMoneyException.class)
	public void testConstructorMoneyFromNegativeBigDecimal() throws InvalidMoneyException {
		@SuppressWarnings("unused")
		Money money = new Money(new BigDecimal("-12.34"));
	}
	
	/**
	 * Test constructor for money from another money (copy constructor).
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testConstructMoneyFromMoney() throws InvalidMoneyException {
		Money money1 = new Money(BigDecimal.valueOf(102.23));
		Money money2 = new Money(money1);
		assertEquals(money1, money2);
	}

	/**
	 * Test get value as a big decimal from money object.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testToBigDecimal() throws InvalidMoneyException {
		Money money = new Money(new BigDecimal(123.45));
		assertEquals("123.45", money.toBigDecimal().toString());
	}
	
	/**
	 * Test constructor rounding logic
	 * (make sure little errors in doubles don't make it into Money).
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testConstructorRounding() throws InvalidMoneyException {
		Money money = new Money(new BigDecimal(123.45000001));
		assertEquals("123.45", money.toBigDecimal().toString() );
		money = new Money(new BigDecimal(123.44999999999));
		assertEquals("123.45", money.toBigDecimal().toString());
		money = new Money("123.4499999");
		assertEquals("123.45", money.toBigDecimal().toString());
	}

	/**
	 * Test getting string representation of money.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testToString() throws InvalidMoneyException {
		Money money = new Money("123.45");
		assertEquals("$123.45", money.toString());
		assertEquals(NumberFormat.getCurrencyInstance().format(123.45), money.toString());
	}

	/**
	 * Test adding money objects together.
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testAdd() throws InvalidMoneyException {
		Money money1 = new Money("10.00");
		Money money2 = new Money("5.00");
		Money result = money1.add(money2);
		assertEquals(new Money("15.00"), result);
	}
	
	/**
	 * Test multiplying a money by a BigDecimal multiplier. 
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testMultiply() throws InvalidMoneyException {
		Money money1 = new Money("10.00");
		BigDecimal multiplier = new BigDecimal(0.5);
		Money result = money1.multiply(multiplier);
		assertEquals(new Money("5.00"), result);
	}
	
	/**
	 * Tests rounding to nearest nickel. 
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testRoundingToNearestNickel() throws InvalidMoneyException {
		Money money1 = new Money("12.34").roundUpToNearestNickel();
		assertEquals(new Money("12.35"), money1);
		
		money1 = new Money("12.349").roundUpToNearestNickel();
		assertEquals(new Money("12.35"), money1);
		
		money1 = new Money("12.35").roundUpToNearestNickel();
		assertEquals(new Money("12.35"), money1);
		
		money1 = new Money("12.351").roundUpToNearestNickel();
		assertEquals(new Money("12.35"), money1);
		
		money1 = new Money("12.39").roundUpToNearestNickel();
		assertEquals(new Money("12.40"), money1);

		money1 = new Money("12.391").roundUpToNearestNickel();
		assertEquals(new Money("12.40"), money1);
	}
	
	/**
	 * Test equals method. 
	 * 
	 * Money's equals method was generated by IDE but uses the compareTo 
	 * method to compare BigDecimals rather than equals. This is because
	 * BigDecimal is sensitive to things like trailing zeroes.  0.0 != 0.00
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testEquals() throws InvalidMoneyException {
		Money money1 = new Money("12.00");
		Money money2 = new Money(new BigDecimal(12));
		assertEquals(money1, money2);
		
		money1 = new Money("12.00");
		money2 = new Money(new BigDecimal(12.000));
		assertEquals(money1, money2);

		money1 = new Money("12.00");
		money2 = new Money(new BigDecimal("12.00"));
		assertEquals(money1, money2);

		money1 = new Money("12.00");
		money2 = new Money(new BigDecimal("12.001"));
		assertEquals(money1, money2);
	}
	
	/**
	 * Hashcode was generated by IDE 
	 * 
	 * @throws InvalidMoneyException
	 */
	@Test
	public void testHashcode() throws InvalidMoneyException {
		Money money1 = new Money("100.00");
		Money money2 = new Money("10.00");
		assertFalse(money1.hashCode() == money2.hashCode());
	}
	
}
