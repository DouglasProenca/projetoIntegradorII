package view;

import controller.UserDAO;
import model.User;
import objects.Images;
import objects.InternalFrame;
import objects.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.util.Date;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;

public class RegisterUserScreen extends InternalFrame implements DocumentListener {

	private static final long serialVersionUID = 1L;
	private JPanel panelCadastro;
	private JLabel lblUser;
	private TextField txtUser;
	private JLabel lblPassword;
	private JLabel lblPassordMail;
	private JLabel lblMail;
	private JPasswordField txtPasswordMail;
	private JButton btnCheck;
	private JButton btnClose;
	private int id;
	private JPasswordField txtPassword;
	private TextField txtMail;
	private JCheckBox checkBoxEnabled;
	private JCheckBox checkBoxLocked;
	private JCheckBox checkBokExpired;
	private JCheckBox checkBokCredExpired;

	public RegisterUserScreen() {
		super("Cadastrar Usuário", false, true, false, false, 700, 400);
		this.initComponents();
	}

	public RegisterUserScreen(User user) {
		super("Editar Usuário", false, true, false, false, 700, 400);
		this.initComponents();
		this.id = user.getId();
		this.txtUser.setText(user.getUser());
		this.txtMail.setText(user.getMail());
		this.txtPasswordMail.setText(user.getMailPassword());
		this.panelCadastro.setBorder(BorderFactory.createTitledBorder("Alterar Usuário"));
		this.btnCheck.setActionCommand("alter");
		this.checkBoxEnabled.setSelected(user.isEnabled());
		this.checkBoxLocked.setSelected(user.isLocked());
		this.checkBokExpired.setSelected(user.isExpired());
		this.checkBokCredExpired.setSelected(user.isCredExpired());
	}

	private void initComponents() {
		this.getContentPane().add(getPanelCadastro());
	}

