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

	public AccountBean() {
		super();
	}

	public AccountBean(String id, String password, String type, boolean isNewRecord) {
		super();
		this.id = id;
		this.password = password;
		this.type = type;
		this.isNewRecord = isNewRecord;
	}

	public String getId() {
		return id;
	}

	public void setId(String id, boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
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
		return "INSERT INTO " + table + " (id, password, type) VALUES ('" + id + "', '" + password + "', '" + type + "')";
	}

	private static AccountBean getAccountFromRS(ResultSet rs) {
		try {
			return new AccountBean(rs.getString("id"), rs.getString("password"), rs.getString("type"), false);
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

	public static boolean isValidLogin(String id, String password) {
		try {
			DBManager db = DBManager.getInstance();

			ResultSet rs = db.executeQuery(
					"SELECT * FROM " + table + " WHERE id = '" + id + "' AND password = '" + password + "'");
			rs.next();
			rs.getString("id");
			return true;
		} catch (SQLException ex) {
			return false;
		}
	}
	
	public static AccountBean getValidLogin(String id, String password){
		AccountBean loggedAcc = new AccountBean();
		
		try
		{
			DBManager db = DBManager.getInstance();
			PreparedStatement pst = db.getConnection().prepareStatement("SELECT * FROM " + table + " WHERE id=? AND password=?");
	         pst.setString(1, id);
	         pst.setString(2, password);
	         ResultSet rs = pst.executeQuery();
	         if(rs.next()){  
	        	 loggedAcc.setId(rs.getString("id"),false);  
	        	 loggedAcc.setPassword(rs.getString("password"));  	        	  
	        	 loggedAcc.setType(rs.getString("type"));  	
	        	 return loggedAcc;
	            }  
	         else
	        	 return null;
		}
		catch(SQLException ex)
		{
			return null;
		}				
	}

}
