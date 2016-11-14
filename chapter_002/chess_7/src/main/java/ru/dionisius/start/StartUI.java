package ru.dionisius.start;

import ru.dionisius.models;

public class StartUI {

	private void init(){
		
		int boardSize = 8;
		Board board = new Board(boardSize);
		GameController gc = new GameController(board.initialBoardFill());
		private int count = 0;
		do{
			gc.boardShow();
			ask();
			mc.pass(count, Point start, Point finish);
			count++;			
		} while (!moveController.isGameOver)
		
		
	}
	
	public static viod main (String[] args){
		new StartUI().init();
	
	}

}