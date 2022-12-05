package br.senac.model;

import java.util.Date;

/**
 *
 * @author Douglas
 */
public class Category extends Brand {

    private String Categoria;

    public Category() {
    }

    public Category(String Categoria, int id, String marca, String pais, Date date, String user) {
        super(id, marca, pais, date, user);
        this.Categoria = Categoria;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    @Override
    public String toString() {
        return getId() + ";" + Categoria + ";" + getDate() + ";" + getUser();
    }

}
