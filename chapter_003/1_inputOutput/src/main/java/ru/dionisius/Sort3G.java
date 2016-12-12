package ru.dionisius;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Dionisius on 21.11.2016.
 * This class has method sort() for sorting
 * character lines in source file by length size
 * and write them in distance file
 *
 */
public class Sort3G {

    /** sort(File source, File distance).
     * This method sorts character lines in source file
     * by length size and write them in distance file
     * @param source - unsorted source file
     * @param distance - sorted distance file
     */
    public void sort(File source, File distance)  {
        try (RandomAccessFile rafSource = new RandomAccessFile(source, "rw");
             RandomAccessFile rafDistance = new RandomAccessFile(distance, "rw");){
            File temp = File.createTempFile("temp", ".tmp");
            File split1 = File.createTempFile("split1", ".tmp");
            File split2 = File.createTempFile("split2", ".tmp");
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
                    while ((currentLine = rafTemp2.readLine()) != null) {
                        if ("".equals(currentLine) || currentLine == null) {
                            continue;
                        }
                        rafTemp.writeBytes(String.format("%s\r\n", currentLine));
                    }
                    seriePass += serieLength * 2;
                }
                pass++;
                rafSource.seek(0);
                rafTemp.seek(0);
                while ((currentLine = rafTemp.readLine()) != null) {
                    rafSource.writeBytes(String.format("%s\r\n", currentLine));
                }
            }
            rafDistance.seek(0);
            rafSource.seek(0);
            while ((currentLine = rafSource.readLine()) != null) {
                if ("".equals(currentLine) || currentLine == null) {
                    continue;
                }
                rafDistance.writeBytes(String.format("%s\r\n", currentLine));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }


    /** merge(RandomAccessFile first, RandomAccessFile second).
     * This method merges two sorted RandomAccessFile objects
     * with stored sorted by length size character lines
     * @param first - first object to merge
     * @param second - second object to merge
     * @return merged RandomAccessFile object with stored sorted by length size character lines
     * @throws IOException if exception occurs
     */
    private RandomAccessFile merge(RandomAccessFile first, RandomAccessFile second) throws IOException {
        File temp1 = File.createTempFile("temp1", ".tmp");
        temp1.deleteOnExit();
        RandomAccessFile rafTemp = new RandomAccessFile(temp1, "rw");
        String firstString = null;
        String secondString = null;
        boolean isFirstLines = true;
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
            } else if (firstString.length() > secondString.length()) {
                rafTemp.writeBytes(String.format("%s\r\n", secondString));
                secondString = second.readLine();
            } else {
                rafTemp.writeBytes(String.format("%s\r\n", secondString));
                rafTemp.writeBytes(String.format("%s\r\n", firstString));
                secondString = second.readLine();
                firstString = first.readLine();
            }
        }
        return rafTemp;
    }

    /**getLinesNumber(RandomAccessFile raf).
     * this method counts lines of characters
     * stored in specified RandomAccessFile object
     * @param raf specified RandomAccessFile object
     * @return number of character lines in specified object
     * @throws IOException if exception occurs
     */
    private int getLinesNumber(RandomAccessFile raf) throws IOException {
        String currentLine;
        int linesNumber = 0;
        raf.seek(0);
        while ((currentLine = raf.readLine()) != null) {
            if (currentLine.isEmpty()) {
                continue;
            }
            linesNumber++;
        }
        return linesNumber;
    }
}



