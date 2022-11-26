package br.senac.model;

import java.util.Date;

/**
 *
 * @author Douglas
 */
public class Product extends Category {

    private String nome;
    private float valor;
    private int quantidade;

    public Product() {
        super();
    }

    public Product(String nome, float valor, int quantidade, String Categoria, int id, String marca, String pais, Date date, String user) {
        super(Categoria, id, marca, pais, date, user);
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return getId() + ";" + nome + ";" + getMarca() + ";" + getCategoria() + ";"
                + valor + ";" + quantidade + ";" + getDate() + ";" + getUser() + ";";
    }
}
