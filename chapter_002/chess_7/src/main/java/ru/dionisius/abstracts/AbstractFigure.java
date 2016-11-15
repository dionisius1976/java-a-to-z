package ru.dionisius.abstracts;

import ru.dionisius.interfaces;

public abstract class AbstractFigure implements Figure{
	private final boolean white;
	
	public AbstractFigure(boolean white){
		this.white = white;
	}
		
	public boolean isWhite(){
		return this.white;
	}
	
	abstract boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY);
	
	abstract char getSymbol();
}