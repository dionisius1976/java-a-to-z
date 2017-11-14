package ru.dionisius.service;

/**
 * Interface for all input classes.
 */
interface Input {
	/**
	 * Gets string 'question" and returns string 'answer'.
	 * @param question question.
	 * @return string 'answer'.
	 */
	 String ask(String question);

	/**
	 * Validate user's input.
	 * Gets string 'question', validate if user's answer
	 * belong to validate answers range and returns string 'answer'.
	 * @param question question.
	 * @param range validate answers range.
	 * @return string 'answer'.
	 */
	int ask(String question, int[] range);
}