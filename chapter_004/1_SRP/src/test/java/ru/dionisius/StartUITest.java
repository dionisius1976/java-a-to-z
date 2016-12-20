package ru.dionisius;

import org.junit.Test;
import ru.dionisius.inputs.ConsoleInput;
import ru.dionisius.trackers.MenuTracker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Dionisius on 19.12.2016.
 */
public class StartUITest {
    /**
     * Line separator for this operation system.
     */
    private final String lineSep = System.lineSeparator();
    /**
     * Input from console.
     */
    private ConsoleInput consoleInput = new ConsoleInput();
    /**
     * Tracker with actions for this calculator.
     */
    private MenuTracker menuTracker = new MenuTracker(this.consoleInput);;
    /**
     * Emulated user's input.
     */
    private String userInput;
    /**
     * First value for calculation.
     */
    private String  firstValue;
    /**
     * Second value for calculation.
     */
    private String secondValue;
    /**
     * Expected value of calculation.
     */
    private String expectedValue;
    /**
     * Chosen item of calculator operations menu.
     */
    private String menuItem;
    /**
     * Key for quit the program.
     */
    private String quit;

    /**
     * Verifies addition operation of this calculator.
     */
    @Test
    public void whenSevenPlusEightThenFifteen() {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            this.menuItem = "0";
            this.firstValue = "7";
            this.secondValue = "8";
            this.quit = "y";
            this.userInput = String.format("%s%s%s%s%s%sy%s", this.menuItem, this.lineSep,
                    this.firstValue, this.lineSep, this.secondValue, this.lineSep, this.lineSep);
            ByteArrayInputStream input = new ByteArrayInputStream(this.userInput.getBytes());
            System.setOut(new PrintStream(output));
            System.setIn(input);
            ConsoleInput consoleInput = new ConsoleInput();
            this.menuTracker = new MenuTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(consoleInput).init(this.menuTracker);
            this.expectedValue = "15,0";
            assertThat(output.toString().contains(this.expectedValue), is(true));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Verifies subtraction operation of this calculator.
     */
    @Test
    public void whenTenMinusEightThenTwo() {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            this.menuItem = "1";
            this.firstValue = "10";
            this.secondValue = "8";
            this.expectedValue = "2,0";
            this.quit = "y";
            this.userInput = String.format("%s%s%s%s%s%sy%s", this.menuItem, this.lineSep,
                    this.firstValue, this.lineSep, this.secondValue, this.lineSep, this.lineSep);
            ByteArrayInputStream input = new ByteArrayInputStream(this.userInput.getBytes());
            System.setOut(new PrintStream(output));
            System.setIn(input);
            ConsoleInput consoleInput = new ConsoleInput();
            this.menuTracker = new MenuTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(consoleInput).init(this.menuTracker);
            assertThat(output.toString().contains(this.expectedValue), is(true));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Verifies multiplication operation of this calculator.
     */
    @Test
    public void whenTreeMultiplyFiveThenFifteen() {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            this.menuItem = "2";
            this.firstValue = "3";
            this.secondValue = "5";
            this.expectedValue = "15,0";
            this.quit = "y";
            this.userInput = String.format("%s%s%s%s%s%sy%s", this.menuItem, this.lineSep,
                    this.firstValue, this.lineSep, this.secondValue, this.lineSep, this.lineSep);
            ByteArrayInputStream input = new ByteArrayInputStream(this.userInput.getBytes());
            System.setOut(new PrintStream(output));
            System.setIn(input);
            ConsoleInput consoleInput = new ConsoleInput();
            this.menuTracker = new MenuTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(consoleInput).init(this.menuTracker);
            assertThat(output.toString().contains(this.expectedValue), is(true));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Verifies division operation of this calculator.
     */
    @Test
    public void whenEightDivideFourThenTwo() {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            this.menuItem = "3";
            this.firstValue = "8";
            this.secondValue = "4";
            this.expectedValue = "2,0";
            this.quit = "y";
            this.userInput = String.format("%s%s%s%s%s%sy%s", this.menuItem, this.lineSep, this.firstValue,
                    this.lineSep, this.secondValue, this.lineSep, this.lineSep);
            ByteArrayInputStream input = new ByteArrayInputStream(this.userInput.getBytes());
            System.setOut(new PrintStream(output));
            System.setIn(input);
            ConsoleInput consoleInput = new ConsoleInput();
            this.menuTracker = new MenuTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(consoleInput).init(this.menuTracker);
            assertThat(output.toString().contains(this.expectedValue), is(true));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
