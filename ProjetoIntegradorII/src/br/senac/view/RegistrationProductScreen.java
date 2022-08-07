package br.senac.view;

import br.senac.controller.ProductDAO;
import br.senac.objects.images;
import br.senac.model.Brand;
import br.senac.model.Product;
import br.senac.objects.Excel;
import br.senac.objects.InternalFrame;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Douglas
 */
public class RegistrationProductScreen extends InternalFrame {

    private JTabbedPane painelAbas;
    private JPanel panelCadastro;
    private JPanel panelExcel;
    private JLabel lblProduct;
    private JTextField txtProduct;
    private JLabel lblMarca;
    private JLabel lblValor;
    private JLabel lblQuantidade;
    private JTextField txtValor;
    private JTextField txtQuantidade;
    private JButton btnCheck;
    private JButton btnClose;
    private JButton btnCheckExcel;
    private JButton btnExcluirExcel;
    private final String colunas[] = {"Produto", "Valor", "Marca", "Quantidade"};
    private JTable tblExcel;
    private DefaultTableModel dm;
    private JScrollPane scroll;
    private ArrayList<Product> productsList;
    private JButton btnImportExcel;

    private int id;
    private JComboBox<String> jboBrand;

    public RegistrationProductScreen(String formato) {
        super((formato.equals("Creation") ? "Cadastrar" : "Alterar"), false, true, false, false, 700, 400);
        InitComponents(formato);
    }

    public RegistrationProductScreen(Product product, String formato) {
        super((formato.equals("Creation") ? "Cadastrar" : "Alterar"), false, true, false, false, 700, 400);
        InitComponents(formato);
        this.txtProduct.setText(product.getNome());
        this.jboBrand.setSelectedItem(product.getMarca());
        this.txtValor.setText(String.valueOf(product.getValor()));
        this.txtQuantidade.setText(String.valueOf(product.getQuantidade()));
        this.id = product.getId();
    }

    private void InitComponents(String formato) {
        this.setLayout(null);
        this.getContentPane().add(getPainelAbas(formato));

    }

    private JTabbedPane getPainelAbas(String formato) {
        painelAbas = new JTabbedPane();
        painelAbas.setBounds(10, 10, 670, 350);
        painelAbas.add("Cadastro", getPanelCadastro(formato));
        if (formato.equals("Creation")) {
            painelAbas.add("Excel", getPanelExcel());
        }
        return painelAbas;
    }

    private JPanel getPanelCadastro(String formato) {
        panelCadastro = new JPanel(null);
        panelCadastro.setBorder(BorderFactory.createTitledBorder(formato.equals("Creation") ? "Cadastrar Marca" : "Alterar Marca"));
        panelCadastro.add(getLblProduct());
        panelCadastro.add(getTxtProduct());
        panelCadastro.add(getLblMarca());
        panelCadastro.add(getJboBrand());
        panelCadastro.add(getLblValor());
        panelCadastro.add(getTxtValor());
        panelCadastro.add(getLblQuantidade());
        panelCadastro.add(getTxtQuantidade());
        panelCadastro.add(getBtnCheck(formato));
        panelCadastro.add(getBtnClose());
        return panelCadastro;
    }

    private JPanel getPanelExcel() {
        panelExcel = new JPanel(null);
        panelExcel.add(getScrollPane());
        panelExcel.add(getBtnCheckExcel());
        panelExcel.add(getBtnExcluirExcel());
        panelExcel.add(getBtnImportExcel());
        return panelExcel;
    }

    private JTextField getTxtProduct() {
        txtProduct = new JTextField();
        txtProduct.setBounds(100, 30, 230, 25);
        return txtProduct;
    }

    private JLabel getLblProduct() {
        lblProduct = new JLabel("Produto:");
        lblProduct.setBounds(25, 30, 50, 25);
        return lblProduct;
    }

