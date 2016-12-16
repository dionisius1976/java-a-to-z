package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests calculation of method factorial() of class Factorial.
 */
public class FactorialTest {
	/**
	 * Testing numeral eight.
	 */
	static final int NUMERAL_EIGHT = 9;
	/**
	 * Testing numeral one.
	 */
	static final int NUMERAL_ONE = 1;
	/**
	 * Testing numeral zero.
	 */
	static final int NUMERAL_ZERO = 0;
	/**
	 * Expected result of calculation.
	 */
	static final int EXPECTED_RESULT = 362880;
	/**
	 * Factorial class reference variable.
	 */
	private Factorial factorial;
	/**
	 * Factorial method calculation result value.
	 */
	private int resultValue;

	/**
	 * Tests if expected value equals method factorial() result value.
	 */
	@Test
	public void whenNumeralIsNineThenFactorialIsExpectedValue() {
		factorial = new Factorial(NUMERAL_EIGHT);
		this.resultValue = factorial.factorial();
		assertThat(EXPECTED_RESULT, is(this.resultValue));
	}
	/**
	 * Tests if value of factorial of zero is one.
	 */
	@Test
	public void whenNumeralIsZeroThenFactorialIsOne() {
		this.factorial = new Factorial(NUMERAL_ZERO);
		this.resultValue = factorial.factorial();
		assertThat(NUMERAL_ONE, is(this.resultValue));
	}
	/**
	 * Tests if value of factorial of one is one.
	 */
	@Test
	public void whenNIsOneThenFactorialIsOne() {
		this.factorial = new Factorial(NUMERAL_ONE);
		this.resultValue = factorial.factorial();
		assertThat(NUMERAL_ONE, is(this.resultValue));
	}
}