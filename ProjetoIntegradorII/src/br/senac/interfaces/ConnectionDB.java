package br.senac.interfaces;

import java.sql.Connection;

/**
 *
 * @author Douglas
 */
public interface ConnectionDB {

    public Connection getConexao();

    public Connection getConexaoTest(String user, String password, String database, String server);
    }
