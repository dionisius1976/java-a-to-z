package ru.dionisius.controls;

import ru.dionisius.models.IBoard;
import ru.dionisius.models.Value;

/**
 * Created by Dionisius on 20.01.2017.
 */
public class MoveController implements IMoveController {
    /**
     * Specified board.
     */
    private IBoard board;
    /**
     * The flag for computer first move.
     */
    private final boolean isComputerFirstMove;
    /**
     * Number of the same signs in range needs for victory.
     */
    private final int ruleRange;
    /**
     * The type of computer's mark.
     */
    private final Value computerMark;
    /**
     * The type of player's mark.
     */
    private final Value playerMark;
    /**
     * The counter for empty cells for this board.
     */
    private int emptyCells;
    /**
     * Constructor.
     * @param board
     * @param ruleRange
     * @param isComputerFirstMove
     */
    public MoveController(IBoard board, final int ruleRange, boolean isComputerFirstMove) {
        this.board = board;
        this.isComputerFirstMove = isComputerFirstMove;
        this.ruleRange = ruleRange;
        this.emptyCells = board.getBoard().length * board.getBoard()[0].length;
        if (isComputerFirstMove) {
            this.computerMark = Value.X;
            this.playerMark = Value.O;
        } else {
            this.computerMark = Value.O;
            this.playerMark = Value.X;
        }
    }

    @Override
    public IBoard getBoard() {
        return this.board;
    }

    @Override
    public int getEmptyCells() {
        return this.emptyCells;
    }

    @Override
    public boolean isComputerFirstMove() {
        return this.isComputerFirstMove;
    }

    @Override
    public void computerMove() {
        if (this.emptyCells == board.getBoard().length * board.getBoard()[0].length) {
            this.markCell(this.board.getRangeX() / 2,
                    this.board.getRangeY() / 2,
                    this.computerMark);
        } else {
            int maxX = this.board.getRangeX();
            int maxY = this.board.getRangeY();
            boolean isMarked = false;
            for (int i = 0; i <= maxX; i++) {
                for (int j = 0; j <= maxY; j++) {
                    if (this.isEmpty(i, j)) {
                        this.markCell(i, j, this.computerMark);
                        isMarked = true;
                        break;
                    }
                }
                if (isMarked) {
                    break;
                }
            }

        }
    }

    @Override
    public void playerMove(final int coordinateX, final int coordinateY) {
        this.markCell(coordinateX, coordinateY, this.playerMark);
    }

    @Override
    public String getTheWinner() {
        String winner = null;
        if (this.emptyCells == 0) {
            winner = "Tie";
        } else {
            if (this.isHorizontalWon(this.computerMark)
                    || this.isVerticalWon(this.computerMark)
                    || this.isObliquelyLeftWon(this.computerMark)
                    || this.isObliquelyRightWon(this.computerMark)) {
                winner = "Computer";
            }
            if (this.isHorizontalWon(this.playerMark)
                    || this.isVerticalWon(this.playerMark)
                    || this.isObliquelyLeftWon(this.playerMark)
                    || this.isObliquelyRightWon(this.playerMark)) {
                winner = "Player";
            }
        }
        return winner;
    }

    /**
     * Marks specified cell with specified mark.
     * @param coordinateX X coordinate of specified cell.
     * @param coordinateY Y coordinate of specified cell.
     * @param value specified mark value.
     */
    private void markCell(final int coordinateX, final int coordinateY, final Value value) {
        if (this.isEmpty(coordinateX, coordinateY)) {
            board.setValue(coordinateX, coordinateY, value);
            this.emptyCells--;
        } else {
            throw new OcuppiedCellException("Клетка не свободна!");
        }
    }

    /**
     * Checks if specified cell is empty.
     * @param coordinateX X coordinate of specified cell.
     * @param coordinateY Y coordinate of specified cell.
     * @return true if specified cell is empty and false if not.
     */
    private boolean isEmpty(final int coordinateX, final int coordinateY) {
        return this.board.getBoard()[coordinateX][coordinateY] == null;
    }

