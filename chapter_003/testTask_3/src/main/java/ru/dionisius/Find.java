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
     * Maximum count of file mask parts separated by '*'.
     */
    private static final int MAX_MASK_PARTS_COUNT = 50;
    /**
     * Buffer size to write in log file.
     */
    private static final int BUFFER_SIZE = 1024;
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
    private final String[] availableKeys = {"-d", "-n", "-m", "-f", "-r", "-o", "-help"};
    /**
     * Help menu list.
     */
    private final String helpInfo = "\nДанная программа производит поиск файлов заданном каталоге и подкаталогах.\n"
                                    + "Имя файла может задаваться, целиком или  по маске.\n"
                                    + "Возможные ключи:\n"
                                    + "-d - директория в которой начинается поиск.\n"
                                    + "-n - имя искомого файла или маски\n"
                                    + "-m - искать по максимальному совпадению имени.\n"
                                    + "-f - искать по полному совпадению имени.\n"
                                    + "-r - регулярное выражение.\n"
                                    + "-o - путь и имя файла, в который будет записан результат поиска.\n"
                                    + "-help - помощь.\n";

    /**Find().
     * Constructor
     * @param args arguments from console
     */
    public Find(String[] args) {
        this.args = args;
    }

    /** init().
     * Initial method to start searching
     */
    public void init() {
        if (!this.isKeyHelp()) {
            if (this.areKeysValidate() && this.filesToFind != null) {
                this.initiateFields();
                try (FileOutputStream fout = new FileOutputStream(this.workingDirectory)) {
                    if (!this.isLogFileMask()) {
                        this.findFilesAndWriteLog(fout, this.workingDirectory, this.filesToFind);
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.printf("Задан неверный ключ или параметр!\nИсользуйте ключ -help для получения подсказки.");
            }
        } else {
            System.out.printf(this.helpInfo);
        }
    }

    /**areKeysValidate().
     * This method checks if keys specified in console input are validate
     * @return true if keys specified in console input is validate and false if not
     */
    private boolean areKeysValidate() {
        boolean areValidate =  false;
        if (this.args != null && this.args.length != 0) {
            String[] argsKeys = new String[this.availableKeys.length];
            int count = 0;
            for (int i = 0; i < this.args.length; i++) {
                if (this.args[i].startsWith("-")) {
                    argsKeys[count] = this.args[i];
                    count++;
                }
            }
            for (int i = 0; i < this.args.length; i++) {
                for (int j = 0; j < this.availableKeys.length; j++) {
                    if (argsKeys[i] == null) {
                        continue;
                    }
                    if (argsKeys[i].equals(this.availableKeys[j])) {
                        areValidate = true;
                    }
                }
                if (!areValidate) {
                    break;
                } else  {
                    areValidate = false;
                }
            }
        }
        return  areValidate;
    }

    /**initiateFields().
     * This metod initiates fields filesToFind, logFile
     * and workingDirectory using keys specified in console input
     */
    private void initiateFields() {
        this.setFilesToFind();
        this.setLogFile();
        this.setWorkingDirectory();
    }

    /** setFilesToFind().
     * Sets specified file name to find
     * using parsing argument from console
     */
    private void setFilesToFind() {
        String filesToFind = null;
        for (int i = 0; i < this.args.length; i++) {
            if ("-n".equals(args[i]) && !this.args[i + 1].startsWith("-")) {
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
        for (int i = 0; i < this.args.length; i++) {
            if ("-o".equals(args[i]) && !this.args[i + 1].startsWith("-")) {
                logFile = args[i + 1];
            }
        }
        if (logFile == null) {
            File file = new File(String.format("%s%s%s", System.getProperty("user.dir"), File.separator, "log.txt"));
            System.out.printf("Имя файла для записи результата поиска не задано.\nПо умолчанию результаты поиска будут "
                            + "записаны в файл: %s\\%s\n", file.getAbsolutePath(), file.getName());
            logFile = String.format("%s\\%s", file.getAbsolutePath(), file.getName());
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
            if ("-d".equals(this.args[i]) && !this.args[i + 1].startsWith("-")) {
                workingDiretory = this.args[i + 1];
            }
        }
        if (workingDiretory == null) {
            workingDiretory = System.getProperty("user.dir");
            System.out.printf("Директория не задана. По умолчанию используется текущая директория %s\n", workingDiretory);
        }
        this.workingDirectory = workingDiretory;
    }

    /**isHelp().
     * This method checks if key "-help" presents in
     * console input
     * @return true if presents, false if not
     */
    private boolean isKeyHelp() {
        boolean isHelp = false;
        for (String key: this.args) {
            if ("-help".equals(key)) {
                isHelp = true;
            }
        }
        return isHelp;
    }

    private String[] splitMask(String mask) {
        String[] maskParts = new String[MAX_MASK_PARTS_COUNT];
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.logFile.length(); i++) {
            if((this.logFile.charAt(i) != '*' && count == 0) ||  this.logFile.charAt(i) != '.') {
                continue;
            }
            if (this.logFile.charAt(i) != '*') {
                sb.append(this.logFile.charAt(i));
            } else {
                maskParts[count] = sb.toString();
                count++;
                sb = new StringBuilder();
            }
        }
        String[] maskPartsWithoutNulls = null;
        System.arraycopy(maskParts, 0, maskPartsWithoutNulls, 0, count);
        return maskPartsWithoutNulls;
    }

    private boolean isLogFileMask() {
        boolean isMask = false;
        for (int i = 0; i < this.logFile.length(); i++) {
            if (this.logFile.charAt(i) == '*') {
                isMask = true;
            }
        }
        return isMask;
    }

    /** findFilesAndWriteLog().
     * This method find all files that match the specified
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