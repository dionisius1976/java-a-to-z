package ru.dionisius;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by Dionisius on 29.11.2016.
 */
public class PropertiesSetter {
    private static final Properties prop = new Properties();;
    private static OutputStream output = null;

    public static void setProperties() {
        try {
            File file = new File(String.format("%s%s%s", System.getProperty("user.dir"),
                    File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\config.properties"));
            file.createNewFile();
            output = new FileOutputStream(String.format("%s%s%s", System.getProperty("user.dir"),
                    File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\config.properties"));
            prop.setProperty("server_port", "5000");
            prop.setProperty("ip_address", "127.0.0.1");
            prop.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
