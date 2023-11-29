package controller;

import interfaces.DAO;
import model.Sale;
import objects.ConnectionManager;
import view.MainScreen;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class SaleDAO implements DAO {

	private static SaleDAO uniqueInstance;
	
	private SaleDAO() {

	}

	public static synchronized SaleDAO getInstance() {
		return uniqueInstance == null ? uniqueInstance = new SaleDAO() : uniqueInstance;
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
		Sale sale = (Sale) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("INSERT INTO rc_venda VALUES(?,?,?,?)");
			instrucaoSQL.setInt(1, sale.getId_cliente());
			instrucaoSQL.setFloat(2, sale.getTotal());
			instrucaoSQL.setDate(3, new Date(sale.getData().getTime()));
			instrucaoSQL.setInt(4, sale.getUser());
			instrucaoSQL.executeUpdate();
		} catch (SQLException ex) {
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

	public boolean saveList(int produto, float valor, int quantidade, int user) {
		Connection conexao = ConnectionManager.getInstance().getConexao();
		
		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("EXEC sp_insert_lista_venda ?,?,?,?");
			instrucaoSQL.setInt(1, produto);
			instrucaoSQL.setInt(2, quantidade);
			instrucaoSQL.setFloat(3, valor);
			instrucaoSQL.setInt(4, user);
			instrucaoSQL.executeUpdate();
		} catch (SQLException | IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}

	public int returnSale() {
		int id = 0;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT top(1)id ");
		query.append("FROM rc_venda ");
		query.append("ORDER BY id DESC");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return id;
	}
}
