package ru.dionisius.view;

import ru.dionisius.controls.IMoveController;
import ru.dionisius.models.Value;

/**
 * Created by Dionisius on 21.01.2017.
 */
public class Show implements IShow {
    @Override
    public void showGame(IMoveController moveController) {
        Value[][] board = moveController.getBoard().getBoard();
        String cell = null;
        int maxX = moveController.getBoard().getRangeX();
        int maxY = moveController.getBoard().getRangeY();
        for (int i = 0; i <= maxX; i++) {
            System.out.printf("%d%s", i, " ");
            for (int j = 0; j <= maxY; j++) {
                if (board[i][j] == null) {
                    cell = " ";
                } else {
                    cell = board[i][j].toString();
                }
                System.out.printf("%s%s%s%s", "[", cell, "]", " ");
            }

            System.out.println();
        }
        int indent = new Integer(maxX).toString().length() + 2;
        for (int i = 0; i != indent; i++) {
            System.out.print(" ");
        }
        for (int i = 0; i <= maxY ; i++) {
            System.out.printf("%d%s%s", i, " ", " ");
        }
        System.out.println();
    }
}
