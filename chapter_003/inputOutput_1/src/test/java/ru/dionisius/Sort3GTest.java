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


//    @Test
//    public void sort() throws IOException {
//        String[] test = {"aaa", "bb", "c"};
//        String[] expected = {"c", "bb", "aaa"};
//        String currentstring;
//        try{
//            File source = File.createTempFile("source", ".txt");
//            File distance = File.createTempFile("distance", ".txt");
//            source.deleteOnExit();
//            distance.deleteOnExit();
//            RandomAccessFile raSource = new RandomAccessFile(source, "rw");
//            Sort3G sort3G = new Sort3G();
//            for (String line : test) {
//                raSource.writeBytes(String.format("%s\r\n", line));
//            }
//            RandomAccessFile raDistance = new RandomAccessFile(distance, "rw");
//            while ((currentstring = raDistance.readLine()) != null) {
//                System.out.println("before.sort");
//                System.out.print(currentstring + " ");
//            }
//            sort3G.sort(source, distance);
//
//            while ((currentstring = raDistance.readLine()) != null) {
//                System.out.println("after.sort");
//                System.out.println(currentstring);
//            }
//
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }

//    @Test
//    public void merge() throws IOException {
//        String currentstring;
//        String[] test = {"aaa", "bb", "c", "wwwww", "zzzz"};
//        String[] expected = {"c", "bb", "aaa", "zzzz", "wwwww"};
//        File source = File.createTempFile("source", ".txt");
//        File distance = File.createTempFile("distance", ".txt");
//        source.deleteOnExit();
//        distance.deleteOnExit();
//        RandomAccessFile raSource = new RandomAccessFile(source, "rw");
//        RandomAccessFile raDistance = new RandomAccessFile(distance, "rw");
//        for (String line : test) {
//            raSource.writeBytes(String.format("%s\r\n", line));
//        }
//        while ((currentstring = raSource.readLine()) != null) {
//            System.out.println(currentstring);
//        }
//        for (String line : expected) {
//            raDistance.writeBytes(String.format("%s\r\n", line));
//        }
//        while ((currentstring = raDistance.readLine()) != null) {
//            System.out.println(currentstring);
//        }
//
//        Sort3G sort3G = new Sort3G();
//        raDistance = sort3G.merge(raSource, raDistance);
//        while ((currentstring = raDistance.readLine()) != null) {
//            System.out.println(currentstring);
//        }
//    }

    @Test
    public void testing() {
        String currentstring;
        String[] test = {"aaa", "bb", "c", "wwwww", "zzzz"};
        File source = null;
        try {
            source = File.createTempFile("source", ".txt");

            RandomAccessFile raSource = new RandomAccessFile(source, "rw");
            for (String line : test) {
                raSource.writeBytes(String.format("%s\r\n", line));
            }
            //while ((currentstring = raSource.readLine()) != null) {
            //    System.out.println(currentstring);
            //}
            System.out.println(raSource.readLine());
            source.deleteOnExit();
            //Sort3G sort3G = new Sort3G();
           // while ((currentstring = sort3G.testing(raSource).readLine()) != null) {
           //     System.out.println(currentstring);
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }




    }
}