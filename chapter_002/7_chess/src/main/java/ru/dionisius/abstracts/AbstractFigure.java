package ru.dionisius.abstracts;

import ru.dionisius.interfaces.Figure;
import ru.dionisius.models.Board;

/**
 * Abstract class for all chess figures.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public abstract class AbstractFigure implements Figure {
	/**
	 * Color of this figure. True - white, false - black.
	 */
	private final boolean white;
	/**
	 * Constructor.
	 * @param white Color of this figure. True - white, false - black.
	 */
	public AbstractFigure(boolean white) {
		this.white = white;
	}
	/**
	 * Verifies this figure  if it is white or black.
	 * @return true if white and false if black.
	 */
	public boolean isWhite() {
		return this.white;
	}
	/**
	 * Verifies if move for specified figure is possible.
	 * @param board game board.
	 * @param startX starting coordinate X for this figure.
	 * @param startY starting coordinate Y for this figure.
	 * @param finishX end coordinate X for this figure.
	 * @param finishY starting coordinate Y for this figure.
	 * @return true if vove is possible and false if not.
	 */
	public abstract boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY);
	/**
	 * Returns this figure identify symbol.
	 * @return this figure identify symbol.
	 */
	public abstract char getSymbol();
}