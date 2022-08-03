package br.senac.controller;

import br.senac.view.BrandReportScreen;
import br.senac.view.MainScreen;
import br.senac.view.objetos.GerenciadorConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Douglas
 */
public class ProductDAO {

    public static boolean excluirProduct(int id) {
        boolean retorno = false;
        try {
            Connection conexao = GerenciadorConexao.getConexao();
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
}
