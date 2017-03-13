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
    private static final int BUFFER_SIZE = 1024;
    /**
     * Arguments from console.
     */
    private final String[] args;
    /**
     * Name of starting directory to seek file.
     */
    private String workingDirectory;
    /**
     * File name  to find.
     */
    private String fileToFind;
    /**
     * Path to log file to write log information.
     */
    private String logFile;
    /**
     * Current operating system line separator.
     */
    private final String lsep = System.getProperty("line.separator");
    /**
     * Current operating system file separator in path name.
     */
    private final String fsep = System.getProperty("file.separator");
    /**
     * Available keys list.
     */
    private final String[] availableKeys = {"-d", "-n", "-o", "-help"};
    /**
     * Help information.
     */
    private final String[] helpInfo = {"Данная программа производит поиск файлов с заданным именем в заданном каталоге и подкаталогах.",
            "Возможные ключи:",
            "-d - директория в которой начинается поиск в формате <диск>:\\<директория1>\\<директория2>...",
            "-n - имя искомого файла",
            "-o - путь и имя файла, в который будет записан результат поиска в формате "
                    + "<диск>:\\<директория1>\\<директория2>...\\<директорияN>\\<имя_файла>",
            "-help - помощь."};

    /**
     * Constructor.
     * @param args array of arguments from console
     */
    public Find(final String[] args) {
        this.args = args;
    }

    /** init().
     * Initial method to start searching
     */
    public void init() {
        if (!this.hasKeyHelp()) {
            this.setFileToFind();
            if (this.areKeysValidate() && this.fileToFind != null) {
                this.setWorkingDirectory();
                this.setLogFile();
                try (FileOutputStream fout = new FileOutputStream(this.logFile)) {
                        this.findFilesAndWriteLog(fout, this.workingDirectory);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.printf("Задан неверный ключ или параметр!%sИсользуйте ключ -help для получения подсказки.%s",
                        this.lsep, this.lsep);
            }
        } else {
            this.showInfo();
        }
    }
    /**
     * Shows info about this program and using keys.
     */
    private void showInfo() {
        for (String line: this.helpInfo) {
            System.out.println(line);
        }
    }
    /**areKeysValidate().
     * This method checks if keys specified in console input are validate
     * @return true if keys specified in console input is validate and false if not
     */
    private boolean areKeysValidate() {
        boolean areValidate =  false;
        for (int i = 0; i < this.args.length; i++) {
            for (int j = 0; j < this.availableKeys.length; j++) {
                if (this.args[i].startsWith("-")) {
                    if (this.args[i].equals(this.availableKeys[j])) {
                        areValidate = true;
                        break;
                    } else {
                        areValidate = false;
                    }
                }
            }
            if (!areValidate) {
                break;
            }
        }
        return  areValidate;
    }
       /** setFilesToFind().
     * Sets specified file name to find
     */
    private void setFileToFind() {
        String file = null;
        for (int i = 0; i < this.args.length; i++) {
            if ("-n".equals(args[i]) && !(this.args[i + 1].startsWith("-"))) {
                file = args[i + 1];
            }
        }
        this.fileToFind = file;
    }
    /** setLogFile().
     * Sets specified log file path
     */
    private void setLogFile() {
        String logFile = null;
        for (int i = 0; i < this.args.length; i++) {
            if ("-o".equals(args[i]) && !(this.args[i + 1].startsWith("-"))) {
                logFile = args[i + 1];
            }
        }
        if (logFile == null) {
            File file = new File(String.format("%s%s%s", System.getProperty("user.dir"), this.fsep, "log.txt"));
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.printf("Имя файла для записи результата поиска не задано.%sПо умолчанию результаты поиска будут "
                            + "записаны в файл: %%s%s", this.lsep, file.getAbsolutePath(), this.lsep);
            logFile = file.getAbsolutePath();
        }
        this.logFile = logFile;
    }
    /** setWorkingDirectory ().
     * Sets specified working superior start directory to seek
     */
    private void setWorkingDirectory() {
        String workingDiretory = null;
        for (int i = 0; i < this.args.length; i++) {
            if ("-d".equals(this.args[i]) && !(this.args[i + 1].startsWith("-"))) {
                workingDiretory = this.args[i + 1];
            }
        }
        if (workingDiretory == null) {
            workingDiretory = System.getProperty("user.dir");
            System.out.printf("Директория не задана. По умолчанию используется текущая директория %s%s",
                    workingDiretory, this.lsep);
        }
        this.workingDirectory = workingDiretory;
    }
    /**isHelp().
     * Checks if key "-help" presents in console input
     * @return true if presents, false if not
     */
    private boolean hasKeyHelp() {
        boolean isHelp = false;
        for (String key: this.args) {
            if ("-help".equals(key)) {
                isHelp = true;
                break;
            }
        }
        return isHelp;
    }
    /**
     * findFilesAndWriteLog().
     * Seek for all files that match the specified
     * file name in specified directory and all subdirectiries
     * @param out outputstream to write seek results
     * @param currentDir name of superior start directory to seek files
     * @throws IOException when exception while writing log file
     */
    private void findFilesAndWriteLog(final FileOutputStream out, final String currentDir) throws IOException {
        StringBuilder sb = new StringBuilder();
        File[] listFiles = new File(currentDir).listFiles();
        if (listFiles != null) {
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles != null && listFiles[i] != null && !(listFiles[i].isDirectory())
                        && this.fileToFind.equals(listFiles[i].getName())) {
                    sb.append(String.format("%s%s%s%s", listFiles[i].getAbsolutePath(),
                            this.fsep, listFiles[i].getName(), this.lsep));
                } else {
                    this.findFilesAndWriteLog(out, listFiles[i].getAbsolutePath());
                }
            }
            if (sb.toString().trim() != "") {
                this.writeToLog(out, sb.toString());
            }
        }
    }
    /**
     * Writes the absolute paths for found files in the log file.
     * @param out stream to write log file.
     * @param filesList list of the files that should be written in log file
     * @throws IOException if error while writing the file
     */
    private void writeToLog(final FileOutputStream out, final String filesList) throws IOException {
        InputStream in = new ByteArrayInputStream(filesList.getBytes());
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
            out.flush();
        }
        in.close();
    }
     /**
     * Starts this programm.
     * @param args arguments from console
     */
    public static void main(String[] args) {
        new Find(args).init();
    }
}