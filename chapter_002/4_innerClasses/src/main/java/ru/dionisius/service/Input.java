package ru.dionisius.service;

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