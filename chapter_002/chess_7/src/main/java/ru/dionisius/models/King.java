package ru.dionisius.models;

import ru.dionisius.abstracts.*;
import ru.dionisius.interfaces.*;

public class King extends AbstractFigure implements  Figure {
	
	public King (boolean white){
		super(white);
	}
		
	public boolean isMovePossible (Board board, int startX, int startY, int finishX, int finishY) {
		boolean isMovePossible = false;
		int deltaY = Math.abs(finishX - startX);
		int deltaX = Math.abs(finishY - startY);
		if(deltaX <= 1 && deltaY <= 1 && (board.isCellFree(finishX, finishY) || board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY))){
			isMovePossible = true;
		}
		return isMovePossible;
	}
		
	public char getSymbol(){
			return 'K';
	}
	
}