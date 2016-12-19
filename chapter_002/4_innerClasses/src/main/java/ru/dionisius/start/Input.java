package ru.dionisius.start;

/**
 * Interface for all input classes.
 */
interface Input {
	/**
	 * Gets string 'quastion" and returns string 'answer'.
	 * @param question question.
	 * @return string 'answer'.
	 */
	 String ask(String question);
}