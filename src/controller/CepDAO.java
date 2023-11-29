package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.DAO;
import model.Cep;
import model.User;
import objects.ConnectionManager;
import view.MainScreen;

public class CepDAO implements DAO {

	private static CepDAO uniqueInstance;

	private CepDAO() {

	}
	
	public static synchronized CepDAO getInstance() {
		return uniqueInstance == null ? uniqueInstance = new CepDAO() : uniqueInstance;
	}

	@Override
	public boolean delete(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ArrayList<?> getAll() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean save(Object object) {
		Cep cep = (Cep) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao
					.prepareStatement("EXEC sp_insert_rc_endereco_cliente ?,?,?,?,?,?,?,?");
			instrucaoSQL.setString(1, cep.getCep());
			instrucaoSQL.setString(2, cep.getLogradouro());
			instrucaoSQL.setString(3, cep.getBairro());
			instrucaoSQL.setString(4, cep.getLocalidade());
			instrucaoSQL.setString(5, cep.getUf());
			instrucaoSQL.setInt(6, 0);
			instrucaoSQL.setString(7, cep.getComplemento());
			instrucaoSQL.setInt(8, User.getInstance().getId());
			instrucaoSQL.executeUpdate();
		} catch (SQLException | IllegalArgumentException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
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
	public ArrayList<?> getBy(String key) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

}
