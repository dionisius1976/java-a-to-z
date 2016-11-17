package ru.dionisius;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dionisius on 17.11.2016.
 */
public class NumberChecker {

    /**
     *  private variable refer to InputStream object.
     */
    private  InputStream in;

    /**
     *  NumberChecker (InputStream in).
     *  Constructor initialize private variable
     *  @param i InputStream object
     */
    public NumberChecker(InputStream i) {
        this.in = i;
    }

    /**
     * isNumber().
     * This method checks inputStream for even or odd count of bytes
     * @return true if count of bytes in InputStream is even or false if count of bytes in InputStream is odd
     */
    public boolean isNumber() {
        BufferedInputStream bis = new BufferedInputStream(in);
        boolean endOfFile = false;
        int val;
        int counter = 0;
        try {
            while ((val = bis.read()) != -1) {
                counter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter % 2 == 0;
    }
}
