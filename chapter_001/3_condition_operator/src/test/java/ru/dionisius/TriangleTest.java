package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Tests method area() of Triangle class.
 */
public class TriangleTest {
	/**
	 * The margin error in calculation with double types.
	 */
	static final double THE_MARGIN_ERROR = 0.01;
	/**
	 * X coordinate of point a.
	 */
	static final double A_X = 13.1;
	/**
	 * X coordinate of point a.
	 */
	static final double A_Y = -7.3;
	/**
	 * Y coordinate of point b.
	 */
	static final double B_X = -3.9;
	/**
	 * Y coordinate of point b.
	 */
	static final double B_Y = -1.3;
	/**
	 * X coordinate of point c.
	 */
	static final double C_X = -8.5;
	/**
	 * Y coordinate of point c.
	 */
	static final double C_Y = 5.3;
	/**
	 * Expected zero result.
	 */
	static final double EXPECTED_ZERO = 0;
	/**
	 * Expected area.
	 */
	static final double EXPECTED_AREA = 42.3;
	/**
	 * Point a.
	 */
	private Point a;
	/**
	 * Point b.
	 */
	private Point b;
	/**
	 * Point c.
	 */
	private Point c;
	/**
	 * Triangle variable.
	 */
	private Triangle triangle;
	/**
	 * Calculating area.
	 */
	private double area;
	/**
	 * Tests if method result is equals expected result when each of the sides are not zero.
	 */
	@Test
	public void whenEachSideIsMoreThanZeroThenCalculatedAreaIsEqualsExpectedArea() {
		this.a = new Point(A_X, A_Y);
		this.b = new Point(B_X, B_Y);
		this.c = new Point(C_X, C_Y);
		this.triangle = new Triangle(this.a, this.b, this.c);
		this.area = triangle.area();
		assertThat(EXPECTED_AREA, closeTo(this.area, THE_MARGIN_ERROR));
	}
	/**
	 * Tests if method result is zero when side AB equals zero.
	 */
	@Test
	public void whenABIsZeroThenAreaIsZero() {
		this.a = new Point(A_X, A_Y);
		this.b = this.a;
		this.c = new Point(C_X, C_Y);
		this.triangle = new Triangle(this.a, this.b, this.c);
		this.area = triangle.area();
		assertThat(EXPECTED_ZERO, closeTo(this.area, THE_MARGIN_ERROR));
	}
	/**
	 * Tests if method result is zero when side BC equals zero.
	 */
	@Test
	public void whenBCIsZeroThenAreaIsZero() {
		this.a = new Point(A_X, A_Y);
		this.c = new Point(C_X, C_Y);
		this.b = this.c;
		this.triangle = new Triangle(this.a, this.b, this.c);
		this.area = triangle.area();
		assertThat(EXPECTED_ZERO, closeTo(this.area, THE_MARGIN_ERROR));
	}
	/**
	 * Tests if method result is zero when side AC equals zero.
	 */
	@Test
	public void whenCAIsZeroThenAreaIsZero() {
		this.b = new Point(B_X, B_Y);
		this.c = new Point(C_X, C_Y);
		this.a = this.c;
		this.triangle = new Triangle(this.a, this.b, this.c);
		this.area = triangle.area();
		assertThat(EXPECTED_ZERO, closeTo(this.area, THE_MARGIN_ERROR));
	}
}