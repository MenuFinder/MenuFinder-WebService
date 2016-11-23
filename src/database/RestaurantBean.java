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
		isNewRecord = false;
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
		isNewRecord = true;
	}

	public RestaurantBean() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		isNewRecord = false;
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

	protected String getUpdateQuery() {
		return "UPDATE " + table + " SET name = '" + name + "', cif = '" + cif + "', address = '" + address
				+ "', city = '" + city + "', postalcode = " + postalCode + ", state = '" + state + "', country = '"
				+ country + "', email = '" + email + "', phone '" + phone + "' WHERE id = " + id;
	}

	protected String getInsertQuery() {
		return "INSERT INTO " + table + " (name, cif, address, city, " + "postalcode, state, country, "
				+ "email, phone, account) VALUES ('" + name + "', '" + cif + "', '" + address + "', '" + city + "', "
				+ postalCode + ", '" + state + "', '" + country + "', '" + email + "', '" + phone + "', '" + account
				+ "')";
	}

	public static RestaurantBean getRestaurantById(long id) {
		try {
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table + " WHERE id = " + id);
			rs.next();
			return getRestaurantFromRS(rs);
		} catch (SQLException ex) {
			System.err.println("Error retrieving restaurants with id: '" + id + "'.");
			ex.printStackTrace();
			return null;
		}
	}

	public static List<RestaurantBean> getAllBeans() {
		List<RestaurantBean> restaurants = new ArrayList<>();
		try {
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table);
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
			DBManager db = DBManager.getInstance();
			ResultSet rs = db.executeQuery("SELECT * FROM " + table + " WHERE account = '" + accountId + "'");
			while (rs.next()) {
				restaurants.add(getRestaurantFromRS(rs));
			}
		} catch (SQLException ex) {
			System.err.println("Error retrieving restaurants list of account '" + accountId + "'.");
			ex.printStackTrace();
		}
		return restaurants;
	}

	public String getAllRowsQuery() {
		return "SELECT * FROM " + table;
	}

	private static RestaurantBean getRestaurantFromRS(ResultSet rs) {
		try {
			return new RestaurantBean(rs.getLong("id"), rs.getString("name"), rs.getString("cif"),
					rs.getString("address"), rs.getString("city"), rs.getString("postalcode"), rs.getString("state"),
					rs.getString("country"), rs.getString("email"), rs.getString("phone"), rs.getString("account"));
		} catch (SQLException e) {
			System.err.println("Error retrieving bean.");
			e.printStackTrace();
			return null;
		}
	}

}
