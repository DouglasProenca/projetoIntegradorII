package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import interfaces.DAO;
import model.Country;
import objects.ConnectionManager;
import view.MainScreen;

public class CountryDAO implements DAO {
	
	private static CountryDAO uniqueInstance;
	
	private CountryDAO() {

	}

	public static synchronized CountryDAO getInstance() {
		return uniqueInstance == null ? uniqueInstance = new CountryDAO() : uniqueInstance;
	}

	@Override
	public boolean delete(int id) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ArrayList<Country> getAll() {
		ArrayList<Country> listCountry = new ArrayList<>();

		Connection conexao = ConnectionManager.getInstance().getConexao();
		
		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("SELECT * FROM rc_pais");

			ResultSet rs = instrucaoSQL.executeQuery();
			
			while (rs.next()) {
				Country country = new Country(rs);
				
				listCountry.add(country);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return listCountry;
	}

	@Override
	public boolean save(Object object) {
		throw new UnsupportedOperationException("Not supported yet.");
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
