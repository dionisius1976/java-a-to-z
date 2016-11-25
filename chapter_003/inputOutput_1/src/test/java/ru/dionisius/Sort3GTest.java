package ru.dionisius;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Dionisius on 21.11.2016.
 */
public class Sort3GTest {

    /**
     * @throws IOException if exception occurs.
     */
    @Test
    public void sort() throws IOException {
        String currentString;
        File source = File.createTempFile("source", ".txt");
        File distance = File.createTempFile("distance", ".txt");
        source.deleteOnExit();
        distance.deleteOnExit();
        RandomAccessFile raSource = new RandomAccessFile(source, "rw");
        String[] test = {"55555", "1", "4444", "22", "333"};
        for (String line: test) {
            raSource.writeBytes(String.format("%s\r\n", line));
        }
        raSource.seek(0);
        Sort3G sort3G = new Sort3G();
        sort3G.sort(source, distance);
        RandomAccessFile raDistance = new RandomAccessFile(distance, "r");
        while ((currentString = raDistance.readLine()) != null) {
                System.out.println(currentString);
        }
    }
}