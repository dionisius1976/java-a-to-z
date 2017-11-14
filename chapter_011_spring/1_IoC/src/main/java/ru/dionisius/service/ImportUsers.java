package ru.dionisius.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dionisius.data.models.User;
import ru.dionisius.data.storages.UserStorage;

/**
 * Created by Dionisius on 28.10.2017.
 * Starts this application.
 */
public class ImportUsers {

    /**
     * Method that starts application.
     * @param args console arguments.
     */
    public static void main(String[] args) {
        User firstUser = new User("1", "1", "Ivan", "Karamazov", "+79211112233");
        User secondUser = new User("2", "2", "Tatiana", "Filgingauer", "+79112225566");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");

        UserStorage us = context.getBean(UserStorage.class);

        us.addUser(firstUser);
        us.addUser(secondUser);
        System.out.println(us.getAllUsers());
    }
}
