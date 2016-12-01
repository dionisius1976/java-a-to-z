package ru.dionisius;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by Dionisius on 30.11.2016.
 */
public class Find {
    /**
     * Buffer size to write in log file.
     */
    private final int BUFFER_SIZE = 1024;
    /**
     * Arguments from console.
     */
    private final String[] args;
    /**
     * Name of superior start directory to seek files.
     */
    private String workingDirectory;
    /**
     * File name or mask of files to find.
     */
    private String filesToFind;
    /**
     * Path to file to write log information.
     */
    private String logFile;
    /**
     * Available keys list.
     */
    private final String[] keys = {"-d", "-n", "-m", "-f", "-r", "-o", "-help"};
    /**
     * Help menu list.
     */
    private final String helpInfo = "Данная программа для поиска файлов заданном каталоге и подкаталогах.\r\n"
                                    + "Имя файла может задаваться, целиком, по маске, по регулярному выражени.\r\n"
                                    + "Возможные ключи: \n"
                                    + "-d - директория в которой начинается поиск.\n"
                                    + "-n - имя искомого файла, маски, либо регулярного выражения.\n"
                                    + "-m - искать по максимальному совпадению имени.\n"
                                    + "-f - искать по полному совпадению имени.\n"
                                    + "-r - регулярное выражение.\n"
                                    + "-o - путь и имя файла, в который будет записан результат поиска.\r\n";


    /**Find().
     * Constructor
     * @param args arguments from console
     */
    public Find(String[] args) {
        this.args = args;
    }

    /** init().
     * Initial method to start
     */
    public void init() {
        this.setFilesToFind();
        this.setLogFile();
        this.setWorkingDirectory();
        try (FileOutputStream fout = new FileOutputStream(this.workingDirectory)) {
            this.findFilesAndWriteLog(fout, this.workingDirectory, this.filesToFind);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** setFilesToFind().
     * Sets specified file name to find
     * using parsing argument from console
     */
    private void setFilesToFind() {
        String filesToFind = null;
        for (int i = 0; i < args.length; i++) {
            if ("-n".equals(args[i])) {
                filesToFind = args[i + 1];
            }
        }
        this.filesToFind = filesToFind;
    }

    /** setLogFile().
     * Sets specified log file path
     * using parsing argument from console
     */
    private void setLogFile() {
        String logFile = null;
        for (int i = 0; i < args.length; i++) {
            if ("-o".equals(args[i])) {
                logFile = args[i + 1];
            }
        }
        this.logFile = logFile;
    }

    /** setWorkingDirectory ().
     * Sets specified working superior start directory to
     * seek files using parsing argument from console
     */
    private void setWorkingDirectory() {
        String workingDiretory = null;
        for (int i = 0; i < this.args.length; i++) {
            if ("-d".equals(this.args[i])) {
                workingDiretory = this.args[i + 1];
            }
        }
        this.workingDirectory = workingDiretory;
    }

    /** findFilesAndWriteLog().
     * This method find all files that math the specified
     * file name in specified directory and all subdirectiries
     * @param fout outputstream to write seek results
     * @param currentDir name of superior start directory to seek files
     * @param filesToFind file name or mask of files to find
     * @throws IOException when exception while writing log file
     */
    private void findFilesAndWriteLog(FileOutputStream fout, String currentDir, String filesToFind) throws IOException {
        File[] listFiles = new File(currentDir).listFiles();
        for (int i = 0; i < listFiles.length; i++) {
            if (!listFiles[i].isDirectory() && this.filesToFind.equals(listFiles[i])) {
                InputStream in = new ByteArrayInputStream(String.format("%s\\%s\r\n", listFiles[i].getAbsolutePath(),
                        listFiles[i].getName()).getBytes());
                byte[] buffer = new byte[BUFFER_SIZE];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    fout.write(buffer, 0, length);
                    fout.flush();
                }
                in.close();
            } else {
                this.findFilesAndWriteLog(fout, listFiles[i].getAbsolutePath(), filesToFind);
            }
        }
    }

    /**main().
     * This method starts this programm
     * @param args arguments from console
     */
    public static void main(String[] args) {
        new Find(args).init();
    }
}

//1. Создать программу для поиска файла.
//        2. Программа должна искать данные в заданном каталоге и подкаталогах.
//        3. Имя файла может задаваться, целиком, по маске, по регулярному выражение(не обязательно).
//        4. Программа должна собираться в jar и запускаться через java -jar find.jar -d c:/ -n *.txt -m -o log.txt
//        Ключи
//        -d - директория в которая начинать поиск.
//        -n - имя файл, маска, либо регулярное выражение.
//        -m - искать по макс, либо -f - полное совпадение имени. -r регулярное выражение.
//        -o - результат записать в файл.
//        5. Программа должна записывать результат в файл.
//        6. В программе должна быть валидация ключей и подсказка.