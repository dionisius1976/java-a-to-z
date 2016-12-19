package ru.dionisius.models;

import ru.dionisius.abstracts.AbstractFigure;
import ru.dionisius.interfaces.Figure;

import java.util.Random;

/**
 * Chess figure pawn.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public class Pawn extends AbstractFigure implements Figure {
	/**
	 * Start coordinate X for first move for whites.
	 */
	static final int WHITE_FIRST_MOVE_X = 1;
	/**
	 * Start coordinate X for first move for blacks.
	 */
	static final int BLACK_FIRST_MOVE_X = 7;
	/**
	 * Difference X coordinates for first move.
	 */
	static final int DELTA_X_FIRST_MOVE = -2;
	/**
	 * Random number for generating hash code.
	 */
	static final int RANDOM_NUMBER_255 = 255;
	/**
	 * This figure identify symbol.
	 */
	static final char SYMBOL = 'P';
	/**
	 * Constructor.
	 * @param white color of this figure. True - white, false - black.
	 */
	public Pawn(boolean white) {
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
		if (this.isWhite()) {
			if (board.isCellFree(finishX, finishY)) {
				if (startX == WHITE_FIRST_MOVE_X) {
					if (deltaY == 0 && deltaX == 2 && board.isCellFree(startX + 1, startY)) {
						isMovePossible = true;
					}
				}
				if (deltaY == 0 && deltaX == 1) {
					isMovePossible = true;
				}
			}
			if (board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY)) {
				if (Math.abs(deltaY) == 1 && deltaX == 1) {
					isMovePossible = true;
				}
			}
			return isMovePossible;
		} else {
			if (board.isCellFree(finishX, finishY)) {
				if (startX == BLACK_FIRST_MOVE_X) {
					if (deltaY == 0 && deltaX == DELTA_X_FIRST_MOVE && board.isCellFree(startX - 1, startY)) {
						isMovePossible = true;
					}
				}
				if (deltaY == 0 && deltaX == -1) {
					isMovePossible = true;
				}
			}
			if (board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY)) {
				if (Math.abs(deltaY) == 1 && deltaX == -1) {
					isMovePossible = true;
				}
			}
			return isMovePossible;
		}
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