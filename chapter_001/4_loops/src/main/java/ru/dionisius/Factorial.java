package ru.dionisius;

/**
 * Class Factorial.
 * Its method factorial() calculates
 * factorial of specified numeral.
 */
public class Factorial {

	/**
	 * Specified numeral.
	 */
	private int n;

	/**
	 * Default constructor.
	 * @param n specified numeral.
	 */
	public Factorial(final int n) {
		this.n = n;
	}

	/**
	 * Calculates factorial of specified numeral of this class.
	 * @return factorial value.
	 */
	public int factorial() {
		int factorial = 1;
		if (this.n >= 0) {
			while (this.n > 1) {
				factorial *= this.n;
				this.n--;
			}
		} else {
			System.out.println("It's impossible calculate factorial for negative numeral!");
		}
		return factorial;
	}
}