package ru.dionisius.data;

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

    void setSold(final Set<Ad> ads);

    User getUserById (final long id);
    User getUserByLoginAndPassword(final String login, final String password);

    long createUser(final User user);


    void closeConnection();
}
