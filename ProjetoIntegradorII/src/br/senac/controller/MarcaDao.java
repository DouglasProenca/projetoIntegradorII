package br.senac.controller;

import br.senac.interfaces.DAO;
import br.senac.model.Brand;
import br.senac.view.MainScreen;
import br.senac.objects.ConnectionManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public final class MarcaDao implements DAO {

    private static MarcaDao uniqueInstance;
    private final ConnectionManager connectionManager = ConnectionManager.getInstance();

    private MarcaDao() {

    }

    public static synchronized MarcaDao getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new MarcaDao();
        }
        return uniqueInstance;
    }

    @Override
    public boolean delete(int id) {
        boolean retorno = false;
        try {
            Connection conexao = connectionManager.getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_marca WHERE id = ?");
            instrucaoSQL.setInt(1, id);

            int linhasAfetadas = instrucaoSQL.executeUpdate();
            retorno = linhasAfetadas > 0 ? true : false;
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
            Connection conexao = connectionManager.getConexao();

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
        boolean retorno = false;

        try {
            Brand brand = (Brand) object;

            Connection conexao = connectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_marca values(?,(select paisId from rc_pais where paisNome = ?),(select getDate()),1)");

            instrucaoSQL.setString(1, brand.getMarca());
            instrucaoSQL.setString(2, brand.getPais());

            retorno = instrucaoSQL.executeUpdate() > 0 ? true : false;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            retorno = false;
        } catch (SecurityException ex) {
            Logger.getLogger(MarcaDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MarcaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return retorno;
    }

    @Override
    public boolean alter(Object object) {
        boolean retorno = false;

        try {
            Brand brand = (Brand) object;
            Connection conexao = connectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("UPDATE rc_marca SET marca=?"
                    + ",pais=(select paisID from rc_pais where paisNome = ?)"
                    + "WHERE id = ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, brand.getMarca());
            instrucaoSQL.setString(2, brand.getPais());
            instrucaoSQL.setInt(3, brand.getId());

            //Mando executar a instrução SQL
            int linhasAfetadas = instrucaoSQL.executeUpdate();

            retorno = linhasAfetadas > 0 ? true : false;

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

            Connection conexao = connectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("select m.id\n"
                    + "	  , m.marca\n"
                    + "	  , p.paisNome\n"
                    + "	  , m.[date]\n"
                    + "	  , m.[user]\n"
                    + "from rc_marca m\n"
                    + "inner join rc_pais p\n"
                    + "	on p.paisId = m.pais");

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

            Connection conexao = connectionManager.getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("select m.id\n"
                    + "	  , m.marca\n"
                    + "	  , p.paisNome\n"
                    + "	  , m.[date]\n"
                    + "	  , m.[user]\n"
                    + "from rc_marca m\n"
                    + "inner join rc_pais p\n"
                    + "	on p.paisId = m.pais\n"
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
