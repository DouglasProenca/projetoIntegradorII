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
public class PropertiesSystem {

    private static Properties prop;
    private static final String path = "config.properties";

    private static void LoadPropertiesFile() {
        prop = new Properties();
        File f = new File(path);

        try {
            if (f.isFile()) {
                FileInputStream file;
                file = new FileInputStream(f);

                prop.load(file);
            } else {
                f.createNewFile();
                FileInputStream file = new FileInputStream(f);
                prop.load(file);
                setLookAndFeel("");
            }
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getLookAndFeel() {
        LoadPropertiesFile();
        return prop.getProperty("lookandfeel.name");
    }

    public static void setLookAndFeel(String name) {
        try {
            LoadPropertiesFile();
            prop.setProperty("lookandfeel.name", name);
            prop.store(new FileOutputStream(path), "Configurações de Propriedades Sistema");
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getServer() {
        LoadPropertiesFile();
        return prop.getProperty("jdbc.server");
    }

    public void setServer(String name) {
        LoadPropertiesFile();
        prop.setProperty("jdbc.server", name);
        try {
            prop.store(new FileOutputStream(path), "Configurações de Propriedades Sistema");
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDatabase() {
        LoadPropertiesFile();
        return prop.getProperty("jdbc.database");
    }

    public void setDatabase(String name) {
        LoadPropertiesFile();
        prop.setProperty("jdbc.database", name);
        try {
            prop.store(new FileOutputStream(path), "Configurações de Propriedades Sistema");
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLogin() {
        LoadPropertiesFile();
        return prop.getProperty("jdbc.login");
    }

    public void setLogin(String name) {
        LoadPropertiesFile();
        prop.setProperty("jdbc.login", name);
        try {
            prop.store(new FileOutputStream(path), "Configurações de Propriedades Sistema");
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSenha() {
        LoadPropertiesFile();
        return prop.getProperty("jdbc.senha");
    }

    public void setSenha(String name) {
        LoadPropertiesFile();
        prop.setProperty("jdbc.senha", name);
        try {
            prop.store(new FileOutputStream(path), "Configurações de Propriedades Sistema");
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getColor() {
        LoadPropertiesFile();
        return prop.getProperty("lookandfeel.color");
    }

    public void setColor(String name) {
        LoadPropertiesFile();
        prop.setProperty("lookandfeel.color", name);
        try {
            prop.store(new FileOutputStream(path), "Configurações de Propriedades Sistema");
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
