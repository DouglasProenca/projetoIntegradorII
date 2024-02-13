package interfaces;

import java.sql.Connection;

public interface ConnectionDB {

    /**
     *
     * @return connection
     */
    public abstract Connection getConexao();

    /**
     *
     * @param user
     * @param password
     * @param database
     * @param server
     * @return connection
     */
    public abstract Connection getConexaoTest(String user, String password, String database, String port, String server);
}
