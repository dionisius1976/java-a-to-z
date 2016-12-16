package ru.dionisius;

/**
 * Class Square.
 * This class calculates quadratic equation.
 */
public class Square {
	/**
	 * First coefficient of specified quadratic equation.
	 */
	private final float a;
	/**
	 * Second coefficient of specified quadratic equation.
	 */
	private final float b;
	/**
	 * Third coefficient of specified quadratic equation.
	 */
	private final float c;
	/**
	 * Default constructor.
	 * @param a first coefficient of specified quadratic equation.
	 * @param b second coefficient of specified quadratic equation.
	 * @param c third coefficient of specified quadratic equation.
	 */
	public Square(final float a, final float b, final float c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	/**
	 * Calculates this quadratic equation.
	 * @param x free variable of this quadratic equation.
	 * @return calculation value of this quadratic equation.
	 */
	public float calculate(final float x) {
		return this.a * x * x + this.b * x + this.c;
	}
}