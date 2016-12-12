package ru.dionisius;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
 * Created by Dionisius on 19.11.2016.
 */
public class Abuse {

    /** dropAbuses(InputStream in, OutputStream out, String[] abuse).
     * This method delete all abused words from array String[] in InputStream in
     * and write this InputStream in OutputStream out
     * @param in - input stream
     * @param  out - output stream
     * @param abuse - array of abused words
     */
    public void dropAbuses(InputStream in, OutputStream out, String[] abuse) {
        boolean isAbuse = false;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            int streamChar;
            while ((streamChar = reader.read()) != -1) {
                sb.append((char) streamChar);
                for (String abuseWord: abuse) {
                    if (abuseWord.startsWith(sb.toString())) {
                        isAbuse = true;
                        if ((sb.toString().toLowerCase()).equals(abuseWord.toLowerCase())) {
                            sb.delete(0, sb.length());
                        }
                        break;
                    } else {
                        isAbuse = false;
                    }
                }
                if (!isAbuse) {
                    writer.write(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}


