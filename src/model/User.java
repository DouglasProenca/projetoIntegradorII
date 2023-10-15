package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import objects.Utils;

public class User {

	private static User uniqueInstance;
	private int id;
	private String mail;
	private String mailPassword;
	private String user;
	private String password;
	private Date date;
	private boolean enabled;
	private boolean locked;
	private boolean credExpired;
	private boolean expired;

	public User() {
	}
	
	public User(int id, String mail, String mailPassword, String user, String password, Date date, boolean enabled,
			boolean locked, boolean credExpired, boolean expired) {
		super();
		this.id = id;
		this.mail = mail;
		this.mailPassword = mailPassword;
		this.user = user;
		this.password = password;
		this.date = date;
		this.enabled = enabled;
		this.locked = locked;
		this.credExpired = credExpired;
		this.expired = expired;
	}

	public User(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.mail = rs.getString("mail");
		this.mailPassword = Utils.decodeString(rs.getString("mailpassword"));
		this.user = rs.getString("user");
		this.password = rs.getString("password");
		this.date = rs.getDate("data");
		this.enabled = rs.getBoolean("enabled");
		this.locked = rs.getBoolean("locked");
		this.credExpired = rs.getBoolean("cred_expired");
		this.expired = rs.getBoolean("expired");
	}

	public static synchronized User getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new User();
		}
		return uniqueInstance;
	}

	public Date getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMailPassword() {
		return mailPassword;
	}

	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isCredExpired() {
		return credExpired;
	}

	public void setCredExpired(boolean credExpired) {
		this.credExpired = credExpired;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public void setEmpty() {
		this.user = null;
		this.password = null;
		this.id = 0;
		this.mail = null;
		this.mailPassword = null;
	}
}
