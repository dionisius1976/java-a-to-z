package ru.dionisius.findText;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Dionisius on 11.03.2017.
 * Checks if any file of specified directory contains specified text.
 */
public class TextInDirectorySearcher implements Runnable {
    /**
     * Specified working directory.
     */
    private final String workingDirectory;
    /**
     * Specified text to find.
     */
    private final String findingText;

    /**
     * Constructor.
     * @param workingDirectory specified working directory.
     * @param findingText specified text to find.
     */
    public TextInDirectorySearcher(String workingDirectory, String findingText) {
        this.workingDirectory = workingDirectory;
        this.findingText = findingText;
    }

    /**
     * Verifies if any of files of the directory contains specified text.
     * Throws TextIsFoundException if if any of files of the directory contains specified text.
     */
    private void searchInDirectory(){
        try {
            File[] listFiles = new File(this.workingDirectory).listFiles();
            for (File currentFile : listFiles) {
                if (Find.isFound || Thread.interrupted()) {
                    break;
                }
                if (!currentFile.isDirectory()) {
                    this.consist(currentFile, this.findingText);
                } else {
                    new Thread(new TextInDirectorySearcher(currentFile.getCanonicalPath(), this.findingText)).start();

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if specified text exists in specified file.
     * @param file specified file.
     * @param text specified text.
     * @throws IOException if IOException occurs.
     */
    public synchronized void consist (final File file, final String text) throws IOException {
        StringBuilder sb = new StringBuilder();
        String sbString = null;
        try (FileInputStream reader = new FileInputStream(file)) {
            int value = 0;
            while ((value = reader.read()) != -1) {
                sb.append((char) value);
                sbString = sb.toString();
                if (text.startsWith(sbString)) {
                    if (text.equalsIgnoreCase(sbString)) {
                        Find.isFound = true;
                        if (Find.fileWithText == null) {
                            Find.fileWithText = file.getCanonicalPath();
                        }
                        break;
                    }
                } else {
                    if (sbString.charAt(sbString.indexOf(sbString.length() - 1)) == text.charAt(0)) {
                        sb.delete(0, sb.length() - 1);
                    } else {
                        sb.delete(0, sb.length());
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        this.searchInDirectory();
    }
}