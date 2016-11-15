package ru.dionisius.start;

import ru.dionisius.models.*;
import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class QueenTest {
	Board board;
	
	@Before
	public void setup(){
		board = new Board();
	}
	
	@Test
	public void whenPathIsNotFreeThenMoveIsFalse(){
		int startX = 3;
		int startY = 2;
		int finishX = 5;
		int finishY = 0;
		int betweenX = 4;
		int betweenY = 1;
		board.setFigure(new Queen(false), startX, startY);
		board.setFigure(new Queen(true), finishX, finishY);
		board.setFigure(new Knight(true), betweenX, betweenY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenFinishCellOccupiedByOpponentThenMoveIsTrue(){
		int startX = 3;
		int startY = 2;
		int finishX = 2;
		int finishY = 0;
		board.setFigure(new Queen(false), startX, startY);
		board.setFigure(new Queen(true), finishX, finishY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = true;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenFinishCellOccupiedByAllayThenMoveIsFalse(){
		int startX = 3;
		int startY = 2;
		int finishX = 5;
		int finishY = 0;
		board.setFigure(new Queen(false), startX, startY);
		board.setFigure(new Pawn(false), finishX, finishY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
	@Test
	public void whenWrongWayToMoveThenMoveIsFalse(){
		int startX = 4;
		int startY = 3;
		int finishX = 2;
		int finishY = 2;
		board.setFigure(new Queen(false), startX, startY);
		boolean result = board.getFigure(startX, startY).isMovePossible(board, startX, startY, finishX, finishY);
		boolean expectedValue = false;
		assertEquals(expectedValue, result);
	}
	
}