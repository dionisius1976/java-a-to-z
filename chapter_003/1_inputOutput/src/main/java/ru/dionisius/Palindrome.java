package ru.dionisius;

import java.util.Scanner;

/**
 * Created by Dionisius on 23.11.2016.
 * This class for checking if specified word
 * is palindrome.
 */
public class Palindrome {

    /**
     * method isPalinrome(), checks if the word is palindrome.
     * @param string - checking word
     * @return true if word is palindrome, false if not
     */
    public boolean isPalindrome(String string) {
        boolean isPalindrome = true;
        string = string.toLowerCase();
        for (int i = 0; i < string.length() / 2; i++) {
            if (string.charAt(i) != string.charAt(string.length() - 1 - i)) {
                isPalindrome = false;
                break;
            }
        }
        return isPalindrome;
    }

    /**
     * Main method. Runs the program.
     * Get inputed in console by user word
     * and prints true if inputed word is palindrome
     * and false if inputed word is not palindrome.
     *
     * @param args inputed in console word
     */
    public static void main(String[] args) {
        final int PALINDROME_LENGTH = 5;
        try (Scanner scanner = new Scanner(System.in)) {
            ru.dionisius.Palindrome palindrome = new ru.dionisius.Palindrome();
            while (true) {
                System.out.print("inputs five characters testing word: ");
                String string = scanner.nextLine();
                if (string.length() != PALINDROME_LENGTH) {
                    System.out.println("It's not five characters word!");
                    continue;
                }
                System.out.println(palindrome.isPalindrome(string));
                break;
            }
        }
    }
}
