package ru.dionisius;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Random;
import java.util.Scanner;
/**
 * Created by Dionisius on 24.11.2016.
 */
public class Chat {
    /**
     * File separator for paths supporting by current operation system.
     */
    private final String fSep = File.separator;
    /**
     * Current operating system line separator.
     */
    private final String lSep = System.getProperty("line.separator");
    /**
     * Stop command.
     */
    static final String STOP = "стоп";
    /**
     * Terminate command.
     */
    static final String FINISH = "закончить";
    /**
     * Continue command.
     */
    static final String CONTINUE = "продолжить";
    /**
     * Limit on the number of characters.
     * That may be read while still preserving the mark.
     */
    static final int READ_AHEAD_LIMIT = 200;
    /**
     * Inputed from console user phrase.
     */
    private String userPhrase = "";
    /**
     * File object with answers.
     */
    private File answersFile = new File(this.getClass().getResource("/answers.txt").getPath());
    /**
     * File object for log.
     */
    private File logFile = new File(this.getClass().getResource("/log.txt").getPath());
    /**
     * Random answer phrase from answer file.
     */
    private String answer;
    /**
     * Boolean switcher for mute mode for answers.
     */
    private boolean mute;
    /**
     * Constructor.
     */
    public Chat() { }
    /**
     *  Starts the chat between user and program.
     *  and writes their dialogue in log file
     */
    public void start() {
        int answersNumber = 0;
        mute = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.logFile));
             Scanner scanner = new Scanner(System.in);
             BufferedReader reader = new BufferedReader(new FileReader(this.answersFile))) {
            reader.mark(READ_AHEAD_LIMIT);
            while (reader.readLine() != null) {
                answersNumber++;
            }
            reader.reset();
            while (scanner.hasNext()) {
                userPhrase = scanner.nextLine();
                writer.write(String.format("User: %s%s", userPhrase, this.lSep));
                if (STOP.equalsIgnoreCase(userPhrase)) {
                    mute = true;
                }
                if (CONTINUE.equalsIgnoreCase(userPhrase)) {
                    mute = false;
                }
                if (FINISH.equalsIgnoreCase(userPhrase)) {
                    break;
                }
                int lineBorder = new Random(System.currentTimeMillis()).nextInt(answersNumber);
                if (!mute) {
                    for (int i = 0; i != lineBorder + 1; i++) {
                        answer = reader.readLine();
                    }
                    reader.reset();
                    writer.write(String.format("Program: %s%s", answer, this.lSep));
                    System.out.println(answer);
                }
            }
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    /**
     * Main method of program.
     * @param args params from console
     */
    public static void main(String[] args) {
        new Chat().start();
    }
}
