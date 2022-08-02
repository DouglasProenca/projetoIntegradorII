package br.senac.view;

import br.senac.controller.MarcaDao;
import br.senac.geral.images;
import br.senac.model.Marca;
import br.senac.view.objetos.InternalFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Douglas
 */
public class RegistrationBrandScreen extends InternalFrame implements ActionListener {

    private JLabel lblBrand;
    private JLabel lblpais;
    private JButton btnCheck;
    private JButton btnExcel;
    private JButton btnClose;
    private JTextField txtBrand;
    private JComboBox<String> jboCountry;
    private JPanel panel;

    public RegistrationBrandScreen(String formato) {
        super((formato.equals("Creation") ? "Cadastrar" : "Alterar"), false, true, false, false, 400, 400);
        InitComponents(formato);
    }

    private void InitComponents(String formato) {
        this.setLayout(null);
        this.getContentPane().add(getPanel(formato));
    }

    private JPanel getPanel(String formato) {
        panel = new JPanel(null);
        panel.setBorder(BorderFactory.createTitledBorder(formato.equals("Creation") ? "Cadastrar Marca" : "Alterar Marca"));
        panel.setBounds(10, 10, 366, 345);
        panel.add(getTxtBrand());
        panel.add(getLblBrand());
        panel.add(getJboCountry());
        panel.add(getLblpais());
        panel.add(getBtnClose());
        panel.add(getBtnCheck(formato));
        return panel;
    }

    private JTextField getTxtBrand() {
        txtBrand = new JTextField();
        txtBrand.setBounds(100, 30, 230, 25);
        return txtBrand;
    }

    private JLabel getLblBrand() {
        lblBrand = new JLabel("Marca:");
        lblBrand.setBounds(25, 30, 40, 25);
        return lblBrand;
    }

    private JComboBox<String> getJboCountry() {
        jboCountry = new JComboBox<String>();
        jboCountry.setBounds(100, 80, 230, 25);
        try {
            for (Marca p : MarcaDao.AllCountry()) {
                String usu = p.getPais();
                jboCountry.addItem(usu);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return jboCountry;
    }

    private JLabel getLblpais() {
        lblpais = new JLabel("Pais:");
        lblpais.setBounds(25, 80, 230, 25);
        return lblpais;
    }

    private JButton getBtnClose() {
        btnClose = new JButton("Cancelar", images.imagemClose());
        btnClose.setBounds(200, 280, 150, 40);
        btnClose.addActionListener(this);
        btnClose.setActionCommand("close");
        return btnClose;
    }

    private JButton getBtnCheck(String formato) {
        btnCheck = new JButton("Salvar", images.imagemCheck());
        btnCheck.setBounds(20, 280, 150, 40);
        btnCheck.addActionListener(this);
        btnCheck.setActionCommand(formato.equals("Creation") ? "save" : "alter");
        return btnCheck;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                Marca objMarca = new Marca(txtBrand.getText(), jboCountry.getSelectedItem().toString());
                if(MarcaDao.save(objMarca)){
                    JOptionPane.showMessageDialog(this, "Marca Salva Com Sucesso!");
                    this.dispose();
                };
                break;
            case "alter":
                break;
            case "excel":
                break;
            default:
                this.dispose();
                break;
        }
    }

}
