package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class bracketCheckerTest {
	
	@Test
	public void whenTestStringIsTrueThenTrue(){
		String testString = "()(()((())))";
		boolean expectedValue = true;
		BracketChecker checker = new BracketChecker();
		assertEquals(expectedValue, checker.check(testString));
	}
	
	@Test
	public void whenTestStringIsFalseThenFalse(){
		String testString = "())";
		boolean expectedValue = false;
		BracketChecker checker = new BracketChecker();
		assertEquals(expectedValue, checker.check(testString));
	}
	
	@Test
	public void whenFirstBracketIsClosingThenFalse(){
		String testString = ")(";
		boolean expectedValue = false;
		BracketChecker checker = new BracketChecker();
		assertEquals(expectedValue, checker.check(testString));
	}

}