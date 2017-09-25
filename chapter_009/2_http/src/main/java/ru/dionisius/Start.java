package ru.dionisius;

import ru.dionisius.control.DbManager;
import ru.dionisius.control.IDbManager;

/**
 * Created by Dionisius on 12.08.2017.
 */
public class Start {
    public static void main(String[] args) {
        IDbManager manager = new DbManager("config.properties");
//        manager.createUser("Feodor", "111", "f@gmail.com");
        System.out.println(manager.getUser("Feodor", "111"));
        manager.deleteUser("Feodor", "111");
    }
}
