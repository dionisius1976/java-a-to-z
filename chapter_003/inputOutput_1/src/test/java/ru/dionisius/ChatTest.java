package ru.dionisius;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;

/**Class ChatTest for testing class Chat
 * Created by Dionisius on 24.11.2016.
 */
public class ChatTest {

    /**
     * File path for programm answers file
     */
    private  final String filePathAnswers = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "src\\main\\java\\ru\\dionisius\\chatFiles\\answers.txt");
    /**
     *File path for log file
     */
    private  final String filePathLog = String.format("%s%s%s", System.getProperty("user.dir"),
            File.separator, "src\\main\\java\\ru\\dionisius\\chatFiles\\log.txt");
    /**
     *Output stream for tests
     */
    private ByteArrayOutputStream out;
    /**
     * Stop command
     */
    private final String STOP = "стоп";
    /**
     * Terminate command
     */
    private final String FINISH = "закончить";
    /**
     * Continue command
     */
    private final String CONTINUE = "продолжить";

    /** prepareOutputStream()
     * This method prepears output stream
     * for these tests
     */
    @Before
    public void prepareOutputStream() {
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.out));
    }

    /**whenEnterStopThenProgrammIsMute().
     * Tests STOP command
     * @throws IOException
     */
    @Test
    public void whenEnterStopThenProgrammIsMute() throws IOException {
        String input = "стоп\r\nhi!\r\nbye!";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Chat(new File(this.filePathAnswers), new File(this.filePathLog)).start();
        assertEquals(this.out.toString().isEmpty(), true);
    }

    /**whenEnterContinueThenProgrammAnswers().
     * Tests CONTINUE command
     * @throws IOException
     */
    @Test
    public void whenEnterContinueThenProgrammAnswers() throws IOException {
        String input = "стоп\r\nпродолжить\r\nbye!";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Chat(new File(this.filePathAnswers), new File(this.filePathLog)).start();
        assertEquals(this.out.toString().isEmpty(), false);
    }

    /** whenEnterWordsThenProgrammAnswersRandomPhrasesFromTextFile().
     * Tests normal working mode of program
     * @throws IOException
     */
    @Test
    public void whenEnterWordsThenProgrammAnswersRandomPhrasesFromTextFile() throws IOException {
        String input = "hi!\r\nbye!\r\nnevermind!";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Chat(new File(this.filePathAnswers), new File(this.filePathLog)).start();
        assertEquals(this.out.toString().isEmpty(), false);
    }

    /**whenEnterFinishThenProgrammTerminate().
     * Tests FINISH command
     * @throws IOException
     */
    @Test
    public void whenEnterFinishThenProgrammTerminate() throws IOException {
        String input = "закончить\r\nbye!\r\nnevermind!";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        new Chat(new File(this.filePathAnswers), new File(this.filePathLog)).start();
        assertEquals(this.out.toString().isEmpty(), true);
    }
}