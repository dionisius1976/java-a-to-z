package ru.dionisius;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Dionisius on 21.11.2016.
 *
 */
public class Sort3G {

    public void sort(File source, File distance) throws IOException {
        RandomAccessFile rafSource = new RandomAccessFile(source, "rw");
        RandomAccessFile rafDistance = new RandomAccessFile(distance, "rw");
        File temp = File.createTempFile("temp", ".tmp");
        File temp2 = File.createTempFile("temp2", ".tmp");
        File temp3 = File.createTempFile("temp3", ".tmp");
        temp.deleteOnExit();
        temp2.deleteOnExit();
        temp3.deleteOnExit();
        RandomAccessFile rafTemp = new RandomAccessFile(temp, "rw");
        RandomAccessFile rafTemp2 = new RandomAccessFile(temp2, "rw");
        RandomAccessFile rafTemp3 = new RandomAccessFile(temp3, "rw");
        int linesNumberSource = getLinesNumber(rafSource);
        String currentLine = "";
        int base = 2;
        int passValue = 0;
        long pointer = 0;
        int swither = 1;
        int counter = 0;
        while(Math.pow(base, passValue) < linesNumberSource) {
            int serieLength = passValue + 1;
            while ((currentLine = rafSource.readLine()) != null) {
                if (swither > 0) {
                    rafTemp2.writeBytes(String.format("%s\r\n", currentLine));
                } else {
                    rafTemp3.writeBytes(String.format("%s\r\n", currentLine));
                }
                counter++;
                if (counter == serieLength) {
                    swither = swither * (-1);
                    counter = 1;
                }
                while((currentLine = merge(rafTemp2, rafTemp3).readLine()) != null) {
                    rafTemp.seek(pointer);
                    rafTemp.writeBytes(String.format("%s\r\n", currentLine));
                    pointer = pointer + currentLine.length();
                }
            }
            rafSource = rafTemp;
            pointer = 0;
            passValue++;
        }
        while ((currentLine = rafSource.readLine()) != null) {
            rafDistance.writeBytes(String.format("%s\r\n", currentLine));
        }
    }

    public RandomAccessFile merge (RandomAccessFile first, RandomAccessFile second) throws IOException {
         File temp1 = File.createTempFile("temp1", ".tmp");
         temp1.deleteOnExit();
         RandomAccessFile rafTemp = new RandomAccessFile(temp1, "rw");
         int linesNumberMerged = getLinesNumber(first) + getLinesNumber(second);
         String firstString = "";
         String secondString = "";
         boolean isFirstLines = true;
         for (int line = 1; line != linesNumberMerged; line++) {
             if (isFirstLines) {
                 firstString = first.readLine();
                 secondString = second.readLine();
                 isFirstLines = false;
             }
             if (firstString == null) {
                 rafTemp.writeBytes(String.format("%s\r\n", secondString));
                 secondString = second.readLine();
                 continue;
             }
             else if (secondString == null) {
                 rafTemp.writeBytes(String.format("%s\r\n", firstString));
                 firstString = first.readLine();
                 continue;
             }
             else if (firstString.length() < secondString.length()) {
                 rafTemp.writeBytes(String.format("%s\r\n", firstString));
                 firstString = first.readLine();
                 continue;
             }
             else {
                 rafTemp.writeBytes(String.format("%s\r\n", secondString));
                 secondString = second.readLine();
             }
         }
         return rafTemp;
     }

     private int getLinesNumber (RandomAccessFile raf) throws IOException {
         int linesNumber = 0;
         while (raf.readLine() != null) {
             linesNumber++;
         }
         return linesNumber;
     }

//    public static void main(String[] args) throws IOException {
//        String currentstring;
//        Sort3G sort = new Sort3G();
//        String[] test = {"aaa", "bb", "c", "wwwww", "zzzz"};
//        File source = File.createTempFile("source", ".txt");
//        File distance = File.createTempFile("distance", ".txt");
//        //File tmp = File.createTempFile("tmp", ".txt");
//        source.deleteOnExit();
//        //tmp.deleteOnExit();
//        distance.deleteOnExit();
//
//        RandomAccessFile raSource = new RandomAccessFile(source, "rw");
//        RandomAccessFile raDistance = new RandomAccessFile(distance, "rw");
//        //RandomAccessFile raTmp = new RandomAccessFile(tmp, "rw");
//        for (String line : test) {
//            raSource.writeBytes(String.format("%s\r\n", line));
//        }
//        sort.sort(source, distance);
//        while ((currentstring = raSource.readLine()) != null) {
//            System.out.println("Hi!");
//            System.out.println(currentstring);
//        }
//        raSource = sort.merge(raSource, raDistance);
//        while ((currentstring = raSource.readLine()) != null) {
//            System.out.println(currentstring);
//        }
//    }
}



