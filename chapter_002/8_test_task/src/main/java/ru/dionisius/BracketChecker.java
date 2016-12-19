package ru.dionisius;

/**
 * BracketChecker.
 *
 * @author Dionisius
 * @version $Id$
 * @since 0.1
 */
public class BracketChecker {

	/**
	 * check().
	 * This method checks if rules for brackets in specified string execute.
	 * @param checkString - specified string.
	 *@return true if rules execute and false if not.
	 */
	public boolean check(String checkString) {
		char[] checkArray = checkString.toCharArray();
		boolean test = false;
		int openBracketCounter = 0;
		int closeBracketCounter = 0;
		if (checkArray[0] != ')') {
			for (int index = 0; index < checkArray.length; index++) {
				if (checkArray[index] == '(') {
					openBracketCounter++;
				}
				if (checkArray[index] == ')') {
					closeBracketCounter++;
				}
			}
			if (openBracketCounter == closeBracketCounter) {
				test = true;
			}
		}
		return test;
	}
}