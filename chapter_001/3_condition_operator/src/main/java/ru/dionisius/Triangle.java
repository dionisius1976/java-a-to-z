package ru.dionisius;

/**
 * Class Triangle.
 * Calculates the area of triangle created by
 * three specified points.
 */
public class Triangle {
	/**
	 * First point of triangle.
	 */
	private final Point a;
	/**
	 * Second point of triangle.
	 */
	private final Point b;
	/**
	 * Third point of triangle.
	 */
	private final Point c;

	/**
	 * Default constructor.
	 * @param a First point of triangle.
	 * @param b Second point of triangle.
	 * @param c Third point of triangle.
	 */
	public Triangle(final Point a, final Point b, final Point c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}

	/**
	 * Calculates the area of triangle created by
	 * three specified points.
	 * @return ares of triangle created by
	 * three specified points.
	 */
	public double area() {
		double area = 0;
		double ab = this.a.distanceTo(this.b);
		double bc = this.b.distanceTo(this.c);
		double ca = this.c.distanceTo(this.a);
		if (ab > 0 && bc > 0 && ca > 0) {
			double perimeter = (ab + bc + ca) / 2;
			area = Math.sqrt(perimeter * (perimeter - ab) * (perimeter - bc) * (perimeter - ca));
		} else {
			System.out.println("Данная фигура не является тругольником! Длина одной из сторон равна нулю.");
		}
		return area;
	}
}
