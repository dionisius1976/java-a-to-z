package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class Calculator.
 */
public class CalculatorTest {

	/**
	 * First argument.
	 */
	static final int FIRST = 9;
	/**
	 * Second argument.
	 */
	static final int SECOND = 3;
	/**
	 * Reference variable for Calculator instance.
	 */
	private Calculator calc;

	/**
	 * Constructor.
	 * Initiate variable for Calculator instance
	 */
	public CalculatorTest() {
		this.calc = new Calculator();
	}

	/**
	 *Tests addition of two parameters and compare result with expected result.
	 */
	@Test
	public void whenFirstArgumentAndSecondArgumentPassToAddMethodThenResultIsSummOfFirstAnsSecondArguments() {
		this.calc.add(this.FIRST, this.SECOND);
		double calcResult = this.calc.getResult();
		double expectedResult = this.FIRST + this.SECOND;
		assertThat(expectedResult, is(calcResult));
	}

	/**
	 *Tests subtruct of two parameters and compare result with expected result.
	 */
	@Test
	public void whenFirstArgumentAndSecondArgumentPassToSubtractMethodThenResultIsSubtractionOfFirstAnsSecondArguments() {
		this.calc.subtruct(this.FIRST, this.SECOND);
		double calcResult = this.calc.getResult();
		double expectedResult = this.FIRST - this.SECOND;
		assertThat(expectedResult, is(calcResult));
	}

	/**
	 *Tests dividing of two parameters and compare result with expected result.
	 */
	@Test
	public void whenFirstArgumentAndSecondArgumentPassToDivMethodThenResultIsDividingOfFirstAnsSecondArguments() {
		this.calc.div(this.FIRST, this.SECOND);
		double calcResult = this.calc.getResult();
		double expectedResult = this.FIRST / this.SECOND;
		assertThat(expectedResult, is(calcResult));
	}

	/**
	 *Tests multiplying of two parameters and compare result with expected result.
	 */
	@Test
	public void whenFirstArgumentAndSecondArgumentPassToMultiplyMethodThenResultIsMultiplyingOfFirstAnsSecondArguments() {
		this.calc.multiple(this.FIRST, this.SECOND);
		double calcResult = this.calc.getResult();
		double expectedResult = this.FIRST * this.SECOND;
		assertThat(expectedResult, is(calcResult));
	}
}