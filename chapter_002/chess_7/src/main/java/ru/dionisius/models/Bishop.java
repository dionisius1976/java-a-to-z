package ru.dionisius.models;

import ru.dionisius.abstracts.*;
import ru.dionisius.interfaces.*;
import java.util.*;

public class Bishop extends AbstractFigure implements  Figure {
	
	public Bishop (boolean white){
		super(white);
	}
	
	public boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY){
		boolean isMovePossible = true;
		int deltaX = finishX - startX;
		int deltaY = finishY - startY;
		if (Math.abs(deltaX) == Math.abs(deltaY) && board.isCellFree(finishX, finishY) || board.isCellOccupiedByOpponent(this.isWhite(), finishX, finishY)) {
			if (finishX > startX && finishY > startY){
				int j = 1;
				for (int i = finishX - 1; i != startX; i--){
					if (!board.isCellFree(i, finishY - j)) {
						isMovePossible = false;
						break;
					}
					j++;
				}
			} else if (finishX > startX && finishY < startY) {
				int j = 1;
				for (int i = finishX - 1; i != startX; i--){
					if (!board.isCellFree(i, finishY + j)) {
						isMovePossible = false;
						break;
					}
					j++;
				}
			} else if (finishX < startX && finishY > startY) {
				int j = 1;
				for (int i = finishX + 1; i != startX; i++){
					if (!board.isCellFree(i, finishY - j)) {
						isMovePossible = false;
						break;
					}
					j++;
				}
			} else {
				int j = 1;
				for (int i = finishX + 1; i != startX; i++){
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