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
		try {
			Connection conexao = ConnectionManager.getInstance().getConexao();
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

		try {

			Connection conexao = ConnectionManager.getInstance().getConexao();

			PreparedStatement instrucaoSQL = conexao.prepareStatement("select * from vmrc_produto");

			ResultSet rs = instrucaoSQL.executeQuery();
			while (rs.next()) {
				Product p = new Product(rs.getString("nome"), rs.getFloat("valor"), rs.getInt("quantidade"),
						rs.getString("categoria"), rs.getInt("id"), rs.getString("marca"), rs.getString("pais"),
						rs.getDate("date"), rs.getString("user"), rs.getBytes("imagem"));

				productList.add(p);
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
		try {
			Product product = (Product) object;
			Connection conexao = ConnectionManager.getInstance().getConexao();

			PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_produto values(?,?,?,?,?,?,?,?)");

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
		try {
			Product product = (Product) object;
			Connection conexao = ConnectionManager.getInstance().getConexao();

			PreparedStatement instrucaoSQL = conexao.prepareStatement("UPDATE rc_produto SET nome=?\n"
					+ ",marca= ?,valor=?,quantidade=?,\n" + "categoria = ?,[user]=?, imagem=?\n" + "WHERE id = ?");

			instrucaoSQL.setString(1, product.getProduct_name());
			instrucaoSQL.setInt(2, product.getBrand_id());
			instrucaoSQL.setFloat(3, product.getProduct_valor());
			instrucaoSQL.setInt(4, product.getProduct_qtd());
			instrucaoSQL.setInt(5, product.getCategory_id());
			instrucaoSQL.setInt(6, Integer.valueOf(product.getUser()));
			instrucaoSQL.setBytes(7, product.getProduct_img());
			instrucaoSQL.setInt(8, product.getProduct_id());

			// Mando executar a instrução SQL
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

		try {

			Connection conexao = ConnectionManager.getInstance().getConexao();
			PreparedStatement instrucaoSQL = conexao.prepareStatement("select * from vmrc_produto where nome like ? or id like ?");
			// Adiciono os parâmetros ao meu comando SQL
			instrucaoSQL.setString(1, "%" + key + '%');
			instrucaoSQL.setString(2, "%" + key + '%');

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getString("nome"), rs.getFloat("valor"), rs.getInt("Quantidade"),
						rs.getString("categoria"), rs.getInt("id"), rs.getString("marca"), rs.getString("pais"),
						rs.getDate("date"), rs.getString("user"), rs.getBytes("imagem"));

				productList.add(p);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return productList;
	}
}
