package view;

import objects.images;
import objects.ConnectionManager;
import objects.InternalFrame;
import objects.TextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;


@SuppressWarnings("serial")
public class BackupScreen extends InternalFrame {


	private JLabel lblDigiteSenha;
    private JButton btnCheck;
    private JButton btnCancelar;
    private JPasswordField txtSenha;
    private TextField jcbTipo;
    private JProgressBar progbarProgresso;
    private JLabel lblImagem;
    private JPanel painel;
    private final ConnectionManager connectionManager = ConnectionManager.getInstance();

    public BackupScreen() {
        super("Backup Banco de Dados", false, true, false, true, 379, 325);
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.getContentPane().add(getPainel());
    }

    private JPanel getPainel() {
        painel = new JPanel(null);
        painel.setSize(350, 270);
        painel.setLocation(10, 10);
        painel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        painel.add(getBtnCheck());
        painel.add(getbtnCancelar());
        painel.add(getTxtSenha());
        painel.add(getLblDigiteSenha());
        painel.add(getLblImagem());
        painel.add(getJcbTipo());
        painel.add(getProgbarProgresso());
        return painel;
    }

    private JProgressBar getProgbarProgresso() {
        progbarProgresso = new JProgressBar();
        progbarProgresso.setBounds(100, 100, 140, 15);
        progbarProgresso.setBackground(Color.WHITE);
        progbarProgresso.setForeground(Color.GREEN);
        progbarProgresso.setStringPainted(true);
        return progbarProgresso;
    }

    private TextField getJcbTipo() {
        jcbTipo = new TextField("All","Microsoft SQL Server");
        jcbTipo.setBounds(30, 140, 280, 20);
        jcbTipo.setEnabled(false);
        return jcbTipo;
    }

    private JLabel getLblImagem() {
        lblImagem = new JLabel(images.getInstance().backup());
        lblImagem.setBounds(140, 20, 50, 70);
        return lblImagem;
    }

    private JLabel getLblDigiteSenha() {
        lblDigiteSenha = new JLabel("Digite a senha: ");
        lblDigiteSenha.setBounds(30, 180, 100, 20);
        return lblDigiteSenha;
    }

    private JPasswordField getTxtSenha() {
        txtSenha = new JPasswordField();
        txtSenha.setBounds(130, 180, 180, 20);
        txtSenha.addKeyListener(this);
        return txtSenha;
    }

    private JButton getbtnCancelar() {
        btnCancelar = new JButton("Cancelar", images.getInstance().imagemClose());
        btnCancelar.setBounds(180, 220, 130, 40);
        btnCancelar.addActionListener(this);
        btnCancelar.setActionCommand("Cancelar");
        return btnCancelar;
    }

    private JButton getBtnCheck() {
        btnCheck = new JButton("Confirmar", images.getInstance().imagemCheck());
        btnCheck.setBounds(30, 220, 130, 40);
        btnCheck.addActionListener(this);
        btnCheck.setActionCommand("Confirmar");
        return btnCheck;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Confirmar":
                if (String.valueOf(txtSenha.getPassword()).equals("1234")) {

                    JFileChooser fc = new JFileChooser();
                    fc.setSelectedFile(new File("Backup Sistema.bak"));
                    if (fc.showSaveDialog(this) == JFileChooser.FILES_ONLY) {
                        File arquivo = fc.getSelectedFile();
                        try {
                            Connection conexao = connectionManager.getConexao();
                            PreparedStatement instrucaoSQL = conexao.prepareStatement(
                                    "BACKUP DATABASE [cr7imports] TO  DISK = N'" + arquivo + "' WITH NOFORMAT, NOINIT,  NAME = N'sistema-Completo Banco de Dados Backup', SKIP, NOREWIND, NOUNLOAD,  STATS = 10");
                            instrucaoSQL.executeUpdate();
                            progbarProgresso.setMaximum(2);
                            progbarProgresso.setValue(100);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(this, ex.getMessage(),
                                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Senha incorreta!", "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                this.dispose();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Confirmar"));
        }
    }
}