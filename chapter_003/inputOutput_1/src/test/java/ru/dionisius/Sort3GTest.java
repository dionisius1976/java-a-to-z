package ru.dionisius;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import static org.junit.Assert.assertEquals;

/**
 * Created by Dionisius on 21.11.2016.
 */
public class Sort3GTest {


    @Test
    public void sort() throws Exception {
        String[] test = {"aaa", "bb", "c"};
        String[] expected = {"c", "bb", "aaa"};
        String currentstring;
        try{
            File source = File.createTempFile("source", ".txt");
            File distance = File.createTempFile("distance", ".txt");
            source.deleteOnExit();
            distance.deleteOnExit();
            RandomAccessFile raSource = new RandomAccessFile(source, "rw");
            Sort3G sort3G = new Sort3G();
            for (String line : test) {
                raSource.writeBytes(String.format("%s\r\n", line));
            }
            RandomAccessFile raDistance = new RandomAccessFile(distance, "rw");
            while ((currentstring = raDistance.readLine()) != null) {
                System.out.println("before.sort");
                System.out.print(currentstring + " ");
            }
            sort3G.sort(source, distance);

            while ((currentstring = raDistance.readLine()) != null) {
                System.out.println("after.sort");
                System.out.println(currentstring);
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Test
    public void merge() throws IOException {
        String currentstring;
        String[] test = {"aaa", "bb", "c", "wwwww", "zzzz"};
        String[] expected = {"c", "bb", "aaa", "zzzz", "wwwww"};
        File source = File.createTempFile("source", ".txt");
        File distance = File.createTempFile("distance", ".txt");
        File tmp = File.createTempFile("tmp", ".txt");
        source.deleteOnExit();
        tmp.deleteOnExit();
        source.deleteOnExit();
        RandomAccessFile raSource = new RandomAccessFile(source, "rw");
        RandomAccessFile raDistance = new RandomAccessFile(distance, "rw");
        RandomAccessFile raTmp = new RandomAccessFile(tmp, "rw");
        for (String line : test) {
            raSource.writeBytes(String.format("%s\r\n", line));
        }
        while ((currentstring = raSource.readLine()) != null) {
            System.out.println(currentstring);
        }
        for (String line : expected) {
            raDistance.writeBytes(String.format("%s\r\n", line));
        }
        while ((currentstring = raDistance.readLine()) != null) {
            System.out.println(currentstring);
        }

        Sort3G sort3G = new Sort3G();
        raTmp = sort3G.merge(raSource, raDistance);
        while ((currentstring = raTmp.readLine()) != null) {
            System.out.println(currentstring);
        }
    }

}