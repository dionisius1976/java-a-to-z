package ru.dionisius.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dionisius.models.Comment;
import ru.dionisius.models.Item;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Date;
import java.util.*;

/**
 * Data base for models.
 * It has methods for adding, deleting,
 * finding by id, updates and get all models.
 */
public class Tracker implements ITracker {
	/**
	 * Logger for database errors.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(Tracker.class);
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
	 * Instance of Random class for generating random values.
	 */
	private static final Random RN = new Random();
	/**
	 * Properties for db connection.
	 */
	private final Properties prs = new Properties();
	/**
	 * The name of properties file.
	 */
	private final String propertiesFile = "config.properties";

	/**
	 * Connects to database.
	 */
	public void connectToDb() {
    	this.loadProperties();
		try {
			this.conn = DriverManager.getConnection(prs.getProperty("url"),
					prs.getProperty("user"), prs.getProperty("password"));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		if (!this.isDbEmpty()) {
			this.createNewDb();
		}
	}

	/**
	 * Disconnects from database.
	 */
	public void disconnectDb() {
		try {
			conn.close();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
	}

	@Override
	public void addNewItem(final Item item) {
	    item.setId(this.generateId());
		try {
			this.st = this.conn.prepareStatement("INSERT INTO orders" +
                    "(order_id, name, create_date, user_id, categorie_id, state_id, description) VALUES (?, ?, ?, ?, ?, ?, ?)");
			this.st.setString(1, String.valueOf(item.getId()));
			this.st.setString(2, item.getName());
			this.st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			this.st.setInt(4,1);
			this.st.setInt(5,1);
			this.st.setInt(6,1);
			this.st.setString(7, item.getDesc());
			st.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}

    @Override
    public void editItem(final long id, final String newName, final String newDesc) {
        try {
            this.st = this.conn.prepareStatement("UPDATE orders SET name = ?, description = ?" +
                    "WHERE order_id = ?");
            this.st.setString(1, newName);
            this.st.setString(2, newDesc);
            this.st.setString(3, String.valueOf(id));
            this.st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteItem(final Item item) {
        int innerOrderId = 0;
        try {
            this.st = this.conn.prepareStatement("SELECT id FROM orders WHERE order_id = ?");
            this.st.setString(1, String.valueOf(item.getId()));
            this.rs = this.st.executeQuery();
            while (this.rs.next()) {
                innerOrderId = this.rs.getInt("id");
            }
            this.st = this.conn.prepareStatement("DELETE FROM order_files WHERE order_id = ?");
            this.st.setInt(1, innerOrderId);
            st.executeUpdate();
            this.st = this.conn.prepareStatement("DELETE FROM order_comments WHERE order_id = ?");
            this.st.setInt(1, innerOrderId);
            st.executeUpdate();
            this.st = this.conn.prepareStatement("DELETE FROM orders WHERE id = ?");
            this.st.setInt(1, innerOrderId);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
	public void addNewComment(long id, String newComment) {
        int orderId = 0;
		try {
			this.st = this.conn.prepareStatement("SELECT id FROM orders WHERE order_id = ?");
			this.st.setString(1, String.valueOf(id));
			this.rs = this.st.executeQuery();
			while (this.rs.next()) {
				orderId = this.rs.getInt("id");
			}
			this.st = this.conn.prepareStatement("INSERT INTO order_comments (name, create_date, order_id) " +
					"VALUES (?, ?, ?)");
            this.st.setString(1, newComment);
            this.st.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            this.st.setInt(3, orderId);
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    @Override
    public Item[] getAllItems() {
        List<Item> allItems = new LinkedList<>();
        Item item = null;
        try {
            this.st = this.conn.prepareStatement("SELECT * FROM orders");
            this.rs = st.executeQuery();
            while (rs.next()) {
                item = new Item(rs.getString("name"), rs.getString("description"),
                        new Date(rs.getTimestamp("create_date").getTime()));
                item.setId(rs.getLong("order_id"));
                allItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allItems.toArray(new Item[0]);
    }

	@Override
	public Item findById(final long id) {
		Item foundedItem = null;
		try {
			this.st = this.conn.prepareStatement("SELECT * FROM orders WHERE order_id = ?");
			this.st.setString(1, String.valueOf(id));
			this.rs = st.executeQuery();
			while (this.rs.next()) {
                foundedItem = new Item(this.rs.getString("name"),
                        this.rs.getString("description"),
                        new Date(this.rs.getTimestamp("create_date").getTime()));
                foundedItem.setId(rs.getLong("order_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundedItem;
	}

	@Override
	public Item[] findByName(final String name) {
		List<Item> allItems = new LinkedList<>();
		Item item = null;
		try {
			this.st = this.conn.prepareStatement("SELECT * FROM orders WHERE name = ?");
			this.st.setString(1, name);
			this.rs = st.executeQuery();
			while (rs.next()) {
				item = new Item(rs.getString("name"), rs.getString("description"),
						new Date(rs.getTimestamp("create_date").getTime()));
				item.setId(rs.getLong("order_id"));
				allItems.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allItems.toArray(new Item[0]);
	}

    @Override
    public Item[] findByDesc(final String desc) {
        List<Item> allItems = new LinkedList<>();
        Item item = null;
        try {
            this.st = this.conn.prepareStatement("SELECT * FROM orders WHERE description = ?");
            this.st.setString(1, desc);
            this.rs = this.st.executeQuery();
            while (rs.next()) {
                item = new Item(rs.getString("name"), rs.getString("description"),
                        new Date(rs.getTimestamp("create_date").getTime()));
                item.setId(rs.getLong("order_id"));
                allItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allItems.toArray(new Item[0]);
    }

	@Override
	public Comment[] getAllComments(final long id) {
		List<Comment> allItems = new LinkedList<>();
		Comment comment = null;
		try {
			this.st = this.conn.prepareStatement("SELECT oc.name FROM order_comments oc\n" +
					"INNER JOIN orders o ON oc.order_id = o.id  WHERE o.order_id = ?;");
			this.st.setString(1, String.valueOf(id));
			this.rs = st.executeQuery();
			while (rs.next()) {
				comment = new Comment(rs.getString("name"));
				allItems.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allItems.toArray(new Comment[0]);
	}

	/**
	 * Loads prorerties.
	 */
	private void loadProperties() {
		try (InputStream in = getClass().getClassLoader().getResourceAsStream(propertiesFile)) {
			this.prs.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Checks if specified in properties database is empty.
	 * @return true if database is empty, false if not.
	 */
	private boolean isDbEmpty() {
		boolean dbIsEmpty = false;
		if (this.getAllItems().length == 0) {
			dbIsEmpty = true;
		}
		return dbIsEmpty;
	}

	/**
	 * Creates new database.
	 */
	private void createNewDb() {
		try (Statement st = conn.createStatement()) {
			st.execute("CREATE TABLE user_roles (id SERIAL PRIMARY KEY, name VARCHAR(50))");
			st.execute("CREATE TABLE user_permissions (id SERIAL PRIMARY KEY, name VARCHAR(50))");
			st.execute("CREATE TABLE role_permissions (role_id INTEGER REFERENCES user_roles(id), " +
					"user_permission_id INTEGER REFERENCES user_permissions(id))");
			st.execute("CREATE TABLE users (id SERIAL PRIMARY KEY, name VARCHAR(50), " +
					"user_role_id INTEGER REFERENCES user_roles(id))");
			st.execute("CREATE TABLE order_states (id SERIAL PRIMARY KEY, name VARCHAR(50))");
			st.execute("CREATE TABLE order_categories (id SERIAL PRIMARY KEY, name VARCHAR(50))");
			st.execute("CREATE TABLE orders (id SERIAL PRIMARY KEY,order_id VARCHAR(100), name VARCHAR(50), " +
					"description VARCHAR(100), create_date TIMESTAMP, user_id INTEGER REFERENCES users(id), " +
					"categorie_id INTEGER REFERENCES order_categories(id), state_id INTEGER REFERENCES order_states(id))");
			st.execute("CREATE TABLE order_files (id SERIAL PRIMARY KEY, name VARCHAR(50), content BYTEA, " +
					"order_id INTEGER REFERENCES orders(id))");
			st.execute("CREATE TABLE order_comments (id SERIAL PRIMARY KEY,\tname TEXT, create_date TIMESTAMP, " +
					"order_id INTEGER REFERENCES orders(id))");
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
	}
	/**
	 * Generates random item's id.
	 * @return generated item's id.
	 */
	private long generateId() {
		return Long.valueOf(System.currentTimeMillis() + RN.nextInt());
	}
}