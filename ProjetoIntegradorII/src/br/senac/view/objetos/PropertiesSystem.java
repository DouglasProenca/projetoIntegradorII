package br.senac.view.objetos;

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

    public void changeLookAndFeel(String name) {
        try {
            Propriedade.setLookAndFeel(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeServer(String name) {
        try {
            Propriedade.setServer(name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeDatabase(String name) {
        try {
            Propriedade.setDatabase(name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeLogin(String name) {
        try {
            Propriedade.setLogin(name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeSenha(String name) {
        try {
            Propriedade.setSenha(name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void changeColor(String name) {
        try {
            Propriedade.setColor(name);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static class Propriedade {

        private static Properties prop;
        private static String path = "config.properties";

        private static void LoadPropertiesFile() throws IOException {
            prop = new Properties();
            File f = new File(path);

            if (f.isFile()) {
                FileInputStream file = new FileInputStream(f);
                prop.load(file);
            } else {
                f.createNewFile();
                FileInputStream file = new FileInputStream(f);
                prop.load(file);
                setLookAndFeel("");
            }
        }

        public static String getLookAndFeel() throws IOException {
            LoadPropertiesFile();
            return prop.getProperty("lookandfeel.name");
        }

        public static void setLookAndFeel(String name) throws IOException {
            LoadPropertiesFile();
            prop.setProperty("lookandfeel.name", name);
            prop.store(new FileOutputStream(path), "");
        }

        public static String getServer() {
            try {
                LoadPropertiesFile();
            } catch (IOException ex) {
                Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prop.getProperty("jdbc.server");
        }

        public static void setServer(String name) throws IOException {
            LoadPropertiesFile();
            prop.setProperty("jdbc.server", name);
            prop.store(new FileOutputStream(path), "");
        }

        public static String getDatabase() {
            try {
                LoadPropertiesFile();
            } catch (IOException ex) {
                Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prop.getProperty("jdbc.database");
        }

        public static void setDatabase(String name) throws IOException {
            LoadPropertiesFile();
            prop.setProperty("jdbc.database", name);
            prop.store(new FileOutputStream(path), "");
        }

        public static String getLogin() {
            try {
                LoadPropertiesFile();
            } catch (IOException ex) {
                Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prop.getProperty("jdbc.login");
        }

        public static void setLogin(String name) throws IOException {
            LoadPropertiesFile();
            prop.setProperty("jdbc.login", name);
            prop.store(new FileOutputStream(path), "");
        }

        public static String getSenha() {
            try {
                LoadPropertiesFile();
            } catch (IOException ex) {
                Logger.getLogger(PropertiesSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            return prop.getProperty("jdbc.senha");
        }

        public static void setSenha(String name) throws IOException {
            LoadPropertiesFile();
            prop.setProperty("jdbc.senha", name);
            prop.store(new FileOutputStream(path), "");
        }

        public static String getColor() throws IOException {
            LoadPropertiesFile();
            return prop.getProperty("lookandfeel.color");
        }

        public static void setColor(String name) throws IOException {
            LoadPropertiesFile();
            prop.setProperty("lookandfeel.color", name);
            prop.store(new FileOutputStream(path), "");
        }
    }
}