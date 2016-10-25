package ru.dionisius;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class TriangleTest {
	@Test
	public void whenFirstSideLengthIsNullThenTheAreaIsMinusOne() {
		Point pointFirst=new Point(3.5,-17.3);
		Point pointSecond=new Point(3.5,-17.3);
		Point pointThird=new Point(-8.5,7.8);
		Triangle triangle=new Triangle(pointFirst, pointSecond, pointThird);
		double area = triangle.area();
		double expectedDistance=-1;
		assertThat(expectedDistance, is(area));
	}
	@Test
	public void whenSecondSideLengthIsNullThenTheAreaIsMinusOne() {
		Point pointFirst=new Point(13.5,-7.3);
		Point pointSecond=new Point(3.5,-17.3);
		Point pointThird=new Point(3.5,-17.3);
		Triangle triangle=new Triangle(pointFirst, pointSecond, pointThird);
		double area = triangle.area();
		double expectedDistance=-1;
		assertThat(expectedDistance, is(area));
	}
	@Test
	public void whenThirdSideLengthIsNullThenTheAreaIsMinusOne() {
		Point pointFirst=new Point(13.5,-7.3);
		Point pointSecond=new Point(3.9,-1.3);
		Point pointThird=new Point(13.5,-7.3);
		Triangle triangle=new Triangle(pointFirst, pointSecond, pointThird);
		double area = triangle.area();
		double expectedDistance=-1;
		assertThat(expectedDistance, is(area));
	}
	@Test
	public void whenThirdSideLengthIsNullTheAreaIsMinusOne() {
		Point pointFirst=new Point(13.1, -7.3);
		Point pointSecond=new Point(-3.9, -1.3);
		Point pointThird=new Point(-8.5, 5.3);
		Triangle triangle=new Triangle(pointFirst, pointSecond, pointThird);
		double area = triangle.area();
		double expectedDistance=42.30;
		assertThat(expectedDistance, closeTo(area, 0.01));
	}
}