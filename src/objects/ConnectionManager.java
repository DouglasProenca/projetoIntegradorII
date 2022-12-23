package objects;

import interfaces.ConnectionDB;
import view.MainScreen;
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
            msg(ex);
        }
    }

    @Override
    public Connection getConexao() {
        String user = ps.getLogin();
        String password = ps.getSenha();
        String database = ps.getDatabase();
        String server = ps.getServer();
        String url = "jdbc:sqlserver://127.0.0.1" + "\\" + server + ":1433;databaseName=" + database;
        return connection(url, user, password);
    }

    @Override
    public Connection getConexaoTest(String user, String password, String database, String server) {
        String url = "jdbc:sqlserver://127.0.0.1" + "\\" + server + ":1433;databaseName=" + database;
        return connection(url, user, password);
    }

    private Connection connection(String url, String user, String password) {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            msg(ex);
        }
        return null;
    }

    public static synchronized ConnectionManager getInstance() {
        return uniqueInstance == null ? uniqueInstance = new ConnectionManager() {
        } : uniqueInstance;
    }

    private static void msg(Exception ex) {
        JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
    }
}
