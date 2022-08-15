package br.senac.controller;

import br.senac.interfaces.DAO;
import br.senac.model.Category;
import br.senac.objects.ConnectionManager;
import br.senac.view.MainScreen;
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
public class CategoryDAO implements DAO {

    private static CategoryDAO uniqueInstance;

    private CategoryDAO() {

    }

    public static synchronized CategoryDAO getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new CategoryDAO();
        }
        return uniqueInstance;
    }

    @Override
    public boolean delete(int id) {
        boolean retorno = false;
        try {
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_categoria WHERE id = ?");
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

    @Override
    public ArrayList<Category> getAll() {
        ArrayList<Category> categoryList = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("select * from rc_categoria");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Category p = new Category(rs.getString("categoria"), rs.getInt("id"), null, null,
                        rs.getDate("data"), rs.getString("user"));
                categoryList.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            categoryList = null;
        }
        return categoryList;
    }

    @Override
    public ArrayList<Category> getBy(String key) {
        ArrayList<Category> categoryList = new ArrayList<Category>();

        try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("select * from rc_categoria where categoria like ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, "%" + key + '%');

            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                Category p = new Category(rs.getString("categoria"), rs.getInt("id"), null, null,
                        rs.getDate("data"), rs.getString("user"));
                categoryList.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            categoryList = null;
        }
        return categoryList;
    }

    public boolean save(Category p) {
        boolean retorno = false;

        try {
            Connection conexao = ConnectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_categoria values(?,(select getDate()),1)");

            instrucaoSQL.setString(1, p.getCategoria().toUpperCase());

            retorno = instrucaoSQL.executeUpdate() > 0 ? true : false;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            retorno = false;
        }

        return retorno;
    }

    public boolean Alter(Category p) {
        boolean retorno = false;

        try {
            Connection conexao = ConnectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("UPDATE rc_categoria SET categoria=?\n"
                    + "WHERE id = ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, p.getCategoria().toUpperCase());
            instrucaoSQL.setInt(2, p.getId());

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
}
