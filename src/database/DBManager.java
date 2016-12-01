package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DBManager {

	private static DBManager instance = new DBManager();
	private static final String datasource = "java:jboss/MenuFinder";
	private Connection connection;

	public static DBManager getInstance() {
		return instance;
	}

	private DBManager() {
		try {
			connection = getDatasource().getConnection();
		} catch (SQLException | NamingException e) {
			System.err.println("Error while obtaining the dataSource '" + datasource + "'.");
			e.printStackTrace();
		}
	}

	public PreparedStatement getPreparedStatement(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void executeUpdate(String query) throws SQLException {
		Statement stm = connection.createStatement();
		stm.executeUpdate(query);
		stm.close();
	}

	public ResultSet executeQuery(String query) throws SQLException {
		Statement stm = connection.createStatement();
		return stm.executeQuery(query);
	}

	private DataSource getDatasource() throws NamingException {
		return (DataSource) new InitialContext().lookup(datasource);
	}

	protected void deleteBean(Bean m) {
		try {
			DBManager db = DBManager.getInstance();
			db.executeUpdate(m.getDeleteQuery());
		} catch (SQLException ex) {
			System.err.println("Error deleting bean.");
			ex.printStackTrace();
		}
	}

	protected void updateBean(Bean m) {
		try {
			DBManager db = DBManager.getInstance();
			db.executeUpdate(m.getUpdateQuery());
		} catch (SQLException ex) {
			System.err.println("Error deleting bean.");
			ex.printStackTrace();
		}
	}

	protected void insertBean(Bean m) {
		try {
			DBManager db = DBManager.getInstance();
			db.executeUpdate(m.getInsertQuery());
		} catch (SQLException ex) {
			System.err.println("Error inserting bean.");
			ex.printStackTrace();
		}
	}

}
