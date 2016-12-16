package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Tests class Square.
 */
public class SquareTest {
	/**
	 * The margin error in calculation with double types.
	 */
	static final double THE_MARGIN_ERROR = 0.01;
	/**
	 * Testing numeral two.
	 */
	static final int NUMERAL_TWO = 2;
	/**
	 * Testing numeral three.
	 */
	static final int NUMERAL_THREE = 3;
	/**
	 * Testing numeral four.
	 */
	static final int NUMERAL_FOUR = 4;
	/**
	 * Testing numeral eight.
	 */
	static final int NUMERAL_EIGHT = 8;
	/**
	 * Expected result of calculation.
	 */
	static final double EXPECTED_VALUE = 156;
	/**
	 * Factorial method calculation result value.
	 */
	private double resultValue;
	/**
	 * Tests if expected value equals method calculate() result value.
	 */
	@Test
	public void whenSpecifiedCoefficientThenExpectedValue() {
		Square square = new Square(NUMERAL_TWO, NUMERAL_THREE, NUMERAL_FOUR);
		this.resultValue = square.calculate(NUMERAL_EIGHT);
		assertThat(this.EXPECTED_VALUE, closeTo(this.resultValue, THE_MARGIN_ERROR));
	}
}