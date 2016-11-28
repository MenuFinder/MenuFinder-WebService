package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuBean extends Bean {

	private static final long serialVersionUID = 1L;
	private static final String table = "menu";

	private long id;
	private long restaurant;
	private String name;
	private String description;
	private double price;
	private double score;

	public MenuBean() {
		super();
	}

	public MenuBean(long id, long restaurant, String name, String description, double price, double score) {
		super();
		this.id = id;
		this.restaurant = restaurant;
		this.name = name;
		this.description = description;
		this.price = price;
		this.score = score;
		isNewRecord = false;
	}

	public MenuBean(long restaurant, String name, String description, double price, double score) {
		super();
		this.restaurant = restaurant;
		this.name = name;
		this.description = description;
		this.price = price;
		this.score = score;
		isNewRecord = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		isNewRecord = false;
		this.id = id;
	}

	public long getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(long restaurant) {
		this.restaurant = restaurant;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE id = " + id;
	}

	protected String getUpdateQuery() {
		return "UPDATE " + table + " SET restaurant = " + restaurant + ", name = '" + name + "', description = '"
				+ description + "', price = " + price + ", score = " + score + " WHERE id = " + id;
	}

	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (restaurant, name, description, price, score) VALUES (" + restaurant + ", '"
				+ name + "', '" + description + "', " + price + ", " + score + ")";
	}

	public static MenuBean getMenuById(long id) {
		try {
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table + " WHERE id = " + id);
			rs.next();
			return getMenuFromRS(rs);
		} catch (SQLException ex) {
			System.err.println("Error retrieving menu with id: '" + id + "'.");
			ex.printStackTrace();
			return null;
		}
	}

	public static List<MenuBean> getAllBeans() {
		List<MenuBean> menus = new ArrayList<>();
		try {
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table);
			while (rs.next()) {
				menus.add(getMenuFromRS(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error retrieving menus list.");
			ex.printStackTrace();
		}
		return menus;
	}

	public static List<MenuBean> getMenusOfRestaurant(long restaurantId) {
		List<MenuBean> menus = new ArrayList<>();
		try {
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table + " WHERE restaurant = " + restaurantId);
			while (rs.next()) {
				menus.add(getMenuFromRS(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error retrieving menus list.");
			ex.printStackTrace();
		}
		return menus;
	}

	private static MenuBean getMenuFromRS(ResultSet rs) {
		try {
			return new MenuBean(rs.getLong("id"), rs.getLong("restaurant"), rs.getString("name"),
					rs.getString("description"), rs.getDouble("price"), rs.getDouble("score"));
		} catch (SQLException e) {
			System.err.println("Error retrieving bean.");
			e.printStackTrace();
			return null;
		}
	}

}
