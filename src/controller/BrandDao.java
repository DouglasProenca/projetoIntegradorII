package controller;

import interfaces.DAO;
import model.Brand;
import model.Country;
import view.MainScreen;
import objects.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public final class BrandDao implements DAO {

    private static BrandDao uniqueInstance;

    private BrandDao() {

    }

    public static synchronized BrandDao getInstance() {
        return uniqueInstance == null ? uniqueInstance = new BrandDao() : uniqueInstance;
    }

    @Override
    public boolean delete(int id) {
        try {
            Connection conexao = ConnectionManager.getInstance().getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_marca WHERE id = ?");
            instrucaoSQL.setInt(1, id);

             instrucaoSQL.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            return (false);
        }
        return (true);
    }

    public ArrayList<Country> AllCountry() {

        ArrayList<Country> listCountry = new ArrayList<>();

        try {
            Connection conexao = ConnectionManager.getInstance().getConexao();

            // Passo 3 - Executo a instrução SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement("select paisId, paisNome from rc_pais");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
            	Country p = new Country();
                p.setCountry_id(rs.getInt("paisId"));
                p.setCountry_nome(rs.getString("paisNome"));
                listCountry.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return listCountry;
    }

    @Override
    public boolean save(Object object) {
        try {
            Brand brand = (Brand) object;

            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_marca values(?,?,?,?)");

            instrucaoSQL.setString(1, brand.getBrand_name());
            instrucaoSQL.setInt(2, (brand.getCountry_id()));
            instrucaoSQL.setDate(3, new Date(brand.getDate().getTime()));
            instrucaoSQL.setInt(4, Integer.valueOf(brand.getUser()));

            instrucaoSQL.executeUpdate();

        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            return (false);
        }
        return true;
    }

    @Override
    public boolean alter(Object object) {
        try {
            Brand brand = (Brand) object;
            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("UPDATE rc_marca SET marca=?"
                    + ",pais= ?,[user]=?\n"
                    + "WHERE id = ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, brand.getBrand_name());
            instrucaoSQL.setInt(2, brand.getCountry_id());
            instrucaoSQL.setInt(3, Integer.valueOf(brand.getUser()));
            instrucaoSQL.setInt(4, brand.getBrand_id());

            //Mando executar a instrução SQL
            instrucaoSQL.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            return (false);
        }
        return (true);
    }

    @Override
    public ArrayList<Brand> getAll() {
        ArrayList<Brand> brandList = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("select * from vmrc_marca");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Brand p = new Brand(rs.getInt("id"), rs.getString("marca"), rs.getString("paisNome"),
                        rs.getDate("date"), rs.getString("user"));
                brandList.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return brandList;
    }

    @Override
    public ArrayList<Brand> getBy(String key) {
        ArrayList<Brand> listaClientes = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getInstance().getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("select * from vmrc_marca where marca like ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, "%" + key + '%');

            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                Brand p = new Brand(rs.getInt("id"), rs.getString("marca"), rs.getString("paisNome"),
                        rs.getDate("date"), rs.getString("user"));
                listaClientes.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return listaClientes;
    }
}