package ru.dionisius.raceCondition;

/**
 * Created by Dionisius on 10.03.2017.
 * Class stores the variable for testing race condition case.
 */
public class VariableStorage {

    /**
     * The variable for testing race condition case.
     */
    public volatile int changingVariable = 0;

    @Override
    public String toString() {
        return String.format("VariableStorage{changingVariable = %d}%s", this.changingVariable, System.lineSeparator());
    }
}
