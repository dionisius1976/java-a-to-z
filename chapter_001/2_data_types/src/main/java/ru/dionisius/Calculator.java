package ru.dionisius;

/**
 * Class Calculator.
 * Execute four mathematics operations:
 * add, multiply, subtruct and divide
 */
public class Calculator {
	/**
	 *Result of mathematical operation.
	 */
	private double result;

	/**
	 * Getter for result.
	 * @return result of mathematical operation
	 */
	public double getResult() {
		return result;
	}

	/**
	 * adds specified integer values and saves result in variable "result".
	 * @param first first integer parameter
	 * @param second second integer parameter
	 */
	public void add(double first, double second) {

		this.result = first + second;
	}

	/**
	 * subtructs specified integer values and saves result in variable "result".
	 * @param first first integer parameter
	 * @param second second integer parameter
	 */
	public void subtruct(double first, double second) {

		this.result = first - second;
	}

	/**
	 * divides specified integer values and saves result in variable "result".
	 * @param first first integer parameter
	 * @param second second integer parameter
	 */
	public void div(double first, double second) {

		this.result = first / second;
	}

	/**
	 * multiple specified integer values and saves result in variable "result".
	 * @param first first integer parameter
	 * @param second second integer parameter
	 */
	public void multiple(double first, double second) {

		this.result = first * second;
	}
}

