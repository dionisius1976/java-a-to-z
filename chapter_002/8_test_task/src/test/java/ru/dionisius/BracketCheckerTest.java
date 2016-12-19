package ru.dionisius;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Class for testing BracketChecker class.
 */
public class BracketCheckerTest {
	/**
	 * Checks string for brace pairs and first brace.
	 */
	@Test
	public void whenTestStringIsTrueThenTrue() {
		String testString = "()(()((())))";
		boolean expectedValue = true;
		BracketChecker checker = new BracketChecker();
		assertEquals(expectedValue, checker.check(testString));
	}
	/**
	 * Checks string for brace pairs and first brace.
	 */
	@Test
	public void whenTestStringIsFalseThenFalse() {
		String testString = "())";
		boolean expectedValue = false;
		BracketChecker checker = new BracketChecker();
		assertEquals(expectedValue, checker.check(testString));
	}
	/**
	 * Checks string for brace pairs and first brace.
	 */
	@Test
	public void whenFirstBracketIsClosingThenFalse() {
		String testString = ")(";
		boolean expectedValue = false;
		BracketChecker checker = new BracketChecker();
		assertEquals(expectedValue, checker.check(testString));
	}
}