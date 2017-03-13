package ru.dionisius.findText;

/**
 * Created by Dionisius on 30.11.2016.
 */
public class Find {
    /**
     * Flag that points true if text in some file was found.
     */
    public static boolean isFound = false;
    /**
     * The file with the searching text.
     */
    public static String fileWithText = null;
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
    private String textToFind;
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
    private final String[] availableKeys = {"-d", "-t", "-help"};
    /**
     * Help information.
     */
    private final String[] helpInfo = {"Данная программа производит поиск файла, содежащего заданный текст в заданном каталоге и его подкаталогах.",
            "Возможные ключи:",
            "-d - директория в которой начинается поиск в формате <диск>:\\<директория1>\\<директория2>...",
            "-t - искомый текст",
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
            if (this.areKeysValidate()) {
                this.setTextToFind();
                this.setWorkingDirectory();
                this.startSearch();
                if (isFound) {
                    System.out.printf("Text is founded in %s file", fileWithText);
                } else {
                    System.out.println("The text is not found");
                }
            } else {
                System.out.printf("Задан неверный ключ или параметр!%s " +
                                "Исользуйте ключ -help для получения подсказки.%s",
                        this.lsep, this.lsep);
            }
        } else {
            this.showInfo();
        }
    }

    /**
     * Starts searching the text in all files and directories of working directory.
     */
    private void startSearch() {
        Thread t = new Thread(new TextInDirectorySearcher(this.workingDirectory, this.textToFind));
        t.start();
        while (!isFound && t.isAlive()) {
            try {
                t.join(100);
                if (isFound) {
                    t.interrupt();
                    t.join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Specified  from console arguments the text that will be searched.
     */
    private void setTextToFind() {
        for (int i = 0; i < this.args.length; i++) {
            if ("-t".equals(this.args[i])) {
                this.textToFind = this.args[i + 1];
            }
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
        boolean areValidate =  true;
        int i = 0;
        while (i != this.args.length - 1) {
            if (this.args[i].startsWith("-")) {
                for (String key : this.availableKeys) {
                    if (!key.equalsIgnoreCase(this.args[i])) {
                        areValidate = false;
                    }
                }
            }
            if (!areValidate) {
                break;
            }
            i++;
        }
        return  areValidate;
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
            System.out.printf("Working directory is not specified. The current directory will be used: %s%s",
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
     * Starts this programm.
     * @param args arguments from console
     */
    public static void main(String[] args) {
        new Find(args).init();
    }
}