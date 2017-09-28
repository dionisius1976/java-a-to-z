package ru.dionisius.model;


import ru.dionisius.exceptions.IncorrectMoveException;
import ru.dionisius.interfaces.Figure;

/**
 * Chess board class.
 * It model game field with figures, provides initial setting
 * of all figures on their controllers positions, sets and remove figures.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public class Board {
	/**
	 * X coordinate of initial position for all white figures.
	 */
	static final int WHITES_START_X_COORDINATE = 0;
	/**
	 * X coordinate of initial position for all black figures.
	 */
	static final int BLACKS_START_X_COORDINATE = 7;
	/**
	 * X coordinate of initial position for first rook.
	 */
	static final int FIRST_ROOK_X_COORDINATE = 0;
	/**
	 * X coordinate of initial position for second rook.
	 */
	static final int SECOND_ROOK_X_COORDINATE = 7;
	/**
	 * X coordinate of initial position for first knight.
	 */
	static final int FIRST_KNIGHT_X_COORDINATE = 1;
	/**
	 * X coordinate of initial position for second knight.
	 */
	static final int SECOND_KNIGHT_X_COORDINATE = 6;
	/**
	 * X coordinate of initial position for first bishop.
	 */
	static final int FIRST_BISHOP_X_COORDINATE = 2;
	/**
	 * X coordinate of initial position for second bishop.
	 */
	static final int SECOND_BISHOP_X_COORDINATE = 5;
	/**
	 * X coordinate of initial position for king.
	 */
	static final int KING_X_COORDINATE = 4;
	/**
	 * X coordinate of initial position for queen.
	 */
	static final int QUEEN_X_COORDINATE = 3;
	/**
	 * X coordinate of initial position for all white pawns.
	 */
	static final int WHITE_PAWN_X_COORDINATE = 1;
	/**
	 * X coordinate of initial position for all black pawns.
	 */
	static final int BLACK_PAWN_X_COORDINATE = 6;
	/**
	 * Chess board dimensions size.
	 */
	static final int BOARD_SIZE = 8;
	/**
	 * Chess board field.
	 */
  	private Figure[][] field = new Figure[BOARD_SIZE][BOARD_SIZE];
	/**
	 * Getter for this field with figures on it.
	 * @return this field with figures on it.
	 */
	public Figure[][] getField() {
		return this.field;
	}
	/**
	 * Returns size of this chess field.
	 * @return size of this chess field.
	 */
	public int getSize() {
		return this.getField().length;
	}
	/**
	 * Returns figure situated on cell with coordinate (x, y).
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @return figure situated on cell with coordinate (x, y).
	 */
	public Figure getFigure(final int x, final int y) {
		return this.field[x][y];
	}
	/**
	 * Sets specified figure on cell with coordinate (x, y).
	 * @param figure specified figure.
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 */
	public void setFigure(Figure figure, int x, int y) {
		this.field[x][y] = figure;
	}
	/**
	 * Initial setting of all figures on their controllers positions.
	 */
	public void initialFill() {
		this.field[WHITES_START_X_COORDINATE][FIRST_ROOK_X_COORDINATE] = new Rook(true);
		this.field[WHITES_START_X_COORDINATE][FIRST_KNIGHT_X_COORDINATE] = new Knight(true);
		this.field[WHITES_START_X_COORDINATE][FIRST_BISHOP_X_COORDINATE] = new Bishop(true);
		this.field[WHITES_START_X_COORDINATE][QUEEN_X_COORDINATE] = new Queen(true);
		this.field[WHITES_START_X_COORDINATE][KING_X_COORDINATE] = new King(true);
		this.field[WHITES_START_X_COORDINATE][SECOND_BISHOP_X_COORDINATE] = new Bishop(true);
		this.field[WHITES_START_X_COORDINATE][SECOND_KNIGHT_X_COORDINATE] = new Knight(true);
		this.field[WHITES_START_X_COORDINATE][SECOND_ROOK_X_COORDINATE] = new Rook(true);
		for (int column = 0; column < this.getSize(); column++) {
			this.field[WHITE_PAWN_X_COORDINATE][column] = new Pawn(true);
		}
		for (int column = 0; column < this.getSize(); column++) {
			this.field[BLACK_PAWN_X_COORDINATE][column] = new Pawn(false);
		}
		this.field[BLACKS_START_X_COORDINATE][FIRST_ROOK_X_COORDINATE] = new Rook(false);
		this.field[BLACKS_START_X_COORDINATE][FIRST_KNIGHT_X_COORDINATE] = new Knight(false);
		this.field[BLACKS_START_X_COORDINATE][FIRST_BISHOP_X_COORDINATE] = new Bishop(false);
		this.field[BLACKS_START_X_COORDINATE][QUEEN_X_COORDINATE] = new Queen(false);
		this.field[BLACKS_START_X_COORDINATE][KING_X_COORDINATE] = new King(false);
		this.field[BLACKS_START_X_COORDINATE][SECOND_BISHOP_X_COORDINATE] = new Bishop(false);
		this.field[BLACKS_START_X_COORDINATE][SECOND_KNIGHT_X_COORDINATE] = new Knight(false);
		this.field[BLACKS_START_X_COORDINATE][SECOND_ROOK_X_COORDINATE] = new Rook(false);
	}
	/**
	 * Removes any figure (clean) from cell with coordinate (x, y).
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 */
	public void removeFigure(int x, int y) {
		this.field[x][y] = null;
	}
	/**
	 * Verifies if cell with coordinate (x, y) is free.
	 * @param x coordinate x.
	 * @param y coordinate y.
	 * @return true if cell is free and false if not.
	 */
	public boolean isCellFree(final int x, final int y) {
		return this.getFigure(x, y) == null;
	}
	/**
	 * Verifies if cell with coordinate (x, y) is occupied by opponent.
	 * @param white color of allay.
	 * @param x coordinate x.
	 * @param y coordinate y.
	 * @return true if cell is occupied by opponent and false if not.
	 */
	public boolean isCellOccupiedByOpponent(final boolean white, final int x, final int y) {
		return (this.getFigure(x, y) != null && this.getFigure(x, y).isWhite() != white);
	}
	/**
	 * Moves figure situated on cell with coordinates (startX, startY) to cell with coordinates (finishX, finishY).
	 * If move is impossible throws IncorrectMoveException.
	 * @param startX X coordinate of starting cell.
	 * @param startY Y coordinate of starting cell.
	 * @param finishX X coordinate of finish cell.
	 * @param finishY Y coordinate of finish cell.
	 */
	public void move(final int startX, final int startY, final int finishX, final int finishY) {
		if (this.getFigure(startX, startY).isMovePossible(this, startX, startY, finishX, finishY)) {
			this.setFigure(this.getFigure(startX, startY), finishX, finishY);
		} else {
			throw new IncorrectMoveException("Невозможный ход для данной фигуры!");
		}
		this.removeFigure(startX, startY);
	}
}