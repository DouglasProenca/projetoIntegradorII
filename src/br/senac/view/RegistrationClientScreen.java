package br.senac.view;

import br.senac.controller.ClientDAO;
import br.senac.model.Client;
import br.senac.model.User;
import br.senac.objects.images;
import br.senac.objects.InternalFrame;
import br.senac.objects.ValidateCpf;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Douglas
 */
public class RegistrationClientScreen extends InternalFrame {

    private JLabel lblName;
    private JLabel lblCPF;
    private JButton btnCheck;
    private JButton btnClose;
    private JTextField txtName;
    private JTextField txtCPF;
    private JPanel panel;
    private final ValidateCpf valCPF = new ValidateCpf();
    private JCheckBox checkCEP;

    public RegistrationClientScreen() {
        super("Cadastrar Cliente", false, true, false, false, 500, 400);
        this.initComponents();
    }

    private void initComponents() {
        this.setLayout(null);
        this.getContentPane().add(getPanelCadastro());
    }

    private JPanel getPanelCadastro() {
        panel = new JPanel(null);
        panel.add(getTxtName());
        panel.setBounds(10, 10, 480, 380);
        panel.add(getLblName());
        panel.add(getTxtCPF());
        panel.add(getLblCPF());
        panel.add(getBtnClose());
        panel.add(getBtnCheck());
        panel.add(getCheckCEP());
        return panel;
    }

    private JTextField getTxtName() {
        txtName = new JTextField();
        txtName.setBounds(100, 30, 350, 25);
        txtName.getDocument().addDocumentListener(new DocListner());
        return txtName;
    }

    private JLabel getLblName() {
        lblName = new JLabel("Nome:");
        lblName.setBounds(25, 30, 40, 25);
        return lblName;
    }

    private JTextField getTxtCPF() {
        txtCPF = new JTextField();
        try {
            txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
        } catch (ParseException ex) {
            Logger.getLogger(SaleScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtCPF.setBounds(100, 80, 350, 25);
        txtCPF.getDocument().addDocumentListener(new DocListner());
        return txtCPF;
    }

    private JLabel getLblCPF() {
        lblCPF = new JLabel("CPF:");
        lblCPF.setBounds(25, 80, 230, 25);
        return lblCPF;
    }

    private JButton getBtnClose() {
        btnClose = new JButton("Cancelar", images.getInstance().imagemClose());
        btnClose.setBounds(250, 250, 200, 40);
        btnClose.addActionListener(this);
        btnClose.setActionCommand("close");
        return btnClose;
    }

    private JButton getBtnCheck() {
        btnCheck = new JButton("Salvar", images.getInstance().imagemCheck());
        btnCheck.setBounds(20, 250, 200, 40);
        btnCheck.addActionListener(this);
        btnCheck.setActionCommand("save");
        btnCheck.setEnabled(false);
        return btnCheck;
    }

    private JCheckBox getCheckCEP() {
        checkCEP = new JCheckBox("Adicionar CEP");
        checkCEP.setBounds(20, 120, 200, 30);
        return checkCEP;
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        this.centralizaForm(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                String cpf = txtCPF.getText().replaceAll("\\.", "").replaceAll("\\-", "");
                if (valCPF.isCPF(cpf)) {
                    Client client = new Client(0, txtName.getText(), valCPF.imprimeCPF(cpf), String.valueOf(User.getInstance().getId()), new Date());
                    ClientDAO.getInstance().save(client);
                    JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
                    if (checkCEP.isSelected()) {
                        RegistrationCepScreen rbs = new RegistrationCepScreen();
                        getParent().add(rbs);
                        rbs.setVisible(true);
                    }
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "CPF inválido!", "Erro de Cadastro",
                            JOptionPane.ERROR_MESSAGE);
                }
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
            if (txtName.getText().length() >= 1
                    && txtCPF.getText().replaceAll("\\.", "").replaceAll("\\-", "")
                            .replaceAll(" ", "").length() >= 11) {
                return true;
            }
            return (false);
        }
    }
}
