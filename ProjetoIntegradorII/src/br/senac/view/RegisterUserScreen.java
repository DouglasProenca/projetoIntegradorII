package br.senac.view;

import br.senac.controller.UserDAO;
import br.senac.model.User;
import br.senac.objects.InternalFrame;
import br.senac.objects.images;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Douglas
 */
public class RegisterUserScreen extends InternalFrame {

    private JPanel panelCadastro;
    private JLabel lblUser;
    private JTextField txtUser;
    private JLabel lblPassword;
    private JLabel lblPassordMail;
    private JLabel lblMail;
    private JPasswordField txtPasswordMail;
    private JButton btnCheck;
    private JButton btnClose;
    private int id;
    private JPasswordField txtPassword;
    private JTextField txtMail;

    public RegisterUserScreen() {
        super("Cadastrar Usuário", false, true, false, false, 700, 400);
        initComponents(false);
    }

    public RegisterUserScreen(User user) {
        super("Editar Usuário", false, true, false, false, 700, 400);
        initComponents(true);
        this.id = user.getId();
        this.txtUser.setText(user.getUser());
        this.txtMail.setText(user.getMail());
        this.txtPasswordMail.setText(user.getMailPassword());
    }

    private void initComponents(boolean type) {
        this.getContentPane().add(getPanelCadastro(type));
    }

    private JPanel getPanelCadastro(boolean type) {
        panelCadastro = new JPanel(null);
        panelCadastro.setBorder(BorderFactory.createTitledBorder(type? "Cadastrar Usuário" : "Alterar Usuário"));
        panelCadastro.add(getLblUser());
        panelCadastro.add(getTxtUser());
        panelCadastro.add(getLblPassword());
        panelCadastro.add(getTxtPassword());
        panelCadastro.add(getLblPassordMail());
        panelCadastro.add(getTxtPasswordMail());
        panelCadastro.add(getBtnCheck(type));
        panelCadastro.add(getBtnClose());
        panelCadastro.add(getLblMail());
        panelCadastro.add(getTxtMail());
        return panelCadastro;
    }

    private JTextField getTxtUser() {
        txtUser = new JTextField();
        txtUser.setBounds(100, 30, 230, 25);
        txtUser.getDocument().addDocumentListener(new DocListner());
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
        return txtPassword;
    }

    private JTextField getTxtMail() {
        txtMail = new JTextField();
        txtMail.setBounds(100, 100, 230, 25);
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
        return txtPasswordMail;
    }

    private JButton getBtnClose() {
        btnClose = new JButton("Cancelar", images.getInstance().imagemClose());
        btnClose.setBounds(400, 250, 250, 40);
        btnClose.addActionListener(this);
        btnClose.setActionCommand("close");
        return btnClose;
    }

    private JButton getBtnCheck(boolean newCad) {
        btnCheck = new JButton("Salvar", images.getInstance().imagemCheck());
        btnCheck.setBounds(25, 250, 250, 40);
        btnCheck.addActionListener(this);
        btnCheck.setActionCommand(newCad == true ? "save" : "alter");
        btnCheck.setEnabled(false);
        return btnCheck;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                User u = new User(0, txtMail.getText(), String.valueOf(txtPasswordMail.getPassword()), txtUser.getText(), String.valueOf(txtPassword.getPassword()), new Date());
                boolean ret = UserDAO.getInstance().save(u);
                if (ret) {
                    JOptionPane.showMessageDialog(null, "Usúario Cadastrado Com Sucesso!");
                    this.dispose();
                }
                break;
            case "alter":
                User user = new User(id, txtMail.getText(), String.valueOf(txtPasswordMail.getPassword()), txtUser.getText(), String.valueOf(txtPassword.getPassword()), new Date());
                boolean retur = UserDAO.getInstance().alter(user);
                if(retur){
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

    private class DocListner implements DocumentListener {

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
            boolean type = false;
            if (txtUser.getText().length() >= 1 && String.valueOf(txtPassword.getPassword()).length() >= 1
                    && txtMail.getText().length() >= 1 && String.valueOf(txtPasswordMail.getPassword()).length() >= 1) {
                type = true;
            }
            return type;
        }
    }
}
