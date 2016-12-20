package ru.dionisius;

import org.junit.Test;
import ru.dionisius.inputs.ConsoleInput;
import ru.dionisius.trackers.ExtTracker;
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
    private ConsoleInput consoleInput;
    /**
     * Tracker with actions for this calculator.
     */
    private MenuTracker menuTracker;
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
            this.consoleInput = new ConsoleInput();
            this.menuTracker = new ExtTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(this.consoleInput).init(this.menuTracker);
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
            this.consoleInput = new ConsoleInput();
            this.menuTracker = new ExtTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(this.consoleInput).init(this.menuTracker);
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
            this.consoleInput = new ConsoleInput();
            this.menuTracker = new ExtTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(this.consoleInput).init(this.menuTracker);
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
            this.consoleInput = new ConsoleInput();
            this.menuTracker = new ExtTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(this.consoleInput).init(this.menuTracker);
            assertThat(output.toString().contains(this.expectedValue), is(true));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     *
     */
    @Test
    public void whenSquareRootOfNineThenThree() {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            this.menuItem = "4";
            this.firstValue = "9";
            this.expectedValue = "3,0";
            this.quit = "y";
            this.userInput = String.format("%s%s%s%s%s%s", this.menuItem, this.lineSep, this.firstValue,
                    this.lineSep, quit, this.lineSep);
            ByteArrayInputStream input = new ByteArrayInputStream(this.userInput.getBytes());
            System.setOut(new PrintStream(output));
            System.setIn(input);
            this.consoleInput = new ConsoleInput();
            this.menuTracker = new ExtTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(this.consoleInput).init(this.menuTracker);
            assertThat(output.toString().contains(this.expectedValue), is(true));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    @Test
    public void  whenInDegreeTwoThenNine() {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            this.menuItem = "5";
            this.firstValue = "3";
            this.secondValue = "2";
            this.expectedValue = "9,0";
            this.quit = "y";
            this.userInput = String.format("%s%s%s%s%s%sy%s", this.menuItem, this.lineSep, this.firstValue,
                    this.lineSep, this.secondValue, this.lineSep, this.lineSep);
            ByteArrayInputStream input = new ByteArrayInputStream(this.userInput.getBytes());
            System.setOut(new PrintStream(output));
            System.setIn(input);
            this.consoleInput = new ConsoleInput();
            this.menuTracker = new ExtTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(this.consoleInput).init(this.menuTracker);
            assertThat(output.toString().contains(this.expectedValue), is(true));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     *
     */
    @Test
    public void whenOneHundredThenDecimalLogarithmIsTwo() {
        try (ByteArrayOutputStream output = new ByteArrayOutputStream()) {
            this.menuItem = "6";
            this.firstValue = "100";
            this.expectedValue = "2,0";
            this.quit = "y";
            this.userInput = String.format("%s%s%s%s%s%s", this.menuItem, this.lineSep, this.firstValue,
                    this.lineSep, quit, this.lineSep);
            ByteArrayInputStream input = new ByteArrayInputStream(this.userInput.getBytes());
            System.setOut(new PrintStream(output));
            System.setIn(input);
            this.consoleInput = new ConsoleInput();
            this.menuTracker = new ExtTracker(consoleInput);
            this.menuTracker.fillActions();
            new StartUI(this.consoleInput).init(this.menuTracker);
            assertThat(output.toString().contains(this.expectedValue), is(true));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
