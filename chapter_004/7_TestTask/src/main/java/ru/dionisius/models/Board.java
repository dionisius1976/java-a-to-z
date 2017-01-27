package ru.dionisius.models;

/**
 * Created by Dionisius on 20.01.2017.
 */
public class Board implements IBoard {
    /**
     * Board as a values storage.
     */
    private final Value[][] board;
    /**
     * Constructor without parameters.
     */
    public Board() {
        this.board = new Value[3][3];
    }

    /**
     * Constructor with parameters.
     * @param sizeX board's X size.
     * @param sizeY board's Y size.
     */
    public Board(final int sizeX, final int sizeY) {
        this.board = new Value[sizeX][sizeY];
    }

    @Override
    public Value[][] getBoard() {
        return this.board;
    }

    @Override
    public void setValue(final int coordinateX, final int coordinateY, final Value value) {
        this.board[coordinateX][coordinateY] = value;
    }

    @Override
    public int getRangeX() {
        return this.board.length - 1;
    }

    @Override
    public int getRangeY() {
        return this.board[0].length - 1;
    }
}
