ru.dionisius.models;

public class Board {
	
    private final int boardSize = 8;
	private Figure[][] field = new Figure[boardSize][boardSize];
	
	public Board (int boardSize) {
		this.boardSize = boardSize;
	}
	
	public int getBoardSize(){
		return this.boardSize;
	}
	
	public Figure getFigure (int x, int y){
		return this.field[x][y];
	}
	
	public void setFigure(Figure figure, int x, int y){
		this.field[x][y] = figure;
	}
	
	public Board initialFill(){
		this.field[0][0] = new Rook(true);
		this.field[0][1] = new Knight(true);
		this.field[0][2] = new Bishop(true);
		this.field[0][3] = new Qween(true);
		this.field[0][4] = new King(true);
		this.field[0][5] = new Bishop(true);
		this.field[0][6] = new Knight(true);
		this.field[0][7] = new Rook(true);
		int whitePownRow = 1;
		for (int column = 0; column < this.boardSize; column++){
			this.field[whitePownRow][column] = new Pawn (true);
		}
		int blackPawnRow = 6;
		for (int column = 0; column < this.boardSize; column++){
			this.field[blackPownRow][column] = new Pawn (false);
		}
		this.field[8][0] = new Rook(false);
		this.field[8][1] = new Knight(false);
		this.field[8][2] = new Bishop(false);
		this.field[8][3] = new Qween(false);
		this.field[8][4] = new King(false);
		this.field[8][5] = new Bishop(false);
		this.field[8][6] = new Knight(false);
		this.field[8][7] = new Rook(false);
		
		return this.board;
		
	}
	
	public void cleanCell(int x, int y){
		this.field[x][y] = null;
	}
	
		
	public boolean isCellFree(int x, int y){
		return this.getFigure(x, y) == null;
	}
			
	public boolean isCellOccupiedByOpponent(boolean white, int x, int y){
		return (this.getFigure(x, y).getWhite() != white));
	}
	
	public boolean isCellUnderAttack(boolean white, int x, int y){
		boolean isUnderAttack = false;
		for (int row = 0; i < this.field.length; row++){
			for (int column = 0; i < this.field.length; column++){
				if (this.field[row][column].getFigure() == null || this.field[row][column].getFigure().getWhite == white) continue;
				if (this.field[row][column].getFigure().isMovePossible(this.field, x, y)) {
					isUnderAttack = true;
					break;
				}
			}
		}
		return isUnderAttack;
	}
	
	public boolean isKingUnderAttack(boolean white, int x, int y){
		boolean isUnderAttack = false;
		for (int row = 0; i < this.field.length; row++){
			for (int column = 0; i < this.field.length; column++){
				if (this.field[row][column].getFigure().getSymbol() == 'K' && this.field[row][column].getFigure().isWhite() == white){
					isUnderAttack = this.isCellUnderAttack(white, row, column);
				} 
				
			}
		}
		return isUnderAttack;
	}
	
	public void draw(){
		for (int row = this.field.length - 1; i >= 0; row--){
			for (int column = 0; i < this.field.length; column++){
				System.out.printf ("%d", row);
				if (this.field[row][column].getFigure() == null){
					System.out.print(" ");
				}
				System.out.printf ("%2s", this.field[row][column].getFigure().getSymbol());
				if (column == this.field.length - 1) System.out.println();
			}
		}
		System.out.println ("%d%d%d%d%d%d%d%d", 0,1,2,3,4,5,6,7);
	}
	
}