package controller;

import interfaces.DAO;
import model.Category;
import objects.ConnectionManager;
import view.MainScreen;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class CategoryDAO implements DAO {

	private static CategoryDAO uniqueInstance;

	private CategoryDAO() {

	}

	public static synchronized CategoryDAO getInstance() {
		return uniqueInstance == null ? uniqueInstance = new CategoryDAO() : uniqueInstance;
	}

	@Override
	public boolean delete(int id) {
		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_categoria WHERE id = ?");
			instrucaoSQL.setInt(1, id);
			instrucaoSQL.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}

	@Override
	public ArrayList<Category> getAll() {
		ArrayList<Category> categoryList = new ArrayList<>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT c.id ");
		query.append("     , c.categoria ");
		query.append("     , c.[data] ");
		query.append("     , u.[user] ");
		query.append("FROM rc_categoria c ");
		query.append("INNER JOIN rc_user u ");
		query.append("   ON c.[user] = u.id");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				Category category = new Category(rs);

				categoryList.add(category);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return categoryList;
	}

	@Override
	public ArrayList<Category> getBy(String key) {
		ArrayList<Category> categoryList = new ArrayList<>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT c.id ");
		query.append("     , c.categoria ");
		query.append("     , c.[data] ");
		query.append("     , u.[user] ");
		query.append("FROM rc_categoria c ");
		query.append("INNER JOIN rc_user u ");
		query.append("   ON c.[user] = u.id ");
		query.append("WHERE categoria LIKE ?");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			instrucaoSQL.setString(1, "%" + key + '%');

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				Category category = new Category(rs);

				categoryList.add(category);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return categoryList;
	}

	@Override
	public boolean save(Object object) {
		Category category = (Category) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("INSERT INTO rc_categoria VALUES(?,?,?)");

			instrucaoSQL.setString(1, category.getCategory_name().toUpperCase());
			instrucaoSQL.setDate(2, new Date(category.getDate().getTime()));
			instrucaoSQL.setInt(3, Integer.valueOf(category.getUser()));
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
		Category category = (Category) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("UPDATE rc_categoria ");
		query.append("SET categoria = ? ");
		query.append("  , [user] = ? ");
		query.append("WHERE id = ?");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			instrucaoSQL.setString(1, category.getCategory_name().toUpperCase());
			instrucaoSQL.setInt(2, Integer.valueOf(category.getUser()));
			instrucaoSQL.setInt(3, category.getCategory_id());
			instrucaoSQL.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}
}
