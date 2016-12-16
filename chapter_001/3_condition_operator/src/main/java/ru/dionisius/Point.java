package ru.dionisius;

/**
 * Class Point.
 * Calculates the distance between specified in this class point and other point.
 */
public class Point {
	/**
	 * X coordinate this class point.
	 */
	private final double x;
	/**
	 * Y coordinate this class point.
	 */
	private final double y;

	/**
	 * Default constructor.
	 * @param x X coordinate this class point.
	 * @param y Y coordinate this class point.
	 */
	public Point(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Calculates the distance between specified in this class point and other point.
	 * @param point Oter point instanse.
	 * @return the distance between specified this class point and other point.
	 */
	public double distanceTo(Point point) {
		return Math.sqrt((point.x - this.x) * (point.x - this.x)
				+ (point.y - this.y) * (point.y - this.y));
		}
}




