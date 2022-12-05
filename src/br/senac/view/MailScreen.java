package br.senac.view;

import br.senac.objects.InternalFrame;
import br.senac.objects.Mail;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Douglas
 */
public class MailScreen extends InternalFrame {

    private JButton btnsend;
    private JButton btnAnexFile;
    private JTextField txtDestinatario;
    private JTextField txtCaminhoFile;
    private JTextField txtAssunto;
    private JTextArea txtCorpo;
    private final JLabel lblDestinatario = new JLabel("Destinatario:");
    private final JLabel lblAssunto = new JLabel("Assunto:");
    private JPanel panelNorth;
    private JPanel panelPageEnd;
    private final Mail mail = new Mail();
    private JProgressBar progressBar;

    public MailScreen() {
        super("Tela de E-mail", false, true, true, true, 707, 400);
        this.initComponents();
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

    private JPanel getPanelNorth() {
        panelNorth = new JPanel(new FlowLayout(10, 10, 10));
        panelNorth.add(lblDestinatario);
        panelNorth.add(getTxtDestinatario());
        panelNorth.add(lblAssunto);
        panelNorth.add(getTxtAssunto());
        return panelNorth;
    }

    private JPanel getPanelPageEnd() {
        panelPageEnd = new JPanel(new FlowLayout(10, 10, 10));
        panelPageEnd.add(getBtnAnexFile());
        panelPageEnd.add(getTxtCaminhoFile());
        panelPageEnd.add(getBtnsend());
        panelPageEnd.add(getProgressBar());
        return panelPageEnd;
    }

    private void initComponents() {
        this.add(BorderLayout.NORTH, getPanelNorth());
        this.add(BorderLayout.CENTER, getTxtCorpo());
        this.add(BorderLayout.PAGE_END, getPanelPageEnd());
    }

    private JProgressBar getProgressBar() {
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        progressBar.setVisible(false);
        return progressBar;
    }

    private JTextField getTxtDestinatario() {
        txtDestinatario = new JTextField();
        txtDestinatario.setPreferredSize(new Dimension(200, 30));
        txtDestinatario.addFocusListener(this);
        return txtDestinatario;
    }

    private JTextField getTxtAssunto() {
        txtAssunto = new JTextField();
        txtAssunto.setPreferredSize(new Dimension(200, 30));
        txtAssunto.addKeyListener(this);
        return txtAssunto;
    }

    private JTextArea getTxtCorpo() {
        txtCorpo = new JTextArea(10, 10);
        return txtCorpo;
    }

    private JButton getBtnAnexFile() {
        btnAnexFile = new JButton("Anexar");
        btnAnexFile.addActionListener(this);
        btnAnexFile.setActionCommand("find");
        btnAnexFile.setPreferredSize(new Dimension(100, 30));
        return btnAnexFile;
    }

    private JTextField getTxtCaminhoFile() {
        txtCaminhoFile = new JTextField();
        txtCaminhoFile.setPreferredSize(new Dimension(200, 30));
        txtCaminhoFile.addKeyListener(this);
        txtCaminhoFile.setEnabled(false);
        return txtCaminhoFile;
    }

    private JButton getBtnsend() {
        btnsend = new JButton("Enviar");
        btnsend.addActionListener(this);
        btnsend.setActionCommand("enviar");
        btnsend.setEnabled(false);
        btnsend.setPreferredSize(new Dimension(100, 30));
        return btnsend;
    }

    @Override
    public void focusLost(FocusEvent e) {
        JTextField src = (JTextField) e.getSource();
        boolean validate = this.isValidEmailAddress(src.getText());
        btnsend.setEnabled(validate);
        if (!validate) {
            JOptionPane.showMessageDialog(this, "E-mail inválido!",
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "enviar":
                boolean ret = false;
                if (!txtAssunto.getText().equals("")
                        && !txtCorpo.getText().equals("")) {
                    progressBar.setVisible(true);
                    Thread t = new Thread(() -> {
                        mail.enviarGmail(txtDestinatario.getText(), txtAssunto.getText(),
                                txtCorpo.getText(), txtCaminhoFile.getText());
                    });
                    t.start();
                    ret = true;
                } else {
                    JOptionPane.showMessageDialog(this, "Campos de Assunto e "
                            + "Corpo do E-mail não podem estar Vazios!",
                            "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
                    progressBar.setVisible(false);
                }
                if (ret) {
                    JOptionPane.showMessageDialog(this, "Email enviado com sucesso!");
                    progressBar.setVisible(false);
                }
                break;
            case "find":
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(null) != 1) {
                    txtCaminhoFile.setText(fc.getSelectedFile().getAbsolutePath());
                }
                break;
        }
    }
}
