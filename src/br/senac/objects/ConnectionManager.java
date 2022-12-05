package br.senac.objects;

import br.senac.interfaces.ConnectionDB;
import br.senac.view.MainScreen;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public abstract class ConnectionManager implements ConnectionDB {

    private static ConnectionManager uniqueInstance;
    private final PropertiesSystem ps = new PropertiesSystem();

    private ConnectionManager() {
    }

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public Connection getConexao() {
        String user = ps.getLogin();
        String password = ps.getSenha();
        String database = ps.getDatabase();
        String server = ps.getServer();
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

    @Override
    public Connection getConexaoTest(String user, String password, String database, String server) {
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

    public static synchronized ConnectionManager getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ConnectionManager() {
            };
        }
        return uniqueInstance;
    }
}
