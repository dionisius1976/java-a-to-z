package ru.dionisius.data.storages;

import org.hibernate.sql.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ru.dionisius.data.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Dionisius on 23.10.2017.
 */

public class JdbcStorage implements IStorage {

    /**
     * Logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcStorage.class);
    /**
     * JdbcTemplate instance.
     */
    private final JdbcTemplate template;


    @Autowired
    public JdbcStorage(final JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public long addUser(final User user) {
        long userId = -1;
        if (!this.isUserExist(user)) {
            this.template.update("INSERT INTO users (login, password, name, surname, tel_number) VALUES ?, ?, ?, ?, ?",
                    user.getLogin(), user.getPassword(), user.getName(), user.getSurname(),
                    user.getTelNumber());
            userId = this.template.queryForObject("SELECT * FROM users where login = ?",
                    new Object[]{user.getLogin()}, new UserMapper()).getId();

        }
        return userId;
    }

    @Override
    public List<User> getAllUsers() {
        return this.template.query("select * from users", new UserMapper());
    }

    @Override
    public User getUser(final long userId) {
        User user = null;
        try {
            user = this.template.queryForObject("select * from users where id = ?", new Object[]{userId}, new UserMapper());
        }  catch (DataAccessException dae) {
            LOGGER.debug(String.format("Can't find user with id %d", userId), dae);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        this.template.update("UPDATE users SET login = ?, password = ?, name = ?, surname = ?, tel_number = ?" +
                "WHERE id = ?", user.getLogin(), user.getPassword(), user.getName(), user.getSurname(),
                user.getTelNumber(), user.getId());
    }

    @Override
    public void deleteUser(final long userId) {
        this.template.update("DELETE FROM users WHERE id = ?", userId);
    }

    /**
     * Checks if specified user exists in storage.
     * @param user specified user.
     * @return true if specified user exists, false if not.
     */
    private boolean isUserExist(final User user) {
        boolean result = false;
        try {
            User foundUser = this.template.queryForObject("select * from users where login = ?", new Object[]{user.getLogin()}, new UserMapper());
            if (foundUser != null) {
                result = true;
            }
        }  catch (DataAccessException dae) {
            LOGGER.debug(String.format("Can't find user with id %d", user.toString()), dae);
        }
        return result;
    }

    /**
     * RowMapper interface class for JdbcTemplate.queryForObject() method.
     */
    public class UserMapper implements RowMapper<User> {
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setTelNumber(rs.getString("tel_number"));
            return user;
        }
    }
}
