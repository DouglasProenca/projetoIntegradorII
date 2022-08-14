package br.senac.controller;

import br.senac.interfaces.DAO;
import br.senac.model.Product;
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
public class ProductDAO implements DAO {

    private static ProductDAO uniqueInstance;

    private ProductDAO() {

    }

    public static synchronized ProductDAO getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new ProductDAO();
        }
        return uniqueInstance;
    }

    @Override
    public boolean delete(int id) {
        boolean retorno = false;
        try {
            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_produto WHERE id = ?");
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
    public ArrayList<Product> getAll() {
        ArrayList<Product> productList = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("select p.id\n"
                    + "	 , p.nome\n"
                    + "	 , m.marca\n"
                    + "	 , p.valor\n"
                    + "	 , p.quantidade\n"
                    + "	 , p.[date]\n"
                    + "	 , p.[user]\n"
                    + "	 , m.pais\n"
                    + "from rc_produto p\n"
                    + "inner join rc_marca m\n"
                    + "	on m.id = p.marca");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("id"), rs.getString("nome"), rs.getFloat("valor"), rs.getInt("quantidade"),
                        rs.getString("marca"), rs.getString("pais"), rs.getString("user"), rs.getDate("date"));

                productList.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            productList = null;
        }
        return productList;
    }

    public boolean save(Product p) {
        boolean retorno = true;

        try {
            Connection conexao = ConnectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_produto values(?,(select id from rc_marca where marca = ?),?,?,(select getDate()),1)");

            instrucaoSQL.setString(1, p.getNome());
            instrucaoSQL.setString(2, p.getMarca());
            instrucaoSQL.setFloat(3, p.getValor());
            instrucaoSQL.setInt(4, p.getQuantidade());

            instrucaoSQL.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            retorno = false;
        }

        return retorno;
    }

    public boolean Alter(Product p) {
        boolean retorno = false;

        try {
            Connection conexao = ConnectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("UPDATE rc_produto SET nome=?"
                    + ",marca=(select id from rc_marca where marca=?),valor=?,quantidade=?"
                    + " WHERE id = ?");

            instrucaoSQL.setString(1, p.getNome());
            instrucaoSQL.setString(2, p.getMarca());
            instrucaoSQL.setFloat(3, p.getValor());
            instrucaoSQL.setInt(4, p.getQuantidade());
            instrucaoSQL.setInt(5, p.getId());

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
    public ArrayList<Product> getBy(String key) {
        ArrayList<Product> productList = new ArrayList<Product>();

        try {

            Connection conexao = ConnectionManager.getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("select p.id\n"
                    + "	 , p.nome\n"
                    + "	 , m.marca\n"
                    + "	 , p.valor\n"
                    + "	 , p.quantidade\n"
                    + "	 , p.[date]\n"
                    + "	 , p.[user]\n"
                    + "	 , m.pais\n"
                    + "from rc_produto p\n"
                    + "inner join rc_marca m\n"
                    + "	on m.id = p.marca\n"
                    + "where p.nome like ?");
            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, "%" + key + '%');

            ResultSet rs = instrucaoSQL.executeQuery();

            while (rs.next()) {
                Product p = new Product(rs.getInt("id"), rs.getString("nome"), rs.getFloat("valor"), rs.getInt("quantidade"),
                        rs.getString("marca"), rs.getString("pais"), rs.getString("user"), rs.getDate("date"));

                productList.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            productList = null;
        }
        return productList;
    }
}
