package model;

import java.util.Date;

public class Sale {

    private int id;
    private int id_cliente;
    private float total;
    private Date data;
    private int user;

    public Sale() {
    }

    public Sale(int id, int id_cliente, float total, Date data, int user) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.total = total;
        this.data = data;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

}
