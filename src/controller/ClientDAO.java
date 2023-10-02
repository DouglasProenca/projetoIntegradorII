package controller;

import interfaces.DAO;
import model.Client;
import objects.ConnectionManager;
import view.MainScreen;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClientDAO implements DAO {

	private static ClientDAO uniqueInstance;
	
	private ClientDAO() {

	}

	public static synchronized ClientDAO getInstance() {
		return uniqueInstance == null ? uniqueInstance = new ClientDAO() : uniqueInstance;
	}

	@Override
	public boolean delete(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ArrayList<Client> getAll() {
		ArrayList<Client> clientList = new ArrayList<>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT c.id ");
		query.append("	 , c.nome ");
		query.append("	 , c.cpf ");
		query.append("	 , c.[data] ");
		query.append("	 , u.[user] ");
		query.append("FROM rc_cliente c ");
		query.append("INNER JOIN rc_user u ");
		query.append("	ON u.id = c.[user]");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				Client client = new Client(rs);
				clientList.add(client);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return clientList;
	}

	@Override
	public boolean save(Object object) {
		Client client = (Client) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("KNSERT INTO rc_cliente VALUES(?,?,?,?)");

			instrucaoSQL.setString(1, client.getNome());
			instrucaoSQL.setString(2, client.getCpf());
			instrucaoSQL.setInt(3, Integer.valueOf(client.getUser()));
			instrucaoSQL.setDate(4, new Date(client.getData().getTime()));
			instrucaoSQL.executeUpdate();
		} catch (SQLException | IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}

	@Override
	public boolean alter(Object object) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ArrayList<Client> getBy(String key) {
		ArrayList<Client> ClientList = new ArrayList<>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT c.id ");
		query.append("	 , c.nome ");
		query.append("	 , c.cpf ");
		query.append("	 , c.[data] ");
		query.append("	 , u.[user] ");
		query.append("FROM rc_cliente c ");
		query.append("INNER JOIN rc_user u ");
		query.append("	ON u.id = c.[user] ");
		query.append("WHERE c.nome LIKE ? ");
		query.append("  OR c.cpf LIKE ? ");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			instrucaoSQL.setString(1, "%" + key + '%');
			instrucaoSQL.setString(2, "%" + key + '%');

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				Client client = new Client(rs);
				ClientList.add(client);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return ClientList;
	}
}
