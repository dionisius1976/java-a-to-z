package ru.dionisius.models;

public interface Figure {

	boolean isMovePossible(Board board, int startX, int startY, int finishX, int finishY);
	
	boolean isWhite();
	
	char getSymbol();
	
	
}