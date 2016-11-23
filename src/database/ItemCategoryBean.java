package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoryBean extends Bean {
	private static final long serialVersionUID = 1L;
	private static final String table = "itemcategory";

	private long id;
	private String name;
	private String description;

	public ItemCategoryBean() {
		super();
	}

	public ItemCategoryBean(long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		isNewRecord = false;
	}

	public ItemCategoryBean(String name, String description) {
		super();
		this.name = name;
		this.description = description;
		isNewRecord = true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		isNewRecord = false;
		this.id = id;
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

	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE id = " + id;
	}

	protected String getUpdateQuery() {
		return "UPDATE " + table + " SET name = '" + name + "', description = '" + description + "' WHERE id = " + id;
	}

	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (name, description) VALUES ('" + name + "', '" + description + "')";
	}

	public static ItemCategoryBean getItemCategoryById(long id) {
		try {
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table + " WHERE id = " + id);
			rs.next();
			return getIteamCategoryFromRS(rs);
		} catch (SQLException ex) {
			System.err.println("Error retrieving itemCategories with id: '" + id + "'.");
			ex.printStackTrace();
			return null;
		}
	}

	public static List<ItemCategoryBean> getAllBeans() {
		List<ItemCategoryBean> itemCategories = new ArrayList<>();
		try {
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table);
			while (rs.next()) {
				itemCategories.add(getIteamCategoryFromRS(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error retrieving itemCategories list.");
			ex.printStackTrace();
		}
		return itemCategories;
	}

	private static ItemCategoryBean getIteamCategoryFromRS(ResultSet rs) {
		try {
			return new ItemCategoryBean(rs.getLong("id"), rs.getString("name"), rs.getString("description"));
		} catch (SQLException e) {
			System.err.println("Error retrieving bean.");
			e.printStackTrace();
			return null;
		}
	}
	
}
