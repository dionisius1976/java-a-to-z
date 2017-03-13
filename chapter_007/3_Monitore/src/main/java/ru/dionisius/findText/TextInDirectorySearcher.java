package ru.dionisius.findText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Dionisius on 11.03.2017.
 * Checks if any file of specified directory contains specified text.
 */
public class TextInDirectorySearcher implements Runnable {
//    /**
//     * TextInFileSearcher instance reference variable.
//     */
//    private final TextInFileSearcher searcher = new TextInFileSearcher();
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
                if (Find.isFinded || Thread.interrupted()) {
                    Thread.currentThread().interrupt();
                    break;
                }
                if (!currentFile.isDirectory()) {
                    this.consist(currentFile, this.findingText);
                } else {
                    Thread t = new Thread(new TextInDirectorySearcher(currentFile.getCanonicalPath(), this.findingText));
                    t.start();
                    while (!Thread.interrupted() && t.isAlive() && !Find.isFinded) {
                        try {
                            t.join(100);
                        if (Thread.interrupted() || !Find.isFinded) {
                            t.interrupt();
                            t.join();
                        }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Verifies if specified text is in specified file.
     * @param file specified file.
     * @param text specified text.
     * @return true if specified text is in specified file.
     */
    public synchronized void consist (final File file, final String text) {
        StringBuilder sb = new StringBuilder();
        String sbString = null;
        try (FileInputStream reader = new FileInputStream(file)) {
            int value = 0;
            boolean isFounded = false;
            while ((value = reader.read()) != -1 && !isFounded) {
                sb.append((char) value);
                sbString = sb.toString();
                if (text.startsWith(sbString)) {
                    if (text.equalsIgnoreCase(sbString)) {
                        isFounded = true;
                        Find.isFinded = true;
                        if (Find.file == null) {
                            Find.file = file.getCanonicalPath();
                        }
                    }
                } else {
                    if (sbString.charAt(sbString.indexOf(sbString.length() - 1)) == text.charAt(0)) {
                        sb.delete(0, sb.length());
                        sb.append(text.charAt(0));
                    } else {
                        sb.delete(0, sb.length());
                    }
                }
            }
        } catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.searchInDirectory();
    }
}
