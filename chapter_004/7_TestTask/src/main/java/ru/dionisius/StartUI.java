package ru.dionisius;

import ru.dionisius.controls.IInput;
import ru.dionisius.controls.IMoveController;
import ru.dionisius.controls.OcuppiedCellException;
import ru.dionisius.controls.ValidateInput;
import ru.dionisius.controls.MoveController;
import ru.dionisius.model.Board;
import ru.dionisius.model.IBoard;
import ru.dionisius.data.IShow;
import ru.dionisius.data.Show;

/**
 * Class starts the program.
 */
public class StartUI {
    /**
     * Type of input.
     */
    private IInput input;
    /**
     * Type of move service.
     */
    private IMoveController moveController;
    /**
     * Show class instance.
     */
    private IShow show = new Show();
    /**
     * Default constructor.
     * @param input Type of input.
     * @param moveController Type of move service.
     */
    public StartUI(IInput input, IMoveController moveController) {
        this.input = input;
        this.moveController = moveController;
    }
    /**
	 * Initiates controllers of the program.
	 */
    public void init () {
        boolean invalid;
        boolean isComputerMove;
        if (this.moveController.isComputerFirstMove()) {
            isComputerMove = true;
        } else {
            isComputerMove = false;
        }
        do {
            if (isComputerMove) {
                System.out.println("Ход компьютера...");
                this.moveController.computerMove();
                show.showGame(this.moveController);
            } else {
                invalid = true;
                do {
                    try {
                        System.out.println("Ваш ход...");
                        this.moveController.playerMove(Integer.valueOf(input.ask("Введите вертикальную координату Х: ",
                                this.moveController.getBoard().getRangeX())),
                                Integer.valueOf(input.ask("Введите горизонтальную координату Y: ",
                                        this.moveController.getBoard().getRangeY())));
                        invalid = false;
                    } catch (OcuppiedCellException oce) {
                        System.out.println("Ячейка не свободна!");
                    }
                    show.showGame(this.moveController);
                } while (invalid);
                if (this.moveController.getEmptyCells() == 0) {
                    break;
                }
            }
            isComputerMove = !isComputerMove;
        } while (this.moveController.getTheWinner() == null);
        if (this.moveController.getTheWinner().equals("Tie")) {
            System.out.println("It is TIE!");
        } else {
            if (this.moveController.getTheWinner().equals("Computer")) {
                System.out.println("Computer is won!");
            } else {
                if (this.moveController.getTheWinner().equals("Player")) {
                    System.out.println("You are won!");
                }
            }
        }
    }
    /**
     * Starts the program in operating system.
     * @param args console inputted arguments.
     */
    public static void main(String[] args) {
        IBoard board = new Board(3, 3);
        ValidateInput input = new ValidateInput();
        IMoveController moveController = new MoveController(board, 3, true);
        new StartUI(input, moveController).init();
    }
}