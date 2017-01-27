package ru.dionisius.view;

import ru.dionisius.controls.IMoveController;

/**
 * Created by Dionisius on 20.01.2017.
 * Interface for all IShow classes.
 */
public interface IShow {
    /**
     * Shows board in specified IMoveController instance.
     * @param moveController specified IMoveController instance.
     */
    void showGame(IMoveController moveController);
}
