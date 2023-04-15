package model;

import java.util.Date;

public class Brand extends Object {

	private int id;
	private String marca;
	private String pais;
	private Date date;
	private String user;

	public Brand() {

	}

	public Brand(int id, String marca, String pais, Date date, String user) {
		this.id = id;
		this.marca = marca;
		this.pais = pais;
		this.date = date;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public String ComboboxPais() {
		return pais;
	}


	public String tooString() {
		return id + ";" + marca + ";" + pais + ";" + date + ";" + user + ";";
	}
	
	@Override
	public String toString() {
		return id + ";" + marca + ";" + pais + ";" + date + ";" + user + ";";
	}

}
