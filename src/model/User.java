package model;

import java.util.Date;

/**
 *
 * @author Douglas
 */
public class User {

    private static User uniqueInstance;
    private int id;
    private String mail;
    private String mailPassword;
    private String user;
    private String password;
    private Date date;

    public User() {
    }

    public User(int id, String mail, String mailPassword, String user, String password, Date date) {
        this.id = id;
        this.mail = mail;
        this.mailPassword = mailPassword;
        this.user = user;
        this.password = password;
        this.date = date;
    }
    
    public static synchronized User getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new User();
        }
        return uniqueInstance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMailPassword() {
        return mailPassword;
    }

    public void setMailPassword(String mailPassword) {
        this.mailPassword = mailPassword;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmpty() {
        this.user = null;
        this.password = null;
        this.id = 0;
        this.mail = null;
        this.mailPassword = null;
    }
}
