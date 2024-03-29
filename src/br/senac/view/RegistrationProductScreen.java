package br.senac.view;

import br.senac.controller.CategoryDAO;
import br.senac.controller.BrandDao;
import br.senac.controller.ProductDAO;
import br.senac.objects.images;
import br.senac.model.Product;
import br.senac.model.User;
import br.senac.objects.Excel;
import br.senac.objects.InternalFrame;
import br.senac.objects.SpinnerNumberInt;
import br.senac.objects.TableModel;
import br.senac.objects.TextFieldNumber;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
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
    private JLabel lblCategoria;
    private JLabel lblQuantidade;
    private TextFieldNumber txtValor;
    private SpinnerNumberInt txtQuantidade;
    private JButton btnCheck;
    private JButton btnClose;
    private JButton btnCheckExcel;
    private JButton btnExcluirExcel;
    private final String colunas[] = {"Produto", "Valor", "Marca", "Categoria", "Quantidade"};
    private JTable tblExcel;
    private JScrollPane scroll;
    private ArrayList<Product> productsList = new ArrayList<Product>();
    private JButton btnImportExcel;
    private int id;
    private JComboBox<String> jboBrand;
    private JComboBox<String> jboCategoria;
    private final BrandDao dao = BrandDao.getInstance();
    private final ProductDAO daop = ProductDAO.getInstance();
    private final CategoryDAO daoc = CategoryDAO.getInstance();
    private final Excel excel = new Excel();

    public RegistrationProductScreen() {
        super("Cadastrar", false, true, false, false, 700, 400);
        this.initComponents(false);
    }

    public RegistrationProductScreen(Product product) {
        super("Alterar", false, true, false, false, 700, 400);
        this.initComponents(true);
        this.txtProduct.setText(product.getNome());
        this.jboBrand.setSelectedItem(product.getMarca());
        this.txtValor.setText(String.valueOf(product.getValor()));
        this.txtQuantidade.setValue(product.getQuantidade());
        this.id = product.getId();
    }

    private void initComponents(boolean type) {
        this.setLayout(null);
        this.getContentPane().add(getPainelAbas(type));
    }

    private JTabbedPane getPainelAbas(boolean type) {
        painelAbas = new JTabbedPane();
        painelAbas.setBounds(10, 10, 670, 350);
        painelAbas.add("Cadastro", getPanelCadastro(type));
        if (!type) {
            painelAbas.add("Excel", getPanelExcel());
        }
        return painelAbas;
    }

    private JPanel getPanelCadastro(boolean type) {
        panelCadastro = new JPanel(null);
        panelCadastro.setBorder(BorderFactory.createTitledBorder(!type ? "Cadastrar Produto" : "Alterar Produto"));
        panelCadastro.add(getLblProduct());
        panelCadastro.add(getTxtProduct());
        panelCadastro.add(getLblMarca());
        panelCadastro.add(getJboBrand());
        panelCadastro.add(getLblValor());
        panelCadastro.add(getTxtValor());
        panelCadastro.add(getLblQuantidade());
        panelCadastro.add(getTxtQuantidade());
        panelCadastro.add(getBtnCheck(type));
        panelCadastro.add(getBtnClose());
        panelCadastro.add(getLblCategoria());
        panelCadastro.add(getJboCategoria());
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
        txtProduct.getDocument().addDocumentListener(new DocListner());
        return txtProduct;
    }

    private JLabel getLblProduct() {
        lblProduct = new JLabel("Produto:");
        lblProduct.setBounds(25, 30, 50, 25);
        return lblProduct;
    }

    private JComboBox<String> getJboBrand() {
        jboBrand = new JComboBox<>();
        jboBrand.setBounds(410, 30, 230, 25);
        dao.getAll().stream().map((p) -> p.getMarca()).forEachOrdered((usu) -> {
            jboBrand.addItem(usu);
        });
        return jboBrand;
    }

    private JComboBox<String> getJboCategoria() {
        jboCategoria = new JComboBox<>();
        jboCategoria.setBounds(100, 100, 230, 25);
        daoc.getAll().stream().map((p) -> p.getCategoria()).forEachOrdered((usu) -> {
            jboCategoria.addItem(usu);
        });
        return jboCategoria;
    }

    private JLabel getLblMarca() {
        lblMarca = new JLabel("Marca:");
        lblMarca.setBounds(350, 30, 50, 25);
        return lblMarca;
    }

    private JLabel getLblCategoria() {
        lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(25, 100, 90, 25);
        return lblCategoria;
    }

    private JLabel getLblValor() {
        lblValor = new JLabel("Valor:");
        lblValor.setBounds(350, 100, 90, 25);
        return lblValor;
    }

    private JTextField getTxtValor() {
        txtValor = new TextFieldNumber();
        txtValor.setBounds(410, 100, 230, 25);
        txtValor.getDocument().addDocumentListener(new DocListner());
        return txtValor;
    }

    private JLabel getLblQuantidade() {
        lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(25, 180, 90, 25);
        return lblQuantidade;
    }

    private JSpinner getTxtQuantidade() {
        txtQuantidade = new SpinnerNumberInt();
        txtQuantidade.setBounds(100, 180, 230, 25);
        return txtQuantidade;
    }

    private JButton getBtnClose() {
        btnClose = new JButton("Cancelar", images.getInstance().imagemClose());
        btnClose.setBounds(400, 250, 250, 40);
        btnClose.addActionListener(this);
        btnClose.setActionCommand("close");
        return btnClose;
    }

    private JButton getBtnCheck(boolean type) {
        btnCheck = new JButton("Salvar", images.getInstance().imagemCheck());
        btnCheck.setBounds(25, 250, 250, 40);
        btnCheck.addActionListener(this);
        btnCheck.setActionCommand(!type ? "save" : "alter");
        btnCheck.setEnabled(false);
        return btnCheck;
    }

    private JTable getTblExcel() {
        tblExcel = new JTable(new TableModel(colunas, 0));
        tblExcel.getSelectionModel().addListSelectionListener(this);
        tblExcel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return tblExcel;
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
    public void internalFrameOpened(InternalFrameEvent e) {
        JInternalFrame frame = (JInternalFrame) e.getSource();
        this.centralizaForm(frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "save":
                Product objProduct = new Product(txtProduct.getText(), Float.parseFloat(txtValor.getText()), Integer.parseInt(txtQuantidade.getValue().toString()),
                        jboCategoria.getSelectedItem().toString(), 0, jboBrand.getSelectedItem().toString(), null, new Date(), String.valueOf(User.getInstance().getId()));
                if (daop.save(objProduct)) {
                    JOptionPane.showMessageDialog(this, "Produto Salvo Com Sucesso!");
                    this.dispose();
                }
                break;
            case "alter":
                Product objMarcaAlt = new Product(txtProduct.getText(), Float.parseFloat(txtValor.getText()), Integer.parseInt(txtQuantidade.getValue().toString()),
                        jboCategoria.getSelectedItem().toString(), id, jboBrand.getSelectedItem().toString(), null, new Date(), String.valueOf(User.getInstance().getId()));
                daop.alter(objMarcaAlt);
                this.dispose();
                break;
            case "import":
                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                if (fc.showOpenDialog(null) != 1) {
                    String dados[][] = excel.importExcel(fc.getSelectedFile(), tblExcel);
                    for (int i = 0; i < dados.length; i++) {
                        if (dados[i][0] != null) {
                            Product p = new Product(dados[i][0], Float.parseFloat(dados[i][1]),
                                    Integer.parseInt(dados[i][4]), dados[i][3], 0, dados[i][2], null, new Date(), String.valueOf(User.getInstance().getId()));
                            productsList.add(p);
                        }
                    }
                    CarregarJTable(productsList, false);
                }
                break;
            case "delete":
                CarregarJTable(productsList, true);
                break;
            case "saveExcel":
                boolean ret = false;
                if (productsList != null) {
                    for (int i = 0; i < productsList.toArray().length; i++) {
                        ret = daop.save(productsList.get(i));
                    }
                    if (ret) {
                        JOptionPane.showMessageDialog(this, "Registros incluidos com sucesso!");
                        this.dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Selecione uma importação para continuar",
                            "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
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
            modelo.addRow(new Object[]{p.getNome(), p.getValor(), p.getMarca(), p.getCategoria(), p.getQuantidade()});
        });
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
            boolean type = false;
            if (txtProduct.getText().length() >= 1 && txtValor.getText().length() >= 1 && txtQuantidade.getValue().toString().length() >= 1) {
                type = true;
            }
            return type;
        }
    }
}
