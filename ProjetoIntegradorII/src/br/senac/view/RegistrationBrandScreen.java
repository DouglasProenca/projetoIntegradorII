package br.senac.view;

import br.senac.controller.MarcaDao;
import br.senac.objects.images;
import br.senac.model.Brand;
import br.senac.model.User;
import br.senac.objects.Excel;
import br.senac.objects.InternalFrame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Douglas
 */
public class RegistrationBrandScreen extends InternalFrame {

    private JTabbedPane painelAbas;
    private JLabel lblBrand;
    private JPanel panelExcel;
    private JLabel lblpais;
    private JButton btnCheck;
    private JButton btnClose;
    private int id;
    private JTextField txtBrand;
    private final String colunas[] = {"Marca", "Pais"};
    private JComboBox<String> jboCountry;
    private JPanel panel;
    private DefaultTableModel dm;
    private JTable tblExcel;
    private JScrollPane scroll;
    private JButton btnCheckExcel;
    private ArrayList<Brand> brandList;
    private JButton btnExcluirExcel;
    private JButton btnImportExcel;
    private final MarcaDao dao = MarcaDao.getInstance();

    public RegistrationBrandScreen(String formato) {
        super((formato.equals("Creation") ? "Cadastrar" : "Alterar"), false, true, false, false, 500, 400);
        this.InitComponents(formato);
    }

    public RegistrationBrandScreen(Brand brand, String formato) {
        super((formato.equals("Creation") ? "Cadastrar" : "Alterar"), false, true, false, false, 500, 400);
        this.InitComponents(formato);
        this.txtBrand.setText(brand.getMarca());
        this.jboCountry.setSelectedItem(brand.getPais());
        this.id = brand.getId();
    }

    private void InitComponents(String formato) {
        this.setLayout(null);
        this.getContentPane().add(getPainelAbas(formato));
    }

    private JTabbedPane getPainelAbas(String formato) {
        painelAbas = new JTabbedPane();
        painelAbas.setBounds(10, 10, 470, 350);
        painelAbas.add("Cadastro", getPanelCadastro(formato));
        if (formato.equals("Creation")) {
            painelAbas.add("Excel", getPanelExcel());
        }
        return painelAbas;
    }

    private JPanel getPanelExcel() {
        panelExcel = new JPanel(null);
        panelExcel.add(getScrollPane());
        panelExcel.add(getBtnCheckExcel());
        panelExcel.add(getBtnExcluirExcel());
        panelExcel.add(getBtnImportExcel());
        return panelExcel;
    }

    private JTable getTblExcel() {
        tblExcel = new JTable(getDm());
        tblExcel.getSelectionModel().addListSelectionListener(this);
        tblExcel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return tblExcel;
    }

