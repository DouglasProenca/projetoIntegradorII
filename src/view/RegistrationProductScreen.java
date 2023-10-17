package view;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import controller.BrandDAO;
import controller.CategoryDAO;
import controller.ProductDAO;
import model.Product;
import model.User;
import objects.Excel;
import objects.Images;
import objects.InternalFrame;
import objects.SpinnerNumberInt;
import objects.TableModel;
import objects.TextField;
import objects.Utils;


public class RegistrationProductScreen extends InternalFrame implements DocumentListener {

	private static final long serialVersionUID = 1L;
	private JTabbedPane painelAbas;
	private JPanel panelCadastro;
	private JPanel panelExcel;
	private JLabel lblProduct;
	private TextField txtProduct;
	private JLabel lblMarca;
	private JLabel lblValor;
	private JLabel lblCategoria;
	private JLabel lblQuantidade;
	private TextField txtValor;
	private SpinnerNumberInt txtQuantidade;
	private JButton btnCheck;
	private JButton btnClose;
	private JButton btnCheckExcel;
	private JButton btnExcluirExcel;
	private final String colunas[] = { "Produto", "Valor", "Marca", "Categoria", "Quantidade" };
	private JTable tblExcel;
	private JScrollPane scroll;
	private ArrayList<Product> productsList = new ArrayList<Product>();
	private JButton btnImportExcel;
	private int id;
	private JComboBox<String> jboBrand;
	private JComboBox<String> jboCategoria;
	private final BrandDAO dao = BrandDAO.getInstance();
	private final ProductDAO daop = ProductDAO.getInstance();
	private final CategoryDAO daoc = CategoryDAO.getInstance();
	private final Excel excel = new Excel();
	private JLabel lblFoto;
	private JLabel lblImagem;
	Vector<Integer> id_marca = new Vector<Integer>();
	Vector<Integer> id_categoria = new Vector<Integer>();
	private ProductReportScreen productReportScreen;

	public RegistrationProductScreen(ProductReportScreen productReportScreen) {
		super("Cadastrar", false, true, false, false, 700, 400);
		this.initComponents();
		this.productReportScreen = productReportScreen;
	}

	public RegistrationProductScreen(Product product,ProductReportScreen productReportScreen) {
		super("Alterar", false, true, false, false, 700, 400);
		this.initComponents();
		this.txtProduct.setText(product.getProduct_name());
		this.jboBrand.setSelectedItem(product.getBrand_name());
		this.txtValor.setText(String.valueOf(product.getProduct_valor()));
		this.txtQuantidade.setValue(product.getProduct_qtd());
		this.id = product.getProduct_id();
		this.lblImagem.setIcon(product.getProduct_img() == null ? null
				: new ImageIcon(Utils.exibiImagemLabel(product.getProduct_img())));
		this.painelAbas.remove(1);
		this.panelCadastro.setBorder(BorderFactory.createTitledBorder("Alterar Produto"));
		this.btnCheck.setActionCommand("alter");
		this.productReportScreen = productReportScreen;
	}

	private void initComponents() {
		this.setLayout(null);
		this.getContentPane().add(getPainelAbas());
	}

	private JTabbedPane getPainelAbas() {
		painelAbas = new JTabbedPane();
		painelAbas.setBounds(10, 10, 670, 350);
		painelAbas.add("Cadastro", getPanelCadastro());
		painelAbas.add("Excel", getPanelExcel());
		return painelAbas;
	}

