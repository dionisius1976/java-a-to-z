ru.dionisius.controllers;

import ru.dionisius.models;

public class GameController {
	
	private Board board;

	public GameController(Board board){
		this.board = board;
	}
	
	public void Pass(int startX, int startY, int finishX, int finishY){
		this.board.setFigure(this.board.getFigure(startX, startY), finishX, finishY);
		this.board.cleanCell(startX, startY);
		this.turnTheBoard();
	}

	
	public Board turnTheBoard(Board board){
		
	}
		
	public boolean isGameOver(){
		return (this.board.getWhiteKing().isUnderAttack() && this.board.getWhiteKing().getPossibleMove == null ||
				this.board.getBlackKing().isUnderAttack() && this.board.getBlackKing().getPossibleMove == null);
	}
	
	public boolean isWinnerWhite(){
		return (this.board.getBlackKing().isUnderAttack() && this.board.getBlackKing().getPossibleMove == null);
	}
	
	public boolean isPat(){
		boolean isPat = true;
		for(int x = 0; this.board.length; x++){
			for(int y = 0; this.board.length; y++){
				if (this.board.cells[x][y] = null) continue;
				//if (this.board.cells[x][y].getFigure().
			}
		}
		return isPat;
	}
	
	public void Pass(int startX, int startY, int finishX, int finishY){
		if(this.board.getFigure(startX, startY).isMovePossible(this.board, startX, startY, finishX, finishY){
			this.board.setFigure(this.board.getFigure(startX, startY), finishX, finishY);
			this.board.cleanCell(startX, startY);
		}
	}
	
}