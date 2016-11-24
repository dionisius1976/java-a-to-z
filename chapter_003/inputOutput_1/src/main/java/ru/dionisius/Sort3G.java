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
        File split1 = File.createTempFile("split1", ".tmp");
        File split2 = File.createTempFile("split1", ".tmp");
        temp.deleteOnExit();
        split1.deleteOnExit();
        split2.deleteOnExit();
        RandomAccessFile rafTemp = new RandomAccessFile(temp, "rw");
        RandomAccessFile rafTemp2;
        RandomAccessFile rafSplit1 = new RandomAccessFile(split1, "rw");
        RandomAccessFile rafSplit2 = new RandomAccessFile(split2, "rw");
        String currentLine;
        int seriePass = 0;
        int base = 2;
        int pass = 0;
        int serieLength;
        int linesNumberSource = getLinesNumber(rafSource);
        while ((serieLength = (int) Math.pow(base, pass)) < linesNumberSource) {
            rafSource.seek(0);
            rafTemp.seek(0);
            seriePass = 0;
            while (linesNumberSource - seriePass > 0) {
                rafSplit1.seek(0);
                rafSplit2.seek(0);
                for (int i = 0; i < serieLength; i++) {
                    currentLine = rafSource.readLine();
                    rafSplit1.writeBytes(String.format("%s\r\n", currentLine));
                }
                for (int i = 0; i < serieLength; i++) {
                    currentLine = rafSource.readLine();
                    rafSplit2.writeBytes(String.format("%s\r\n", currentLine));
                }
                rafTemp2 = merge(rafSplit1, rafSplit2);
                rafTemp2.seek(0);
                while((currentLine = rafTemp2.readLine()) != null) {
                    rafTemp.writeBytes(String.format("%s\r\n", currentLine));
                }
                seriePass += serieLength * 2;
            }
            pass++;
            rafSource.seek(0);
            rafTemp.seek(0);
            while((currentLine = rafTemp.readLine()) != null) {
                rafSource.writeBytes(String.format("%s\r\n", currentLine));
            }
        }

        rafDistance.seek(0);
        rafSource.seek(0);
        while((currentLine = rafSource.readLine()) != null) {
            rafDistance.writeBytes(String.format("%s\r\n", currentLine));
        }

    }

//    public RandomAccessFile splitAndMerge (RandomAccessFile raf, int serieLength) throws IOException {
//        RandomAccessFile[] splitArray = this.split(raf, serieLength);
//        RandomAccessFile temp = this.merge(splitArray[0], splitArray[1]);
//        return temp;
//    }

    /** split(RandomAccessFile raf, int serieLength).
     * This method splits RandomAccessFile object
     * with stored sorted by length character lines
     * to two RandomAccessFile objects with specified
     * number of lines
     * @param raf
     * @param serieLength
     * @return
     * @throws IOException
     */
//    public RandomAccessFile[] split (RandomAccessFile raf, int serieLength) throws  IOException{
//        String currentString;
//        RandomAccessFile[] array = new RandomAccessFile[2];
//        File split1 = File.createTempFile("split1", ".tmp");
//        File split2 = File.createTempFile("split1", ".tmp");
//        split1.deleteOnExit();
//        split2.deleteOnExit();
//        RandomAccessFile raf1 = new RandomAccessFile(split1, "rw");
//        RandomAccessFile raf2 = new RandomAccessFile(split2, "rw");
//        raf.seek(0);
//        for (int i = 0; i < serieLength; i++) {
//            currentString = raf.readLine();
//            raf1.writeBytes(String.format("%s\r\n", currentString));
//        }
//        for (int i = 0; i < serieLength; i++) {
//            currentString = raf.readLine();
//            raf2.writeBytes(String.format("%s\r\n", currentString));
//        }
//        array[0] = raf1;
//        array[1] = raf2;
//        return array;
//    }

    /** merge(RandomAccessFile first, RandomAccessFile second).
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
         String firstString = null;
         String secondString = null;
         boolean isFirstLines = true;
         String currentString;
         //if(second.readLine() != null) {
             int linesNumberMerged = getLinesNumber(first) + getLinesNumber(second);
             first.seek(0);
             second.seek(0);
             rafTemp.seek(0);
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
//         } else {
//             first.seek(0);
//             while ((currentString = first.readLine()) != null) {
//                 rafTemp.writeBytes(String.format("%s\r\n", currentString));
//             }
//         }
//        System.out.println("merge:");
//        rafTemp.seek(0);
//        while ((currentString = rafTemp.readLine()) != null) {
//
//            System.out.println(String.format("%s\r\n", currentString));
//        }
        //rafTemp.seek(0);
         return rafTemp;
     }

    /**getLinesNumber(RandomAccessFile raf).
     * this method counts lines of characters
     * stored in specified RandomAccessFile object
     * @param raf specified RandomAccessFile object
     * @return number of character lines in specified object
     * @throws IOException
     */
    // Tested! Works!
     private int getLinesNumber (RandomAccessFile raf) throws IOException {
         String currentLine;
         int linesNumber = 0;
         raf.seek(0);
         while ((currentLine = raf.readLine()) != null) {
             if (currentLine.isEmpty()) continue;
             linesNumber++;
         }
         return linesNumber;
     }

}



