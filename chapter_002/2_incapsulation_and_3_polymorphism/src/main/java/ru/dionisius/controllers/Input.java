package ru.dionisius.controllers;
/**
 * Interface for all input classes.
 */
interface Input {

	/**
	 * Gets string 'quastion" and returns string 'answer'.
	 * @param quation question.
	 * @return string 'answer'.
	 */
	 String ask(String quation);
}