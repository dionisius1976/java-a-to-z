package ru.dionisius.models;

import ru.dionisius.abstracts.*;
import ru.dionisius.interfaces.*;
import java.util.*;

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