    private DefaultTableModel getDm() {
        dm = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int rowIndex, int mColIndex) {
                return false;
            }
        };
        return dm;
    }

    private JScrollPane getScrollPane() {
        scroll = new JScrollPane(getTblExcel());
        scroll.setBounds(0, 0, 300, 300);
        return scroll;
    }

    private JPanel getPanelCadastro(String formato) {
        panel = new JPanel(null);
        panel.setBorder(BorderFactory.createTitledBorder(formato.equals("Creation") ? "Cadastrar Marca" : "Alterar Marca"));
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
        txtBrand.setBounds(100, 30, 350, 25);
        txtBrand.getDocument().addDocumentListener(new DocListner());
        return txtBrand;
    }

    private JLabel getLblBrand() {
        lblBrand = new JLabel("Marca:");
        lblBrand.setBounds(25, 30, 40, 25);
        return lblBrand;
    }

    private JComboBox<String> getJboCountry() {
        jboCountry = new JComboBox<>();
        jboCountry.setBounds(100, 80, 350, 25);
        dao.AllCountry().stream().map((p) -> p.getPais()).forEachOrdered((String usu) -> {
            jboCountry.addItem(usu);
        });
        return jboCountry;
    }

    private JLabel getLblpais() {
        lblpais = new JLabel("Pais:");
        lblpais.setBounds(25, 80, 230, 25);
        return lblpais;
    }

    private JButton getBtnClose() {
        btnClose = new JButton("Cancelar", images.getInstance().imagemClose());
        btnClose.setBounds(250, 250, 200, 40);
        btnClose.addActionListener(this);
        btnClose.setActionCommand("close");
        return btnClose;
    }

    private JButton getBtnCheck(String formato) {
        btnCheck = new JButton("Salvar", images.getInstance().imagemCheck());
        btnCheck.setBounds(20, 250, 200, 40);
        btnCheck.addActionListener(this);
        btnCheck.setActionCommand(formato.equals("Creation") ? "save" : "alter");
        btnCheck.setEnabled(false);
        return btnCheck;
    }

    private JButton getBtnCheckExcel() {
        btnCheckExcel = new JButton("Incluir");
        btnCheckExcel.setBounds(350, 82, 100, 20);
        btnCheckExcel.addActionListener(this);
        btnCheckExcel.setActionCommand("saveExcel");
        return btnCheckExcel;
    }

    private JButton getBtnExcluirExcel() {
        btnExcluirExcel = new JButton("Excluir");
        btnExcluirExcel.setBounds(350, 2, 100, 20);
        btnExcluirExcel.setEnabled(false);
        btnExcluirExcel.addActionListener(this);
        btnExcluirExcel.setActionCommand("delete");
        return btnExcluirExcel;
    }

    private JButton getBtnImportExcel() {
        btnImportExcel = new JButton("Importar");
        btnImportExcel.setBounds(350, 42, 100, 20);
        btnImportExcel.addActionListener(this);
        btnImportExcel.setActionCommand("import");
        return btnImportExcel;
    }

    private void CarregarJTable(ArrayList<Brand> productsList, boolean excluir) {
        if (excluir) {
            int row = tblExcel.getSelectedRow();
            String nome = tblExcel.getModel().getValueAt(row, 0).toString();
            for (int i = 0; i < productsList.toArray().length; i++) {
                if (productsList.get(i).getMarca().equals(nome)) {
                    productsList.remove(i);
                    break;
                }
            }
        }
        DefaultTableModel modelo = (DefaultTableModel) tblExcel.getModel();
        modelo.setRowCount(0);
        productsList.forEach((p) -> {
            modelo.addRow(new Object[]{p.getMarca(), p.getPais()});
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                Brand objMarca = new Brand(0, txtBrand.getText(), jboCountry.getSelectedItem().toString(), new Date(), String.valueOf(User.getInstance().getId()));
                if (dao.save(objMarca)) {
                    JOptionPane.showMessageDialog(this, "Marca Salva Com Sucesso!");
                    this.dispose();
                }
                break;
            case "alter":
                Brand objMarcaAlt = new Brand(id, txtBrand.getText(), jboCountry.getSelectedItem().toString(), new Date(), String.valueOf(User.getInstance().getId()));
                dao.alter(objMarcaAlt);
                this.dispose();
                break;
            case "import":
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int choice = fc.showOpenDialog(null);
                if (choice != 1) {
                    brandList = Excel.importBrand(fc.getSelectedFile());
                    CarregarJTable(brandList, false);
                }
                break;
            case "delete":
                CarregarJTable(brandList, true);
                break;
            case "saveExcel":
                boolean ret = false;
                for (int i = 0; i < brandList.toArray().length; i++) {
                    ret = dao.save(brandList.get(i));
                }
                if (ret) {
                    JOptionPane.showMessageDialog(this, "Registros incluidos com sucesso!");
                }
                break;
            default:
                this.dispose();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            boolean rowsAreSelected = tblExcel.getSelectedRowCount() > 0;
            btnExcluirExcel.setEnabled(rowsAreSelected);
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
            boolean type = txtBrand.getText().length() <= 0 ? false : true;
            return type;
        }
    }
}
