package ru.dionisius.start;

import org.junit.Test;
import ru.dionisius.models.Board;
import ru.dionisius.models.King;
import ru.dionisius.models.Queen;

import static junit.framework.TestCase.assertEquals;

/**
 * Testing class for figure king.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public class KingTest {
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
	static final int FINISH_X_COORDINATE = 3;
	/**
	 * Finish Y coordinate.
	 */
	static final int FINISH_Y_COORDINATE = 3;
	/**
	 * Occupied by other figure X coordinate.
	 */
	static final int WRONG_MOVE_X_COORDINATE = 1;
	/**
	 * Occupied by other figure Y coordinate.
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
		this.board.setFigure(new King(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(true), FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_TRUE, this.resultValue);
	}
	/**
	 * Verifies if move is possible when finish cell is occupied by ally.
	 */
	@Test
	public void whenFinishCellOccupiedByAllyThenMoveIsFalse() {
		this.board.setFigure(new King(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(false), FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Verifies if move is possible whe wrong finish cell specified.
	 */
	@Test
	public void whenWrongWayToMoveThenMoveIsFalse() {
		this.board.setFigure(new King(false), START_X_COORDINATE, START_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, WRONG_MOVE_X_COORDINATE, WRONG_MOVE_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
}