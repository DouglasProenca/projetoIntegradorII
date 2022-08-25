package br.senac.view;

import br.senac.controller.UserDAO;
import br.senac.model.User;
import br.senac.objects.CryptoUtils;
import br.senac.objects.InternalFrame;
import br.senac.objects.JMenuHelp;
import br.senac.objects.JMenuMenu;
import br.senac.objects.JMenuReport;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Douglas
 */
public class LoginScreen extends InternalFrame {

    private JLabel lblUsuario;
    private JLabel lblSenha;
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnConfirmar;

    public LoginScreen() {
        super("Login", false, false, false, false, 404, 325);
        initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.setLocation(450, 150);
        this.getContentPane().add(getLblUsuario());
        this.getContentPane().add(getLblSenha());
        this.getContentPane().add(getTxtUsuario());
        this.getContentPane().add(getTxtSenha());
        this.getContentPane().add(getBtnConfirmar());
        JMenuMenu.getInstance().setEnabled(false);
        JMenuHelp.getInstance().setEnabled(false);
        JMenuReport.getInstance().setEnabled(false);
    }

    private JLabel getLblUsuario() {
        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(50, 50, 90, 26);
        return lblUsuario;
    }

    private JLabel getLblSenha() {
        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(50, 130, 90, 26);
        return lblSenha;
    }

    private JTextField getTxtUsuario() {
        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 50, 240, 26);
        txtUsuario.addKeyListener(this);
        return txtUsuario;
    }

    private JPasswordField getTxtSenha() {
        txtSenha = new JPasswordField();
        txtSenha.setBounds(120, 130, 240, 26);
        txtSenha.addKeyListener(this);
        return txtSenha;
    }

    private JButton getBtnConfirmar() {
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBounds(90, 200, 200, 46);
        btnConfirmar.addActionListener(this);
        btnConfirmar.setActionCommand("Confirmar");
        return btnConfirmar;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Confirmar":
                UserDAO.getInstance().getBy(txtUsuario.getText());
                boolean senhaOk = false;
                try {
                    senhaOk = CryptoUtils.verificarSenha((String.valueOf(txtSenha.getPassword())), User.getInstance().getPassword());
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(this, "Usuário não encontrado", "Aviso de Falha de Acesso",
                            JOptionPane.ERROR_MESSAGE);
                    break;
                }

                if (senhaOk) {
                    JMenuMenu.getInstance().setEnabled(true);
                    JMenuHelp.getInstance().setEnabled(true);
                    JMenuReport.getInstance().setEnabled(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Aviso de Falha de Acesso",
                            JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            ActionEvent z = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Confirmar");
            actionPerformed(z);
        }
    }
}
