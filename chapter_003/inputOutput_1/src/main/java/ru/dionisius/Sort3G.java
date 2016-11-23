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
        RandomAccessFile[] splitArray;
        RandomAccessFile rafSource = new RandomAccessFile(source, "rw");
        RandomAccessFile rafDistance = new RandomAccessFile(distance, "rw");
        File temp = File.createTempFile("temp", ".tmp");
//        File temp2 = File.createTempFile("temp2", ".tmp");
//        File temp3 = File.createTempFile("temp3", ".tmp");
//        temp.deleteOnExit();
//        temp2.deleteOnExit();
//        temp3.deleteOnExit();
        RandomAccessFile rafTemp = new RandomAccessFile(temp, "rw");
//        RandomAccessFile rafTemp2 = new RandomAccessFile(temp2, "rw");
//        RandomAccessFile rafTemp3 = new RandomAccessFile(temp3, "rw");
        int linesNumberSource = getLinesNumber(rafSource);
        String currentLine = "";
        int base = 2;
        int pass = 0;
        int counter = 0;
        while(Math.pow(base, pass) < linesNumberSource) {
            int serieLength = pass + 1;

            splitArray = split(rafSource, serieLength);
            rafTemp = merge(splitArray[0], splitArray[1]);
            while((currentLine = rafTemp.readLine()) !=  null) {
                rafSource.writeBytes(String.format("%s\r\n", currentLine));
            }

            pass++;







//            while ((currentLine = rafSource.readLine()) != null) {
//                if (swither > 0) {
//                    rafTemp2.writeBytes(String.format("%s\r\n", currentLine));
//                } else {
//                    rafTemp3.writeBytes(String.format("%s\r\n", currentLine));
//                }
//                if (counter == serieLength) {
//                    swither = swither * (-1);
//                    counter = 0;
//                }
//                counter++;
//                while((currentLine = merge(rafTemp2, rafTemp3).readLine()) != null) {
//                    rafTemp.seek(pointer);
//                    rafTemp.writeBytes(String.format("%s\r\n", currentLine));
//                    pointer = pointer + currentLine.length();
//                }
//            }
//            rafSource = rafTemp;
//            pointer = 0;
//            pass++;
        }
        while ((currentLine = rafSource.readLine()) != null) {
            rafDistance.writeBytes(String.format("%s\r\n", currentLine));
        }
    }

    /** split.
     * This method splits RandomAccessFile object
     * with stored sorted by length character lines
     * to two RandomAccessFile objects with specified
     * number of lines
     * @param raf
     * @param serieLength
     * @return
     * @throws IOException
     */
    public RandomAccessFile[] split (RandomAccessFile raf, int serieLength) throws  IOException{
        String currentString = "";
        RandomAccessFile[] array = new RandomAccessFile[2];
        File split1 = File.createTempFile("split1", ".tmp");
        File split2 = File.createTempFile("split1", ".tmp");
        split1.deleteOnExit();
        split2.deleteOnExit();
        RandomAccessFile raf1 = new RandomAccessFile(split1, "rw");
        RandomAccessFile raf2 = new RandomAccessFile(split2, "rw");
        for (int i = 0; i < serieLength; i++) {
            currentString = raf.readLine();
            if (currentString == null) {
                break;
            }
            raf1.writeBytes(String.format("%s\r\n", currentString));
        }
        for (int i = 0; i < serieLength; i++) {
            currentString = raf.readLine();
            if (currentString == null) {
                break;
            }
            raf2.writeBytes(String.format("%s\r\n", currentString));
        }
        array[0] = raf1;
        array[1] = raf2;
        return array;
    }

    /** merge().
     * This method merges two sorted RandomAccessFile objects
     * with stored sorted by length character lines
     * @param first - first object to merge
     * @param second - second object to merge
     * @return merged RandomAccessFile object with stored sorted by length character lines
     * @throws IOException
     */
    // Tested! Works!
    public RandomAccessFile merge (RandomAccessFile first, RandomAccessFile second) throws IOException {
         File temp1 = File.createTempFile("temp1", ".tmp");
         temp1.deleteOnExit();
         RandomAccessFile rafTemp = new RandomAccessFile(temp1, "rw");
         String firstString = "";
         String secondString = "";
         boolean isFirstLines = true;
         if(second != null) {
             int linesNumberMerged = getLinesNumber(first) + getLinesNumber(second);
             first.seek(0);
             second.seek(0);
             for (int line = 0; line != linesNumberMerged; line++) {
                 if (isFirstLines) {
                     firstString = first.readLine();
                     secondString = second.readLine();
                     isFirstLines = false;
                 }
                 if (firstString == null) {
                     rafTemp.writeBytes(String.format("%s\r\n", secondString));
                     secondString = second.readLine();
                     continue;
                 } else if (secondString == null) {
                     rafTemp.writeBytes(String.format("%s\r\n", firstString));
                     firstString = first.readLine();
                     continue;
                 } else if (firstString.length() < secondString.length()) {
                     rafTemp.writeBytes(String.format("%s\r\n", firstString));
                     firstString = first.readLine();
                     continue;
                 } else if (firstString.length() > secondString.length()){
                     rafTemp.writeBytes(String.format("%s\r\n", secondString));
                     secondString = second.readLine();
                 }
                 else {
                     rafTemp.writeBytes(String.format("%s\r\n", secondString));
                     rafTemp.writeBytes(String.format("%s\r\n", firstString));
                     secondString = second.readLine();
                     firstString = first.readLine();
                 }
             }
         } else {
             rafTemp.writeBytes(String.format("%s\r\n", first.readLine()));
         }
         return rafTemp;
     }

    /**getLinesNumber().
     * this method counts lines of characters
     * stored in specified RandomAccessFile object
     * @param raf specified RandomAccessFile object
     * @return number of character lines in specified object
     * @throws IOException
     */
    // Tested! Works!
     private int getLinesNumber (RandomAccessFile raf) throws IOException {
         int linesNumber = 0;
         raf.seek(0);
         while (raf.readLine() != null) {
             linesNumber++;
         }
         return linesNumber;
     }

}



