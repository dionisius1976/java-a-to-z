package ru.dionisius.control;

import ru.dionisius.models.Address;
import ru.dionisius.models.MusicType;
import ru.dionisius.models.Role;
import ru.dionisius.models.User;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Created by Dionisius on 26.07.2017.
 * This database manager works with specified database.
 */
public class DbManager implements IDbManager{

    /**
     * Logger for database errors.
     */
    private static final Logger LOG = LoggerFactory.getLogger(DbManager.class);

    /**
     * Properties for specified database connection.
     */
    private final Properties prs = new Properties();

    /**
     * Properties for specified database connection.
     */
    private final String propertiesFile = "chapter_009/additional_task/src/main/resources/config.properties";

    /**
     * A fully qualified Java class name of a Driver implementation.
     */
    private static final String SQL_DRIVER = "org.postgresql.Driver";
    /**
     *
     */
    private static final String TABLE_ROLE = "role";
    /**
     *
     */
    private static final String TABLE_MUSIC = "music_type";
    /**
     *
     */
    private static final String CREATE_TABLE_IF_NOT_EXIST_MUSIC_TYPE_QUERY = "CREATE TABLE IF NOT EXISTS " +
            "music_type (id SERIAL PRIMARY KEY, name VARCHAR(50));";
    /**
     *
     */
    private static final String CREATE_TABLE_IF_NOT_EXIST_ADDRESS_QUERY = "CREATE TABLE IF NOT EXISTS address " +
            "(id SERIAL PRIMARY KEY, zip_code VARCHAR(50), country VARCHAR(50), city VARCHAR(100), " +
            "street VARCHAR(100), house VARCHAR(50), flat VARCHAR(50));";
    /**
     *
     */
    private static final String CREATE_TABLE_IF_NOT_EXIST_ROLE_QUERY = "CREATE TABLE IF NOT EXISTS role " +
            "(id SERIAL PRIMARY KEY, name VARCHAR(50));";
    /**
     *
     */
    private static final String CREATE_TABLE_IF_NOT_EXIST_USERS_QUERY = "CREATE TABLE IF NOT EXISTS users " +
            "(id SERIAL PRIMARY KEY, user_id VARCHAR(100), name VARCHAR(100),surname VARCHAR(100), " +
            "login VARCHAR(100), password VARCHAR(100), address_id INTEGER REFERENCES address(id), " +
            "role_id INTEGER REFERENCES role(id), create_date TIMESTAMP);";
    /**
     *
     */
    private static final String CREATE_TABLE_IF_NOT_EXIST_USER_MUSIC_QUERY = "CREATE TABLE IF NOT EXISTS user_music " +
            "(user_id INTEGER REFERENCES users(id), music_type_id INTEGER REFERENCES music_type(id));";
    /**
     * Connection for specified database.
     */
    private Connection conn = null;

    /**
     * Statement for specified connection.
     */
    private PreparedStatement st = null;
    /**
     * ResultSet instance of specified query.
     */
    private ResultSet rs = null;
    /**
     * ResultSet instance of specified query.
     */
    private ResultSet tempRS = null;
    private DataSource ds = null;

    /**
     *
     */
    private static final DbManager INSTANCE = new DbManager();

    /**
     * Constructor.
     */
    private DbManager() {
        this.connectToDb();
        this.createTablesIfNotExist();
        this.fillTableWithInitialValuesIfNotExist(TABLE_ROLE, Arrays.asList(Role.values()));
        this.fillTableWithInitialValuesIfNotExist(TABLE_MUSIC, Arrays.asList(MusicType.values()));
    }

    /**
     *
     */
    public static DbManager getInstance() {
        return INSTANCE;
    }

    /**
     * Checks if user with specified login and password exists.
     * @param login user's login.
     * @param password user's password.
     * @return true if exists and false if not.
     */
    private boolean areLoginAndPasswordExist(final String login, final String password) {
        boolean areExist = false;
        List<User> allUsers = this.getAllUsers();
        for(User user: allUsers) {
            if(user.getLogin().equals(login) && user.getPassword().equals(password)) {
                areExist = true;
                break;
            }
        }
        return areExist;
    }



