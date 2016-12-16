package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Tests method distanceTo in class Point.
 */
public class PointTest {
	/**
	 * X coordinate of first point.
	 */
	static final double FIRST_X = 4.5;
	/**
	 * Y coordinate of first point.
	 */
	static final double FIRST_Y = -7.5;
	/**
	 * X coordinate of second point.
	 */
	static final double SECOND_X = -5.5;
	/**
	 * Y coordinate of second point.
	 */
	static final double SECOND_Y = 0.5;
	/**
	 * Expected zero result.
	 */
	static final double EXPECTED_ZERO = 0;
	/**
	 * Expected result of method distanceTo().
	 */
	static final double EXPECTED_RESULT = 12.81;
	/**
	 * The margin error in calculation with double types.
	 */
	static final double THE_MARGIN_ERROR = 0.01;
	/**
	 * First point.
	 */
	private Point pointFirst;
	/**
	 * Second point.
	 */
	private Point pointSecond;
	/**
	 * X coordinate of first point.
	 */
	/**
	 * Distance between points.
	 */
	private double distance;

	/**
	 * Tests if distance is null when first point and second point are the same.
	 */
	@Test
	public void whenFirstPointAndTheSecondPointAreTheSameThenDistanseIsZero() {
		this.pointFirst = new Point(FIRST_X, FIRST_Y);
		this.pointSecond = new Point(FIRST_X, FIRST_Y);
		this.distance = pointFirst.distanceTo(pointSecond);
		assertThat(EXPECTED_ZERO, is(distance));
		}

	/**
	 * Tests if distance equals expected result when points are different.
	 */
	@Test
	public void whenFirstPointAndTheSecondPointAreDifferentThenExpectedResult() {
		Point pointFirst = new Point(FIRST_X, FIRST_Y);
		Point pointSecond = new Point(SECOND_X, SECOND_Y);
		this.distance = pointFirst.distanceTo(pointSecond);
		assertThat(EXPECTED_RESULT, closeTo(this.distance, THE_MARGIN_ERROR));
		}
}