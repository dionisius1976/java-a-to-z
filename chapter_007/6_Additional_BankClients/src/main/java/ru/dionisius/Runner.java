package ru.dionisius;

import java.time.LocalTime;

/**
 * Created by Dionisius on 28.03.2017.
 * Runs the program.
 */
public class Runner {
    /**
     * Runs the program.
     * @param args console arguments.
     */
    public static void main(String[] args) {
        Bank bank = new Bank();

        Client client1 = new Client(1, LocalTime.of(8, 15), LocalTime.of(12, 30));
        Client client2 = new Client(2, LocalTime.of(13, 0), LocalTime.of(14, 0));
        Client client3 = new Client(3, LocalTime.of(14, 15), LocalTime.of(17, 0));

        bank.enter(client1);
        bank.enter(client2);
        bank.enter(client3);

        bank.showResults();
    }
}
