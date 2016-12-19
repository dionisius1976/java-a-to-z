package ru.dionisius.models;

import ru.dionisius.abstracts.AbstractFigure;
import ru.dionisius.interfaces.Figure;

import java.util.Random;

/**
 * Chess figure king.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public class King extends AbstractFigure implements Figure {
	/**
	 * Random number for generating hash code.
	 */
	static final int RANDOM_NUMBER_255 = 255;
	/**
	 * This figure identify symbol.
	 */
	static final char SYMBOL = 'K';
	/**
	 * Constructor.
	 * @param white color of this figure. True - white, false - black.
	 */
	public King(boolean white) {
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
		int deltaY = Math.abs(finishX - startX);
		int deltaX = Math.abs(finishY - startY);
		if (deltaX <= 1 && deltaY <= 1 && (board.isCellFree(finishX, finishY) || board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY))) {
			isMovePossible = true;
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