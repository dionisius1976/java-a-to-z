package ru.dionisius;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Dionisius on 24.11.2016.
 */
public class Chat {
    private final String STOP = "стоп";
    private final String FINISH = "закончить";
    private final String CONTINUE = "продолжить";
    private String userPhrase = "";
    private File answersFile;
    private File logFile;
    RandomAccessFile rafAnswerFile;
    RandomAccessFile rafLogFile;
    private Scanner scanner;
    private String answer;
    private Random rand;
    private boolean mute;

    public Chat(File answersFile, File logFile) {
        this.answersFile = answersFile;
        this.logFile = logFile;
    }

    public void start() {
        int answersNumber = 0;
        rand = new Random();
        mute = false;
        scanner = new Scanner(System.in);
        try {
            rafAnswerFile = new RandomAccessFile(answersFile, "r");
            rafLogFile = new RandomAccessFile(logFile, "rw");
            while(rafAnswerFile.readLine() != null) {
                answersNumber++;
            }
            rafAnswerFile.seek(0);
            while (!userPhrase.equals(FINISH.toLowerCase())) {
                System.out.print("Введите фразу: ");
                userPhrase = scanner.nextLine();
                rafLogFile.writeBytes(String.format("%s%s\r\n", "User: ", userPhrase));
                if (userPhrase.equals(STOP.toLowerCase())) mute = true;
                if (userPhrase.equals(CONTINUE.toLowerCase())) mute = false;
                if (userPhrase.equals(FINISH.toLowerCase())) continue;
                if (!mute) {
                    int lineBorder = new Random(System.currentTimeMillis()).nextInt(answersNumber);
                    for (int i = 0; i != lineBorder; i++) {
                        answer = rafAnswerFile.readLine().toString();
                    }
                    rafAnswerFile.seek(0);
                    rafLogFile.writeBytes(String.format("%s%s\r\n", "Programm: ", answer));
                    System.out.println(answer);
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String filePathAnswers = String.format("%s%s%s", System.getProperty("user.dir"),
                File.separator, "chapter_003\\inputOutput_1\\src\\main\\java\\ru\\dionisius\\chatFiles\\answers.txt");
        String filePathLog = String.format("%s%s%s", System.getProperty("user.dir"),
                File.separator, "chapter_003\\inputOutput_1\\src\\main\\java\\ru\\dionisius\\chatFiles\\log.txt");
        new Chat(new File(filePathAnswers), new File(filePathLog)).start();
    }
}
