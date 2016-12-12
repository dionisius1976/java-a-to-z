package ru.dionisius;

/**
 *
 */
public class Point {
	/**
	 *
	 */
	public double x;
	/**
	 *
	 */
	public double y;

	/**
	 * @param x
	 * @param y
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @param point
	 * @return
	 */
	public double distanceTo(Point point) {
		double distance = Math.sqrt((point.x - this.x) * (point.x - this.x)
				+ (point.y - this.y) * (point.y - this.y));
		if (distance > 0) return distance;
		else return -1;
		}
	}




