package ru.dionisius.models;

import ru.dionisius.abstracts.*;
import ru.dionisius.interfaces.*;

public class Queen extends AbstractFigure implements  Figure {
	
	public Queen (boolean white){
		super(white);
	}
		
	public boolean isMovePossible (Board board, int startX, int startY, int finishX, int finishY) {
		Rook rook = new Rook(this.isWhite());
		Bishop bishop = new Bishop(this.isWhite());
		return (rook.isMovePossible (board, startX, startY, finishX, finishY) 
			|| bishop.isMovePossible (board, startX, startY, finishX, finishY));
	}
		
	public char getSymbol(){
			return 'Q';
	}
	
}