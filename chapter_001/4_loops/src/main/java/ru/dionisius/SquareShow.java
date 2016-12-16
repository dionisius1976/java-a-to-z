package ru.dionisius;

/**
 * Created by Dionisius on 13.12.2016.
 * Prints all values of this quadratic equation
 * in specified range and specified step.
 */
public class SquareShow {
    /**
     * Specified range start value.
     */
    private final float start;
    /**
     * Specified range end value.
     */
    private final float finish;
    /**
     * Specified range step value.
     */
    private final float step;
    /**
     * Specified class Square variable.
     */
    private final Square square;
    /**
     * Default constructor.
     * @param start specified range start value.
     * @param finish specified range end value.
     * @param step specified range step value.
     * @param square specified quadratic equation.
     */
    public SquareShow(float start, float finish, float step, Square square) {
        this.start = start;
        this.finish = finish;
        this.step = step;
        this.square = square;
    }
    /**
     * Method show().
     * Prints all values of this quadratic equation
     * in specified range with specified step.
     * @param start range start value.
     * @param finish range end value.
     * @param step range step value.
     */
    public void show(float start, float finish, float step) {
        for (float i = start; i <= finish; i += step) {
            System.out.printf("Значение выражения при х=%-d равно: %f", i, this.square.calculate(i));
        }
    }


}
