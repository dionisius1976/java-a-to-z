package ru.dionisius.controllers;

import ru.dionisius.models.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Data base for models.
 * It has methods for adding, deleting,
 * finding by id, updates and get all models.
 */
public class Tracker implements ITracker {

	private final Connection conn;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	/**
	 * Instance of Random class for generating random values.
	 */
	private static final Random RN = new Random();

	/**
	 * Constructor.
	 * @param conn Connection object for specified data base.
	 */
	public Tracker(final Connection conn) {
		this.conn = conn;
	}

	@Override
	public void add(Item item) {
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
			st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Item[] getAll() {
		List<Item> allItems = new LinkedList<>();
		Item item = null;
		try {
			this.st = this.conn.prepareStatement("SELECT * FROM orders");
			this.rs = st.executeQuery();
			while (rs.next()) {
				item = new Item(rs.getString("name"), rs.getString("description"),
						new Date(rs.getTimestamp("create_date").getTime()));
				allItems.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allItems.toArray(new Item[0]);
	}

	@Override
	public void delete(Item item) {
		try {
			this.st = this.conn.prepareStatement("DELETE FROM orders WHERE order_id = ?");
			this.st.setString(1, String.valueOf(item.getId()));
			st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Item findById(long id) {
		Item foundedItem = null;
		try {
			this.st = this.conn.prepareStatement("SELECT * FROM orders WHERE order_id = ?");
			this.st.setString(1, String.valueOf(id));
			this.rs = st.executeQuery();
			while (this.rs.next()) {
                foundedItem = new Item(this.rs.getString("name"),
                        this.rs.getString("description"),
                        new Date(this.rs.getTimestamp("create_date").getTime()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundedItem;
	}

	@Override
	public void update(long id, String newName, String newDesc) {
        try {
            this.st = this.conn.prepareStatement("UPDATE orders SET name = ?, description = ?" +
                    "WHERE order_id = ?");
            this.st.setString(1, newName);
            this.st.setString(2, newDesc);
            this.st.setString(3, String.valueOf(id));
        } catch (SQLException e) {
            e.printStackTrace();
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