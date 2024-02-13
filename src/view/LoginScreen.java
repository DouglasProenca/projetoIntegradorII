package view;

import controller.UserDAO;
import model.User;
import enums.Images;
import objects.InternalFrame;
import objects.Menu;
import objects.PopUpMenu;
import objects.TextField;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.security.auth.login.LoginException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import bcrypt.Bcrypt;


public class LoginScreen extends InternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblUsuario;
	private JLabel lblSenha;
	private TextField txtUsuario;
	private JPasswordField txtSenha;
	private JButton btnConfirmar;

	public LoginScreen() {
		super("Login", false, false, false, false, 404, 325);
		this.initComponents();
	}

	private void initComponents() {
		super.setLayout(null);
		super.getContentPane().add(getLblUsuario());
		super.getContentPane().add(getLblSenha());
		super.getContentPane().add(getTxtUsuario());
		super.getContentPane().add(getTxtSenha());
		super.getContentPane().add(getBtnConfirmar());
		this.lockMenu(false);
		PopUpMenu.lockMenu(false);
	}

	private JLabel getLblUsuario() {
		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(40, 50, 90, 26);
		return lblUsuario;
	}

	private JLabel getLblSenha() {
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(40, 130, 90, 26);
		return lblSenha;
	}

	private TextField getTxtUsuario() {
		txtUsuario = new TextField();
		txtUsuario.setBounds(110, 50, 240, 26);
		txtUsuario.addKeyListener(this);
		return txtUsuario;
	}

	private JPasswordField getTxtSenha() {
		txtSenha = new JPasswordField();
		txtSenha.setBounds(110, 130, 240, 26);
		txtSenha.addKeyListener(this);
		return txtSenha;
	}

	private JButton getBtnConfirmar() {
		btnConfirmar = new JButton("Confirmar", Images.CHECK.getImage());
		btnConfirmar.setBounds(40, 200, 311, 46);
		btnConfirmar.addActionListener(this);
		btnConfirmar.setActionCommand("Confirmar");
		return btnConfirmar;
	}

	private void lockMenu(boolean ret) {
		int qtd = MainScreen.menubar.getComponentCount();
		for (int i = 0; i < qtd; i++) {
			if (MainScreen.menubar.getComponent(i) instanceof Menu) {
				Menu menu = (Menu) MainScreen.menubar.getComponent(i);
				menu.setEnabled(ret);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			switch (e.getActionCommand()) {
			case "Confirmar":
				UserDAO.getInstance().getBy(txtUsuario.getText());
				CheckLogin();
				lockMenu(true);
				PopUpMenu.lockMenu(true);
				this.dispose();
				break;
			}
		} catch (LoginException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Aviso de Falha de Acesso", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void CheckLogin() throws LoginException {
		if (User.getInstance().getPassword() == null) {
			throw new LoginException("Usuário não encontrado!");
		}
		if (!Bcrypt.verificarSenha((String.valueOf(txtSenha.getPassword())), User.getInstance().getPassword())) {
			throw new LoginException("Usuário ou senha incorretos!");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		} else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Confirmar"));
		}
	}
}
