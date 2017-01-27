package ru.dionisius.controls;

import ru.dionisius.models.IBoard;

/**
 * Created by Dionisius on 20.01.2017.
 */
public interface IMoveController {
    /**
     * Returns current board.
     * @return current board.
     */
    IBoard getBoard();
    /**
     * Makes computer's move.
     */
    void computerMove();

    /**
     * Makes player's move.
     * @param coordinateX X coordinate of specified cell.
     * @param coordinateY Y coordinate of specified cell.
     */
    void playerMove(final int coordinateX, final int coordinateY);

    /**
     * Returns the winner's name.
     * @return the winner's name or null if there is no winner.
     */
    String getTheWinner();

    /**
     * Returns a number of this board empty cells.
     * @return a number of this board empty cells.
     */
    int getEmptyCells();

    /**
     * Checks if first move makes computer.
     * @return true if first move makes computer and false if not.
     */
    boolean isComputerFirstMove();
}
