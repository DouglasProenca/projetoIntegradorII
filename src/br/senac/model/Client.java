package br.senac.model;

import java.util.Date;

/**
 *
 * @author Douglas
 */
public class Client {

    private int id;
    private String nome;
    private String cpf;
    private String user;
    private Date data;

    public Client() {
    }

    public Client(int id, String nome, String cpf, String user, Date data) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.user = user;
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
