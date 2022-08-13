package br.senac.controller;

import br.senac.model.Brand;
import br.senac.model.Product;
import br.senac.view.MainScreen;
import br.senac.objects.ConnectionManager;
import java.io.IOException;
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
public class ProductDAO {

    public static boolean delete(int id) {
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

    public static ArrayList<Product> getAll() {
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

    public static boolean save(Product p) {
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

    public static boolean AlterProduct(Product p) {
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

    public static boolean saveExcel(ArrayList<Product> p) {
        boolean retorno = true;
        for (int z = 0; z < p.toArray().length; z++) {
            try {
                Connection conexao = ConnectionManager.getConexao();

                PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_produto values(?,(select id from rc_marca where marca = ?),?,?,(select getDate()),1)");

                instrucaoSQL.setString(1, p.get(z).getNome());
                instrucaoSQL.setString(2, p.get(z).getMarca());
                instrucaoSQL.setFloat(3, p.get(z).getValor());
                instrucaoSQL.setInt(4, p.get(z).getQuantidade());

                instrucaoSQL.executeUpdate();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                        "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
                retorno = false;
            }
        }
        return retorno;
    }

    public static ArrayList<Brand> AllBrands() throws IOException {

        ArrayList<Brand> listBrand = new ArrayList<Brand>();

        try {

            Connection conexao = ConnectionManager.getConexao();

            // Passo 3 - Executo a instrução SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement("SELECT marca FROM rc_marca");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Brand p = new Brand();
                p.setMarca(rs.getString("marca"));
                listBrand.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            listBrand = null;
        }
        return listBrand;
    }
    
    public static ArrayList<Product> getProduct(String product) {
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
            instrucaoSQL.setString(1, "%" + product + '%');

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
