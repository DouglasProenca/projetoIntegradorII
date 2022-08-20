package br.senac.controller;

import br.senac.interfaces.DAO;
import br.senac.model.User;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<?> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean save(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alter(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<?> getBy(String key) {
        ArrayList<User> listaUser = new ArrayList<>();

        try {

            Connection conexao = ConnectionManager.getInstance().getConexao();
            PreparedStatement instrucaoSQL = conexao.prepareStatement("select * from rc_user where [user] = ?");

            //Adiciono os parâmetros ao meu comando SQL
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
