package database;

public class AccountSubscriptionBean extends Bean {

	private static final long serialVersionUID = 1L;
	private static final String table = "usersubscription";

	private String account;
	private long restaurant;

	public AccountSubscriptionBean() {
		super();
	}

	public AccountSubscriptionBean(String account, long restaurant) {
		super();
		this.account = account;
		this.restaurant = restaurant;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(long restaurant) {
		this.restaurant = restaurant;
	}

	public static String getTable() {
		return table;
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE account = '" + account + "' AND restaurant = " + restaurant;
	}

	@Override
	protected String getUpdateQuery() {
		return null;
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (account, restaurant) VALUES ('" + account + "', " + restaurant + ")";
	}

}
