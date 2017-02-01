package ru.dionisius;

/**
 * Created by Dionisius on 30.01.2017.
 */
public class BanknoteChanger {

    private final int[] coins = {1, 5, 10};

    public void change(int banknote) {
        for (int tens = 0; tens <= (banknote / coins[2]); tens++) {
            int moneyWithoutTens = banknote - tens * coins[2];
            for (int fives = 0; fives <= (moneyWithoutTens / coins[1]); fives++) {
                this.printCoins(coins[2], tens);
                this.printCoins(coins[1], fives);
                this.printCoins(coins[0], (moneyWithoutTens - fives * coins[1]));
                System.out.println("");
            }
        }
    }

    private void printCoins(final int coin, final int amount) {
        if (amount != 0) {
            for (int index = 0; index < amount; index++) {
                System.out.print(coin + " ");
            }
        }
    }

    public static void main(String[] args) {
        BanknoteChanger bc = new BanknoteChanger();
        bc.change(20);
    }
}
