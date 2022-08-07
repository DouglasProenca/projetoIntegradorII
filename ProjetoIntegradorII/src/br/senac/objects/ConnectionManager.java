package br.senac.objects;

import br.senac.view.MainScreen;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public class ConnectionManager {

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Connection getConexao() {
        String user = PropertiesSystem.Propriedade.getLogin();
        String password = PropertiesSystem.Propriedade.getSenha();
        String database = PropertiesSystem.Propriedade.getDatabase();
        String server = PropertiesSystem.Propriedade.getServer();
        String url = "jdbc:sqlserver://127.0.0.1" + "\\" + server + ":1433;databaseName=" + database;

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }

    public static Connection getConexaoTest(String user, String password, String database, String server) {
        String url = "jdbc:sqlserver://127.0.0.1" + "\\" + server + ":1433;databaseName=" + database;

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return con;
    }
}