package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests class Calculate.
 */
public class CalculateTest {

	/**
	 * Tests that result string consists of three words, from one imputed word.
	 */
	@Test
	public void whenOneStopIsInEchoThenTreeStopsAreInCommandLine() {
		Calculate calc = new Calculate();
		String result = calc.echo("stop");
		assertThat(result, is("stop stop stop"));
	}

	/**
	 * Tests that result string consists of three nulls, from one imputed null.
	 */
	@Test
	public void whenNullIsInEchoThenTreeStopsAreInCommandLine() {
		Calculate calc = new Calculate();
		String result = calc.echo(null);
		assertThat(result, is("null null null"));
	}
}