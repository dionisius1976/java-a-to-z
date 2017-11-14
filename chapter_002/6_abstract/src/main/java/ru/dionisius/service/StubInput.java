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
	 * @param question quastion for user.
	 * @return position of emulated user's answer in array of answers.
	 */
	public String ask(String question) {
		if (this.position == 4) {
			this.position = 0;
		}
		return this.answers[position++];
	}

	/**
	 * @param question question.
	 * @param range    validate answers range.
	 * @return position of emulated user's answer in array of answers.
	 */
	public int ask(String question, int[] range) {
//		return -1;
		if (this.position == 4) {
			this.position = 0;
		}
		return Integer.valueOf(this.answers[this.position++]);
	}
}