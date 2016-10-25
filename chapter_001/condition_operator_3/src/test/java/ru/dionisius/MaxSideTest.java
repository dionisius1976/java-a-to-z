package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class MaxSideTest {
	@Test
	public void whenFirstSideIsMaxThenReturnedSideIsFirst() {
	Point a=new Point(-8.5, -1.5);
	Point b=new Point(2.5, 3.5);
	Point c=new Point(1.5, -2.5);
	Triangle triangle=new Triangle(a, b, c);
	MaxSideCalc maxSideCalc=new MaxSideCalc(triangle);
	double expectedSide=12.08;
	double maxSide=maxSideCalc.maxSide();
	assertThat(expectedSide, closeTo(maxSide, 0.01));
	}
}