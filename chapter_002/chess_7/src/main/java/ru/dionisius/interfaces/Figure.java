ru.dionisius.models;

public interface Figure {

	void isMovePossible(Board board, int startX, int startY, int finishX, int finishY);
	
	boolean isWhite();
	
	char getSymbol();
	
	
}