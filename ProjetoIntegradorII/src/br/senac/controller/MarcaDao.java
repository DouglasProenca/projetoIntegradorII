package br.senac.controller;

import br.senac.interfaces.DAO;
import br.senac.model.Brand;
import br.senac.view.MainScreen;
import br.senac.objects.ConnectionManager;
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
public final class MarcaDao implements DAO {

    private static MarcaDao uniqueInstance;

    private MarcaDao() {

    }

    public static synchronized MarcaDao getInstance() {
        if (uniqueInstance == null) uniqueInstance = new MarcaDao();
        return uniqueInstance;
    }

    @Override
    public boolean delete(int id) {
        boolean retorno = true;
        try {
            Connection conexao = ConnectionManager.getInstance().getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_marca WHERE id = ?");
            instrucaoSQL.setInt(1, id);

            instrucaoSQL.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            retorno = false;
        }
        return retorno;
    }

    public ArrayList<Brand> AllCountry() {

        ArrayList<Brand> listCountry = new ArrayList<>();

        try {
            Connection conexao = ConnectionManager.getInstance().getConexao();

            // Passo 3 - Executo a instrução SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement("SELECT paisNome FROM rc_pais");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Brand p = new Brand();
                p.setPais(rs.getString("paisNome"));
                listCountry.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            listCountry = null;
        }
        return listCountry;
    }

    @Override
    public boolean save(Object object) {
        boolean retorno = true;

        try {
            Brand brand = (Brand) object;

            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_marca values(?,(select paisId from rc_pais where paisNome = ?),?,?)");

            instrucaoSQL.setString(1, brand.getMarca());
            instrucaoSQL.setString(2, brand.getPais());
            instrucaoSQL.setDate(3, new java.sql.Date(brand.getDate().getTime()));
            instrucaoSQL.setInt(4, Integer.valueOf(brand.getUser()));

            instrucaoSQL.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            retorno = false;
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }

        return retorno;
    }

    @Override
    public boolean alter(Object object) {
        boolean retorno = true;

        try {
            Brand brand = (Brand) object;
            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("UPDATE rc_marca SET marca=?"
                    + ",pais=(select paisID from rc_pais where paisNome = ?),[user]=?\n"
                    + "WHERE id = ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, brand.getMarca());
            instrucaoSQL.setString(2, brand.getPais());
            instrucaoSQL.setInt(3, Integer.valueOf(brand.getUser()));
            instrucaoSQL.setInt(4, brand.getId());

            //Mando executar a instrução SQL
            instrucaoSQL.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            retorno = false;
        }
        return retorno;
    }

    @Override
    public ArrayList<Brand> getAll() {
        ArrayList<Brand> brandList = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("select m.id\n"
                    + "	 , m.marca\n"
                    + "	 , p.paisNome\n"
                    + "	 , m.[date]\n"
                    + "	 , u.[user]\n"
                    + "from rc_marca m\n"
                    + "inner join rc_pais p\n"
                    + "	on p.paisId = m.pais\n"
                    + "inner join rc_user u\n"
                    + "	on u.id = m.[user]");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Brand p = new Brand(rs.getInt("id"), rs.getString("marca"), rs.getString("paisNome"),
                        rs.getDate("date"), rs.getString("user"));
                brandList.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            brandList = null;
        }
        return brandList;
    }

    @Override
    public ArrayList<Brand> getBy(String key) {
        ArrayList<Brand> listaClientes = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getInstance().getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("select m.id\n"
                    + "	  , m.marca\n"
                    + "	  , p.paisNome\n"
                    + "	  , m.[date]\n"
                    + "	  , u.[user]\n"
                    + "from rc_marca m\n"
                    + "inner join rc_pais p\n"
                    + "	on p.paisId = m.pais\n"
                    + "inner join rc_user u\n"
                    + "	on u.id = m.[user]\n"
                    + "where m.marca like ?");

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
            listaClientes = null;
        }
        return listaClientes;
    }
}