package ru.dionisius.start;

import ru.dionisius.models.*;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class BoardTest {
	
	Board board;
	Pawn whitePawn;
	Bishop whiteBishop;
	Pawn blackPawn;
	int testX;
	int testY;
	
	@Before
	public void setup(){
		board = new Board();
		board.setFigure(whitePawn, 1, 1);
		board.setFigure(whiteBishop, 0, 3);
		board.setFigure(blackPawn, 2, 3);
		testX = 5;
		testY = 6;
	}
	
	@Test
	public void whenCellIsEmptyThenIsCellFreeIsTrue(){
		board.removeFigure(testX, testY);
		boolean expectedValue = true;
		boolean result = board.isCellFree(testX, testY);
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenSetFigureThenThisFigureIsInThisCell(){
		Figure expectedFigure = whitePawn;
		Figure checkedFigure = board.getFigure(1,1);
		assertEquals(expectedFigure, checkedFigure);
	}
	
	@Test
	public void whenRemoveFigureThenCellisEmpty(){
		board.removeFigure(testX, testY);
		Figure expectedValue = null;
		Figure checkedFigure = board.getFigure(testX, testY);
		assertEquals(expectedValue, checkedFigure);
	}
	
	@Test
	public void whenCellOccupiedByOpponentThenCellOccupiedByOpponentIsTrue(){
		board.setFigure(new Queen(false), testX, testY);
		boolean expectedValue = true;
		boolean result = board.isCellOccupiedByOpponent(true, testX, testY);
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenCellIsNotOccupiedByOpponentThenCellOccupiedByOpponentIsFalse(){
		board.removeFigure(testX, testY);
		boolean expectedValue = false;
		boolean result = board.isCellOccupiedByOpponent(false, testX, testY);
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenCellIsEmptyThenCellOccupiedByOpponentIsFalse(){
		board.removeFigure(testX, testY);
		boolean expectedValue = false;
		boolean result = board.isCellOccupiedByOpponent(false, testX, testY);
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenMoveThenStartCellIsEmptyAndFinishCellIsOccupiedByMovingFigure(){
		int startX = 0;
		int startY = 1;
		int finishX = 2;
		int finishY = 0;
		int whiteKnightForEqualsX = 0;
		int whiteKnightForEqualsY = 6;
		board.move(startX, startY, finishX, finishY);
		Figure expectedEmptyCellFigureExpectedValue = null;
		boolean occupiedByWhiteKnightCellExpectedValue = true;
		Figure emptyCellResult = board.getFigure(startX, startY);
		boolean occupiedByWhiteKnightCellResult = board.getFigure(whiteKnightForEqualsX, whiteKnightForEqualsY).equals(board.getFigure(finishX, finishY));
		assertEquals(expectedEmptyCellFigureExpectedValue, emptyCellResult);
		assertEquals(occupiedByWhiteKnightCellExpectedValue, occupiedByWhiteKnightCellResult);
	}
	
}