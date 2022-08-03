package br.senac.controller;

import br.senac.model.Marca;
import br.senac.view.MainScreen;
import br.senac.view.objetos.GerenciadorConexao;
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
public class MarcaDao {

    public static boolean excluirMarca(int id) {
        boolean retorno = false;
        try {
            Connection conexao = GerenciadorConexao.getConexao();
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

    public static ArrayList<Marca> getAllBrands() {

        ArrayList<Marca> listaClientes = new ArrayList<Marca>();

        try {

            Connection conexao = GerenciadorConexao.getConexao();

            // Passo 3 - Executo a instrução SQL
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
                Marca p = new Marca(rs.getInt("id"), rs.getString("marca"), rs.getString("paisNome"),
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

    public static ArrayList<Marca> AllCountry() throws IOException {

        ArrayList<Marca> listCountry = new ArrayList<Marca>();

        try {

            Connection conexao = GerenciadorConexao.getConexao();

            // Passo 3 - Executo a instrução SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement("SELECT paisNome FROM rc_pais");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Marca p = new Marca();
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

    public static boolean save(Marca p) {
        boolean retorno = false;

        try {
            Connection conexao = GerenciadorConexao.getConexao();

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

    public static ArrayList<Marca> getBrands(String brand) {
        ArrayList<Marca> listaClientes = new ArrayList<Marca>();

        try {

            Connection conexao = GerenciadorConexao.getConexao();
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
                Marca p = new Marca(rs.getInt("id"), rs.getString("marca"), rs.getString("paisNome"),
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

    public static boolean AlterBrand(Marca p) {
        boolean retorno = false;

        try {
            Connection conexao = GerenciadorConexao.getConexao();

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
