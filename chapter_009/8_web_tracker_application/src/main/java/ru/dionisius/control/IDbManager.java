package ru.dionisius.control;

import ru.dionisius.model.Address;
import ru.dionisius.model.MusicType;
import ru.dionisius.model.Role;
import ru.dionisius.model.User;

import java.util.List;

/**
 * Created by Dionisius on 09.09.2017.
 */
public interface IDbManager {

    /**
     * @param userName
     * @param userSurname
     * @param userlogin
     * @param userPassword
     * @param userAddress
     * @param userRole
     * @param userMusicTypeList
     * @return
     */
    String createUser (final String userName, final String userSurname, final String userlogin,
                       final String userPassword, final Address userAddress, final Role userRole,
                       final List<MusicType> userMusicTypeList);

    /**
     * @return
     */
    List<User> getAllUsers ();

    /**
     * @param id
     * @return
     */
    User getUserById (final String id);

    /**
     * @param role
     * @return
     */
    List<User> getUserByRole (final Role role);

    /**
     * @param userLogin
     * @return
     */
   User getUserIdByLoginAndPassword(final String userLogin, final String userPassword);

    /**
     * @param userId
     */
    void deleteUser(final String userId);

    /**
     * Deletes all users from database.
     */
    void deleteAllUsers();

    void updateUser(final String oldUserId, final String userName, final String userSurname,
                    final String userlogin, final String userPassword, final Address userAddress,
                    final Role userRole, final List<MusicType> userMusicTypeList);
}
