package ru.dionisius.model;

/**
 * Created by Dionisius on 20.01.2017.
 * Interface for all Board instances.
 */
public interface IBoard {
    /**
     * Returns this board.
     * @return this board.
     */
    Value[][] getBoard();

    /**
     * Sets specified value to specified cell.
     * @param coordinateX specified cell's X coordinate.
     * @param coordinateY specified cell's Y coordinate.
     * @param value specified value.
     */
    void setValue(int coordinateX, int coordinateY, Value value);

    /**
     * Returns this board's X size.
     * @return this board's X size.
     */
    int getRangeX();

    /**
     * Returns this board's Y size.
     * @return this board's Y size.
     */
    int getRangeY();
}
