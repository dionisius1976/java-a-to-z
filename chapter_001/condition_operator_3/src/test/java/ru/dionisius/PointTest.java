package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class PointTest {

	@Test
	public void whenFirstPointAndTheSecondPointAreTheSameThenDistanseIsMinusOne() {
		Point pointFirst = new Point(3.5,-17.3);
		Point pointSecond = new Point(3.5,-17.3);
		double distance=pointFirst.distanceTo(pointSecond);
		double expectedDistance = -1;
		assertThat(expectedDistance, is(distance));
		}
		
	@Test
	public void whenFirstPointAndTheSecondPointAreDifferent() {
		Point pointFirst = new Point(4.5,-7.5);
		Point pointSecond = new Point(-5.5,0.5);
		double distance=pointFirst.distanceTo(pointSecond);
		double expectedDistance = 12.81;
		assertThat(expectedDistance, closeTo(distance, 0.01));
		}
	
}