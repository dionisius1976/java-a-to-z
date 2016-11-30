package ru.dionisius;

import ru.dionisius.server.ServerSocket;

import java.io.File;

/**
 * Created by Dionisius on 29.11.2016.
 */
public class StartClientServer {
    public static void main(String[] args) {
        File file = new File((String.format("%s%s%s", System.getProperty("user.dir"),
                File.separator, "chapter_003\\socket_2\\src\\main\\java\\ru\\dionisius\\config.properties")));
        if (!file.exists()) {
            PropertiesSetter.setProperties();
        }
        new ServerSocket(file);
    }
}



