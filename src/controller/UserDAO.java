package controller;

import interfaces.DAO;
import model.User;
import objects.ConnectionManager;
import objects.Utils;
import view.MainScreen;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class UserDAO implements DAO {

	private static UserDAO uniqueInstance;

	private UserDAO() {

	}

	public static synchronized UserDAO getInstance() {
		return uniqueInstance == null ? uniqueInstance = new UserDAO() : uniqueInstance;
	}

	@Override
	public boolean delete(int id) {
		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("DELETE FROM rc_user WHERE id = ?");
			instrucaoSQL.setInt(1, id);
			instrucaoSQL.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);

			return (false);
		}
		return (true);
	}

	@Override
	public ArrayList<User> getAll() {
		ArrayList<User> userList = new ArrayList<User>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT id ");
		query.append("    , [user] ");
		query.append("    , [password] ");
		query.append("    , mail ");
		query.append("    , mailpassword ");
		query.append("    , [data] ");
		query.append("    , IIF(account_non_expired = 1,'false','true') expired ");
		query.append("    , IIF(account_non_locked = 1,'false','true') locked ");
		query.append("    , IIF(credentials_non_expired = 1,'false','true') cred_expired ");
		query.append("    , IIF([enabled] = 1, 'true','false') [enabled] ");
		query.append("FROM rc_user");
		
		try {

			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				User p = new User(rs);

				userList.add(p);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return userList;
	}

	@Override
	public boolean save(Object object) {
		User user = (User) object;

		Connection conexao = ConnectionManager.getInstance().getConexao();

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("EXEC sp_insert_rc_user ?,?,?,?,?,?,?,?,?");
			instrucaoSQL.setString(1, user.getUser());
			instrucaoSQL.setString(2, Utils.gerarhashSenha(user.getPassword()));
			instrucaoSQL.setString(3, user.getMail());
			instrucaoSQL.setString(4, Utils.codeString(user.getMailPassword()));
			instrucaoSQL.setDate(5, new Date(user.getDate().getTime()));
			instrucaoSQL.setBoolean(6, user.isExpired());
			instrucaoSQL.setBoolean(7, user.isLocked());
			instrucaoSQL.setBoolean(8, user.isCredExpired());
			instrucaoSQL.setBoolean(9, user.isEnabled());
			instrucaoSQL.executeUpdate();
		} catch (SQLException | IllegalArgumentException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}

	@Override
	public boolean alter(Object object) {
		User user = (User) object;
		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("UPDATE rc_user ");
		query.append("SET [user] = ? ");
		query.append("  , mail = ? ");
		query.append("  , mailpassword = ? ");
		query.append("  , [data] = ? ");
		query.append("  , [enabled] = IIF(? = 'true',1,0) ");
		query.append("  , account_non_expired = IIF(? = 'false',1,0) ");
		query.append("  , account_non_locked = IIF(? = 'false',1,0) ");
		query.append("  , credentials_non_expired = IIF(? = 'false',1,0) ");
		query.append("WHERE id = ?");

		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());
			instrucaoSQL.setString(1, user.getUser());
			instrucaoSQL.setString(2, user.getMail());
			instrucaoSQL.setString(3, Utils.codeString(user.getMailPassword()));
			instrucaoSQL.setDate(4, new Date(user.getDate().getTime()));
			instrucaoSQL.setBoolean(5, user.isEnabled());
			instrucaoSQL.setBoolean(6, user.isExpired());
			instrucaoSQL.setBoolean(7, user.isLocked());
			instrucaoSQL.setBoolean(8, user.isCredExpired());
			instrucaoSQL.setInt(9, user.getId());
			instrucaoSQL.executeUpdate();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
			return (false);
		}
		return (true);
	}

	@Override
	public ArrayList<User> getBy(String key) {
		ArrayList<User> listaUser = new ArrayList<User>();

		Connection conexao = ConnectionManager.getInstance().getConexao();

		StringBuilder query = new StringBuilder();
		query.append("SELECT id ");
		query.append("    , [user] ");
		query.append("    , [password] ");
		query.append("    , mail ");
		query.append("    , mailpassword ");
		query.append("    , [data] ");
		query.append("    , IIF(account_non_expired = 1,'false','true') expired ");
		query.append("    , IIF(account_non_locked = 1,'false','true') locked ");
		query.append("    , IIF(credentials_non_expired = 1,'false','true') cred_expired ");
		query.append("    , IIF([enabled] = 1, 'true','false') [enabled] ");
		query.append("FROM rc_user ");
		query.append("WHERE [user] = ?");
		
		try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement(query.toString());

			instrucaoSQL.setString(1, key);

			ResultSet rs = instrucaoSQL.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String mail = rs.getString("mail");
				String mailPassword = Utils.decodeString(rs.getString("mailPassword"));
				String user = rs.getString("user");
				String password = rs.getString("password");

				User.getInstance().setId(id);
				User.getInstance().setMail(mail);
				User.getInstance().setMailPassword(mailPassword);
				User.getInstance().setUser(user);
				User.getInstance().setPassword(password);
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(), "Aviso de Falha",
					JOptionPane.ERROR_MESSAGE);
		}
		return listaUser;
	}
}