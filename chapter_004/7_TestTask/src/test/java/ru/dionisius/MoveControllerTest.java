package ru.dionisius;

import org.junit.Before;
import org.junit.Test;
import ru.dionisius.controls.IMoveController;
import ru.dionisius.controls.MoveController;
import ru.dionisius.controls.OcuppiedCellException;
import ru.dionisius.models.Board;
import ru.dionisius.models.IBoard;
import ru.dionisius.models.Value;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Dionisius on 27.01.2017.
 */
public class MoveControllerTest {
    /**
     *
     */
    private IBoard board;
    /**
     *
     */
    private IMoveController moveController;
    /**
     *
     */
    private int ruleRange = 5;
    /**
     *
     */
    private int sizeX = 5;
    /**
     *
     */
    private int sizeY = 5;
    /**
     *
     */
    private final int coordinateX = 2;
    /**
     *
     */
    private final int coordinateY = 2;
    /**
     *
     */
    private Value expectedValue;
    /**
     *
     */
    private Value resultValue;
    /**
     *
     */
    private int result;
    /**
     *
     */
    private int expected;
    /**
     * Initiates initial values for tests.
     */
    @Before
    public void init() {
        this.board = new Board(this.sizeX, this.sizeY);
        this.moveController = new MoveController(this.board, this.ruleRange, true);
    }
    /**
     * Tests if number of empty cells on new board is equal this board size.
     */
    @Test
    public void whenNewBoardThenGetEmptyCellsIsBoardSize() throws Exception {
        this.result = this.moveController.getEmptyCells();
        this.expected = this.moveController.getBoard().getBoard().length
                * this.moveController.getBoard().getBoard()[0].length;
        assertThat(this.result, is(this.expected));
    }
    /**
     * Tests if empty cells number is decreased by two after two cells are marked.
     */
    @Test
    public void whenTwoMarksAreOnBoardThenGetEmptyCellsIsBoardSizeMinusTwoCells() throws Exception {
        this.moveController.playerMove(this.coordinateX, this.coordinateY);
        this.moveController.playerMove(0, 1);
        this.result = this.moveController.getEmptyCells();
        this.expected = this.moveController.getBoard().getBoard().length
                * this.moveController.getBoard().getBoard()[0].length - 2;
        assertThat(this.result, is(this.expected));
    }
    /**
     * Tests if isComputerFirstMove() returns true when computer starts the game.
     */
    @Test
    public void whenComputerFirstMoveThenIsComputerFirstMoveIsTrue() throws Exception {
        boolean expectedValue = true;
        boolean result = this.moveController.isComputerFirstMove();
        assertThat(result, is(expectedValue));
    }
    /**
     * Tests if isComputerFirstMove() returns false when computer does not controllers the game.
     */
    @Test
    public void whenComputerIsNotFirstMoveThenIsComputerFirstMoveIsFalse() throws Exception {
        boolean expectedValue = false;
        IMoveController mc = new MoveController(this.board, 5, false);
        boolean result = mc.isComputerFirstMove();
        assertThat(result, is(expectedValue));
    }
    /**
     * Tests if computer marks central cell when it starts the game.
     */
    @Test
    public void whenComputerFirstMoveThenItMarksCentralCell() throws Exception {
        this.expectedValue = Value.X;
        this.moveController.computerMove();
        this.resultValue = this.moveController.getBoard().getBoard()[2][2];
        assertThat(this.resultValue, is(this.expectedValue));
    }
    /**
     * Tests if computer marks first empty cell when it does not controllers the game.
     */
    @Test
    public void whenComputerMoveIsNotFirstThenItMarksFirstEmptyCell() throws Exception {
        this.moveController.playerMove(this.coordinateX, this.coordinateY);
        this.expectedValue = Value.X;
        this.moveController.computerMove();
        this.resultValue = this.moveController.getBoard().getBoard()[0][0];
        assertThat(this.resultValue, is(this.expectedValue));
    }
    /**
     * Tests if OcuppiedCellException throws when user try to mark occupied cell.
     */
    @Test (expected = OcuppiedCellException.class)
    public void whenPlayerMoveAndCellIsOccupiedThenThrowsOccupiedCellException() throws Exception {
        this.moveController.playerMove(this.coordinateX, this.coordinateY);
        this.moveController.playerMove(this.coordinateX, this.coordinateY);
    }
    /**
     * Tests if specified cell is marked by user's mark after user marked this cell.
     */
    @Test
    public void whenPlayerMoveThenCellIsOccupiedByPlayersMark() throws Exception {
        this.moveController.playerMove(this.coordinateX, this.coordinateY);
        this.expectedValue = Value.O;
        this.resultValue = this.moveController.getBoard().getBoard()[this.coordinateX][this.coordinateY];
        assertThat(this.resultValue, is(this.expectedValue));
    }
    /**
     * Tests if there is no winner when game is not over.
     */
    @Test
    public void getTheWinner() throws Exception {
        this.moveController.playerMove(this.coordinateX, this.coordinateY);
        String expectedValue = null;
        String resultValue = this.moveController.getTheWinner();
        assertThat(resultValue, is(expectedValue));
    }
    /**
     * Tests if computer is won when it sets ruleRange number of it's marks in horizontal range.
     */
    @Test
    public void whenComputerHorizontalRangeWonThenComputerIsWon() throws Exception {
        String expectedValue = "Computer";
        for (int i = 0; i < this.ruleRange; i++) {
            this.moveController.computerMove();
        }
        String resultValue = this.moveController.getTheWinner();
        assertThat(resultValue, is(this.expectedValue));
    }
    /**
     * Tests if player is won when he sets ruleRange number of it's marks in vertical range.
     */
    @Test
    public void whenPlayerVerticalRangeWonThenPlayerIsWon() throws Exception {
        String expectedValue = "Player";
        for (int i = 0; i < this.ruleRange; i++) {
            this.moveController.playerMove(this.coordinateX, i);
        }
        String resultValue = this.moveController.getTheWinner();
        assertThat(resultValue, is(expectedValue));
    }
    /**
     * Tests if player is won when he sets ruleRange number of it's marks in obliquely right range.
     */
    @Test
    public void whenPlayerObliquelyRightWonThenPlayerIsWon() throws Exception {
        String expectedValue = "Player";
        int x = 0;
        int y = 0;
        for (int i = 0; i < this.ruleRange; i++) {
            this.moveController.playerMove(x, y);
            x++;
            y++;
        }
        String resultValue = this.moveController.getTheWinner();
        assertThat(resultValue, is(expectedValue));
    }
    /**
     * Tests if player is won when he sets ruleRange number of it's marks in obliquely left range.
     */
    @Test
    public void whenPlayerObliquelyLeftWonThenPlayerIsWon() throws Exception {
        String expectedValue = "Player";
        int x = 0;
        int y = this.moveController.getBoard().getRangeY();
        for (int i = 0; i < this.ruleRange; i++) {
            this.moveController.playerMove(x, y);
            x++;
            y--;
        }
        String resultValue = this.moveController.getTheWinner();
        assertThat(resultValue, is(expectedValue));
    }
    /**
     * Tests if there is tie when it is tie.
     */
    @Test
    public void whenTieThenTie() throws Exception {
        String expectedValue = "Tie";
        IBoard newBoard = new Board();
        IMoveController mc = new MoveController(newBoard, 3, true);
        mc.computerMove();
        mc.playerMove(0, 0);
        mc.computerMove();
        mc.playerMove(0, 2);
        mc.computerMove();
        mc.playerMove(1, 2);
        mc.computerMove();
        mc.playerMove(2, 1);
        mc.computerMove();
        String resultValue = mc.getTheWinner();
        assertThat(resultValue, is(expectedValue));
    }
}
