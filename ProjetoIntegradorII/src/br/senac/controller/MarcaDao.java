/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public static boolean excluirMarca(int id) throws IOException {
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

    public static ArrayList<Marca> todos() throws IOException {

        ArrayList<Marca> listaClientes = new ArrayList<Marca>();

        try {

            Connection conexao = GerenciadorConexao.getConexao();

            // Passo 3 - Executo a instrução SQL
            PreparedStatement instrucaoSQL = conexao.prepareStatement("SELECT * FROM rc_marca");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                Marca p = new Marca(rs.getInt("id"), rs.getString("marca"), rs.getString("pais"),
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
