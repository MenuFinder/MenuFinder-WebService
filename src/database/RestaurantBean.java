package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RestaurantBean extends Bean {

	private static final long serialVersionUID = 1L;
	private static final String table = "restaurant";

	private long id;
	private String name;
	private String cif;
	private String address;
	private String city;
	private String postalCode;
	private String state;
	private String country;
	private String email;
	private String phone;
	private String account;
	private double score;

	public RestaurantBean(long id, String name, String cif, String address, String city, String postalCode,
			String state, String country, String email, String phone, String account) {
		super();
		this.id = id;
		this.name = name;
		this.cif = cif;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.state = state;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.account = account;
		this.score = 0;
	}

	public RestaurantBean(String name, String cif, String address, String city, String postalCode, String state,
			String country, String email, String phone, String account) {
		super();
		this.name = name;
		this.cif = cif;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.state = state;
		this.country = country;
		this.email = email;
		this.phone = phone;
		this.account = account;
		id = -1;
		this.score = 0;
	}

	public RestaurantBean() {
		super();
		this.score = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	protected String getDeleteQuery() {
		return "DELETE FROM " + table + " WHERE id = " + id;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	protected String getUpdateQuery() {
		return "UPDATE " + table + " SET name = '" + name + "', cif = '" + cif + "', address = '" + address
				+ "', city = '" + city + "', postalcode = '" + postalCode + "', state = '" + state + "', country = '"
				+ country + "', email = '" + email + "', phone = '" + phone + "' WHERE id = " + id;
	}

	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (name, cif, address, city, " + "postalcode, state, country, "
				+ "email, phone, account) VALUES ('" + name + "', '" + cif + "', '" + address + "', '" + city + "', '"
				+ postalCode + "', '" + state + "', '" + country + "', '" + email + "', '" + phone + "', '" + account
				+ "')";
	}

	public static RestaurantBean getRestaurantById(long id) throws SQLException {
		ResultSet rs = select("SELECT * FROM " + table + " WHERE id = " + id);
		rs.next();
		return getRestaurantFromRS(rs);
	}

	public static List<RestaurantBean> getAllBeans() {
		List<RestaurantBean> restaurants = new ArrayList<>();
		try {
			ResultSet rs = select("SELECT * FROM " + table);
			while (rs.next()) {
				restaurants.add(getRestaurantFromRS(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error retrieving restaurants list.");
			ex.printStackTrace();
		}
		return restaurants;
	}

	public static List<RestaurantBean> getRestaurantsOfAccount(String accountId) {
		List<RestaurantBean> restaurants = new ArrayList<>();
		try {
			ResultSet rs = select("SELECT * FROM " + table + " WHERE account = '" + accountId + "'");
			while (rs.next()) {
				restaurants.add(getRestaurantFromRS(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error retrieving restaurants list of account '" + accountId + "'.");
			ex.printStackTrace();
		}
		return restaurants;
	}

	private static RestaurantBean getRestaurantFromRS(ResultSet rs) throws SQLException {
		RestaurantBean restaurant = new RestaurantBean();
		restaurant.setId(rs.getLong("id"));
		restaurant.setName(rs.getString("name"));
		restaurant.setCif(rs.getString("cif"));
		restaurant.setAddress(rs.getString("address"));
		restaurant.setCity(rs.getString("city"));
		restaurant.setPostalCode(rs.getString("postalcode"));
		restaurant.setState(rs.getString("state"));
		restaurant.setCountry(rs.getString("country"));
		restaurant.setEmail(rs.getString("email"));
		restaurant.setPhone(rs.getString("phone"));
		restaurant.setAccount(rs.getString("account"));
		restaurant.setScore(rs.getDouble("score"));
		return restaurant;
	}

	public static List<RestaurantBean> getSubscribedRestaurantsOfAccount(String accountId) {
		List<RestaurantBean> restaurants = new ArrayList<>();
		try {
			ResultSet rs = select("SELECT r.* FROM " + table + " r, " + AccountSubscriptionBean.getTable()
				+ " us WHERE us.account = '" + accountId + "' AND r.id = us.restaurant");
			while (rs.next()) {
				restaurants.add(getRestaurantFromRS(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error retrieving restaurants list of account '" + accountId + "'.");
			ex.printStackTrace();
		}
		return restaurants;
	}

	public static double getScoreOfRestaurant(long restaurantId) throws SQLException {
		ResultSet rs = select("SELECT AVG(m.score) AS score FROM " + MenuBean.getTable() + " m, " + table
				+ " r WHERE r.id = m.restaurant AND r.id = " + restaurantId);
		rs.next();
		return rs.getDouble("score");
	}

}
