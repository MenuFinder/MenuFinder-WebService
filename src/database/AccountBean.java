package database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBean extends Bean {

	private static final long serialVersionUID = 1L;
	private static final String table = "menufinder.account";

	private String id;
	private String password;
	private String type;

	public AccountBean() {
		super();
	}

	public AccountBean(String password, String type) {
		super();
		this.password = password;
		this.type = type;
		isNewRecord = true;
	}

	public AccountBean(String id, String password, String type) {
		super();
		this.id = id;
		this.password = password;
		this.type = type;
		isNewRecord = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		isNewRecord = false;
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE id = '" + id + "'";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE " + table + " SET password = '" + password + "', type = '" + type + "' WHERE id = '" + id + "'";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (password, type) VALUES ('" + password + "', '" + type + "')";
	}

	private static AccountBean getAccountFromRS(ResultSet rs) {
		try {
			return new AccountBean(rs.getString("id"), rs.getString("password"), rs.getString("type"));
		} catch (SQLException e) {
			System.err.println("Error retrieving bean.");
			e.printStackTrace();
			return null;
		}
	}

	public static AccountBean getAccountById(long id) {
		try {
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table + " WHERE id = '" + id + "'");
			rs.next();
			return getAccountFromRS(rs);
		} catch (SQLException ex) {
			System.err.println("Error retrieving account with id: '" + id + "'.");
			ex.printStackTrace();
			return null;
		}
	}

}
