package controller;

import interfaces.DAO;
import model.Brand;
import view.MainScreen;
import objects.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public final class BrandDAO implements DAO {

	private static BrandDAO uniqueInstance;

	private BrandDAO() {

	}

	public static synchronized BrandDAO getInstance() {
		return uniqueInstance == null ? uniqueInstance = new BrandDAO() : uniqueInstance;
	}

	@Override
	public boolean delete(int id) {
		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_marca WHERE id = ?");
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
	public boolean save(Object object) {
		Brand brand = (Brand) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("INSERT INTO rc_marca VALUES(?,?,?,?)");
			instrucaoSQL.setString(1, brand.getBrand_name());
			instrucaoSQL.setInt(2, (brand.getCountry_id()));
			instrucaoSQL.setDate(3, new Date(brand.getDate().getTime()));
			instrucaoSQL.setInt(4, Integer.valueOf(brand.getUser()));
			instrucaoSQL.executeUpdate();
		} catch (SQLException | IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return true;
	}

	@Override
	public boolean alter(Object object) {
		Brand brand = (Brand) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("UPDATE rc_marca ");
		query.append("SET marca = ? ");
		query.append("  , pais = ? ");
		query.append("  ,[user] = ? ");
		query.append("WHERE id = ?");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());
			instrucaoSQL.setString(1, brand.getBrand_name());
			instrucaoSQL.setInt(2, brand.getCountry_id());
			instrucaoSQL.setInt(3, Integer.valueOf(brand.getUser()));
			instrucaoSQL.setInt(4, brand.getBrand_id());
			instrucaoSQL.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}

	@Override
	public ArrayList<Brand> getAll() {
		ArrayList<Brand> brandList = new ArrayList<>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT m.id ");
		query.append("     , m.marca ");
		query.append("     , p.paisNome ");
		query.append("     , m.[date] ");
		query.append("     , u.[user] ");
		query.append("FROM rc_marca m ");
		query.append("INNER JOIN rc_pais p ");
		query.append("	on p.paisId = m.pais ");
		query.append("INNER JOIN rc_user u");
		query.append("  on u.id = m.[user]");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			ResultSet rs = instrucaoSQL.executeQuery();
			while (rs.next()) {
				Brand brand = new Brand(rs);
				
				brandList.add(brand);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return brandList;
	}

	@Override
	public ArrayList<Brand> getBy(String key) {
		ArrayList<Brand> listaClientes = new ArrayList<>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT m.id ");
		query.append("     , m.marca ");
		query.append("     , p.paisNome ");
		query.append("     , m.[date] ");
		query.append("     , u.[user] ");
  		query.append("FROM rc_marca m ");
		query.append("INNER JOIN rc_pais p ");
		query.append("	on p.paisId = m.pais ");
		query.append("INNER JOIN rc_user u");
		query.append("  on u.id = m.[user]");
		query.append("WHERE m.marca LIKE ?");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			instrucaoSQL.setString(1, "%" + key + '%');

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				Brand brand = new Brand(rs);
				
				listaClientes.add(brand);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return listaClientes;
	}
}