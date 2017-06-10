package ru.dionisius;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static junit.framework.TestCase.assertEquals;

/**
 * Class ChatTest for testing class Chat.
 * Created by Dionisius on 24.11.2016.
 */
public class ChatTest {
    /**
     * Output stream for tests.
     */
    private ByteArrayOutputStream out;
    /**
     * Prepares output stream for these tests.
     */
    @Before
    public void prepareOutputStream() {
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.out));
    }
    /**
     * Tests STOP command.
     * @throws IOException if IO exception occurs.
     */
    @Test
    public void whenEnterStopThenProgrammIsMute() throws IOException {
        String input = "стоп\r\nhi!\r\nbye!";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Chat().start();
        assertEquals(this.out.toString().isEmpty(), true);
    }
//    /**
//     * Tests CONTINUE command.
//     * @throws IOException if IO exception occurs
//     */
//    @Test
//    public void whenEnterContinueThenProgrammAnswers() throws IOException {
//        String cont = "стоп\r\nпродолжить\r\nbye!";
//        System.setIn(new ByteArrayInputStream(cont.getBytes()));
//        new Chat().controllers();
//        assertEquals(this.out.toString().isEmpty(), false);
//    }

//    /**
//     * Tests normal working mode of program.
//     * @throws IOException if IO exception occurs
//     */
//    @Test
//    public void whenEnterWordsThenProgrammAnswersRandomPhrasesFromTextFile() throws IOException {
//        String str = "hi!\r\nbye!\r\nnevermind!";
//        System.setIn(new ByteArrayInputStream(str.getBytes()));
//        new Chat().controllers();
//        assertEquals(this.out.toString().isEmpty(), false);
//    }

    /**
     * Tests FINISH command.
     * @throws IOException if IO exception occurs.
     */
    @Test
    public void whenEnterFinishThenProgrammTerminate() throws IOException {
        String finish = "закончить\r\nbye!\r\nnevermind!";
        System.setIn(new ByteArrayInputStream(finish.getBytes()));
        new Chat().start();
        assertEquals(true, this.out.toString().isEmpty());
    }
}