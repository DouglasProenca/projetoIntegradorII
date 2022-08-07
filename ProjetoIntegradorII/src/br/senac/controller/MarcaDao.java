package br.senac.controller;

import br.senac.model.Brand;
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
public class MarcaDao{
    
    
    public static boolean delete(int id) {
        boolean retorno = false;
        try {
            Connection conexao = ConnectionManager.getConexao();
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

    public static ArrayList<Brand> getAllBrands() {

        ArrayList<Brand> brandList = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getConexao();

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

    public static ArrayList<Brand> AllCountry() throws IOException {

        ArrayList<Brand> listCountry = new ArrayList<Brand>();

        try {

            Connection conexao = ConnectionManager.getConexao();

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

    public static boolean save(Brand p) {
        boolean retorno = false;

        try {
            Connection conexao = ConnectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_marca values(?,(select paisId from rc_pais where paisNome = ?),(select getDate()),1)");

            instrucaoSQL.setString(1, p.getMarca());
            instrucaoSQL.setString(2, p.getPais());

            retorno = instrucaoSQL.executeUpdate() > 0 ? true : false;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            retorno = false;
        }

        return retorno;
    }

    public static ArrayList<Brand> getBrands(String brand) {
        ArrayList<Brand> listaClientes = new ArrayList<Brand>();

        try {

            Connection conexao = ConnectionManager.getConexao();
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
            instrucaoSQL.setString(1, "%" + brand + '%');

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

    public static boolean AlterBrand(Brand p) {
        boolean retorno = false;

        try {
            Connection conexao = ConnectionManager.getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("UPDATE rc_marca SET marca=?"
                    + ",pais=(select paisID from rc_pais where paisNome = ?)"
                    + "WHERE id = ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, p.getMarca());
            instrucaoSQL.setString(2, p.getPais());
            instrucaoSQL.setInt(3, p.getId());

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
