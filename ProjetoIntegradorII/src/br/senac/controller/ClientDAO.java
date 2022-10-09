package br.senac.controller;

import br.senac.interfaces.DAO;
import br.senac.model.Client;
import br.senac.objects.ConnectionManager;
import br.senac.view.MainScreen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public class ClientDAO implements DAO {

    private static ClientDAO uniqueInstance;

    public static synchronized ClientDAO getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ClientDAO();
        }
        return uniqueInstance;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Client> getAll() {
        ArrayList<Client> clientList = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("select c.id\n"
                    + "	 , c.nome\n"
                    + "	 , c.cpf\n"
                    + "	 , c.[data]\n"
                    + "	 , u.[user]\n"
                    + "from rc_cliente c\n"
                    + "inner join rc_user u\n"
                    + "	on u.id = c.[user]");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Client p = new Client(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"),
                        rs.getString("user"), rs.getDate("data"));
                clientList.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            clientList = null;
        }
        return clientList;
    }

    @Override
    public boolean save(Object object) {
        try {
            Client client = (Client) object;

            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_cliente values(?,?,?,?)");

            instrucaoSQL.setString(1, client.getNome());
            instrucaoSQL.setString(2, client.getCpf());
            instrucaoSQL.setInt(3, Integer.valueOf(client.getUser()));
            instrucaoSQL.setDate(4, new java.sql.Date(client.getData().getTime()));

            instrucaoSQL.executeUpdate();

        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            return (false);
        } 
        return (true);
    }

    @Override
    public boolean alter(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Client> getBy(String key) {
        ArrayList<Client> ClientList = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getInstance().getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("select c.id\n"
                    + "	 , c.nome\n"
                    + "	 , c.cpf\n"
                    + "	 , c.[data]\n"
                    + "	 , u.[user]\n"
                    + "from rc_cliente c\n"
                    + "inner join rc_user u\n"
                    + "	on u.id = c.[user]\n"
                    + "where c.nome like ?\n"
                    + "or c.cpf like ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, "%" + key + '%');
            instrucaoSQL.setString(2, "%" + key + '%');

            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                Client p = new Client(rs.getInt("id"), rs.getString("nome"), rs.getString("cpf"),
                        rs.getString("user"), rs.getDate("data"));
                ClientList.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            ClientList = null;
        }
        return ClientList;
    }

}
