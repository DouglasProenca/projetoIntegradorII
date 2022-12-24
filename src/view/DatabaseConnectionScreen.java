package view;

import objects.images;
import objects.ConnectionManager;
import objects.InternalFrame;
import objects.PropertiesSystem;
import objects.TextField;

import java.awt.event.ActionEvent;
import java.sql.Connection;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

@SuppressWarnings("serial")
public class DatabaseConnectionScreen extends InternalFrame {

    private JLabel lblDriver;
    private JLabel lblServer;
    private JLabel lblDatabase;
    private JLabel lblLogin;
    private JLabel lblSenha;
    private JLabel lblImagem;
    private JLabel lblPort;
    private JPanel painel;
    private TextField txtDriver;
    private JButton btnTestar;
    private JButton btnSalvar;
    private JButton btnCancelar;
    private JPasswordField txtSenha;
    private TextField txtPort;
    private TextField txtLogin;
    private TextField txtDatabase;
    private TextField txtServer;
    private final ConnectionManager connectionManager = ConnectionManager.getInstance();
    private final PropertiesSystem ps = new PropertiesSystem();

    public DatabaseConnectionScreen() {
        super("Conexão Banco de Dados", false, true, false, true, 440, 380);
        this.initComponents();
    }

    public DatabaseConnectionScreen(boolean closabe, boolean iconifiable) {
        super("Conexão Banco de Dados", false, closabe, false, iconifiable, 440, 380);
        this.initComponents();
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
    
    private JLabel getLbLPort() {
        lblPort = new JLabel("Porta:");
        lblPort.setBounds(305, 50, 50, 20);
        return lblPort;
    }

    private TextField getTxtLogin() {
        txtLogin = new TextField();
        txtLogin.setBounds(90, 110, 300, 20);
        txtLogin.setText(ps.getLogin());
        return txtLogin;
    }

    private TextField getTxtDatabase() {
        txtDatabase = new TextField();
        txtDatabase.setBounds(90, 80, 300, 20);
        txtDatabase.setText(ps.getDatabase());
        return txtDatabase;
    }

    private JPasswordField getTxtSenha() {
        txtSenha = new JPasswordField();
        txtSenha.setBounds(90, 140, 300, 20);
        txtSenha.setText(ps.getSenha());
        return txtSenha;
    }

    private TextField getTxtServer() {
        txtServer = new TextField();
        txtServer.setBounds(90, 50, 200, 20);
        txtServer.setText(ps.getServer());
        return txtServer;
    }
    
    private TextField getTxtPort() {
        txtPort = new TextField("Number");
        txtPort.setBounds(340, 50, 50, 20);
        txtPort.setText(ps.getPort());
        return txtPort;
    }

    private TextField getTxtDriver() {
        txtDriver = new TextField("All","Microsoft SQL Server");
        txtDriver.setBounds(90, 20, 300, 20);
        txtDriver.setEnabled(false);
        return txtDriver;
    }

    private JButton getBtnCancelar() {
        btnCancelar = new JButton("Cancelar", images.getInstance().imagemClose());
        btnCancelar.setBounds(280, 270, 125, 48);
        btnCancelar.addActionListener(this);
        btnCancelar.setActionCommand("close");
        return btnCancelar;
    }

    private JButton getBtnSalvar() {
        btnSalvar = new JButton("Salvar", images.getInstance().imagemCheck());
        btnSalvar.setBounds(145, 270, 125, 48);
        btnSalvar.addActionListener(this);
        btnSalvar.setActionCommand("save");
        return btnSalvar;
    }

    private JButton getBtnTestar() {
        btnTestar = new JButton("Testar", images.getInstance().imagemRefresh());
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
        painel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
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
        painel.add(getTxtPort());
        painel.add(getLbLPort());
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
                ps.setDatabase(txtDatabase.getText());
                ps.setPort(txtPort.getText());
                ps.setLogin(txtLogin.getText());
                ps.setServer(txtServer.getText());
                ps.setSenha(String.valueOf(txtSenha.getPassword()));
                JOptionPane.showMessageDialog(this, "Sistema será fechado para atualização");
                System.exit(0);
                break;
            case "test":
                Connection conexao = connectionManager.getConexaoTest(txtLogin.getText(), String.valueOf(txtSenha.getPassword()),
                        txtDatabase.getText(), txtPort.getText(), txtServer.getText());
                if (conexao != null) {
                    this.setLblImagem(images.getInstance().conectionSucess());
                } else {
                    this.setLblImagem(images.getInstance().conectionError());
                }
                break;
        }
    }
}
