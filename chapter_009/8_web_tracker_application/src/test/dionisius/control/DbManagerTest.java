package ru.dionisius.control;

import ru.dionisius.model.Address;
import ru.dionisius.model.MusicType;
import ru.dionisius.model.Role;
import ru.dionisius.model.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dionisius on 12.09.2017.
 * Tests DbManager class methods:
 *
 * String createUser (final String userName, final String userSurname, final String userlogin,
 *      final String userPassword, final Address userAddress, final Role userRole,
 *      final List<MusicType> userMusicTypeList);
 *
 * List<User> getAllUsers ();
 *
 * User getUserById (final String id);
 *
 * User getUserByAddress (final Address address);
 *
 * List<User> getUserByRole (final Role role);
 *
 * User getUserIdByLogin(final String userLogin);
 *
 * void deleteUser(final String userId);
 *
 * void deleteAllUsers();
 *
 * void updateUser(final String oldUserId, final String userName, final String userSurname,
 *      final String userlogin, final String userPassword, final Address userAddress,
 *      final Role userRole, final List<MusicType> userMusicTypeList);
 */
public class DbManagerTest {
    final String user1Name = "Ivan";
    final String user2Name = "Alexey";
    final String user1Surname = "Petrov";
    final String user2Surname = "Sidorov";
    final String user1login = "aaa";
    final String user2login = "bbb";
    final String user1Password = "777";
    final String user2Password = "hello";
    final Address user1Address = new Address("191000", "Russia",
            "Moskow", "Pushkina", "15", "176");
    final Address user2Address = new Address("185678", "Belorus",
            "Minsk", "Lukashenko", "17/7", "88");
    final Address user3Address = new Address("222222", "Germany",
            "Berlin", "RottenBluemen str.", "122", "71");
    final Role user1Role = Role.ADMIN;
    final Role user2Role = Role.USER;
    final List<MusicType> user1MusicTypeList = new LinkedList<>();
    final List<MusicType> user2MusicTypeList = new LinkedList<>();
    private final IDbManager manager = DbManager.getInstance();
    private String createdUser1Id = null;
    private String createdUser2Id = null;

    /**
     * creates first user.
     */
    public void createUser1() {
        this.user1MusicTypeList.clear();
        this.user1MusicTypeList.add(MusicType.PUNK);
        this.user1MusicTypeList.add(MusicType.ROCK);
        createdUser1Id = this.manager.createUser(this.user1Name, this.user1Surname, this.user1login,
                this.user1Password, this.user1Address, this.user1Role, this.user1MusicTypeList);
    }

    /**
     * Creates second user.
     */
    public void createUser2() {
        this.user2MusicTypeList.clear();
        this.user2MusicTypeList.add(MusicType.DISCO);
        this.user2MusicTypeList.add(MusicType.JAZZ);
        this.user2MusicTypeList.add(MusicType.ROCK);
        this.createdUser2Id = this.manager.createUser(this.user2Name, this.user2Surname, this.user2login,
                this.user2Password, this.user2Address, this.user2Role, this.user2MusicTypeList);
    }


    /**
     * Creates second user with the first user's address.
     */
    public void createUser2WithUser1Address() {
        this.user2MusicTypeList.clear();
        this.user2MusicTypeList.add(MusicType.DISCO);
        this.user2MusicTypeList.add(MusicType.SOUL);
        this.createdUser2Id = this.manager.createUser(this.user2Name, this.user2Surname, this.user2login,
                this.user2Password, this.user1Address, this.user2Role, this.user2MusicTypeList);
    }

    /**
     * Creates second user with with the first user's login and password.
     */
    public void createUser2WithUser1LoginAndUser1Password() {
        this.user2MusicTypeList.clear();
        this.user2MusicTypeList.add(MusicType.DISCO);
        this.user2MusicTypeList.add(MusicType.JAZZ);
        this.user2MusicTypeList.add(MusicType.ROCK);
        this.createdUser2Id = this.manager.createUser(this.user2Name, this.user2Surname, this.user1login,
                this.user1Password, this.user2Address, this.user2Role, this.user2MusicTypeList);
    }

