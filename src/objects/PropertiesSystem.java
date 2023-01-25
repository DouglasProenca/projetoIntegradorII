package objects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


@SuppressWarnings("serial")
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
        return this.getProperty(Utils.codeString("lookandfeel.name"));
    }

    public void setLookAndFeel(String stylus) {
        gravar("lookandfeel.name", stylus);
    }

    public String getServer() {
        return this.getProperty(Utils.codeString("jdbc.server"));
    }

    public void setServer(String server) {
        gravar("jdbc.server", server);
    }

    public String getDatabase() {
        return this.getProperty(Utils.codeString("jdbc.database"));
    }
    
    public String getPort() {
        return this.getProperty(Utils.codeString("jdbc.port"));
    }

    public void setDatabase(String database) {
        gravar("jdbc.database", database);
    }
    
    public void setPort(String port) {
        gravar("jdbc.port", port);
    }

    public String getLogin() {
        return this.getProperty(Utils.codeString("jdbc.login"));
    }

    public void setLogin(String login) {
        gravar("jdbc.login", login);
    }

    public String getSenha() {
        return this.getProperty(Utils.codeString("jdbc.senha"));
    }

    public void setSenha(String password) {
        gravar("jdbc.senha", password);
    }

    public String getColor() {
        return this.getProperty(Utils.codeString("lookandfeel.color"));
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
        return Utils.decodeString(super.getProperty(key));
    }

    private void gravar(String key, String value) {
        this.setProperty(Utils.codeString(key),Utils.codeString(value));
        try {
            this.store(new FileOutputStream(path), name);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
