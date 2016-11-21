package ru.dionisius;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dionisius on 19.11.2016.
 */
public class AbuseTest {

        /**
         * check().
         * This method checks deleting abused words from string
         */
        @Test
        public void whenSpacesAreAbsentThenExpectedArray() {
            String[] abuseWords = {"ol", "oz"};
            String inputString = "kolozola";
            InputStream in = new ByteArrayInputStream(inputString.getBytes());
            OutputStream out = new ByteArrayOutputStream();
            Abuse abuse = new Abuse();
            abuse.dropAbuses(in, out, abuseWords);
            String result = out.toString();
            String expectedValue = "ka";
            assertEquals(expectedValue, result);
        }

        /**
        * check().
        * This method checks checks deleting abused words from string with whitespaces
        */
        @Test
        public void whenSpacesArePresentThenExpectedArray() {
        String[] abuseWords = {"one", "three", "five"};
        String inputString = "one two three four five";
        InputStream in = new ByteArrayInputStream(inputString.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        Abuse abuse = new Abuse();
        abuse.dropAbuses(in, out, abuseWords);
        String result = out.toString();
        String expectedValue = " two  four ";
        assertEquals(expectedValue, result);
    }
}



