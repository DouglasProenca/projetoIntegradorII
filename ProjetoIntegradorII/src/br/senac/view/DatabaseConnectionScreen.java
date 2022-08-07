package br.senac.view;

import br.senac.objects.images;
import br.senac.objects.ConnectionManager;
import br.senac.objects.InternalFrame;
import br.senac.objects.PropertiesSystem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Douglas
 */
public class DatabaseConnectionScreen extends InternalFrame implements ActionListener {

    private JLabel lblDriver;
    private JLabel lblServer;
    private JLabel lblDatabase;
    private JLabel lblLogin;
    private JLabel lblSenha;
    private JLabel lblImagem;
    private JPanel painel;
    private JTextField txtDriver;
    private JButton btnTestar;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JPasswordField txtSenha;
    private JTextField txtLogin;
    private JTextField txtDatabase;
    private JTextField txtServer;

    public DatabaseConnectionScreen() {
        super("Conexão Banco de Dados", false, true, false, true, 440, 380);
        initComponents();
    }

    private JLabel getLblImagem() {
        lblImagem = new JLabel();
        lblImagem.setBounds(180, 180, 66, 68);
        return lblImagem;
    }

    private JLabel getLblDriver() {
        lblDriver = new JLabel("Driver:");
        lblDriver.setBounds(28, 25, 50, 10);
        return lblDriver;
    }

    private JLabel getLblServer() {
        lblServer = new JLabel("Server:");
        lblServer.setBounds(25, 55, 50, 10);
        return lblServer;
    }

    private JLabel getLblDatabase() {
        lblDatabase = new JLabel("Database:");
        lblDatabase.setBounds(10, 85, 80, 10);
        return lblDatabase;
    }

    private JLabel getLblLogin() {
        lblLogin = new JLabel("Login:");
        lblLogin.setBounds(31, 115, 50, 15);
        return lblLogin;
    }

    private JLabel getLblSenha() {
        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(25, 145, 50, 15);
        return lblSenha;
    }

    private JTextField getTxtLogin() {
        txtLogin = new JTextField();
        txtLogin.setBounds(90, 110, 300, 20);
        txtLogin.setText(PropertiesSystem.Propriedade.getLogin());
        return txtLogin;
    }

    private JTextField getTxtDatabase() {
        txtDatabase = new JTextField();
        txtDatabase.setBounds(90, 80, 300, 20);
        txtDatabase.setText(PropertiesSystem.Propriedade.getDatabase());
        return txtDatabase;
    }

    private JTextField getTxtSenha() {
        txtSenha = new JPasswordField();
        txtSenha.setBounds(90, 140, 300, 20);
        txtSenha.setText(PropertiesSystem.Propriedade.getSenha());
        return txtSenha;
    }

    private JTextField getTxtServer() {
        txtServer = new JTextField();
        txtServer.setBounds(90, 50, 300, 20);
        txtServer.setText(PropertiesSystem.Propriedade.getServer());
        return txtServer;
    }

    private JTextField getTxtDriver() {
        txtDriver = new JTextField("Microsoft SQL Server");
        txtDriver.setBounds(90, 20, 300, 20);
        txtDriver.setEnabled(false);
        return txtDriver;
    }

    private JButton getBtnCancelar() {
        btnCancelar = new JButton("Cancelar", images.imagemClose());
        btnCancelar.setBounds(280, 270, 125, 48);
        btnCancelar.addActionListener(this);
        btnCancelar.setActionCommand("close");
        return btnCancelar;
    }

    private JButton getBtnSalvar() {
        btnSalvar = new JButton("Salvar", images.imagemCheck());
        btnSalvar.setBounds(145, 270, 125, 48);
        btnSalvar.addActionListener(this);
        btnSalvar.setActionCommand("save");
        return btnSalvar;
    }

    private JButton getBtnTestar() {
        btnTestar = new JButton("Testar", images.imagemRefresh());
        btnTestar.setBounds(10, 270, 125, 48);
        btnTestar.addActionListener(this);
        btnTestar.setActionCommand("test");
        return btnTestar;
    }

    private void setLblImagem(Icon imagem) {
        this.lblImagem.setIcon(imagem);
    }

    private JPanel getPainel() {
        painel = new JPanel(null);
        painel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painel.setSize(415, 330);
        painel.setLocation(10, 10);
        painel.add(getLblDriver());
        painel.add(getLblServer());
        painel.add(getLblDatabase());
        painel.add(getLblLogin());
        painel.add(getLblSenha());
        painel.add(getTxtDriver());
        painel.add(getTxtServer());
        painel.add(getTxtDatabase());
        painel.add(getTxtLogin());
        painel.add(getTxtSenha());
        painel.add(getBtnCancelar());
        painel.add(getBtnSalvar());
        painel.add(getLblImagem());
        painel.add(getBtnTestar());
        return painel;
    }

    private void initComponents() {
        this.setLayout(null);
        this.getContentPane().add(getPainel());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "close":
                this.dispose();
                break;
            case "save":
                PropertiesSystem ps = new PropertiesSystem();
                ps.changeDatabase(txtDatabase.getText());
                ps.changeLogin(txtLogin.getText());
                ps.changeServer(txtServer.getText());
                ps.changeSenha(String.valueOf(txtSenha.getPassword()));
                JOptionPane.showMessageDialog(this, "Sistema será fechado para atualização");
                System.exit(0);
                break;
            case "test":
                Connection conexao;
                conexao = ConnectionManager.getConexaoTest(txtLogin.getText(), String.valueOf(txtSenha.getPassword()),
                        txtDatabase.getText(), txtServer.getText());
                if (conexao != null) {
                    setLblImagem(images.conectionSucess());
                } else {
                    setLblImagem(images.conectionError());
                }
                break;
        }
    }
}
