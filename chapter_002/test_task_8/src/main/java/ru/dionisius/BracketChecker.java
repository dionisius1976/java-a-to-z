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
	 * This method checks string for brace pairs and first brace
	 * @param - string to check
	 */
		
	public boolean check(String checkString){
		char[] checkArray = checkString.toCharArray();
		boolean test = false;
		int openBracketCounter = 0;
		int closeBracketCounter = 0;
		if(checkArray[0] != ')'){
			for (int index = 0; index < checkArray.length; index++) {
				if (checkArray[index] == '(') openBracketCounter++;
				if (checkArray[index] == ')') closeBracketCounter++;
			}
			if (openBracketCounter == closeBracketCounter) test = true;
		}
		return test;
	}
}