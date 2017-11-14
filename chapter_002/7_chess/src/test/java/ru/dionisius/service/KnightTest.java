package ru.dionisius.service;

import org.junit.Test;
import ru.dionisius.model.Board;
import ru.dionisius.model.Knight;
import ru.dionisius.model.Queen;

import static junit.framework.TestCase.assertEquals;

/**
 * Testing class for figure knight.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public class KnightTest {
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
	static final int FINISH_X_COORDINATE = 2;
	/**
	 * Finish Y coordinate.
	 */
	static final int FINISH_Y_COORDINATE = 4;
	/**
	 * Wrong move X coordinate.
	 */
	static final int WRONG_MOVE_X_COORDINATE = 3;
	/**
	 * Wrong move Y coordinate.
	 */
	static final int WRONG_MOVE_Y_COORDINATE = 3;
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
	 * Verifies if move is possible when finish cell is occupied by opponent.
	 */
	@Test
	public void whenFinishCellOccupiedByOpponentThenMoveIsTrue() {
		this.board.setFigure(new Knight(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(true), FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_TRUE, this.resultValue);
	}
	/**
	 * Verifies if move is possible when finish cell is occupied by ally.
	 */
	@Test
	public void whenFinishCellOccupiedByAllayThenMoveIsFalse() {
		this.board.setFigure(new Knight(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(false), FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Verifies if move is possible whe wrong finish cell specified.
	 */
	@Test
	public void whenWrongWayToMoveThenMoveIsFalse() {
		this.board.setFigure(new Knight(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, WRONG_MOVE_X_COORDINATE, WRONG_MOVE_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
}