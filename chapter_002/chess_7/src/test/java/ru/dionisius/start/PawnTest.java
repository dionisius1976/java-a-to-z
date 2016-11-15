package ru.dionisius.start;

import ru.dionisius.models.*;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class PawnTest {
	Board board;
			
	@Before
	public void setup(){
		board = new Board();
	}
	
	@Test
	public void whenFirstTwoCellsMoveThenTrue(){
		int startX = 1;
		int startY = 3;
		int finishX = 3;
		int finishY = 3;
		board.setFigure(new Pawn(true), startX, startY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = true;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenFirstTwoCellsMoveAndPathIsNotFreeThenFalse(){
		int startX = 1;
		int startY = 3;
		int finishX = 3;
		int finishY = 3;
		int betweenX = 2;
		int betweenY = 3;
		board.setFigure(new Pawn(true), startX, startY);
		board.setFigure(new Knight(true), betweenX, betweenY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenFinishCellOccupiedByOpponentThenAttackIsTrue(){
		int startX = 3;
		int startY = 2;
		int finishX = 4;
		int finishY = 3;
		board.setFigure(new Pawn(true), startX, startY);
		board.setFigure(new Queen(false), finishX, finishY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = true;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenFinishCellOccupiedByAllayThenMoveIsFalse(){
		int startX = 3;
		int startY = 2;
		int finishX = 4;
		int finishY = 1;
		board.setFigure(new Pawn(false), startX, startY);
		board.setFigure(new Queen(false), finishX, finishY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenWrongWayToMoveThenMoveIsFalse(){
		int startX = 4;
		int startY = 3;
		int finishX = 3;
		int finishY = 3;
		board.setFigure(new Pawn(true), startX, startY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenPawnIsBlackThenMoveLikeWhiteIsFalse(){
		int startX = 3;
		int startY = 3;
		int finishX = 4;
		int finishY = 3;
		board.setFigure(new Pawn(false), startX, startY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
}