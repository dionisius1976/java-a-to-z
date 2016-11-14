ru.dionisius.models;

import ru.dionisius.abstracts;
import ru.dionisius.interfaces;

public class Bishop extends AbstractFigure implements  Figure {
	
	public Bishop (boolean white){
		super(white);
	}
	
	public boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY){
	boolean isMovePossible = true;
		int deltaX = finishX - startX;
		int deltaY = finishY - startY;
		if (deltaX == deltaY && (board.isCellFree(finishX, finishY) || board.isCellOccupiedByOpponent(this.white, finishX, finishY))) {
			if (finishX > startX && finishY > startY){
				int j = 1;
				for (int i = finishX - 1); i != startX; i--){
					if (!board.isCellFree(i, finishY - j)) {
						isMovePossible = false;
						break;
					}
					j++;
				}
			} else if (finishX > startX && finishY < startY) {
				int j = 1;
				for (int i = finishX - 1); i != startX; i--){
					if (!board.isCellFree(i, finishY + j)) {
						isMovePossible = false;
						break;
					}
					j++;
				}
			} else if (finishX < startX && finishY > startY) {
				int j = 1;
				for (int i = finishX + 1); i != startX; i++){
					if (!board.isCellFree(i, finishY - j)) {
						isMovePossible = false;
						break;
					}
					j++;
				}
			} else {
				int j = 1;
				for (int i = finishX + 1); i != startX; i++){
					if (!board.isCellFree(i, finishY + j)) {
						isMovePossible = false;
						break;
					}
					j++;
				}
			}
		} else isMovePossible = false;
		return isMovePossible;
	}
	
	public char getSymbol(){
			return 'B';
	}
}