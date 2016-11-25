package ru.dionisius;

import org.junit.Test;

import java.io.File;
import java.io.RandomAccessFile;

import static org.junit.Assert.*;

/**
 * Created by Dionisius on 24.11.2016.
 */
public class ChatTest {
    @Test
    public void start() throws Exception {
        File answersFile = File.createTempFile("answers", ".txt");
        File logFile = File.createTempFile("log", ".txt");
        answersFile.deleteOnExit();
        logFile.deleteOnExit();
        RandomAccessFile rafAnswersFile = new RandomAccessFile(answersFile, "rw");
       // RandomAccessFile rafLogFile = new RandomAccessFile(logFile, "rw");
        String[] phrases = {"Привет!", "Как дела?", "Повторите, не понял.", "Именно это я и хотел от Вас узнать.",
                "Прекрасная погода, не правда ли?", "Пора валить.","Всё пропало."};
        for(String line: phrases) {
            rafAnswersFile.writeBytes(String.format("%s\r\n", line));
        }
        Chat chat = new Chat(answersFile, logFile);
        chat.start();
    }

}