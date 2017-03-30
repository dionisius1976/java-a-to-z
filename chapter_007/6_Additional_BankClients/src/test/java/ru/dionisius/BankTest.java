package ru.dionisius;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import static org.hamcrest.core.Is.is;

import java.time.LocalTime;

import static org.junit.Assert.*;

/**
 * Created by Dionisius on 30.03.2017.
 * Test class for Bank class.
 */
public class BankTest {
    /**
     * Bank instance.
     */
    private Bank bank;
    /**
     * Client instance for test.
     */
    private Client client1;
    /**
     * Client instance for test.
     */
    private Client client2;
    /**
     * Client instance for test.
     */
    private Client client3;
    /**
     * Client instance for test.
     */
    private Client client4;
    /**
     * Client instance for test.
     */
    private Client client5;
    /**
     * Client instance for test.
     */
    private Client client6;
    /**
     * Actual handling result.
     */
    String actual;
    /**
     * Expected result.
     */
    String expected;

    @Rule
    public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    /**
     * Tests if expected result when tree clients are together in the bank.
     */
    @Test
    public void whenThreeClientsThenExpectedResult() {
        this.bank = new Bank();
        this.client1 = new Client(1, LocalTime.of(8, 15), LocalTime.of(12, 30));
        this.client2 = new Client(2, LocalTime.of(11, 0), LocalTime.of(14, 0));
        this.client3 = new Client(3, LocalTime.of(12, 0), LocalTime.of(17, 0));
        this.bank.enter(client1);
        this.bank.enter(client2);
        this.bank.enter(client3);
        bank.showResults();
        this.actual = systemOutRule.getLog().trim();
        this.expected = "Max count of clients was 3. In duration from 12:00 to 12:30.".trim();
        assertThat(this.actual, is(this.expected));
    }
    /**
     * Tests if expected result when tree clients are together in the bank two times per day.
     */
    @Test
    public void whenFiveClientsAndTwoTimeCutsThenExpectedResult() {
        this.bank = new Bank();
        this.client1 = new Client(1, LocalTime.of(8, 15), LocalTime.of(12, 30));
        this.client2 = new Client(2, LocalTime.of(11, 0), LocalTime.of(13, 15));
        this.client3 = new Client(3, LocalTime.of(12, 0), LocalTime.of(13, 43));
        this.client4 = new Client(4, LocalTime.of(14, 0), LocalTime.of(20, 0));
        this.client5 = new Client(5, LocalTime.of(15, 15), LocalTime.of(17, 0));
        this.client6 = new Client(6, LocalTime.of(16, 12), LocalTime.of(16, 55));
        this.bank.enter(client1);
        this.bank.enter(client2);
        this.bank.enter(client3);
        this.bank.enter(client4);
        this.bank.enter(client5);
        this.bank.enter(client6);
        bank.showResults();
        this.actual = systemOutRule.getLog().trim();
        this.expected = "Max count of clients was 3. In duration from 12:00 to 12:30.\r" +
                "\n" +
                "Max count of clients was 3. In duration from 16:12 to 16:55.".trim();
        assertThat(this.actual, is(this.expected));
    }
    /**
     * Tests if expected result when there is tree client per day in the bank at different times.
     */
    @Test
    public void whenThereIsNoTimeCutsWithMoreThenOneClientThenExpectedResult() {
        this.bank = new Bank();
        this.client1 = new Client(1, LocalTime.of(8, 15), LocalTime.of(12, 30));
        this.client2 = new Client(2, LocalTime.of(13, 0), LocalTime.of(14, 0));
        this.client3 = new Client(3, LocalTime.of(14, 15), LocalTime.of(17, 0));
        this.bank.enter(client1);
        this.bank.enter(client2);
        this.bank.enter(client3);
        bank.showResults();
        this.actual = systemOutRule.getLog().trim();
        this.expected = "Max count of clients was 1. In duration from 08:15 to 12:30.\r\n" +
                "Max count of clients was 1. In duration from 13:00 to 14:00.\r\n" +
                "Max count of clients was 1. In duration from 14:15 to 17:00.".trim();
        assertThat(this.actual, is(this.expected));
    }
}