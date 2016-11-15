package ru.dionisius.models;

import ru.dionisius.exceptions.*;
import ru.dionisius.models.*;

public class Board {
	
  	private Figure[][] field = new Figure[8][8];
	
	public Board () {
		this.initialFill(this.field);
	}
	
	public Figure[][] getField(){
		return this.field;
	}
	
	public int getSize(){
		return this.getField().length;
	}
	
	public Figure getFigure (int x, int y){
		return this.field[x][y];
	}
	
	public void setFigure(Figure figure, int x, int y){
		this.field[x][y] = figure;
	}
	
	private void initialFill(Figure[][] field){
		this.field[0][0] = new Rook(true);
		this.field[0][1] = new Knight(true);
		this.field[0][2] = new Bishop(true);
		this.field[0][3] = new Queen(true);
		this.field[0][4] = new King(true);
		this.field[0][5] = new Bishop(true);
		this.field[0][6] = new Knight(true);
		this.field[0][7] = new Rook(true);
		int whitePownRow = 1;
		for (int column = 0; column < this.getSize(); column++){
			this.field[whitePownRow][column] = new Pawn (true);
		}
		int blackPawnRow = 6;
		for (int column = 0; column < this.getSize(); column++){
			this.field[blackPawnRow][column] = new Pawn (false);
		}
		this.field[7][0] = new Rook(false);
		this.field[7][1] = new Knight(false);
		this.field[7][2] = new Bishop(false);
		this.field[7][3] = new Queen(false);
		this.field[7][4] = new King(false);
		this.field[7][5] = new Bishop(false);
		this.field[7][6] = new Knight(false);
		this.field[7][7] = new Rook(false);
	}
	
	public void removeFigure(int x, int y){
		this.field[x][y] = null;
	}
			
	public boolean isCellFree(int x, int y){
		return this.getFigure(x, y) == null;
	}
			
	public boolean isCellOccupiedByOpponent(boolean white, int x, int y){
		boolean isOccupiedByOpponent = false;
		if (this.getFigure(x, y) != null && this.getFigure(x, y).isWhite() != white) isOccupiedByOpponent = true;
		return isOccupiedByOpponent;
	}
	
	public void move(int startX, int startY, int finishX, int finishY){
		if (this.getFigure(startX, startY).isMovePossible(this, startX, startY, finishX, finishY)){
			this.setFigure(this.getFigure(startX, startY), finishX, finishY);
		} else throw new IncorrectMoveException ("Невозможный ход для данной фигуры!");
		this.removeFigure(startX, startY);
	}
	
	public void draw(){
		for (int row = this.getSize() - 1; row >= 0; row--){
			for (int column = 0; column < this.getSize(); column++){
				System.out.printf ("%d", row);
				if (this.getFigure(row, column) == null){
					System.out.print(" ");
				}
				System.out.printf ("%2s", getFigure(row, column).getSymbol());
				if (column == this.getSize() - 1) System.out.println();
			}
		}
		System.out.printf("%d%d%d%d%d%d%d%d", 0,1,2,3,4,5,6,7);
		System.out.println();
	}
	
}