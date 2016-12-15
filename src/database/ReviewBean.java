package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewBean extends Bean {

	private static final long serialVersionUID = 1L;
	private static final String table = "review";

	private long id;
	private String review;
	private String parentType;
	private long parentId;
	private String account;

	public ReviewBean() {
		super();
	}

	public ReviewBean(String review, String parentType, long parentId, String account) {
		super();
		this.review = review;
		this.parentType = parentType;
		this.parentId = parentId;
		this.account = account;
	}

	public ReviewBean(long id, String review, String parentType, long parentId, String account) {
		super();
		this.id = id;
		this.review = review;
		this.parentType = parentType;
		this.parentId = parentId;
		this.account = account;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE id = " + id;
	}

	@Override
	protected String getUpdateQuery() {
		return "UPDATE " + table + " SET review = '" + review + "' WHERE id = " + id;
	}

	@Override
	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (review, parent_type, parent_id, account) VALUES ('" + review + "', '"
				+ parentType + "', " + parentId + ", '" + account + "')";
	}

	protected static ReviewBean getReviewBeanFromRS(ResultSet rs) throws SQLException {
		return new ReviewBean(rs.getLong("id"), rs.getString("review"), rs.getString("parent_type"),
				rs.getLong("parent_id"), rs.getString("account"));
	}

	protected static List<ReviewBean> getReviewsOfParent(String parentType, long parentId) {
		List<ReviewBean> reviews = new ArrayList<>();
		try {
			ResultSet rs = select("SELECT * FROM " + table + " WHERE parent_type = '" + parentType
					+ "' AND parent_id = " + parentId);
			while (rs.next()) {
				reviews.add(getReviewBeanFromRS(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error retrieving reviews list.");
			ex.printStackTrace();
		}
		return reviews;
	}

	public static ReviewBean getReviewById(long id) throws SQLException {
		ResultSet rs = select("SELECT * FROM " + table + " WHERE id = " + id);
		rs.next();
		return getReviewBeanFromRS(rs);
	}

}
