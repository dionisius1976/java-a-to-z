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
    public void sort() throws IOException {
        //String[] expected = {"c", "bb", "aaa"};
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

//    @Test // Works!
//    public void merge() throws IOException {
//        String currentstring;
//        String[] test = {"a", "bbb", "cccc"};
//        String[] expected = {"zz", "wwwww"};
//        File source = File.createTempFile("source", ".txt");
//        File distance = File.createTempFile("distance", ".txt");
//        source.deleteOnExit();
//        distance.deleteOnExit();
//        RandomAccessFile raSource = new RandomAccessFile(source, "rw");
//        RandomAccessFile raDistance = new RandomAccessFile(distance, "rw");
//        for (String line : test) {
//            raSource.writeBytes(String.format("%s\r\n", line));
//        }
//        for (String line : expected) {
//            raDistance.writeBytes(String.format("%s\r\n", line));
//        }
//        Sort3G sort3G = new Sort3G();
//        raDistance = sort3G.merge(raSource, raDistance);
//        raDistance.seek(0);
//        while ((currentstring = raDistance.readLine()) != null) {
//            System.out.println(currentstring);
//        }
//    }


//    @Test // Works!
//    public void split() throws IOException {
//        String currentstring;
//        RandomAccessFile[] array;
//        String[] test = {"a", "bbb", "cccc", "zzz", "wwwww"};
//        File source = File.createTempFile("source", ".txt");
//        //File distance = File.createTempFile("distance", ".txt");
//        source.deleteOnExit();
//        //distance.deleteOnExit();
//        RandomAccessFile raSource = new RandomAccessFile(source, "rw");
//        //RandomAccessFile raDistance = new RandomAccessFile(distance, "rw");
//        for (String line : test) {
//            raSource.writeBytes(String.format("%s\r\n", line));
//        }
//        Sort3G sort3G = new Sort3G();
//        raSource.seek(0);
//        array = sort3G.split(raSource, 3);
//        array[0].seek(0);
//        array[1].seek(0);
//        for (int i = 0; i < array.length; i++) {
//            while ((currentstring = array[i].readLine()) != null) {
//            System.out.println(currentstring);
//            }
//        }
//        array[0].seek(0);
//        array[1].seek(0);
//        raSource = sort3G.merge(array[0], array[1]);
//        raSource.seek(0);
//        while ((currentstring = raSource.readLine()) != null) {
//                System.out.println(currentstring);
//            }
//    }

//    @Test
//    public void testing() {
//        String currentstring;
//        String[] test = {"aaa", "bb", "c", "wwwww", "zzzz"};
//        File source = null;
//        try {
//            source = File.createTempFile("source", ".txt");
//
//            RandomAccessFile raSource = new RandomAccessFile(source, "rw");
//            for (String line : test) {
//                raSource.writeBytes(String.format("%s\r\n", line));
//            }
//
////            while ((currentstring = raSource.readLine()) != null) {
////                System.out.println(currentstring);
////            }
//            //System.out.println(raSource.readLine());
//            //source.deleteOnExit();
//            Sort3G sort3G = new Sort3G();
//            raSource.seek(0);
//            while ((currentstring = sort3G.testing(raSource).readLine()) != null) {
//                System.out.println(currentstring);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//    }
}