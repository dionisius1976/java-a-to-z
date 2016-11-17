package ru.dionisius;

public class BracketChecker {
		
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