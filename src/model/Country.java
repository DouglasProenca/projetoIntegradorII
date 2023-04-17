package model;

public class Country extends Object {

	private int country_id;
	private String country_nome;
	
	public  Country() {
		
	}

	public Country(int country_id, String country_nome) {
		this.country_id = country_id;
		this.country_nome = country_nome;
	}
	
	public Country(int country_id) {
		this.country_id = country_id;
	}
	
	public Country(String country_nome) {
		this.country_nome = country_nome;
	}


	public int getCountry_id() {
		return country_id;
	}

	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}

	public String getCountry_nome() {
		return country_nome;
	}

	public void setCountry_nome(String country_nome) {
		this.country_nome = country_nome;
	}
	
}