	private boolean isValidEmailAddress(String email) {
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			return (false);
		}
		return (true);
	}

	private JPanel getPanelCadastro() {
		panelCadastro = new JPanel(null);
		panelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Usuário"));
		panelCadastro.add(getLblUser());
		panelCadastro.add(getTxtUser());
		panelCadastro.add(getLblPassword());
		panelCadastro.add(getTxtPassword());
		panelCadastro.add(getLblPassordMail());
		panelCadastro.add(getTxtPasswordMail());
		panelCadastro.add(getBtnCheck());
		panelCadastro.add(getBtnClose());
		panelCadastro.add(getLblMail());
		panelCadastro.add(getTxtMail());
		panelCadastro.add(getCheckBoxEnabled());
		panelCadastro.add(getCheckBokExpired());
		panelCadastro.add(getCheckBoxLocked());
		panelCadastro.add(getCheckBokCredExpired());
		return panelCadastro;
	}

	private TextField getTxtUser() {
		txtUser = new TextField("Letter");
		txtUser.setBounds(100, 30, 230, 25);
		txtUser.getDocument().addDocumentListener(this);
		return txtUser;
	}

	private JLabel getLblUser() {
		lblUser = new JLabel("User:");
		lblUser.setBounds(25, 30, 50, 25);
		return lblUser;
	}

	private JPasswordField getTxtPassword() {
		txtPassword = new JPasswordField();
		txtPassword.setBounds(410, 30, 230, 25);
		txtPassword.getDocument().addDocumentListener(this);
		return txtPassword;
	}

	private TextField getTxtMail() {
		txtMail = new TextField();
		txtMail.setBounds(100, 100, 230, 25);
		txtMail.addFocusListener(this);
		txtMail.getDocument().addDocumentListener(this);
		return txtMail;
	}

	private JLabel getLblPassword() {
		lblPassword = new JLabel("Senha:");
		lblPassword.setBounds(350, 30, 50, 25);
		return lblPassword;
	}

	private JLabel getLblMail() {
		lblMail = new JLabel("E-mail:");
		lblMail.setBounds(25, 100, 90, 25);
		return lblMail;
	}

	private JLabel getLblPassordMail() {
		lblPassordMail = new JLabel("Senha:");
		lblPassordMail.setBounds(350, 100, 90, 25);
		return lblPassordMail;
	}

	private JPasswordField getTxtPasswordMail() {
		txtPasswordMail = new JPasswordField();
		txtPasswordMail.setBounds(410, 100, 230, 25);
		txtPasswordMail.getDocument().addDocumentListener(this);
		return txtPasswordMail;
	}

	private JButton getBtnClose() {
		btnClose = new JButton("Cancelar", Images.CLOSE.getImage());
		btnClose.setBounds(400, 250, 250, 40);
		btnClose.addActionListener(this);
		btnClose.setActionCommand("close");
		return btnClose;
	}

	private JButton getBtnCheck() {
		btnCheck = new JButton("Salvar", Images.CHECK.getImage());
		btnCheck.setBounds(25, 250, 250, 40);
		btnCheck.addActionListener(this);
		btnCheck.setActionCommand("save");
		btnCheck.setEnabled(false);
		return btnCheck;
	}
	
	private JCheckBox getCheckBoxEnabled() {
		checkBoxEnabled = new JCheckBox("Ativo");
		checkBoxEnabled.setBounds(25, 150, 250, 40);
		return checkBoxEnabled;
	}
	
	private JCheckBox getCheckBokExpired() {
		checkBokExpired = new JCheckBox("Expirado");
		checkBokExpired.setBounds(25, 190, 250, 40);
		return checkBokExpired;
	}
	
	private JCheckBox getCheckBoxLocked() {
		checkBoxLocked = new JCheckBox("Bloqueado");
		checkBoxLocked.setBounds(350, 150, 250, 40);
		return checkBoxLocked;
	}
	
	private JCheckBox getCheckBokCredExpired() {
		checkBokCredExpired = new JCheckBox("Cred. Expirado");
		checkBokCredExpired.setBounds(350, 190, 250, 40);
		return checkBokCredExpired;
	}

	@Override
	public void focusLost(FocusEvent e) {
		TextField src = (TextField) e.getSource();
		boolean validate = this.isValidEmailAddress(src.getText());
		btnCheck.setEnabled(validate);
		if (!validate) {
			JOptionPane.showMessageDialog(this, "E-mail inválido!", "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		InternalFrame frame = (InternalFrame) e.getSource();
		this.centralizaForm(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "save":
			User u = new User(0, txtMail.getText(), String.valueOf(txtPasswordMail.getPassword()), txtUser.getText(),
					String.valueOf(txtPassword.getPassword()), new Date(),checkBoxEnabled.isSelected(),
					checkBoxLocked.isSelected(), checkBokCredExpired.isSelected(), checkBokExpired.isSelected());
			if (UserDAO.getInstance().save(u)) {
				JOptionPane.showMessageDialog(null, "Usúario Cadastrado Com Sucesso!");
				this.dispose();
			}
			break;
		case "alter":
			User user = new User(id, txtMail.getText(), String.valueOf(txtPasswordMail.getPassword()),
					txtUser.getText(), String.valueOf(txtPassword.getPassword()), new Date(),checkBoxEnabled.isSelected(),
					checkBoxLocked.isSelected(), checkBokCredExpired.isSelected(), checkBokExpired.isSelected());
			if (UserDAO.getInstance().alter(user)) {
				JOptionPane.showMessageDialog(null, "Usúario Alterado Com Sucesso!");
				this.dispose();
			}
			this.dispose();
			break;
		default:
			this.dispose();
			break;
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		btnCheck.setEnabled(warn());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		btnCheck.setEnabled(warn());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		btnCheck.setEnabled(warn());
	}

	private boolean warn() {
		if (txtUser.getText().length() >= 1 && String.valueOf(txtPassword.getPassword()).length() >= 1
				&& String.valueOf(txtPasswordMail.getPassword()).length() >= 1) {
			return (true);
		}
		return (false);
	}
}
