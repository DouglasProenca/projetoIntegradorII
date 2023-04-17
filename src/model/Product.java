package model;

import java.util.Date;

public class Product extends Category {

	private int product_id;
	private String product_name;
	private float product_valor;
	private int product_qtd;
	private byte[] product_img;

	public Product() {

	}

	public Product(String nome, float valor, int quantidade, String Categoria, int id, String marca, String pais,
			Date date, String user) {
		super(Categoria, id, marca, pais, date, user);
		this.product_name = nome;
		this.product_valor = valor;
		this.product_valor = quantidade;
	}

	public Product(String nome, float valor, int quantidade, String Categoria, int id, String marca, String pais,
			Date date, String user, byte[] imagem) {
		super(Categoria, 0, marca, pais, date, user);
		this.product_id = id;
		this.product_name = nome;
		this.product_valor = valor;
		this.product_qtd = quantidade;
		this.product_img = imagem;
	}

	public Product(String nome, float valor, int quantidade, int category_id, int brand_id, Date date, String user,
			byte[] imagem) {
		super(category_id, brand_id, date, user);
		this.product_name = nome;
		this.product_valor = valor;
		this.product_qtd = quantidade;
		this.product_img = imagem;
	}

	public Product(int product_id, String nome, float valor, int quantidade, int category_id, int brand_id, Date date,
			String user, byte[] imagem) {
		super(category_id, brand_id, date, user);
		this.product_id = product_id;
		this.product_name = nome;
		this.product_valor = valor;
		this.product_qtd = quantidade;
		this.product_img = imagem;
	}

	public int getProduct_id() {
		return product_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public float getProduct_valor() {
		return product_valor;
	}

	public int getProduct_qtd() {
		return product_qtd;
	}

	public byte[] getProduct_img() {
		return product_img;
	}

	@Override
	public String toString() {
		return product_id + ";" + product_name + ";" + getBrand_name() + ";" + getCategory_name() + ";" + product_valor
				+ ";" + product_qtd + ";" + getDate() + ";" + getUser() + ";";
	}
}