    @Override
    public String createUser(final String userName, final String userSurname, final String userLogin,
                           final String userPassword, final Address userAddress, final Role userRole,
                           final List<MusicType> userMusicTypeList) {
        String userId = null;
        if (!this.areLoginAndPasswordExist(userLogin, userPassword)) {
            this.insertAddress(userAddress);
            userId = this.insertUser(userName, userSurname, userLogin, userPassword,
                    this.getAddressId(userAddress), this.getRoleId(userRole));
            this.insertUserMusic(userId, userMusicTypeList);
        } else {
            userId = this.getUserIdByLoginAndPassword(userLogin, userPassword).getId();
        }
            return userId;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new LinkedList<>();
        String sql = "SELECT * FROM users;";
        try (PreparedStatement ps = this.conn.prepareStatement(sql)) {
            try (ResultSet rSet = ps.executeQuery()) {
                while(rSet.next()) {
                    allUsers.add(this.getUserById(rSet.getString("user_id")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return allUsers;
    }

    @Override
    public User getUserById(final String userId) {
        String userName = null;
        String userSurname = null;
        String userLogin = null;
        String userPassword = null;
        Timestamp create_date = null;
        int currentUserAddressId = 0;
        int currentUserRoleId = 0;
        User foundUser = null;
        Address currentAddress = null;
        List<MusicType> currentUserMusicTypes = new LinkedList<>();
        Role currentUserRole = null;
        try {
            this.st = conn.prepareStatement("SELECT * FROM users WHERE user_id = ?;");
            this.st.setString(1, userId);
            this.tempRS = this.st.executeQuery();
            while (this.tempRS.next()) {
                userName = this.tempRS.getString("name");
                userSurname = this.tempRS.getString("surname");
                userLogin = this.tempRS.getString("login");
                userPassword = this.tempRS.getString("password");
                create_date = this.tempRS.getTimestamp("create_date");
                currentUserAddressId = this.tempRS.getInt("address_id");
                currentUserRoleId = this.tempRS.getInt("role_id");
                currentAddress = this.getAddressById(currentUserAddressId);
                currentUserRole = this.getRoleById(currentUserRoleId);

                this.st = conn.prepareStatement("SELECT music_type.name FROM user_music " +
                                                    "INNER JOIN users " +
                                                    "ON users.id = user_music.user_id " +
                                                    "INNER JOIN music_type " +
                                                    "ON music_type.id = user_music.music_type_id " +
                                                    "WHERE users.user_id = ?;");
                this.st.setString(1, userId);
                this.tempRS = this.st.executeQuery();
                while (this.tempRS.next()) {
                    currentUserMusicTypes.add(MusicType.valueOf(this.tempRS.getString("name")));
                }

                foundUser = new User(userId, userName, userSurname, userLogin, userPassword,
                        currentAddress, currentUserRole, currentUserMusicTypes, create_date);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return foundUser;
    }


    @Override
    public List<User> getUserByRole(final Role role) {
        List<User> foundUsers = new LinkedList<>();
        int roleId = this.getRoleId(role);
        if (roleId != 0) {
            try {
                this.st = conn.prepareStatement("SELECT user_id FROM users WHERE role_id = ?;");
                this.st.setInt(1, roleId);
                this.rs = st.executeQuery();
                while (this.rs.next()) {
                    foundUsers.add(this.getUserById(this.rs.getString("user_id")));
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return foundUsers;
    }

    @Override
    public void deleteUser(final String userId) {
        try {
            this.st = conn.prepareStatement("DELETE FROM user_music WHERE user_id = " +
                    "(SELECT id FROM users WHERE user_id = ?);");
            this.st.setString(1, userId);
            this.st.executeUpdate();
            this.st = conn.prepareStatement("DELETE FROM users WHERE user_id = ?;");
            this.st.setString(1, userId);
            this.st.executeUpdate();
            this.st = conn.prepareStatement("DELETE FROM address WHERE user_id = " +
                    "(SELECT id FROM users WHERE user_id = ?);");
            this.st.setString(1, userId);
            this.st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public User getUserIdByLoginAndPassword(final String userLogin, final String userPassword) {
        User foundUser = null;
            try {
                this.st = conn.prepareStatement("SELECT user_id FROM users WHERE login = ? AND password = ?;");
                this.st.setString(1, userLogin);
                this.st.setString(2, userPassword);
                this.rs = st.executeQuery();
                while (this.rs.next()) {
                    foundUser = this.getUserById(this.rs.getString("user_id"));
                }
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        return foundUser;
    }

    @Override
    public void deleteAllUsers() {
        try {
            this.st = conn.prepareStatement("DROP TABLE user_music; DROP TABLE users; DROP TABLE address;");
            this.st.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        this.createTablesIfNotExist();
    }

    @Override
    public void updateUser(final String oldUserId, final String userName, final String userSurname,
                           final String userlogin, final String userPassword, final Address userAddress,
                           final Role userRole, final List<MusicType> userMusicTypeList) {
        this.deleteUser(oldUserId);
        this.createUser(userName, userSurname, userlogin, userPassword, userAddress,
                        userRole, userMusicTypeList);
    }

    @Override
    protected void finalize() throws Throwable {
        this.disconnectDb();
        super.finalize();
    }

    /**
     * Loads properties.
     */
    private void loadProperties() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
            this.prs.load(in);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Creates a new table if it doesn't exist.
     */
    private void createTablesIfNotExist() {
        StringBuilder builder = new StringBuilder();
        builder.append(CREATE_TABLE_IF_NOT_EXIST_MUSIC_TYPE_QUERY);
        builder.append(CREATE_TABLE_IF_NOT_EXIST_ROLE_QUERY);
        builder.append(CREATE_TABLE_IF_NOT_EXIST_ADDRESS_QUERY);
        builder.append(CREATE_TABLE_IF_NOT_EXIST_USERS_QUERY);
        builder.append(CREATE_TABLE_IF_NOT_EXIST_USER_MUSIC_QUERY);
        try {
            this.st = this.conn.prepareStatement(builder.toString());
            st.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Fills specified table with specified values.
     * @param tableName specified table name.
     * @param initialValues list of specified values.
     */
    private void fillTableWithInitialValuesIfNotExist(final String tableName, final List<Enum> initialValues) {
        try {
            for(Enum value : initialValues) {
                this.st = conn.prepareStatement(String.format("%s%s%s%s%s", "INSERT INTO ", tableName, "(name) " +
                        "SELECT ? WHERE NOT EXISTS (SELECT id FROM ", tableName, " WHERE name = ?);"));
                st.setString(1, value.name());
                st.setString(2, value.name());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Insert specified address.
     * Throws AddressAlredyExistException if specified address is already exists.
     * @param address specified address.
     */
    private void insertAddress(final Address address) {
            try {
                this.st = conn.prepareStatement("INSERT INTO address(zip_code, country, city, street, house, flat)" +
                        "VALUES (?, ?, ?, ?, ?, ?);");
                this.st.setString(1, address.getZip_code());
                this.st.setString(2, address.getCountry());
                this.st.setString(3, address.getCity());
                this.st.setString(4, address.getStreet());
                this.st.setString(5, address.getHouse());
                this.st.setString(6, address.getFlat());
                st.executeUpdate();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
    }

    /**
     * @param address
     * @return
     */
    private int getAddressId(final Address address) {
        int addressId = 0;
        try {
            this.st = conn.prepareStatement("SELECT id FROM address WHERE zip_code = ? AND country = ? " +
                    "AND city = ? AND street = ? AND house = ? AND flat = ?;");
            this.st.setString(1, address.getZip_code());
            this.st.setString(2, address.getCountry());
            this.st.setString(3, address.getCity());
            this.st.setString(4, address.getStreet());
            this.st.setString(5, address.getHouse());
            this.st.setString(6, address.getFlat());
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                addressId = this.rs.getInt("id");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return addressId;
    }

    private Address getAddressById(final int addressId) {
        Address foundAddress = null;
        try {
            this.st = conn.prepareStatement("SELECT * FROM address WHERE id = ?;");
            this.st.setInt(1, addressId);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                foundAddress = new Address(this.rs.getString("zip_code"),
                        this.rs.getString("country"),
                        this.rs.getString("city"),
                        this.rs.getString("street"),
                        this.rs.getString("house"),
                        this.rs.getString("flat"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return foundAddress;
    }

    private int getRoleId(final Role role) {
        int roleId = 0;
        try {
            this.st = conn.prepareStatement("SELECT id FROM role WHERE name = ?;");
            this.st.setString(1, role.name());
            this.rs = st.executeQuery();
            while (this.rs.next()) {
                roleId = this.rs.getInt("id");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return roleId;
    }

    private Role getRoleById(final int roleId) {
        Role foundRole = null;
        try {
            this.st = conn.prepareStatement("SELECT name FROM role WHERE id = ?;");
            this.st.setInt(1, roleId);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                foundRole = Role.valueOf(this.rs.getString("name"));
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return foundRole;
    }

    private String insertUser (final String name, final String surname, final String login,
                               final String password, final int addressId, final int roleId) {
        String userId = null;
        try {
            this.st = conn.prepareStatement("INSERT INTO users(user_id, name, " +
                    "surname, login, password, address_id, role_id, create_date)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);");
            this.st.setString(1, this.generateId());
            this.st.setString(2, name);
            this.st.setString(3, surname);
            this.st.setString(4, login);
            this.st.setString(5, password);
            this.st.setInt(6, addressId);
            this.st.setInt(7, roleId);
            this.st.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            this.st = conn.prepareStatement("SELECT user_id FROM users WHERE address_id = ?;");
            this.st.setInt(1, addressId);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                userId = this.rs.getString("user_id");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return userId;
    }

    private int getMusicTypeId(final MusicType musicType) throws SQLException {
        int result = 0;
        this.st = conn.prepareStatement("SELECT id FROM music_type WHERE name = ?;");
        this.st.setString(1, musicType.name());
        this.rs = st.executeQuery();
        while (this.rs.next()) {
            result = this.rs.getInt("id");
        }
        return result;
    }

    private void insertUserMusic (final String userId, final List<MusicType> userMusicTypesList) {
        int userSqlId = 0;
        int userMusicTypeId = 0;
        try {
            this.st = conn.prepareStatement("SELECT id FROM users WHERE user_id = ?;");
            this.st.setString(1, userId);
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                userSqlId = this.rs.getInt("id");
            }
            for (MusicType musicType: userMusicTypesList) {
                userMusicTypeId = this.getMusicTypeId(musicType);
                this.st = conn.prepareStatement("INSERT INTO user_music(user_id, music_type_id)" +
                        "VALUES (?, ?);");
                this.st.setInt(1, userSqlId);
                this.st.setInt(2, userMusicTypeId);
                st.executeUpdate();
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    /**
     * Connects to specified database.
     */
    private void connectToDb() {
        this.loadProperties();
        PoolProperties p = new PoolProperties();
        p.setUrl(this.prs.getProperty("url"));
        p.setDriverClassName(SQL_DRIVER);
        p.setUsername(this.prs.getProperty("user"));
        p.setPassword(this.prs.getProperty("password"));
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);
        try {
            this.conn = datasource.getConnection();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    /**
     * Generates random user's id.
     * @return generated user's id.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * Disconnects from working database.
     */
    public void disconnectDb() {
        try {
            this.conn.close();
            this.st.close();
            this.rs.close();
            this.tempRS.close();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (this.conn != null) {
                try {
                    this.conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            if (this.st != null) {
                try {
                    this.st.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            if (this.rs != null) {
                try {
                    this.rs.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
            if (this.tempRS != null) {
                try {
                    this.tempRS.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
    }
}
