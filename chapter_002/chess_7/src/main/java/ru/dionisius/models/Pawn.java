ru.dionisius.models;

import util.Math;
import ru.dionisius.abstracts;
import ru.dionisius.interfaces;

private class Pawn extends AbstractFigure implements  Figure {
		
	public class Pawn (boolean white){
		super(white);
	}
			
	public boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY) {
		boolean isMovePossible = false;
		int deltaX = finishX - startX;
		int deltaY = finishY - startY;
		if (board.isCellFree(finishX, finishY)){
			if (startX == 1){
				if (deltaY == 0 && deltaX == 2 && board.isCellFree(startX + 1, startY)) {
					isMovePossible = true;
				}
			}
			if (deltaY == 0 && deltaX == 1) isMovePossible = true;
		}
		if (board.isCellOccupiedByOpponent(this.white, finishX, finishY){
			if (Math.abs(deltaY) == 1 && deltaX == 1) isMovePossible = true;
		}
		return isMovePossible;
	}
		
	public char getSymbol(){
		return 'P';
	}
	
}