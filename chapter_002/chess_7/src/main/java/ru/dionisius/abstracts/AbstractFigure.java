package ru.dionisius.models;

import java.util.*;

public abstract class AbstractFigure {
	private final boolean white;
	
	public AbstractFigure(boolean white){
		this.white = white;
	}
		
	public boolean isWhite(){
		return this.white;
	}
	
	abstract boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY);
	
	abstract char getSymbol();
	
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