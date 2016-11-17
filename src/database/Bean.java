package database;

import java.io.Serializable;

public abstract class Bean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	protected boolean isNewRecord;
	
	public void save() {
		if (isNewRecord)
			insert();
		else
			update();
	}
	
	protected abstract String getDeleteQuery();
	protected abstract String getUpdateQuery();
	protected abstract String getInsertQuery();

	protected void update() {
		DBManager.getInstance().updateBean(this);
	}

	protected void insert() {
		DBManager.getInstance().insertBean(this);
	}
	
	public void delete() {
		DBManager.getInstance().deleteBean(this);
	}

}
