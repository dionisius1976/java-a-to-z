package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class bracketCheckerTest {

	/**
	 * check()
	 * This method checks string for brace pairs and first brace
	 * @param - string to check
	 */
	@Test
	public void whenTestStringIsTrueThenTrue(){
		String testString = "()(()((())))";
		boolean expectedValue = true;
		BracketChecker checker = new BracketChecker();
		assertEquals(expectedValue, checker.check(testString));
	}

	/**
	 * check()
	 * This method checks string for brace pairs and first brace
	 * @param - string to check
	 */
	@Test
	public void whenTestStringIsFalseThenFalse(){
		String testString = "())";
		boolean expectedValue = false;
		BracketChecker checker = new BracketChecker();
		assertEquals(expectedValue, checker.check(testString));
	}

	/**
	 * check()
	 * This method checks string for brace pairs and first brace
	 * @param - string to check
	 */
	@Test
	public void whenFirstBracketIsClosingThenFalse(){
		String testString = ")(";
		boolean expectedValue = false;
		BracketChecker checker = new BracketChecker();
		assertEquals(expectedValue, checker.check(testString));
	}

}