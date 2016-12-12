package ru.dionisius;

/**
 * Created by Dionisius on 10.12.2016.
 */
public class BanknoteChanger {

    public void change(int banknote, int[] coin) {
        int counter = 0;
        for (int i = 0; i < coin.length; i++) {
            if (banknote % coin[i] == 0) {
                int n = banknote / coin[i];
                counter++;
                while (n != 0) {

                }
            }
            if (i != 0) {

            }
        }
    }


//    public static void main(String[] args) {
//        int f = 1;
//        for (int i = 1; i <= 5; i++) {
//            f *= i;
//        }
//        System.out.println(f);
//    }
}

