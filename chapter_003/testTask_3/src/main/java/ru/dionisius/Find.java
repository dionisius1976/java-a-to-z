package ru.dionisius;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Dionisius on 30.11.2016.
 */
public class Find {
    private final String[] args;
    private final String[] keys = {"-d", "-n", "-m", "-f", "-r", "-o", "-help"};
    private final String helpInfo = "Данная программа для поиска файлов заданном каталоге и подкаталогах.\r\n"+
                                    "Имя файла может задаваться, целиком, по маске, по регулярному выражени.\r\n"+
                                    "Возможные ключи: \n" +
                                    "-d - директория в которой начинается поиск.\n" +
                                    "-n - имя искомого файла, маски, либо регулярного выражения.\n" +
                                    "-m - искать по максимальному совпадению имени.\n" +
                                    "-f - искать по полному совпадению имени.\n" +
                                    "-r - регулярное выражение.\n" +
                                    "-o - имя файла, в который будет записан результат поиска.\r\n";


    public Find(String[] args) {
        this.args = args;
    }

    public void init() {

    }

    private String getWorkingDirectory (String[] args) {
        String workingDiretory = null;
        for (int i = 0; i < args.length; i++) {
            if ("-d".equals(args[i])) workingDiretory = args[i+1];
        }
        return workingDiretory;
    }

    private File[] getSubDirectories (File dir) {
        File subDirectories[] = null;
        int count = 0;
        for (int i = 0; i < dir.listFiles().length; i++) {
            if(dir.listFiles()[i].isDirectory()) {
                subDirectories[count] = dir.listFiles()[i];
                count++;
            }
        }
        return subDirectories;
    }

    private void writeLog(String pathName, String[] foundedFiles) {
        File file = new File(pathName);
        try {
            file.createNewFile();
            FileInputStream in = new FileInputStream(file);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

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