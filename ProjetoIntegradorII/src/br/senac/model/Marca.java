package br.senac.model;

import java.util.Date;



/**
 *
 * @author Douglas
 */
public class Marca {

    private int id;
    private String marca;
    private String pais;
    private Date date;
    private String user;

    public Marca() {

    }

    public Marca(String marca, String pais) {
        this.marca = marca;
        this.pais = pais;
    }

    public Marca(int id, String marca, String pais, Date date, String user) {
        this.id = id;
        this.marca = marca;
        this.pais = pais;
        this.date = date;
        this.user = user;
    }

    public Marca(int id, String marca, String pais) {
        this.id = id;
        this.marca = marca;
        this.pais = pais;
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
}
