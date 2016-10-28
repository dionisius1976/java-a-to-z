package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class FactorialTest {
	@Test
	public void whenNIsNineFactorialIs362880(){
		int n=9;
		Factorial factorial=new Factorial(n);
		int expectedFactorial=362880;
		int programFactorialValue=factorial.factorial();
		assertThat(expectedFactorial, is(programFactorialValue));
	}
	
	@Test
	public void whenNIsZeroFactorialIsOne(){
		int n=0;
		Factorial factorial=new Factorial(n);
		int expectedFactorial=1;
		int programFactorialValue=factorial.factorial();
		assertThat(expectedFactorial, is(programFactorialValue));
	}
	
	@Test
	public void whenNIsOneFactorialIsOne(){
		int n=1;
		Factorial factorial=new Factorial(n);
		int expectedFactorial=1;
		int programFactorialValue=factorial.factorial();
		assertThat(expectedFactorial, is(programFactorialValue));
	}
}