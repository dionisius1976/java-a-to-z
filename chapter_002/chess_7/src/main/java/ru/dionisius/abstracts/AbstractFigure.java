ru.dionisius.models;

public abstract AbstractFigure {
	private final boolean white;
	
	public AbstractFigure(boolean white){
		this.white = white;
	}
		
	public boolean getWhite(){
		return this.white;
	}
	
	public void isMovePossible(Point finish);
	
	char getSymbol();
}