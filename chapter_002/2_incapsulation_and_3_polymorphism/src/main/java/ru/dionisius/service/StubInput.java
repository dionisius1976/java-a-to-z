package ru.dionisius.service;

/**
 * Class StubInput.
 * This class using for emulate
 * users answers inputed in console.
 */
public class StubInput implements Input {
	/**
	 * Array for emulating users answers.
	 */
	private String[] answers;
	/**
	 * Position in array for emulating users answers.
	 */
	private int position = 0;
	/**
	 * Default constructor.
	 * @param answers Array for emulating users answers.
	 */
	public StubInput(String[] answers) {
		this.answers = answers;
	}

	/**
	 * Returns position of emulated user's answer in array of answers.
	 * @param quastion question.
	 * @return answer.
	 */
	public String ask(String quastion) {
		return this.answers[position++];
	}
}