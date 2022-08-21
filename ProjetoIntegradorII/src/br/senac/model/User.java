package br.senac.model;

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

    private User() {
    }

    public static synchronized User getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new User();
        }
        return uniqueInstance;
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
}
