package ru.dionisius.service;

import org.junit.Test;
import ru.dionisius.model.Board;
import ru.dionisius.model.Knight;
import ru.dionisius.model.Pawn;
import ru.dionisius.model.Queen;

import static junit.framework.TestCase.assertEquals;
/**
 * Testing class for figure pawn.
 * X coordinate vertical, Y coordinate horizontal.
 * Coordinates range is 0..7.
 */
public class PawnTest {
	/**
	 * Chess board.
	 */
	private Board board  = new Board();
	/**
	 * Starting X coordinate.
	 */
	static final int START_X_COORDINATE = 1;
	/**
	 * Starting X coordinate.
	 */
	static final int START_Y_COORDINATE = 1;
	/**
	 * Finish X coordinate.
	 */
	static final int FINISH_X_COORDINATE = 2;
	/**
	 * Finish Y coordinate.
	 */
	static final int FINISH_Y_COORDINATE = 1;
	/**
	 * Finish fight X coordinate.
	 */
	static final int FINISH_FIGHT_X_COORDINATE = 2;
	/**
	 * Finish fight Y coordinate.
	 */
	static final int FINISH_FIGHT_Y_COORDINATE = 0;
	/**
	 * Finish first move X coordinate.
	 */
	static final int FINISH_FIRST_MOVE_X_COORDINATE = 3;
	/**
	 * Finish X first move coordinate.
	 */
	static final int FINISH_FIRST_MOVE_Y_COORDINATE = 1;
	/**
	 * Occupied by other figure X coordinate.
	 */
	static final int OCCUPIED_X_COORDINATE = 2;
	/**
	 * Occupied by other figure Y coordinate.
	 */
	static final int OCCUPIED_Y_COORDINATE = 1;
	/**
	 * Wrong move X coordinate.
	 */
	static final int WRONG_MOVE_X_COORDINATE = 1;
	/**
	 * Wrong move Y coordinate.
	 */
	static final int WRONG_MOVE_Y_COORDINATE = 0;
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
	 * Tests if first move in two cells is possible.
	 */
	@Test
	public void whenFirstTwoCellsMoveThenTrue() {
		this.board.setFigure(new Pawn(true), START_X_COORDINATE, START_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_FIRST_MOVE_X_COORDINATE, FINISH_FIRST_MOVE_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_TRUE, this.resultValue);
	}
	/**
	 * Tests if first move is possible when path is not free.
	 */
	@Test
	public void whenFirstMoveAndPathIsNotFreeThenMoveIsFalse() {
		this.board.setFigure(new Pawn(true), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Knight(true), OCCUPIED_X_COORDINATE, OCCUPIED_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_FIRST_MOVE_X_COORDINATE, FINISH_FIRST_MOVE_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Tests if move is possible when finish cell occupied by opponent.
	 */
	@Test
	public void whenFinishCellOccupiedByOpponentThenMoveIsFalse() {
		this.board.setFigure(new Pawn(true), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(false), FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Tests if move is possible when finish cell occupied by allay.
	 */
	@Test
	public void whenFinishCellOccupiedByAllayThenMoveIsFalse() {
		this.board.setFigure(new Pawn(true), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(true), FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_X_COORDINATE, FINISH_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Tests if fight move is possible when finish cell occupied by opponent.
	 */
	@Test
	public void whenFinishFightCellOccupiedByOpponentThenMoveIsTrue() {
		this.board.setFigure(new Pawn(true), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(false), FINISH_FIGHT_X_COORDINATE, FINISH_FIGHT_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_FIGHT_X_COORDINATE, FINISH_FIGHT_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_TRUE, this.resultValue);
	}
	/**
	 * Tests if fight move is possible when finish cell occupied by allay.
	 */
	@Test
	public void whenFinishFightCellOccupiedByAllayThenMoveIsFalse() {
		this.board.setFigure(new Pawn(true), START_X_COORDINATE, START_Y_COORDINATE);
		this.board.setFigure(new Queen(true), FINISH_FIGHT_X_COORDINATE, FINISH_FIGHT_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, FINISH_FIGHT_X_COORDINATE, FINISH_FIGHT_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Tests if move is possible when it is not by rule.
	 */
	@Test
	public void whenWrongWayToMoveThenMoveIsFalse() {
		this.board.setFigure(new Pawn(true), START_X_COORDINATE, START_Y_COORDINATE);
		this.resultValue = board.getFigure(START_X_COORDINATE, START_Y_COORDINATE).isMovePossible(this.board, START_X_COORDINATE, START_Y_COORDINATE, WRONG_MOVE_X_COORDINATE, WRONG_MOVE_Y_COORDINATE);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
}

