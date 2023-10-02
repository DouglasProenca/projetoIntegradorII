package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Brand extends Country {

	private int brand_id;
	private String brand_name;
	private Date date;
	private String user;

	public Brand() {

	}

	public Brand(int brand_id, String brand_name, String country_nome, Date date, String user) {
		super(country_nome);
		this.brand_id = brand_id;
		this.brand_name = brand_name;
		this.date = date;
		this.user = user;
	}
	
	public Brand(String brand_name, String country_nome, Date date, String user) {
		super(country_nome);
		this.brand_name = brand_name;
		this.date = date;
		this.user = user;
	}
	
	public Brand(int brand_id, String brand_name, int country_id, Date date, String user) {
		super(country_id);
		this.brand_id = brand_id;
		this.brand_name = brand_name;
		this.date = date;
		this.user = user;
	}
	
	public Brand(String brand_name, int country_id, Date date, String user) {
		super(country_id);
		this.brand_name = brand_name;
		this.date = date;
		this.user = user;
	}
	
	public Brand(int brand_id, Date date, String user) {
		this.brand_id = brand_id;
		this.date = date;
		this.user = user;
	}
	
	public Brand(Date date, String user) {
		this.date = date;
		this.user = user;
	}
	
	public Brand(ResultSet rs) throws SQLException {
		super(rs.getString("paisNome"));
		this.brand_id = rs.getInt("id");
		this.brand_name = rs.getString("marca");
		this.date = rs.getDate("date");
		this.user = rs.getString("user");
	}
	
	public int getBrand_id() {
		return brand_id;
	}

	public String getBrand_name() {
		return brand_name;
	}
	

	public Date getDate() {
		return date;
	}


	public String getUser() {
		return user;
	}

	@Override
	public String toString() {
		return brand_id + ";" + brand_name + ";" + getCountry_nome() + ";" + date + ";" + user + ";";
	}

}