    /**
     * Checks if ruleRange number of the same marks are in vertical range.
     * @param value the type of specified mark.
     * @return true ruleRange number of the same marks are in vertical range
     *         and false if not.
     */
    private boolean isVerticalWon(final Value value) {
        int range = 0;
        boolean result = false;
        int counter = 0;
        int maxX = this.board.getRangeX();
        int maxY = this.board.getRangeY();
        Value[][] thisBoard = this.board.getBoard();
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                if (thisBoard[i][j] == value) {
                    counter++;
                    int k = i;
                    range = this.ruleRange;
                    while (range > 1) {
                        k++;
                        if (k > maxX || thisBoard[k][j] != value) {
                            counter = 0;
                            break;
                        } else {
                            counter++;
                        }
                        range--;
                    }
                    if (counter == this.ruleRange) {
                        result = true;
                        break;
                    }
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Checks if ruleRange number of the same marks are in horizontal range.
     * @param value the type of specified mark.
     * @return true ruleRange number of the same marks are in horizontal range
     *         and false if not.
     */
    private boolean isHorizontalWon(final Value value) {
        int range = 0;
        boolean result = false;
        int counter = 0;
        int maxX = this.board.getRangeX();
        int maxY = this.board.getRangeY();
        Value[][] thisBoard = this.board.getBoard();
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY; j++) {
                if (thisBoard[i][j] == value) {
                    counter++;
                    int k = j;
                    range = this.ruleRange;
                    while (range > 1) {
                        k++;
                        if (k > maxY || thisBoard[i][k] != value) {
                            counter = 0;
                            break;
                        } else {
                            counter++;
                        }
                        range--;
                    }
                    if (counter == this.ruleRange) {
                        result = true;
                        break;
                    } else {
                        counter = 0;
                    }
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Checks if ruleRange number of the same marks are in obliquely left range.
     * @param value the type of specified mark.
     * @return true ruleRange number of the same marks are in obliquely left range
     *          and false if not.
     */
    private boolean isObliquelyLeftWon(final Value value) {
        int range = this.ruleRange;
        boolean result = false;
        int counter = 0;
        int maxX = this.board.getRangeX();
        int maxY = this.board.getRangeY();
        Value[][] thisBoard = this.board.getBoard();
        for (int i = 0; i <= maxX; i++) {
            for (int j = this.ruleRange - 1; j <= maxY ; j++) {
                if (thisBoard[i][j] == value) {
                    range = this.ruleRange;
                    counter++;
                    int m = i;
                    int l = j;
                    while (range > 1) {
                        m++;
                        l--;
                        if (m > maxX || l < 0 || thisBoard[m][l] != value) {
                            counter = 0;
                            break;
                        } else {
                            counter++;
                        }
                        range--;
                    }
                    if (counter == this.ruleRange) {
                        result = true;
                        break;
                    } else {
                        counter = 0;
                    }
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }

    /**
     * Checks if ruleRange number of the same marks are in obliquely right range.
     * @param value the type of specified mark.
     * @return true ruleRange number of the same marks are in obliquely right range
     *         and false if not.
     */
    private boolean isObliquelyRightWon(final Value value) {
        int range = this.ruleRange;
        boolean result = false;
        int counter = 0;
        int maxX = this.board.getRangeX();
        int maxY = this.board.getRangeY();
        Value[][] thisBoard = this.board.getBoard();
        for (int i = 0; i <= maxX; i++) {
            for (int j = 0; j <= maxY ; j++) {
                if (thisBoard[i][j] == value) {
                    range = this.ruleRange;
                    counter++;
                    int m = i;
                    int l = j;
                    while (range > 1) {
                        m++;
                        l++;
                        if (m > maxX || l > maxY || thisBoard[m][l] != value) {
                            counter = 0;
                            break;
                        } else {
                            counter++;
                        }
                        range--;
                    }
                    if (counter == this.ruleRange) {
                        result = true;
                        break;
                    } else {
                        counter = 0;
                    }
                }
            }
            if (result) {
                break;
            }
        }
        return result;
    }
}
