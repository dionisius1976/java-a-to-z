package ru.dionisius.findText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Dionisius on 11.03.2017.
 * Verifies if specified text is in specified file.
 */
public class TextInFileSearcher {

//    /**
//     * Verifies if specified text is in specified file.
//     * @param file specified file.
//     * @param text specified text.
//     * @return true if specified text is in specified file.
//     */
//    public synchronized boolean consist (final File file, final String text) {
//        StringBuilder sb = new StringBuilder();
//        String sbString = null;
//        boolean result = false;
//        try (FileInputStream reader = new FileInputStream(file)) {
//            int value = 0;
//            boolean isFounded = false;
//            while ((value = reader.read()) != -1 && !isFounded) {
//                sb.append((char) value);
//                sbString = sb.toString();
//                if (text.startsWith(sbString)) {
//                    if (text.equalsIgnoreCase(sbString)) {
//                        isFounded = true;
//                    }
//                } else {
//                    if (sbString.charAt(sbString.indexOf(sbString.length() - 1)) == text.charAt(0)) {
//                        sb.delete(0, sb.length());
//                        sb.append(text.charAt(0));
//                    } else {
//                        sb.delete(0, sb.length());
//                    }
//                }
//            }
//        } catch (FileNotFoundException fnf) {
//            fnf.printStackTrace();
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        return result;
//    }
}