    private JComboBox<String> getJboBrand() {
        jboBrand = new JComboBox<String>();
        jboBrand.setBounds(410, 30, 230, 25);
        try {
            for (Brand p : ProductDAO.AllBrands()) {
                String usu = p.getMarca();
                jboBrand.addItem(usu);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        return jboBrand;
    }

    private JLabel getLblMarca() {
        lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(350, 30, 50, 25);
        return lblMarca;
    }

    private JLabel getLblValor() {
        lblValor = new JLabel("Valor:");
        lblValor.setBounds(25, 100, 50, 25);
        return lblValor;
    }

    private JTextField getTxtValor() {
        txtValor = new JTextField();
        txtValor.setBounds(100, 100, 230, 25);
        return txtValor;
    }

    private JLabel getLblQuantidade() {
        lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(340, 100, 90, 25);
        return lblQuantidade;
    }

    private JTextField getTxtQuantidade() {
        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(410, 100, 230, 25);
        return txtQuantidade;
    }

    private JButton getBtnClose() {
        btnClose = new JButton("Cancelar", images.imagemClose());
        btnClose.setBounds(400, 250, 250, 40);
        btnClose.addActionListener(this);
        btnClose.setActionCommand("close");
        return btnClose;
    }

    private JButton getBtnCheck(String formato) {
        btnCheck = new JButton("Salvar", images.imagemCheck());
        btnCheck.setBounds(25, 250, 250, 40);
        btnCheck.addActionListener(this);
        btnCheck.setActionCommand(formato.equals("Creation") ? "save" : "alter");
        return btnCheck;
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
        scroll.setBounds(0, 0, 550, 400);
        return scroll;
    }

    private JButton getBtnCheckExcel() {
        btnCheckExcel = new JButton("Incluir");
        btnCheckExcel.setBounds(560, 82, 100, 20);
        btnCheckExcel.addActionListener(this);
        btnCheckExcel.setActionCommand("saveExcel");
        return btnCheckExcel;
    }

    private JButton getBtnExcluirExcel() {
        btnExcluirExcel = new JButton("Excluir");
        btnExcluirExcel.setBounds(560, 2, 100, 20);
        btnExcluirExcel.setEnabled(false);
        btnExcluirExcel.addActionListener(this);
        btnExcluirExcel.setActionCommand("delete");
        return btnExcluirExcel;
    }

    private JButton getBtnImportExcel() {
        btnImportExcel = new JButton("Importar");
        btnImportExcel.setBounds(560, 42, 100, 20);
        btnImportExcel.addActionListener(this);
        btnImportExcel.setActionCommand("import");
        return btnImportExcel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                Product objProduct = new Product(0, txtProduct.getText(), Float.parseFloat(txtValor.getText()),
                         Integer.parseInt(txtQuantidade.getText()), jboBrand.getSelectedItem().toString(), null, null, null);
                if (ProductDAO.save(objProduct)) {
                    JOptionPane.showMessageDialog(this, "Marca Salva Com Sucesso!");
                    this.dispose();
                }
                break;
            case "alter":
                Product objMarcaAlt = new Product(id, txtProduct.getText(), Float.parseFloat(txtValor.getText()),
                         Integer.parseInt(txtQuantidade.getText()), jboBrand.getSelectedItem().toString(), null, "1", null);
                ProductDAO.AlterProduct(objMarcaAlt);
                this.dispose();
                break;
            case "import":
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fc.showOpenDialog(null);
                productsList = Excel.importProduct(fc.getSelectedFile());
                CarregarJTable(productsList, false);
                break;
            case "delete":
                CarregarJTable(productsList, true);
                break;
            case "saveExcel":
                boolean ret = ProductDAO.saveExcel(productsList);
                if (ret) {
                    JOptionPane.showMessageDialog(this, "Registros incluidos com sucesso!");
                }
                break;
            default:
                this.dispose();
                break;
        }
    }

    private void CarregarJTable(ArrayList<Product> productsList, boolean excluir) {
        if (excluir) {
            int row = tblExcel.getSelectedRow();
            String nome = tblExcel.getModel().getValueAt(row, 0).toString();
            for (int i = 0; i < productsList.toArray().length; i++) {
                if (productsList.get(i).getNome().equals(nome)) {
                    productsList.remove(i);
                    break;
                }
            }
        }
        DefaultTableModel modelo = (DefaultTableModel) tblExcel.getModel();
        modelo.setRowCount(0);
        productsList.forEach((p) -> {
            modelo.addRow(new Object[]{p.getNome(), p.getValor(), p.getMarca(), p.getQuantidade()});
        });
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            boolean rowsAreSelected = tblExcel.getSelectedRowCount() > 0;
            btnExcluirExcel.setEnabled(rowsAreSelected);
        }
    }
}
