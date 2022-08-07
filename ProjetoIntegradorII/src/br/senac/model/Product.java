package br.senac.model;

import java.util.Date;

/**
 *
 * @author Douglas
 */
public class Product extends Brand {

    private String nome;
    private float valor;
    private int quantidade;

    public Product() {
        super();
    }

    public Product(int id, String nome, float valor, int quantidade, String marca,String pais, String user, Date Data) {
        super(id, marca, pais, Data, user);
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

}