	private JPanel getPanelCadastro() {
		panelCadastro = new JPanel(null);
		panelCadastro.setBorder(BorderFactory.createTitledBorder("Cadastrar Produto"));
		panelCadastro.add(getLblProduct());
		panelCadastro.add(getTxtProduct());
		panelCadastro.add(getLblMarca());
		panelCadastro.add(getJboBrand());
		panelCadastro.add(getLblValor());
		panelCadastro.add(getTxtValor());
		panelCadastro.add(getLblQuantidade());
		panelCadastro.add(getTxtQuantidade());
		panelCadastro.add(getBtnCheck());
		panelCadastro.add(getBtnClose());
		panelCadastro.add(getLblCategoria());
		panelCadastro.add(getJboCategoria());
		panelCadastro.add(getLblFoto());
		panelCadastro.add(getLblImagem());
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

	private TextField getTxtProduct() {
		txtProduct = new TextField();
		txtProduct.setBounds(100, 30, 230, 25);
		txtProduct.getDocument().addDocumentListener(this);
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
		dao.getAll().stream().forEachOrdered((brand) -> {
			id_marca.addElement(brand.getBrand_id());
			jboBrand.addItem(brand.getBrand_name());
		});
		return jboBrand;
	}

	private JComboBox<String> getJboCategoria() {
		jboCategoria = new JComboBox<>();
		jboCategoria.setBounds(100, 100, 230, 25);

		daoc.getAll().stream().forEachOrdered((cate) -> {
			id_categoria.addElement(cate.getCategory_id());
			jboCategoria.addItem(cate.getCategory_name());
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

	private JLabel getLblFoto() {
		lblFoto = new JLabel("Foto:");
		lblFoto.setBounds(350, 180, 90, 25);
		return lblFoto;
	}

	private JLabel getLblImagem() {
		lblImagem = new JLabel();
		lblImagem.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		lblImagem.setBounds(410, 135, 90, 90);
		lblImagem.addMouseListener(this);
		return lblImagem;
	}

	private TextField getTxtValor() {
		txtValor = new TextField("Number");
		txtValor.setBounds(410, 100, 230, 25);
		txtValor.getDocument().addDocumentListener(this);
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
		btnClose = new JButton("Cancelar", Images.CLOSE.getImage());
		btnClose.setBounds(400, 250, 250, 40);
		btnClose.addActionListener(this);
		btnClose.setActionCommand("close");
		return btnClose;
	}

	private JButton getBtnCheck() {
		btnCheck = new JButton("Salvar", Images.CHECK.getImage());
		btnCheck.setBounds(25, 250, 250, 40);
		btnCheck.addActionListener(this);
		btnCheck.setActionCommand("save");
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
		InternalFrame frame = (InternalFrame) e.getSource();
		this.centralizaForm(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "save":
			Product objProduct = new Product(txtProduct.getText(), Float.parseFloat(txtValor.getText()),
					Integer.parseInt(txtQuantidade.getValue().toString()),
					id_categoria.get(jboCategoria.getSelectedIndex()), id_marca.get(jboBrand.getSelectedIndex()),
					new Date(), String.valueOf(User.getInstance().getId()), lblImagem.getIcon() == null ? null
							: Utils.getImgBytes(Utils.iconToBufferedImage(lblImagem.getIcon())));

			if (daop.save(objProduct)) {
				JOptionPane.showMessageDialog(this, "Produto Salvo Com Sucesso!");
				productReportScreen.loadTable();
				this.dispose();
			}
			break;
		case "alter":
			Product objMarcaAlt = new Product(id,txtProduct.getText(), Float.parseFloat(txtValor.getText()),
					Integer.parseInt(txtQuantidade.getValue().toString()),
					id_categoria.get(jboCategoria.getSelectedIndex()),
					id_marca.get(jboBrand.getSelectedIndex()), new Date(),
					String.valueOf(User.getInstance().getId()), lblImagem.getIcon() == null ? null
							: Utils.getImgBytes(Utils.iconToBufferedImage(lblImagem.getIcon())));
			daop.alter(objMarcaAlt);
			productReportScreen.loadTable();
			this.dispose();
			break;
		case "import":
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(this) == JFileChooser.FILES_ONLY) {
				String dados[][] = excel.importExcel(fc.getSelectedFile(), tblExcel);
				for (int i = 0; i < dados.length; i++) {
					if (dados[i][0] != null) {
						Product p = new Product(dados[i][0], Float.parseFloat(dados[i][1]),
								Integer.parseInt(dados[i][4]), dados[i][3], 0, dados[i][2], null, new Date(),
								String.valueOf(User.getInstance().getId()));
						productsList.add(p);
					}
				}
				CarregarJTable(productsList);
			}
			break;
		case "delete":
			String nome = tblExcel.getModel().getValueAt(tblExcel.getSelectedRow(), 0).toString();
			productsList.removeIf((p) -> p.getProduct_name().equals(nome));
			CarregarJTable(productsList);
			break;
		case "saveExcel":
			if (productsList != null) {
				productsList.forEach((p) -> {
					daop.save(p);
				});
				JOptionPane.showMessageDialog(this, "Registros incluidos com sucesso!");
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Selecione uma importação para continuar", "Aviso de Falha",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		default:
			this.dispose();
			break;
		}
	}

	private void CarregarJTable(ArrayList<Product> productsList) {
		DefaultTableModel modelo = (DefaultTableModel) tblExcel.getModel();
		modelo.setRowCount(0);
		productsList.forEach((p) -> {
			modelo.addRow(new Object[] { p.getProduct_name(), p.getProduct_valor(), p.getBrand_name(),
					p.getCategory_name(), p.getProduct_qtd() });
		});
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			boolean rowsAreSelected = tblExcel.getSelectedRowCount() > 0;
			btnExcluirExcel.setEnabled(rowsAreSelected);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JFileChooser fc = new JFileChooser();
		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			lblImagem.setIcon(new ImageIcon(Utils.setImagemDimensao(fc.getSelectedFile().getAbsolutePath(), 90, 90)));
		}
	}

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
		if (txtProduct.getText().length() >= 1 && txtValor.getText().length() >= 1
				&& txtQuantidade.getValue().toString().length() >= 1) {
			return (true);
		}
		return (false);
	}
}
