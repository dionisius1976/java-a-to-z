package ru.dionisius.storages;

import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import ru.dionisius.models.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Dionisius on 23.10.2017.
 * Testing class for UserStorage class.
 */
public class UserStorageTest {

    /**
     * EmbeddedDatabase instance.
     */
    private EmbeddedDatabase embeddedDatabase = null;
    /**
     * JdbcTemplate instance.
     */
    private JdbcTemplate jdbcTemplate = null;

    @Before
    public void setUp() {
        embeddedDatabase = new EmbeddedDatabaseBuilder()
                .addDefaultScripts()
                .setType(EmbeddedDatabaseType.H2)
                .build();

        jdbcTemplate = new JdbcTemplate(embeddedDatabase);
    }

    @After
    public void tearDown() {
        embeddedDatabase.shutdown();
    }

    /**
     * Tests beans loading.
     */
    @Test
    public void whenContextIsLoadedThenBeansAreLoadedToo() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        IStorage memoryStorage = context.getBean(MemoryStorage.class);
        assertNotNull(memoryStorage);
        UserStorage userStorage = context.getBean(UserStorage.class);
        assertNotNull(userStorage);
    }
    /**
     * Tests add(), get(long id) and getAll() methods of JdbcStorage.
     * Initial users are inserted in data.sql file in resources directory.
     */
    @Test
    public void whenAddingUsersAndAllUsersMethodInJdbcStorageThenExpectedUsersReturned() {
        IStorage jdbcStorage = new JdbcStorage(this.jdbcTemplate);
        UserStorage userStorage = new UserStorage(jdbcStorage);
        User firstExpectedUser = new User("1", "1", "Ivan", "Karamazov", "+79211112233");
        User secondExpectedUser = new User("2", "2", "Tatiana", "Filgingauer", "+79112225566");
        long firstUserId = 1;
        long secondUserId = 2;
        firstExpectedUser.setId(firstUserId);
        secondExpectedUser.setId(secondUserId);
        User firstResultUser = userStorage.getUser(firstUserId);
        User secondResultUser = userStorage.getUser(secondUserId);
        Assert.assertEquals(userStorage.getAllUsers().size(), 2);
        Assert.assertEquals(firstExpectedUser, firstResultUser);
        Assert.assertEquals(secondExpectedUser, secondResultUser);
    }
    /**
     * Tests adding two equal users in JdbcStorage.
     */
    @Test
    public void whenTwoEqualUsersAddedToJdbcStorageThenOnlyOneUserIsInMemoryStorageAndSecondWillReturnMinusOneId() {
        IStorage jdbcStorage = new JdbcStorage(this.jdbcTemplate);
        UserStorage userStorage = new UserStorage(jdbcStorage);
        User thirdUser = new User("1", "1", "Ivan", "Karamazov", "+79211112233");
        long thirdUserId = userStorage.addUser(thirdUser);
        Assert.assertEquals(thirdUserId, -1);
    }
    /**
     * Tests update() and delete() methods in JdbcStorage.
     */
    @Test
    public void whenUserUpdatedInJdbcStorageThenUpdatedUserIsInMemoryStorageAndOldUserIsAbsent() {
        IStorage jdbcStorage = new JdbcStorage(this.jdbcTemplate);
        UserStorage userStorage = new UserStorage(jdbcStorage);
        userStorage.deleteUser(2);
        List<User> allUsers = userStorage.getAllUsers();
        Assert.assertEquals(allUsers.size(), 1);
        User oldUser = new User("1", "1", "Ivan", "Karamazov", "+79211112233");
        oldUser.setId(1);
        User newUser = new User("4", "4", "Nikolay", "Smirnoff", "+79112223399");
        newUser.setId(1);
        userStorage.updateUser(newUser);
        allUsers = userStorage.getAllUsers();
        Assert.assertEquals(allUsers.size(), 1);
        for (User user: allUsers) {
            Assert.assertEquals(user, newUser);
        }
    }
    /**
     * Tests add(), get(long id) and getAll() methods of MemoryStorage.
     */
    @Test
    public void whenAddingUsersAndAllUsersMethodInMemoryStorageThenExpectedUsersReturned() {
        User firstExpectedUser = new User("1", "1", "Ivan", "Smirnoff", "+79112223344");
        User secondExpectedUser = new User("2", "2", "Sidor", "Uzlov", "+79213456789");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        IStorage memoryStorage = context.getBean(MemoryStorage.class);
        UserStorage userStorage = new UserStorage(memoryStorage);
        long firstUserId = userStorage.addUser(firstExpectedUser);
        long secondUserId = userStorage.addUser(secondExpectedUser);
        User firstResultUser = userStorage.getUser(firstUserId);
        User secondResultUser = userStorage.getUser(secondUserId);
        Assert.assertEquals(userStorage.getAllUsers().size(), 2);
        Assert.assertEquals(firstExpectedUser, firstResultUser);
        Assert.assertEquals(secondExpectedUser, secondResultUser);
    }

    /**
     * Tests adding two equal users in MemoryStorage.
     */
    @Test
    public void whenTwoEqualUsersAddedToMemoryStorageThenOnlyOneUserIsInMemoryStorageAndSecondWillReturnMinusOneId() {
        User resultUser = null;
        User expectedUser = new User("1", "1", "Ivan", "Smirnoff", "+79112223344");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        IStorage memoryStorage = context.getBean(MemoryStorage.class);
        UserStorage userStorage = new UserStorage(memoryStorage);
        long firstUserId = userStorage.addUser(expectedUser);
        long secondUserId = userStorage.addUser(expectedUser);
        resultUser = userStorage.getUser(firstUserId);
        Assert.assertEquals(expectedUser, resultUser);
        Assert.assertEquals(userStorage.getAllUsers().size(), 1);
        Assert.assertEquals(secondUserId, -1);
    }
    /**
     * Tests update() method in MemoryStorage.
     */
    @Test
    public void whenUserUpdatedInMemoryStorageThenUpdatedUserIsInMemoryStorageAndOldUserIsAbsent() {
        User oldUser = new User("1", "1", "Ivan", "Smirnoff", "+79112223344");
        User newUser = new User("2", "2", "Sidor", "Uzlov", "+79213456789");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        IStorage memoryStorage = context.getBean(MemoryStorage.class);
        UserStorage userStorage = new UserStorage(memoryStorage);
        long firstUserId = userStorage.addUser(oldUser);
        newUser.setId(firstUserId);
        userStorage.updateUser(newUser);
        List<User> allUsers = userStorage.getAllUsers();
        Assert.assertEquals(allUsers.size(), 1);
        for (User user: allUsers) {
            Assert.assertEquals(user, newUser);
        }
    }
    /**
     * Tests delete() method in MemoryStorage.
     */
    @Test
    public void whenDeleteUserFromMemoryStorageThenThisUserIsAbsent() {
        User firstUser = new User("1", "1", "Ivan", "Smirnoff", "+79112223344");
        User secondUser = new User("2", "2", "Sidor", "Uzlov", "+79213456789");
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        IStorage memoryStorage = context.getBean(MemoryStorage.class);
        UserStorage userStorage = new UserStorage(memoryStorage);
        long firstUserId = userStorage.addUser(firstUser);
        long secondUserId = userStorage.addUser(secondUser);
        userStorage.deleteUser(firstUserId);
        List<User> allUsers = userStorage.getAllUsers();
        Assert.assertEquals(allUsers.size(), 1);
        for (User user: allUsers) {
            Assert.assertEquals(user, secondUser);
        }
    }
}