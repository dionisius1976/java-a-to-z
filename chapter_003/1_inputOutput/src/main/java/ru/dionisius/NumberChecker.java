package ru.dionisius;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dionisius on 17.11.2016.
 *
 * This class checks inputStream for even or odd number in it.
 */
public class NumberChecker {
    /**
     * Checks inputStream for even or odd number in it.
     * @param i - specified InputStream object.
     * @return true if count of bytes in InputStream is even or false if count of bytes in InputStream is odd.
     */
    public boolean isNumber(InputStream i) {
        StringBuilder sb = new StringBuilder();
        int c;
        try (BufferedInputStream bis = new BufferedInputStream(i)) {
            while ((c = bis.read()) != -1) {
                sb.append((char) c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int number = Integer.valueOf(sb.toString());
        return (number % 2 == 0);
    }
}
