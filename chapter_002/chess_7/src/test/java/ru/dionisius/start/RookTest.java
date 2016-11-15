package ru.dionisius.start;

import ru.dionisius.models.*;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class RookTest {
	Board board;
			
	@Before
	public void setup(){
		board = new Board();
	}
	
	@Test
	public void whenPathIsNotFreeThenMoveIsFalse(){
		int startX = 0;
		int startY = 0;
		int finishX = 4;
		int finishY = 0;
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenFinishCellOccupiedByOpponentThenMoveIsTrue(){
		int startX = 4;
		int startY = 3;
		int finishX = 4;
		int finishY = 7;
		board.setFigure(new Rook(false), startX, startY);
		board.setFigure(new Queen(true), finishX, finishY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = true;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenFinishCellOccupiedByAllayThenMoveIsFalse(){
		int startX = 4;
		int startY = 3;
		int finishX = 4;
		int finishY = 7;
		board.setFigure(new Rook(false), startX, startY);
		board.setFigure(new Queen(false), finishX, finishY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenWrongWayToMoveThenMoveIsFalse(){
		int startX = 4;
		int startY = 3;
		int finishX = 5;
		int finishY = 2;
		board.setFigure(new Rook(false), startX, startY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
}