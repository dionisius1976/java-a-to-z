package ru.dionisius.models;

import ru.dionisius.abstracts.*;
import ru.dionisius.interfaces.*;
import java.util.*;

public class Rook extends AbstractFigure implements  Figure {
			
	public Rook (boolean white){
		super(white);
	}
		
	public boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY){
		boolean isMovePossible = true;
		int deltaX = finishX - startX;
		int deltaY = finishY - startY;
		if (board.isCellFree(finishX, finishY) || board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY)){
			if (deltaY == 0 && deltaX != 0){
				if (finishX > startX){
					for (int i = finishX - 1; i != startX; i--){
						if (!board.isCellFree(i, startY)) {
							isMovePossible = false;
							break;
						}
					}
				} else {
					for (int i = finishX + 1; i != startX; i++){
						if (!board.isCellFree(i, startY)) {
							isMovePossible = false;
							break;
						}
					}
				}
				
			}
			if (deltaX == 0 && deltaY != 0){
				if (finishY > startY){
					for (int i = finishY - 1; i != startY; i--){
						if (!board.isCellFree(startX, i)) {
							isMovePossible = false;
							break;
						}
					}
				} else {
					for (int i = finishY + 1; i != startY; i++){
						if (!board.isCellFree(startX, i)) {
							isMovePossible = false;
							break;
						}
					}
				}
			}
			if (deltaX != 0 && deltaY != 0) isMovePossible = false;
		} else isMovePossible = false;
		return isMovePossible;
	}
		
	public char getSymbol(){
			return 'R';
	}
	
	@Override
	public int hashCode() {
		int hash = new Random().nextInt(255);
		return  hash * 255;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		Figure other = (Figure) obj;
		return (this.getSymbol() == other.getSymbol() && this.isWhite() == other.isWhite());
	}

}