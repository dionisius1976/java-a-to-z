package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class SquareTest {
	@Test
	public void whenAIsTwoBIsThreeCIsFourExpressionValueIsOneHundredFiftySix(){
		Square square=new Square(2, 3, 4);
		double expectedValue=156;
		double squareValue=square.calculate(8);
		assertThat(expectedValue, closeTo(squareValue, 0.01));
	}
}