    /**
     * Tests createUser() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenCreateUserInEmptyDBThenOnlyThisUserIsInDB() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        User createdUser = this.manager.getUserById(this.createdUser1Id);
        Assert.assertEquals(1, this.manager.getAllUsers().size());
        Assert.assertEquals(createdUser.getName(), this.user1Name);
        Assert.assertEquals(createdUser.getLogin(), this.user1login);
        Assert.assertEquals(createdUser.getPassword(), this.user1Password);
        Assert.assertEquals(createdUser.getAddress().getZip_code(), this.user1Address.getZip_code());
        Assert.assertEquals(createdUser.getAddress().getCountry(), this.user1Address.getCountry());
        Assert.assertEquals(createdUser.getAddress().getCity(), this.user1Address.getCity());
        Assert.assertEquals(createdUser.getAddress().getStreet(), this.user1Address.getStreet());
        Assert.assertEquals(createdUser.getAddress().getHouse(), this.user1Address.getHouse());
        Assert.assertEquals(createdUser.getAddress().getFlat(), this.user1Address.getFlat());
        Assert.assertEquals(createdUser.getRole(), this.user1Role);
        Assert.assertEquals(createdUser.getMusicTypes(), this.user1MusicTypeList);
    }

    /**
     * Tests createUser() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenCreateTwoEqualUsersInEmptyDBThenOnlyOneUserIsInDB() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        this.createUser1();
        User createdUser = this.manager.getUserById(this.createdUser1Id);
        Assert.assertEquals(1, this.manager.getAllUsers().size());
        Assert.assertEquals(createdUser.getName(), this.user1Name);
        Assert.assertEquals(createdUser.getLogin(), this.user1login);
        Assert.assertEquals(createdUser.getPassword(), this.user1Password);
        Assert.assertEquals(createdUser.getAddress().getZip_code(), this.user1Address.getZip_code());
        Assert.assertEquals(createdUser.getAddress().getCountry(), this.user1Address.getCountry());
        Assert.assertEquals(createdUser.getAddress().getCity(), this.user1Address.getCity());
        Assert.assertEquals(createdUser.getAddress().getStreet(), this.user1Address.getStreet());
        Assert.assertEquals(createdUser.getAddress().getHouse(), this.user1Address.getHouse());
        Assert.assertEquals(createdUser.getAddress().getFlat(), this.user1Address.getFlat());
        Assert.assertEquals(createdUser.getRole(), this.user1Role);
        Assert.assertEquals(createdUser.getMusicTypes(), this.user1MusicTypeList);
    }

    /**
     * Tests createUser() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenCreateTwoUsersWithTheSameAddressesInEmptyDBThenThisTwoUsersAreInDB() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        this.createUser2WithUser1Address();
        User createdUser = this.manager.getAllUsers().get(0);
        User createdUser2 = this.manager.getAllUsers().get(1);
        Assert.assertEquals(2, this.manager.getAllUsers().size());
        Assert.assertEquals(createdUser.getName(), this.user1Name);
        Assert.assertEquals(createdUser.getLogin(), this.user1login);
        Assert.assertEquals(createdUser.getPassword(), this.user1Password);
        Assert.assertEquals(createdUser.getAddress().getZip_code(), this.user1Address.getZip_code());
        Assert.assertEquals(createdUser.getAddress().getCountry(), this.user1Address.getCountry());
        Assert.assertEquals(createdUser.getAddress().getCity(), this.user1Address.getCity());
        Assert.assertEquals(createdUser.getAddress().getStreet(), this.user1Address.getStreet());
        Assert.assertEquals(createdUser.getAddress().getHouse(), this.user1Address.getHouse());
        Assert.assertEquals(createdUser.getAddress().getFlat(), this.user1Address.getFlat());
        Assert.assertEquals(createdUser.getRole(), this.user1Role);
        Assert.assertEquals(createdUser.getMusicTypes(), this.user1MusicTypeList);

        Assert.assertEquals(createdUser2.getName(), this.user2Name);
        Assert.assertEquals(createdUser2.getLogin(), this.user2login);
        Assert.assertEquals(createdUser2.getPassword(), this.user2Password);
        Assert.assertEquals(createdUser2.getAddress().getZip_code(), this.user1Address.getZip_code());
        Assert.assertEquals(createdUser2.getAddress().getCountry(), this.user1Address.getCountry());
        Assert.assertEquals(createdUser2.getAddress().getCity(), this.user1Address.getCity());
        Assert.assertEquals(createdUser2.getAddress().getStreet(), this.user1Address.getStreet());
        Assert.assertEquals(createdUser2.getAddress().getHouse(), this.user1Address.getHouse());
        Assert.assertEquals(createdUser2.getAddress().getFlat(), this.user1Address.getFlat());
        Assert.assertEquals(createdUser2.getRole(), this.user2Role);
        Assert.assertEquals(createdUser2.getMusicTypes(), this.user2MusicTypeList);

    }

    /**
     * Tests createUser() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenCreateTwoUsersInEmptyDBWithTheSameLoginAndPasswordThenTheSecondUserIsNotInDB() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        this.createUser2WithUser1LoginAndUser1Password();
        User createdUser = this.manager.getUserById(this.createdUser1Id);
        Assert.assertEquals(1, this.manager.getAllUsers().size());
        Assert.assertEquals(createdUser.getName(), this.user1Name);
        Assert.assertEquals(createdUser.getLogin(), this.user1login);
        Assert.assertEquals(createdUser.getPassword(), this.user1Password);
        Assert.assertEquals(createdUser.getAddress().getZip_code(), this.user1Address.getZip_code());
        Assert.assertEquals(createdUser.getAddress().getCountry(), this.user1Address.getCountry());
        Assert.assertEquals(createdUser.getAddress().getCity(), this.user1Address.getCity());
        Assert.assertEquals(createdUser.getAddress().getStreet(), this.user1Address.getStreet());
        Assert.assertEquals(createdUser.getAddress().getHouse(), this.user1Address.getHouse());
        Assert.assertEquals(createdUser.getAddress().getFlat(), this.user1Address.getFlat());
        Assert.assertEquals(createdUser.getRole(), this.user1Role);
        Assert.assertEquals(createdUser.getMusicTypes(), this.user1MusicTypeList);
    }

    /**
     * Tests getAllUsers() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenGetAllUsersThenExpectedUsersAreReturned() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        this.createUser2();
        User createdUser = this.manager.getAllUsers().get(0);
        User createdUser2 = this.manager.getAllUsers().get(1);
        Assert.assertEquals(2, this.manager.getAllUsers().size());
        Assert.assertEquals(createdUser.getName(), this.user1Name);
        Assert.assertEquals(createdUser.getLogin(), this.user1login);
        Assert.assertEquals(createdUser.getPassword(), this.user1Password);
        Assert.assertEquals(createdUser.getAddress().getZip_code(), this.user1Address.getZip_code());
        Assert.assertEquals(createdUser.getAddress().getCountry(), this.user1Address.getCountry());
        Assert.assertEquals(createdUser.getAddress().getCity(), this.user1Address.getCity());
        Assert.assertEquals(createdUser.getAddress().getStreet(), this.user1Address.getStreet());
        Assert.assertEquals(createdUser.getAddress().getHouse(), this.user1Address.getHouse());
        Assert.assertEquals(createdUser.getAddress().getFlat(), this.user1Address.getFlat());
        Assert.assertEquals(createdUser.getRole(), this.user1Role);
        Assert.assertEquals(createdUser.getMusicTypes(), this.user1MusicTypeList);

        Assert.assertEquals(createdUser2.getName(), this.user2Name);
        Assert.assertEquals(createdUser2.getLogin(), this.user2login);
        Assert.assertEquals(createdUser2.getPassword(), this.user2Password);
        Assert.assertEquals(createdUser2.getAddress().getZip_code(), this.user2Address.getZip_code());
        Assert.assertEquals(createdUser2.getAddress().getCountry(), this.user2Address.getCountry());
        Assert.assertEquals(createdUser2.getAddress().getCity(), this.user2Address.getCity());
        Assert.assertEquals(createdUser2.getAddress().getStreet(), this.user2Address.getStreet());
        Assert.assertEquals(createdUser2.getAddress().getHouse(), this.user2Address.getHouse());
        Assert.assertEquals(createdUser2.getAddress().getFlat(), this.user2Address.getFlat());
        Assert.assertEquals(createdUser2.getRole(), this.user2Role);
        Assert.assertEquals(createdUser2.getMusicTypes(), this.user2MusicTypeList);
    }

    /**
     * Tests getUserById() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenGetUserByIdThenExpectedUserIsReturned() throws Exception {
        this.whenGetAllUsersThenExpectedUsersAreReturned();
    }


    /**
     * Tests getUserByRole() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenGetUserByRoleThenExpectedUserIsReturned() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        List<User> createdUsers = this.manager.getUserByRole(this.user1Role);
        Assert.assertEquals(1, createdUsers.size());
        User createdUser = this.manager.getAllUsers().get(0);
        Assert.assertEquals(createdUser.getName(), this.user1Name);
        Assert.assertEquals(createdUser.getLogin(), this.user1login);
        Assert.assertEquals(createdUser.getPassword(), this.user1Password);
        Assert.assertEquals(createdUser.getAddress().getZip_code(), this.user1Address.getZip_code());
        Assert.assertEquals(createdUser.getAddress().getCountry(), this.user1Address.getCountry());
        Assert.assertEquals(createdUser.getAddress().getCity(), this.user1Address.getCity());
        Assert.assertEquals(createdUser.getAddress().getStreet(), this.user1Address.getStreet());
        Assert.assertEquals(createdUser.getAddress().getHouse(), this.user1Address.getHouse());
        Assert.assertEquals(createdUser.getAddress().getFlat(), this.user1Address.getFlat());
        Assert.assertEquals(createdUser.getRole(), this.user1Role);
        Assert.assertEquals(createdUser.getMusicTypes(), this.user1MusicTypeList);
    }

    /**
     * Tests getUserByLogin() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenGetUserByLoginThenExpectedUser() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        this.createUser2();
        User expectedUser2 = this.manager.getUserIdByLoginAndPassword(this.user2login, this.user2Password);
        Assert.assertEquals(expectedUser2.getName(), this.user2Name);
        Assert.assertEquals(expectedUser2.getLogin(), this.user2login);
        Assert.assertEquals(expectedUser2.getPassword(), this.user2Password);
        Assert.assertEquals(expectedUser2.getAddress().getZip_code(), this.user2Address.getZip_code());
        Assert.assertEquals(expectedUser2.getAddress().getCountry(), this.user2Address.getCountry());
        Assert.assertEquals(expectedUser2.getAddress().getCity(), this.user2Address.getCity());
        Assert.assertEquals(expectedUser2.getAddress().getStreet(), this.user2Address.getStreet());
        Assert.assertEquals(expectedUser2.getAddress().getHouse(), this.user2Address.getHouse());
        Assert.assertEquals(expectedUser2.getAddress().getFlat(), this.user2Address.getFlat());
        Assert.assertEquals(expectedUser2.getRole(), this.user2Role);
        Assert.assertEquals(expectedUser2.getMusicTypes(), this.user2MusicTypeList);
    }

    /**
     * Tests deleteUser() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenDeleteUserThenThisUserIsNotInDB() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        this.createUser2();
        Assert.assertEquals(2, this.manager.getAllUsers().size());
        this.manager.deleteUser(this.createdUser1Id);
        Assert.assertEquals(1, this.manager.getAllUsers().size());
        User expectedUser = this.manager.getUserById(this.createdUser1Id);
        Assert.assertEquals(null, expectedUser);
    }

    /**
     * Tests deleteAllUsers() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenDBIsNotEmptyAndDeleteAllUsersThenUserTableIsEmpty() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        this.createUser2();
        Assert.assertEquals(2, this.manager.getAllUsers().size());
        this.manager.deleteAllUsers();
        Assert.assertEquals(0, this.manager.getAllUsers().size());
    }

    /**
     * Tests updateUser() method.
     * @throws Exception if exception occurs.
     */
    @Test
    public void whenUpdateUserThenOldUserIsNotInDBAndNewUserIsInDB() throws Exception {
        this.manager.deleteAllUsers();
        this.createUser1();
        Assert.assertEquals(1, this.manager.getAllUsers().size());
        this.manager.updateUser(this.createdUser1Id, this.user2Name, this.user2Surname, this.user2login, this.user2Password,
                this.user2Address, this.user2Role, this.user2MusicTypeList);
        User user = this.manager.getAllUsers().get(0);
        Assert.assertEquals(1, this.manager.getAllUsers().size());
        Assert.assertEquals(user.getName(), this.user2Name);
        Assert.assertEquals(user.getLogin(), this.user2login);
        Assert.assertEquals(user.getPassword(), this.user2Password);
        Assert.assertEquals(user.getAddress().getZip_code(), this.user2Address.getZip_code());
        Assert.assertEquals(user.getAddress().getCountry(), this.user2Address.getCountry());
        Assert.assertEquals(user.getAddress().getCity(), this.user2Address.getCity());
        Assert.assertEquals(user.getAddress().getStreet(), this.user2Address.getStreet());
        Assert.assertEquals(user.getAddress().getHouse(), this.user2Address.getHouse());
        Assert.assertEquals(user.getAddress().getFlat(), this.user2Address.getFlat());
        Assert.assertEquals(user.getRole(), this.user2Role);
        Assert.assertEquals(user.getMusicTypes(), this.user2MusicTypeList);
    }
}