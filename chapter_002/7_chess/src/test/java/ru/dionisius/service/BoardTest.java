package ru.dionisius.service;

import ru.dionisius.interfaces.Figure;
import org.junit.Test;
import ru.dionisius.model.Board;
import ru.dionisius.model.Pawn;

import static junit.framework.TestCase.assertEquals;
/**
 * Class for testing class Board.
 */
public class BoardTest {
	/**
	 * Chess board.
	 */
	private Board board  = new Board();
	/**
	 * White pawn for tests.
	 */
	private Pawn whitePawn = new Pawn(true);
	/**
	 * White pawn for tests X controllers coordinate.
	 */
	static final int X_WHITE_PAWN_START = 1;
	/**
	 * White pawn for tests Y controllers coordinate.
	 */
	static final int Y_WHITE_PAWN_START = 1;
	/**
	 * White pawn for tests X finish coordinate.
	 */
	static final int X_WHITE_PAWN_FINISH = 2;
	/**
	 * White pawn for tests Y finish coordinate.
	 */
	static final int Y_WHITE_PAWN_FINISH = 1;
	/**
	 * Expected figure.
	 */
	private Figure expectedFigure = null;
	/**
	 * Expected figure.
	 */
	private Figure resultFigure = null;
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
	 * Tests if set figure on cell (x, y) is on this cell.
	 */
	@Test
	public void whenSetFigureThenThisFigureIsInThisCell() {
		this.board.setFigure(this.whitePawn, X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		this.expectedFigure = this.whitePawn;
		this.resultFigure = this.board.getFigure(X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		assertEquals(expectedFigure, this.resultFigure);
	}
	/**
	 * Tests when remove figure from cell (x, y) is this cell empty.
	 */
	@Test
	public void whenRemoveFigureThenCellIsEmpty() {
		this.board.setFigure(this.whitePawn, X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		this.board.removeFigure(X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		this.resultValue = this.board.isCellFree(X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		assertEquals(EXPECTED_VALUE_TRUE, this.resultValue);
	}
	/**
	 * Tests if cell (x, y) occupied by opponent is occupied by opponent.
	 */
	@Test
	public void whenCellOccupiedByOpponentThenCellOccupiedByOpponentIsTrue() {
		this.board.setFigure(this.whitePawn, X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		this.resultValue = this.board.isCellOccupiedByOpponent(false, X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		assertEquals(EXPECTED_VALUE_TRUE, this.resultValue);
	}
	/**
	 * Tests if cell (x, y) occupied by ally is not occupied by opponent.
	 */
	@Test
	public void whenCellIsNotOccupiedByAllyThenCellOccupiedByOpponentIsFalse() {
		this.board.setFigure(this.whitePawn, X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		this.resultValue = this.board.isCellOccupiedByOpponent(true, X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Tests if cell is empty it is not occupied by opponent.
	 */
	@Test
	public void whenCellIsEmptyThenCellOccupiedByOpponentIsFalse() {
		this.board.removeFigure(X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		this.resultValue = this.board.isCellOccupiedByOpponent(true, X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Tests if cell is empty it is not occupied by ally.
	 */
	@Test
	public void whenCellIsEmptyThenCellOccupiedByAllyIsFalse() {
		this.board.removeFigure(X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		this.resultValue = this.board.isCellOccupiedByOpponent(false, X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		assertEquals(EXPECTED_VALUE_FALSE, this.resultValue);
	}
	/**
	 * Tests if controllers cell afted move figure is empty and finish cell is occupied by this figure.
	 */
	@Test
	public void whenMoveThenStartCellIsEmpty() {
		this.board.setFigure(this.whitePawn, X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		this.board.move(X_WHITE_PAWN_START, X_WHITE_PAWN_START, X_WHITE_PAWN_FINISH, Y_WHITE_PAWN_FINISH);
		this.resultValue = this.board.isCellFree(X_WHITE_PAWN_START, Y_WHITE_PAWN_START);
		assertEquals(EXPECTED_VALUE_TRUE, this.resultValue);
		this.resultFigure = this.board.getFigure(X_WHITE_PAWN_FINISH, Y_WHITE_PAWN_FINISH);
		this.expectedFigure = this.whitePawn;
		assertEquals(expectedFigure, this.resultFigure);
	}
}