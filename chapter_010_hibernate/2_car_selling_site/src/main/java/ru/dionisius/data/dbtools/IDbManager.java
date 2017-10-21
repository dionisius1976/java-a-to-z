package ru.dionisius.data.dbtools;

import ru.dionisius.data.pojo.Ad;
import ru.dionisius.data.pojo.User;

import java.util.List;
import java.util.Set;

/**
 * Created by Dionisius on 02.10.2017.
 */
public interface IDbManager {
    /**
     * Inserts specified advertisement to database.
     * @param newAd specified advertisement.
     * @return id of created advertisement.
     */
    long createAdvertisement(final Ad newAd);

    /**
     * Returns all advertisements from database.
     * @return all advertisements from database.
     */
    List<Ad> getAll();

    /**
     * Returns all actual advertisements from database.
     * @return all actual advertisements from database.
     */
    List<Ad> getActual();

    /**
     * Marks all advertisements in specified list as "sold".
     * @param ads specified list of advertisements.
     */
    void setSold(final Set<Ad> ads);

    /**
     * Finds user in database by specified login and password.
     * @param login users's login.
     * @param password user's password.
     * @return founded user.
     */
    User getUserByLoginAndPassword(final String login, final String password);

    /**
     * Inserts specified user to database.
     * @param user specified user.
     * @return inserted user's id.
     */
    long createUser(final User user);

    /**
     * Closes current using Hibernate SessionFactory.
     */
    void closeConnection();
}
