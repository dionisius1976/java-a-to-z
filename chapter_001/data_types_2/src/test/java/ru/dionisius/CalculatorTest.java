package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculatorTest {
	
	@Test
	public void whenTreePointTwentyFiveAddNinePOintTwentyFiveThenTwelvePOintTwentyFive(){
		Calculator calc = new Calculator();
		double first=3.25;
		double second=9.25;
		calc.add(first, second);
		double calcResult=calc.result;
		double expectedResult=12.5;
		assertThat(expectedResult, is(calcResult));
	}
	
	@Test
	public void whenThreePointSeventyFiveSubstructNinePOintTwentyFiveThenMinusFivePointFive(){
		Calculator calc = new Calculator();
		double first=3.75;
		double second=9.25;
		calc.substruct(first, second);
		double calcResult=calc.result;
		double expectedResult=-5.5;
		assertThat(expectedResult, is(calcResult));
	}
	
	@Test
	public void whenSixteenPointTwentyFiveDivideSixPointFiveThenTwoPointFive(){
		Calculator calc = new Calculator();
		double first=16.25;
		double second=6.5;
		calc.div(first, second);
		double calcResult=calc.result;
		double expectedResult=2.5;
		assertThat(expectedResult, is(calcResult));
	}
	
	@Test
	public void whenSixPOintFiveMultipleMinusSevenPointFiveThenMinusFortyEightPOintSeventyFive(){
		Calculator calc = new Calculator();
		double first=6.5;
		double second=-7.5;
		calc.multiple(first, second);
		double calcResult=calc.result;
		double expectedResult=-48.75;
		assertThat(expectedResult, is(calcResult));
	}
	
}