package ru.dionisius;


import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Dionisius on 19.11.2016.
 */
public class AbuseTest {
        /**
         * Checks deleting abused words from string.
         */
        @Test
        public void whenSpacesAreAbsentThenExpectedArray() {
            String inputString = "kolozola";
            String[] abuseWords = {"ol", "oz"};
            Abuse abuse = new Abuse();
            try (InputStream in = new ByteArrayInputStream(inputString.getBytes());
                 OutputStream out = new ByteArrayOutputStream()) {
                abuse.dropAbuses(in, out, abuseWords);
                String result = out.toString();
                String expectedValue = "ka";
                assertEquals(expectedValue, result);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }

        /**
        * check().
        * This method checks checks deleting abused words from string with whitespaces
        */
        @Test
        public void whenSpacesArePresentThenExpectedArray() {
        String[] abuseWords = {"one", "three", "five"};
        String inputString = "one two three four five";
        Abuse abuse = new Abuse();
        try (InputStream in = new ByteArrayInputStream(inputString.getBytes());
            OutputStream out = new ByteArrayOutputStream()) {
            abuse.dropAbuses(in, out, abuseWords);
            String result = out.toString();
            String expectedValue = " two  four ";
            assertEquals(expectedValue, result);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}



