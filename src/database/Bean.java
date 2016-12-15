package database;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Bean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected abstract String getDeleteQuery();
	protected abstract String getUpdateQuery();
	protected abstract String getInsertQuery();

	public void update() throws SQLException {
		DBManager.getInstance().updateBean(this);
	}

	public void insert() throws SQLException {
		DBManager.getInstance().insertBean(this);
	}
	
	public void delete() throws SQLException {
		DBManager.getInstance().deleteBean(this);
	}

	protected static ResultSet select(String query) throws SQLException {
		return DBManager.getInstance().getResultSet(query);
	}

}
