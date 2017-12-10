package ru.dionisius;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Dionisius on 06.03.2017.
 */
public class Cash extends ACash {
    /**
     * Specified file with ru.dionisius.ru.dionisius.data.
     */
    private final String fileName;

    /**
     * Constructor.
     * @param fileName specified file with ru.dionisius.ru.dionisius.data.
     */
    public Cash(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected String getData() {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
            FileInputStream input = new FileInputStream(this.fileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        }  catch (FileNotFoundException fnf) {
            fnf.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
