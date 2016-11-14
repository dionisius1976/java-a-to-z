package ru.dionisius.models;

//import java.util.Math;
import ru.dionisius.abstracts.*;
import ru.dionisius.interfaces.*;

public class Knight extends AbstractFigure implements  Figure {
			
	public Knight (boolean white){
		super(white);
	}
		
	public boolean isMovePossible (Board board, int startX, int startY, int finishX, int finishY) {
		boolean isMovePossible = false;
		int deltaX = finishX - startX;
		int deltaY = finishY - startY;
		if (board.isCellFree(finishX, finishY) || board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY)){
			if (Math.abs(deltaX) == 2 && Math.abs(deltaY) == 1 || Math.abs(deltaX) == 1 && Math.abs(deltaY) == 2) isMovePossible = true;
		}
		return isMovePossible;
	}
		
	public char getSymbol(){
			return 'N';
	}
	
}