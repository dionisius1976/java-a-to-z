package ru.dionisius.trackers;

import ru.dionisius.actions.BaseAction;
import ru.dionisius.actions.UserAction;
import ru.dionisius.inputs.Input;

/**
 * Created by Dionisius on 20.12.2016.
 */
public class ExtTracker extends MenuTracker {
    /**
     * Number of available actions.
     */
    static final int NUMBER_OF_ACTIONS = 3;
    /**
     * Menu option 4.
     */
    static final int MENU_OPTION_4 = 4;
    /**
     * Menu option 5.
     */
    static final int MENU_OPTION_5 = 5;
    /**
     * Menu option 2.
     */
    static final int MENU_OPTION_6 = 6;

    /**
     * Array of available actions.
     */
    private final UserAction[] actions = new UserAction[super.NUMBER_OF_ACTIONS + this.NUMBER_OF_ACTIONS];
    /**
     * Default constructor.
     * @param input type of input.
     */
    public ExtTracker(final Input input) {
        super(input);
    }

    @Override
    public void fillActions() {
        this.actions[MENU_OPTION_0] = this.new Addition("Сложить.");
        this.actions[MENU_OPTION_1] = this.new Subtraction("Вычесть.");
        this.actions[MENU_OPTION_2] = this.new Multiplication("Умножить.");
        this.actions[MENU_OPTION_3] = this.new Division("Разделить.");
        this.actions[MENU_OPTION_4] = this.new Sqrt("Вычислить квадратный корень.");
        this.actions[MENU_OPTION_5] = this.new Pow("Возвести в степень.");
        this.actions[MENU_OPTION_6] = this.new Ln("Вычислить десятичный логарифм.");
    }

    @Override
    public int[] getRange() {
        int[] result = new int[this.actions.length];
        for (int index = 0; index < result.length; index++) {
            result[index] = index;
        }
        return result;
    }

    @Override
    public void show() {
        for (UserAction action: this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    @Override
    public void select(int key) {
            this.actions[key].execute(super.input);
            super.isFirst = false;
        }
    /**
     * Calculates square root of specified double value.
     */
    private class Sqrt extends BaseAction implements UserAction {
        /**
         * Default constructor.
         * @param name specified name of this class execute action.
         */
        Sqrt(String name) {
            super(name);
        }
        /**
         * Specified key for this action.
         */
        static final int KEY = 4;
        /**
         * Returns specified key for this class instance that using in menu of available actions.
         * @return specified key for this class instance.
         */
        public int key() {
            return KEY;
        }
        /**
         * Calculates addition of two specified double values.
         * @param input type of input.
         */
        public void execute(Input input) {
            double firstValue = 0;
            if (ExtTracker.super.isFirst) {
                firstValue = Double.valueOf(ExtTracker.super.input.ask("Введите значене числа: "));
            } else {
                firstValue = ExtTracker.super.result;
            }
            ExtTracker.super.result = Math.sqrt(firstValue);
            System.out.printf("Квадратный корень из числа %f равен: %f%s", firstValue,
                    ExtTracker.super.result, ExtTracker.super.lineSep);
        }
    }
    /**
     * Calculates pow of specified double value.
     */
    private class Pow extends BaseAction implements UserAction {
        /**
         * Default constructor.
         * @param name specified name of this class execute action.
         */
        Pow(String name) {
            super(name);
        }
        /**
         * Specified key for this action.
         */
        static final int KEY = 5;
        /**
         * Returns specified key for this class instance that using in menu of available actions.
         * @return specified key for this class instance.
         */
        public int key() {
            return KEY;
        }
        /**
         * Calculates addition of two specified double values.
         * @param input type of input.
         */
        public void execute(Input input) {
            double firstValue = 0;
            if (ExtTracker.super.isFirst) {
                firstValue = Double.valueOf(ExtTracker.super.input.ask("Введите значене основания: "));
            } else {
                firstValue = ExtTracker.super.result;
            }
            double secondValue = Double.valueOf(input.ask("Введите значене степени: "));
            ExtTracker.super.result = Math.pow(firstValue, secondValue);
            System.out.printf("%f в степени %f равно: %f%s", firstValue, secondValue,
                    ExtTracker.super.result, ExtTracker.super.lineSep);
        }
    }
    /**
     * Calculates decimal logarithm of two specified double values.
     */
    private class Ln extends BaseAction implements UserAction {
        /**
         * Default constructor.
         * @param name specified name of this class execute action.
         */
        Ln(String name) {
            super(name);
        }
        /**
         * Specified key for this action.
         */
        static final int KEY = 6;
        /**
         * Returns specified key for this class instance that using in menu of available actions.
         * @return specified key for this class instance.
         */
        public int key() {
            return KEY;
        }
        /**
         * Calculates addition of two specified double values.
         * @param input type of input.
         */
        public void execute(Input input) {
            double firstValue = 0;
            if (ExtTracker.super.isFirst) {
                firstValue = Double.valueOf(ExtTracker.super.input.ask("Введите значене числа: "));
            } else {
                firstValue = ExtTracker.super.result;
            }
            ExtTracker.super.result = Math.log10(firstValue);
            System.out.printf("Десятичный логарифм числа %f равен: %f%s",
                    firstValue, ExtTracker.super.result, ExtTracker.super.lineSep);
        }
    }
}
