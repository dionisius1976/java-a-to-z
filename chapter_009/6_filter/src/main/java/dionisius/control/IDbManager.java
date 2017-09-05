package dionisius.control;

import dionisius.models.User;

import java.util.List;

/**
 * Created by Dionisius on 05.08.2017.
 * Interface for all managers that work
 * with user's database.
 * It can add new user, edit user's personal data,
 * delete specified user and find specified user.
 */
public interface IDbManager {
    /**
     * Creates new user.
     * @param userName user's name.
     * @param userLogin user's login.
     * @param userEmail user's email.
     */
    void createUser(final String userName, final String userLogin, final String userEmail);

    /**
     * Edits user's data.
     * @param userName user's name.
     * @param userLogin user's login.
     * @param newUserLogin new user's login.
     * @param newUserEmail new user's email.
     */
    void editUser(final String userName, final String userLogin,  final String newUserLogin, final String newUserEmail);

    /**
     * Delete specified user.
     * @param userName user's name.
     * @param userLogin user's login.
     */
    void deleteUser(final String userName, final String userLogin);

    /**
     * Returns specified user instance.
     * @param userName user's name.
     * @param userLogin user's login.
     * @return user instance.
     */
    User getUser(final String userName, final String userLogin);

    /**
     * Returns all users in one list.
     * @return list of all users.
     */
    List<User> getAllUsers();

    /**
     * Deletes all users from database.
     */
    void deleteAll();

    /**
     * Disconnects from database.
     */
    void disconnectDb();


}