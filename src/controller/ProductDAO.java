package controller;

import interfaces.DAO;
import model.Product;
import view.MainScreen;
import objects.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProductDAO implements DAO {

	private static ProductDAO uniqueInstance;

	private ProductDAO() {

	}

	public static synchronized ProductDAO getInstance() {
		return uniqueInstance == null ? uniqueInstance = new ProductDAO() : uniqueInstance;
	}

	@Override
	public boolean delete(int id) {
		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_produto WHERE id = ?");
			instrucaoSQL.setInt(1, id);
			instrucaoSQL.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}

	@Override
	public ArrayList<Product> getAll() {
		ArrayList<Product> productList = new ArrayList<>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id ");
		query.append("     , p.nome ");
		query.append("     , m.marca ");
		query.append("     , p.valor ");
		query.append("     , p.quantidade ");
		query.append("     , p.date ");
		query.append("     , u.[user] ");
		query.append("     , m.pais ");
		query.append("     , c.categoria ");
		query.append("     , p.imagem ");
		query.append("FROM rc_produto p ");
		query.append("INNER JOIN rc_marca m ");
		query.append("     ON m.id = p.marca ");
		query.append("INNER JOIN rc_categoria c ");
		query.append("     ON p.categoria = c.id ");
		query.append("INNER JOIN rc_user u ");
		query.append("     ON u.id = p.[user]");

		try {

			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				Product product = new Product(rs);

				productList.add(product);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return productList;
	}

	@Override
	public boolean save(Object object) {
		Product product = (Product) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("INSERT INTO rc_produto VALUES(?,?,?,?,?,?,?,?)");
			instrucaoSQL.setString(1, product.getProduct_name());
			instrucaoSQL.setInt(2, product.getBrand_id());
			instrucaoSQL.setFloat(3, product.getProduct_valor());
			instrucaoSQL.setInt(4, product.getProduct_qtd());
			instrucaoSQL.setDate(5, new Date(product.getDate().getTime()));
			instrucaoSQL.setInt(6, Integer.valueOf(product.getUser()));
			instrucaoSQL.setInt(7, product.getCategory_id());
			instrucaoSQL.setBytes(8, product.getProduct_img());
			instrucaoSQL.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}

	@Override
	public boolean alter(Object object) {
		Product product = (Product) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("UPDATE rc_produto ");
		query.append("SET nome = ? ");
		query.append("  , marca = ? ");
		query.append("  , valor = ? ");
		query.append("  , quantidade = ? ");
		query.append("  , categoria = ? ");
		query.append("  , [user] = ? ");
		query.append("  , imagem = ? ");
		query.append("WHERE id = ?");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());
			instrucaoSQL.setString(1, product.getProduct_name());
			instrucaoSQL.setInt(2, product.getBrand_id());
			instrucaoSQL.setFloat(3, product.getProduct_valor());
			instrucaoSQL.setInt(4, product.getProduct_qtd());
			instrucaoSQL.setInt(5, product.getCategory_id());
			instrucaoSQL.setInt(6, Integer.valueOf(product.getUser()));
			instrucaoSQL.setBytes(7, product.getProduct_img());
			instrucaoSQL.setInt(8, product.getProduct_id());
			instrucaoSQL.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}

	@Override
	public ArrayList<Product> getBy(String key) {
		ArrayList<Product> productList = new ArrayList<>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT p.id ");
		query.append("     , p.nome ");
		query.append("     , m.marca ");
		query.append("     , p.valor ");
		query.append("     , p.quantidade ");
		query.append("     , p.date ");
		query.append("     , u.[user] ");
		query.append("     , m.pais ");
		query.append("     , c.categoria ");
		query.append("     , p.imagem ");
		query.append("FROM rc_produto p ");
		query.append("INNER JOIN rc_marca m ");
		query.append("     ON m.id = p.marca ");
		query.append("INNER JOIN rc_categoria c ");
		query.append("     ON p.categoria = c.id ");
		query.append("INNER JOIN rc_user u ");
		query.append("     ON u.id = p.[user]");
		query.append("WHERE p.nome LIKE ? OR p.id LIKE ?");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());
			instrucaoSQL.setString(1, "%" + key + '%');
			instrucaoSQL.setString(2, "%" + key + '%');

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				Product product = new Product(rs);

				productList.add(product);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return productList;
	}
}
