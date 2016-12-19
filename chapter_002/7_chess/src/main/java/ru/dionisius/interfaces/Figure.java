package ru.dionisius.interfaces;

import ru.dionisius.models.Board;

/**
 * Interface for all chess figures.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public interface Figure {
	/**
	 * Verifies if move for specified figure is possible.
	 * @param board game board.
	 * @param startX starting coordinate X for this figure.
	 * @param startY starting coordinate Y for this figure.
	 * @param finishX end coordinate X for this figure.
	 * @param finishY starting coordinate Y for this figure.
	 * @return true if vove is possible and false if not.
	 */
	boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY);
	/**
	 * Verifies this figure  if it is white or black.
	 * @return true if white and false if black.
	 */
	boolean isWhite();
	/**
	 * Returns this figure identified symbol.
	 * @return this figure identified symbol.
	 */
	char getSymbol();
}