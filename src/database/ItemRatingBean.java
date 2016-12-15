package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRatingBean extends Bean {

	private static final long serialVersionUID = 1L;
	private static final String table = "itemrating";

	private long id;
	private double score;
	private String account;
	private long item;

	public ItemRatingBean() {
		super();
	}

	public ItemRatingBean(double score, String account, long item) {
		super();
		this.score = score;
		this.account = account;
		this.item = item;
	}

	public ItemRatingBean(long id, double score, String account, long item) {
		super();
		this.id = id;
		this.score = score;
		this.account = account;
		this.item = item;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public long getItem() {
		return item;
	}

	public void setItem(long item) {
		this.item = item;
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE id = " + id;
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE " + table + " SET score = '" + score + "' WHERE id = " + id;
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (score, account, item) VALUES (" + score + ", '" + account + "', " + item
				+ ")";
	}

	private static ItemRatingBean getItemRatingFromRS(ResultSet rs) throws SQLException {
		return new ItemRatingBean(rs.getLong("id"), rs.getDouble("score"), rs.getString("account"),
				rs.getLong("item"));
	}
	
	public static List<ItemRatingBean> getRatingsOfItem(long itemId) {
		List<ItemRatingBean> ratings = new ArrayList<>();
		try {
			ResultSet rs = select("SELECT * FROM " + table + " WHERE item = " + itemId);
			while (rs.next()) {
				ratings.add(getItemRatingFromRS(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error retrieving ratings list.");
			ex.printStackTrace();
		}
		return ratings;
	}

	public static double getItemRatingOfItem(long itemId) {
		double ratins = 0;
		List<ItemRatingBean> itemratings = getRatingsOfItem(itemId);
		for (ItemRatingBean rating : itemratings) {
			ratins += rating.getScore();
		}
		return itemratings.size() > 0 ? (ratins / itemratings.size()) : 0;
	}

}
