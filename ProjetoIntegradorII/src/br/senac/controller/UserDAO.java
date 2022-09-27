package br.senac.controller;

import br.senac.interfaces.DAO;
import br.senac.model.User;
import br.senac.objects.ConnectionManager;
import br.senac.objects.CryptoUtils;
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
public class UserDAO implements DAO {

    private static UserDAO uniqueInstance;

    private UserDAO() {

    }

    public static synchronized UserDAO getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new UserDAO();
        }
        return uniqueInstance;
    }

    @Override
    public boolean delete(int id) {
       boolean retorno = true;
        try {
            Connection conexao = ConnectionManager.getInstance().getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_user WHERE id = ?");
            instrucaoSQL.setInt(1, id);

            instrucaoSQL.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            retorno = false;
        }
        return retorno;
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> userList = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("select * from rc_user");

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                User p = new User(rs.getInt("id"), rs.getString("mail"), rs.getString("mailpassword"),
                        rs.getString("user"), rs.getString("password"), rs.getDate("data"));
                userList.add(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            userList = null;
        }
        return userList;
    }

    @Override
    public boolean save(Object object) {
        boolean retorno = true;

        try {
            User user = (User) object;

            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("insert into rc_user values(?,?,?,?,?)");

            instrucaoSQL.setString(1, user.getUser());
            instrucaoSQL.setString(2, CryptoUtils.gerarhashSenha(user.getPassword()));
            instrucaoSQL.setString(3, user.getMail());
            instrucaoSQL.setString(4, user.getMailPassword());
            instrucaoSQL.setDate(5, new java.sql.Date(user.getDate().getTime()));

            instrucaoSQL.executeUpdate();

        } catch (SQLException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            retorno = false;
        }
        return retorno;
    }

    @Override
    public boolean alter(Object object) {
         boolean retorno = true;

        try {
            User user = (User) object;
            Connection conexao = ConnectionManager.getInstance().getConexao();

            PreparedStatement instrucaoSQL = conexao.prepareStatement("UPDATE rc_user SET [user]=?"
                    + ",mail= ?,mailpassword=?,[data]=?\n"
                    + "WHERE id = ?");

            //Adiciono os parâmetros ao meu comando SQL
            instrucaoSQL.setString(1, user.getUser());
            instrucaoSQL.setString(2, user.getMail());
            instrucaoSQL.setString(3, user.getMailPassword());
            instrucaoSQL.setDate(4, new java.sql.Date(user.getDate().getTime()));
            instrucaoSQL.setInt(5, user.getId());

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
    public ArrayList<User> getBy(String key) {
        ArrayList<User> listaUser = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getInstance().getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("select * from rc_user where [user] = ?");

            instrucaoSQL.setString(1, key);

            ResultSet rs = instrucaoSQL.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String mail = rs.getString("mail");
                String mailPassword = rs.getString("mailPassword");
                String user = rs.getString("user");
                String password = rs.getString("password");

                User.getInstance().setId(id);
                User.getInstance().setMail(mail);
                User.getInstance().setMailPassword(mailPassword);
                User.getInstance().setUser(user);
                User.getInstance().setPassword(password);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
            listaUser = null;
        }
        return listaUser;
    }
}