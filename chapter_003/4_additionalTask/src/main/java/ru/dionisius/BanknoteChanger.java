package ru.dionisius;

/**
 * Created by Dionisius on 10.12.2016.
 */
public class BanknoteChanger {
    /**
     * Decomposes integer to sums of summands and prints them.
     * @param banknote to change.
     * @param coins range of coins.
     */
    public void change(int banknote, int[] coins) {
        int[] currentArray = null;
        for (int i = 0; i < coins.length; i++) {
            currentArray = decompose(banknote, coins[i]);
            printArray(currentArray);
            for (int j = currentArray.length - 1; j > 0; j--) {
                for (int k = 0; k < coins.length; k++) {
                    if (currentArray[j] > coins[k]) {
                        for (int l = 0; l < j; l++) {
                            System.out.printf("%d+",currentArray[l]);
                        }
                        printArray(decompose(currentArray[j], coins[k]));
                        for (int l = j + 1; l < currentArray.length; l++) {
                            System.out.println(currentArray[l] + " " + "+" + " ");
                        }
                    }
                }
            }
        }
    }
    /**
     * Decomposes integer n to sum of summands.
     * @param n integer n to decompose.
     * @param summand summands.
     * @return array of summands.
     */
    public int[] decompose(int n, int summand) {
        int[] returningArray = null;
        int rest = n % summand;
        int cycles = n / summand;
        if (n != summand && cycles >= 1) {
            if (rest > 0) {
                returningArray = new int[cycles + rest];
                for (int i = 0; i < cycles; i++) {
                    returningArray[i] = summand;
                }
                returningArray[returningArray.length - 1] = rest;
            }
            if (rest == 0) {
                returningArray = new int[cycles];
                for (int i = 0; i < cycles; i++) {
                    returningArray[i] = summand;
                }
            }
        }
        if (n == summand) {
            returningArray = new int [1];
            returningArray[0] = summand;
        }
        return returningArray;
    }
    /**
     * Prints specified array as sum of summands.
     * @param array specified array.
     */
    public void printArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            System.out.printf("%d+", array[i]);
        }
        System.out.printf("%d%s", array[array.length - 1], System.lineSeparator());
    }
    /**
     * Starts the program.
     * @param args arguments from console.
     */
    public static void main(String[] args) {
        BanknoteChanger bc = new BanknoteChanger();
        int[] co = {10, 5, 1};
        bc.change(10, co);
    }
}
