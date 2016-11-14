package ru.dionisius.models;

import ru.dionisius.abstracts.*;
import ru.dionisius.interfaces.*;

public class Pawn extends AbstractFigure implements  Figure {
		
	public Pawn (boolean white){
		super(white);
	}
			
	public boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY) {
		boolean isMovePossible = false;
		int deltaX = finishX - startX;
		int deltaY = finishY - startY;
		if (this.isWhite() == true) {
			if (board.isCellFree(finishX, finishY)){
				if (startX == 1){
					if (deltaY == 0 && deltaX == 2 && board.isCellFree(startX + 1, startY)) {
						isMovePossible = true;
					}
				}
				if (deltaY == 0 && deltaX == 1) isMovePossible = true;
			}
			if (board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY)) {
				if (Math.abs(deltaY) == 1 && deltaX == 1) isMovePossible = true;
			}
			return isMovePossible;
		} else {
			if (board.isCellFree(finishX, finishY)){
				if (startX == 7){
					if (deltaY == 0 && deltaX == -2 && board.isCellFree(startX - 1, startY)) {
						isMovePossible = true;
					}
				}
				if (deltaY == 0 && deltaX == -1) isMovePossible = true;
			}
			if (board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY)) {
				if (Math.abs(deltaY) == 1 && deltaX == -1) isMovePossible = true;
			}
			return isMovePossible;
		}
	}
		
	public char getSymbol(){
		return 'P';
	}
	
}