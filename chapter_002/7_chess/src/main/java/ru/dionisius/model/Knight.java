package ru.dionisius.model;

import ru.dionisius.abstracts.AbstractFigure;
import ru.dionisius.interfaces.Figure;

import java.util.Random;

/**
 * Chess figure knight.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public class Knight extends AbstractFigure implements Figure {
	/**
	 * Random number for generating hash code.
	 */
	static final int RANDOM_NUMBER_255 = 255;
	/**
	 * This figure identify symbol.
	 */
	static final char SYMBOL = 'N';
	/**
	 * Constructor.
	 * @param white color of this figure. True - white, false - black.
	 */
	public Knight(boolean white) {
		super(white);
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
	public boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY) {
		boolean isMovePossible = false;
		int deltaX = finishX - startX;
		int deltaY = finishY - startY;
		if (board.isCellFree(finishX, finishY) || board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY)) {
			if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1 || Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2) {
				isMovePossible = true;
			}
		}
		return isMovePossible;
	}
	/**
	 * Returns this figure identify symbol.
	 * @return this figure identify symbol.
	 */
	public char getSymbol() {
		return SYMBOL;
	}
	@Override
	public int hashCode() {
		int hash = new Random().nextInt(RANDOM_NUMBER_255);
		return  hash * RANDOM_NUMBER_255;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Figure other = (Figure) obj;
		return (this.getSymbol() == other.getSymbol() && this.isWhite() == other.isWhite());
	}
}