package ru.dionisius.start;

import ru.dionisius.models.*;
import java.util.*;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;


public class BoardTest {
	
	Board board;
	int boardSize;
	Pawn whitePawn;
	Bishop whiteBishop;
	Pawn blackPawn;
	int testX;
	int testY;
	
	@Before
	public void setup(){
		boardSize = 8;
		board = new Board(boardSize);
		board.setFigure(whitePawn, 1, 1);
		board.setFigure(whiteBishop, 0, 3);
		board.setFigure(blackPawn, 2, 3);
		testX = 5;
		testY = 6;
	}
	
	@Test
	public void whenCellIsEmptyThenIsCellFreeIsTrue(){
		board.removeFigure(testX, testY);
		Boolean result = board.isCellFree(testX, testY);
		assertEquals(true, result);
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
		Figure expectedFigure = null;
		Figure checkedFigure = board.getFigure(testX, testY);
		assertEquals(expectedFigure, checkedFigure);
	}
	
	@Test
	public void whenCellOccupiedByOpponentThenCellOccupiedByOpponentIsTrue(){
		board.setFigure(new Queen(false), testX, testY);
		Boolean expectedResult = true;
		Boolean result = board.isCellOccupiedByOpponent(true, testX, testY);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void whenCellIsNotOccupiedByOpponentThenCellOccupiedByOpponentIsFalse(){
		board.setFigure(new Queen(false), testX, testY);
		Boolean expectedResult = false;
		Boolean result = board.isCellOccupiedByOpponent(false, testX, testY);
		board.removeFigure(testX, testY);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void whenCellIsEmptyThenCellOccupiedByOpponentIsFalse(){
		board.removeFigure(testX, testY);
		Boolean expectedResult = false;
		Boolean result = board.isCellOccupiedByOpponent(false, testX, testY);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void whenCellIsUnderAttackThenIsCellIsUnderAttackIsTrue(){
		int underAttackX = 3;
		int underAttackY = 7;
		int fromAttackX = 0;
		int fromAttackY = 4;
		board.setFigure(new Queen(true), fromAttackX, fromAttackY);
		Boolean expectedResult = true;
		Boolean result = board.isCellUnderAttack(false, underAttackX, underAttackY);
		assertEquals(expectedResult, result);
	}
	
	@Test
	public void whenCellIsNotUnderAttackThenIsCellIsUnderAttackIsFalse(){
		int underAttackX = 3;
		int underAttackY = 7;
		int fromAttackX = 0;
		int fromAttackY = 4;
		board.removeFigure(fromAttackX, fromAttackY);
		Boolean expectedResult = true;
		Boolean result = board.isCellUnderAttack(false, underAttackX, underAttackY);
		assertEquals(expectedResult, result);
	}
	
	
}