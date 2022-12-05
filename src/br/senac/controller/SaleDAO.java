package br.senac.controller;

import br.senac.interfaces.DAO;
import br.senac.model.Sale;
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
public class SaleDAO implements DAO {

    private static SaleDAO uniqueInstance;

    public static synchronized SaleDAO getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new SaleDAO();
        }
        return uniqueInstance;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<?> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Object object) {
        try {
            Sale sale = (Sale) object;

            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_venda values(?,?,?,?)");

            instrucaoSQL.setInt(1, sale.getId_cliente());
            instrucaoSQL.setFloat(2, sale.getTotal());
            instrucaoSQL.setDate(3, new java.sql.Date(sale.getData().getTime()));
            instrucaoSQL.setInt(4, sale.getUser());

            instrucaoSQL.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            return (false);
        }
        return (true);
    }

    @Override
    public boolean alter(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<?> getBy(String key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean saveList(int produto, float valor, int quantidade, int user) {
        try {
            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("execute sp_insert_lista_venda ?,?,?,?");

            instrucaoSQL.setInt(1, produto);
            instrucaoSQL.setInt(2, quantidade);
            instrucaoSQL.setFloat(3, valor);
            instrucaoSQL.setInt(4, user);

            instrucaoSQL.executeUpdate();

        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            return (false);
        }
        return (true);
    }

    public int returnSale() {
        int id = 0;
        try {
            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("select top(1)id from rc_venda order by id desc");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
}
