package br.senac.view;

import br.senac.controller.CategoryDAO;
import br.senac.objects.images;
import br.senac.model.Category;
import br.senac.objects.Excel;
import br.senac.objects.InternalFrame;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
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
public class RegistrationCategoryScreen extends InternalFrame {

    private JTabbedPane painelAbas;
    private JLabel lblBrand;
    private JPanel panelExcel;
    private JButton btnCheck;
    private JButton btnClose;
    private int id;
    private JTextField txtBrand;
    private final String colunas[] = {"Categoria"};
    private JPanel panel;
    private DefaultTableModel dm;
    private JTable tblExcel;
    private JScrollPane scroll;
    private JButton btnCheckExcel;
    private ArrayList<Category> categoryList;
    private JButton btnExcluirExcel;
    private JButton btnImportExcel;
    private final CategoryDAO dao = CategoryDAO.getInstance();

    public RegistrationCategoryScreen(String formato) {
        super((formato.equals("Creation") ? "Cadastrar" : "Alterar"), false, true, false, false, 500, 400);
        InitComponents(formato);
    }

    public RegistrationCategoryScreen(Category category, String formato) {
        super((formato.equals("Creation") ? "Cadastrar" : "Alterar"), false, true, false, false, 500, 400);
        InitComponents(formato);
        this.txtBrand.setText(category.getCategoria());
        this.id = category.getId();
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
        panel.setBorder(BorderFactory.createTitledBorder(formato.equals("Creation") ? "Cadastrar Categoria" : "Alterar Categoria"));
        panel.add(getTxtBrand());
        panel.add(getLblBrand());
        panel.add(getBtnClose());
        panel.add(getBtnCheck(formato));
        return panel;
    }

    private JTextField getTxtBrand() {
        txtBrand = new JTextField();
        txtBrand.setBounds(120, 30, 330, 25);
        txtBrand.getDocument().addDocumentListener(new DocListner());
        return txtBrand;
    }

    private JLabel getLblBrand() {
        lblBrand = new JLabel("Categoria:");
        lblBrand.setBounds(25, 30, 70, 25);
        return lblBrand;
    }

    private JButton getBtnClose() {
        btnClose = new JButton("Cancelar", images.imagemClose());
        btnClose.setBounds(250, 250, 200, 40);
        btnClose.addActionListener(this);
        btnClose.setActionCommand("close");
        return btnClose;
    }

    private JButton getBtnCheck(String formato) {
        btnCheck = new JButton("Salvar", images.imagemCheck());
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

    private void CarregarJTable(ArrayList<Category> productsList, boolean excluir) {
        if (excluir) {
            int row = tblExcel.getSelectedRow();
            String nome = tblExcel.getModel().getValueAt(row, 0).toString();
            for (int i = 0; i < productsList.toArray().length; i++) {
                if (productsList.get(i).getCategoria().equals(nome)) {
                    productsList.remove(i);
                    break;
                }
            }
        }
        DefaultTableModel modelo = (DefaultTableModel) tblExcel.getModel();
        modelo.setRowCount(0);
        productsList.forEach((p) -> {
            modelo.addRow(new Object[]{p.getCategoria()});
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                Category objMarca = new Category(txtBrand.getText(), 0, null, null, null, "1");
                if (dao.save(objMarca)) {
                    JOptionPane.showMessageDialog(this, "Categoria Salva Com Sucesso!");
                    this.dispose();
                }
                break;
            case "alter":
                Category objMarcaAlt = new Category(txtBrand.getText(), id, null, null, null, "1");
                dao.Alter(objMarcaAlt);
                this.dispose();
                break;
            case "import":
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.showOpenDialog(null);
                categoryList = Excel.importCategory(fc.getSelectedFile());
                CarregarJTable(categoryList, false);
                break;
            case "delete":
                CarregarJTable(categoryList, true);
                break;
            case "saveExcel":
                boolean ret = false;
                for (int i = 0; i < categoryList.toArray().length; i++) {
                    ret = dao.save(categoryList.get(i));
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
