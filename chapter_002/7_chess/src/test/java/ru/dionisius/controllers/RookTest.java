package ru.dionisius.controllers;

import org.junit.Test;
import ru.dionisius.models.Board;
import ru.dionisius.models.Knight;
import ru.dionisius.models.Queen;
import ru.dionisius.models.Rook;

import static junit.framework.TestCase.assertEquals;
/**
 * Testing class for figure rook.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public class RookTest {
	/**
	 * Chess board.
	 */
	private Board board  = new Board();
	/**
	 * Starting X coordinate.
	 */
	static final int START_X_COORDINATE = 3;
	/**
	 * Starting Y coordinate.
	 */
	static final int START_Y_COORDINATE = 2;
	/**
	 * Finish X coordinate.
	 */
	static final int FINISH_X_COORDINATE = 1;
	/**
	 * Finish Y coordinate.
	 */
	static final int FINISH_Y_COORDINATE = 2;
	/**
	 * Occupied by other figure X coordinate.
	 */
	static final int OCCUPIED_X_COORDINATE = 2;
	/**
	 * Occupied by other figure Y coordinate.
	 */
	static final int OCCUPIED_Y_COORDINATE = 2;
	/**
	 * Wrong move X coordinate.
	 */
	static final int WRONG_MOVE_X_COORDINATE = 2;
	/**
	 * Wrong move Y coordinate.
	 */
	static final int WRONG_MOVE_Y_COORDINATE = 1;
	/**
	 * Expected true value.
	 */
	static final boolean EXPECTED_VALUE_TRUE = true;
	/**
	 * Expected false value.
	 */
	static final boolean EXPECTED_VALUE_FALSE = false;
	/**
	 * Result value.
	 */
	private boolean resultValue;
	/**
	 * Tests if move is possible when path is not free.
	 */
	@Test
	public void whenPathIsNotFreeThenMoveIsFalse() {
		this.board.setFigure(new Rook(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(true), FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		this.board.setFigure(new Knight(true), OCCUPIED_X_COORDINATE, OCCUPIED_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Tests if move is possible when finish cell occupied by opponent.
	 */
	@Test
	public void whenFinishCellOccupiedByOpponentThenMoveIsTrue() {
		this.board.setFigure(new Rook(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(true), FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_TRUE, this.resultValue);
	}
	/**
	 * Tests if move is possible when finish cell occupied by allay.
	 */
	@Test
	public void whenFinishCellOccupiedByAllayThenMoveIsFalse() {
		this.board.setFigure(new Rook(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(false), FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Tests if move is possible when it is not by rule.
	 */
	@Test
	public void whenWrongWayToMoveThenMoveIsFalse() {
		this.board.setFigure(new Rook(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, WRONG_MOVE_X_COORDINATE, WRONG_MOVE_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
}