package br.senac.objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Douglas
 */
public class PropertiesSystem extends Properties {

    private final String path = "config.properties";
    private final String name = "Configurações de Propriedades Sistema";

    public PropertiesSystem() {
    }

    private void LoadPropertiesFile() {
        File f = new File(path);

        try {
            if (f.isFile()) {
                FileInputStream file = new FileInputStream(f);
                this.load(file);
            } else {
                f.createNewFile();
                FileInputStream file = new FileInputStream(f);
                this.load(file);
            }
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLookAndFeel() {
        return this.getProperty("lookandfeel.name");
    }

    public void setLookAndFeel(String stylus) {
        gravar("lookandfeel.name", stylus);
    }

    public String getServer() {
        return this.getProperty("jdbc.server");
    }

    public void setServer(String server) {
        gravar("jdbc.server", server);
    }

    public String getDatabase() {
        return this.getProperty("jdbc.database");
    }

    public void setDatabase(String database) {
        gravar("jdbc.database", database);
    }

    public String getLogin() {
        return this.getProperty("jdbc.login");
    }

    public void setLogin(String login) {
        gravar("jdbc.login", login);
    }

    public String getSenha() {
        return this.getProperty("jdbc.senha");
    }

    public void setSenha(String password) {
        gravar("jdbc.senha", password);
    }

    public String getColor() {
        return this.getProperty("lookandfeel.color");
    }

    public void setColor(String color) {
        gravar("lookandfeel.color", color);
    }

    @Override
    public synchronized Object setProperty(String key, String value) {
        LoadPropertiesFile();
        return super.setProperty(key, value);
    }

    @Override
    public String getProperty(String key) {
        LoadPropertiesFile();
        return super.getProperty(key);
    }

    private void gravar(String key, String value) {
        this.setProperty(key,value);
        try {
            this.store(new FileOutputStream(path), name);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
