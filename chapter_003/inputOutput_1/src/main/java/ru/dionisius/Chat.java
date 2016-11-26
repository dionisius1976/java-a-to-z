package ru.dionisius;

import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

/**
 * Created by Dionisius on 24.11.2016.
 */
public class Chat {

    /**
     * Stop command.
     */
    private final String STOP = "стоп";

    /**
     * Terminate command.
     */
    private final String FINISH = "закончить";

    /**
     * Continue command.
     */
    private final String CONTINUE = "продолжить";

    /**
     * Inputed user phrase.
     */
    private String userPhrase = "";

    /**
     * File object with answers.
     */
    private File answersFile;

    /**
     * File object for log.
     */
    private File logFile;

    /**
     * Random answer phrase from answer file.
     */
    private String answer;

    /**
     * Random object to generate random choose of answer string sron answer file.
     */
    private Random rand;

    /**
     * Boolean swincher for mute mode for answers.
     */
    private boolean mute;

    /**Chat(File answersFile, File logFile).
     * Constructor
     * @param answersFile - file with answers strings of programm
     * @param logFile - file for logging dialog between user and program
     */
    public Chat(File answersFile, File logFile) {
        this.answersFile = answersFile;
        this.logFile = logFile;
    }

    /**start().
     *  This method starts the chat between user and program
     *  and writes their dialogue in log file
     */
    public void start() {
        int answersNumber = 0;
        rand = new Random();
        mute = false;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.logFile));
             Scanner scanner = new Scanner(System.in);
             BufferedReader reader = new BufferedReader(new FileReader(this.answersFile))) {
            reader.mark(200);
            while (reader.readLine() != null) {
                answersNumber++;
            }
            reader.reset();
            while (scanner.hasNext()) {
                userPhrase = scanner.nextLine();
                writer.write(String.format("%s%s\r\n", "User: ", userPhrase));
                if ((userPhrase.toLowerCase()).equals(STOP)) {
                    mute = true;
                }
                if ((userPhrase.toLowerCase()).equals(CONTINUE)) {
                    mute = false;
                }
                if ((userPhrase.toLowerCase()).equals(FINISH)) {
                    break;
                }
                if (!mute) {
                    int lineBorder = new Random(System.currentTimeMillis()).nextInt(answersNumber);
                    for (int i = 0; i != lineBorder + 1; i++) {
                        answer = reader.readLine();
                    }
                    reader.reset();
                    writer.write(String.format("%s%s\r\n", "Programm: ", answer));
                    System.out.println(answer);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /** main method of program.
     * @param args params from console
     */
    public static void main(String[] args) {
        new Chat(new File(String.format("%s%s%s", System.getProperty("user.dir"),
                File.separator, "chapter_003\\inputOutput_1\\src\\main\\java\\ru\\dionisius\\chatFiles\\answers.txt")),
                new File(String.format("%s%s%s", System.getProperty("user.dir"),
                        File.separator, "chapter_003\\inputOutput_1\\src\\main\\java\\ru\\dionisius\\chatFiles\\log.txt"))).start();
    }
}
