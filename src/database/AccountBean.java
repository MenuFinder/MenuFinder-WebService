package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountBean extends Bean {

	private static final long serialVersionUID = 1L;
	private static final String table = "account";

	private String id;
	private String password;
	private String type;
	private String token;
	private String email;

	public AccountBean() {
		super();
	}

	public AccountBean(String id, String password, String type) {
		super();
		this.id = id;
		this.password = password;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE id = '" + id + "'";
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE " + table + " SET password = '" + password + "', type = '" + type + "', token = '" + token
				+ "', email = '" + email + "' WHERE id = '" + id + "'";
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (id, password, type, token, email) VALUES ('" + id + "', '" + password
				+ "', '" + type + "', '" + token + "', '" + email + "')";
	}

	private static AccountBean getAccountFromRS(ResultSet rs) throws SQLException {
		AccountBean account = new AccountBean();
		account.setId(rs.getString("id"));
		account.setPassword(rs.getString("password"));
		account.setType(rs.getString("type"));
		account.setToken(rs.getString("token"));
		account.setEmail(rs.getString("email"));
		return account;
	}

	public static AccountBean getAccountById(String id) throws SQLException {
		ResultSet rs = select("SELECT * FROM " + table + " WHERE id = '" + id + "'");
		rs.next();
		return getAccountFromRS(rs);
	}

	public static boolean isValidLogin(String id, String password) {
		try {
			ResultSet rs = select(
					"SELECT * FROM " + table + " WHERE id = '" + id + "' AND password = '" + password + "'");
			rs.next();
			rs.getString("id");
			return true;
		} catch (SQLException ex) {
			return false;
		}
	}

	public static AccountBean getValidLogin(String id, String password) {
		try {
			DBManager db = DBManager.getInstance();
			PreparedStatement pst = db.getConnection()
					.prepareStatement("SELECT * FROM " + table + " WHERE id=? AND password=?");
			pst.setString(1, id);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) return getAccountFromRS(rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
