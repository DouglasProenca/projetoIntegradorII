package view;

import controller.CategoryDAO;
import model.Category;
import model.User;
import objects.Excel;
import objects.Images;
import objects.InternalFrame;
import objects.TableModel;
import objects.TextField;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;


public class RegistrationCategoryScreen extends InternalFrame implements DocumentListener {

	private static final long serialVersionUID = 1L;
	private JTabbedPane painelAbas;
	private JLabel lblBrand;
	private JPanel panelExcel;
	private JButton btnCheck;
	private JButton btnClose;
	private int id;
	private TextField txtBrand;
	private JPanel panel;
	private JTable tblExcel;
	private JScrollPane scroll;
	private JButton btnCheckExcel;
	private ArrayList<Category> categoryList = new ArrayList<Category>();
	private final Excel excel = new Excel();
	private JButton btnExcluirExcel;
	private JButton btnImportExcel;
	private final CategoryDAO dao = CategoryDAO.getInstance();

	public RegistrationCategoryScreen() {
		super("Cadastrar", false, true, false, false, 500, 400);
		this.initComponents();
	}

	public RegistrationCategoryScreen(Category category) {
		super("Alterar", false, true, false, false, 500, 400);
		this.initComponents();
		this.txtBrand.setText(category.getCategory_name());
		this.id = category.getCategory_id();
		this.btnCheck.setActionCommand("alter");
		this.panel.setBorder(BorderFactory.createTitledBorder("Alterar Categoria"));
		this.painelAbas.remove(1);
	}

	private void initComponents() {
		this.setLayout(null);
		this.getContentPane().add(getPainelAbas());
	}

	private JTabbedPane getPainelAbas() {
		painelAbas = new JTabbedPane();
		painelAbas.setBounds(10, 10, 470, 350);
		painelAbas.add("Cadastro", getPanelCadastro());
		painelAbas.add("Excel", getPanelExcel());
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
		tblExcel = new JTable(new TableModel(new String[] { "Categoria" }, 0));
		tblExcel.getSelectionModel().addListSelectionListener(this);
		tblExcel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return tblExcel;
	}

	private JScrollPane getScrollPane() {
		scroll = new JScrollPane(getTblExcel());
		scroll.setBounds(0, 0, 300, 300);
		return scroll;
	}

	private JPanel getPanelCadastro() {
		panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder("Cadastrar Categoria"));
		panel.add(getTxtBrand());
		panel.add(getLblBrand());
		panel.add(getBtnClose());
		panel.add(getBtnCheck());
		return panel;
	}

	private TextField getTxtBrand() {
		txtBrand = new TextField();
		txtBrand.setBounds(120, 30, 330, 25);
		txtBrand.getDocument().addDocumentListener(this);
		return txtBrand;
	}

	private JLabel getLblBrand() {
		lblBrand = new JLabel("Categoria:");
		lblBrand.setBounds(25, 30, 70, 25);
		return lblBrand;
	}

	private JButton getBtnClose() {
		btnClose = new JButton("Cancelar", Images.CLOSE.getImage());
		btnClose.setBounds(250, 250, 200, 40);
		btnClose.addActionListener(this);
		btnClose.setActionCommand("close");
		return btnClose;
	}

	private JButton getBtnCheck() {
		btnCheck = new JButton("Salvar", Images.CHECK.getImage());
		btnCheck.setBounds(20, 250, 200, 40);
		btnCheck.addActionListener(this);
		btnCheck.setActionCommand("save");
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

	private void CarregarJTable(ArrayList<Category> productsList) {
		DefaultTableModel modelo = (DefaultTableModel) tblExcel.getModel();
		modelo.setRowCount(0);
		productsList.forEach((p) -> {
			modelo.addRow(new Object[] { p.getCategory_name() });
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "save":
			Category objMarca = new Category(txtBrand.getText(), new Date(),
					String.valueOf(User.getInstance().getId()));
			if (dao.save(objMarca)) {
				JOptionPane.showMessageDialog(this, "Categoria Salva Com Sucesso!");
				this.dispose();
			}
			break;
		case "alter":
			Category objMarcaAlt = new Category(txtBrand.getText(), id, new Date(),
					String.valueOf(User.getInstance().getId()));
			dao.alter(objMarcaAlt);
			this.dispose();
			break;
		case "import":
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(this) == JFileChooser.FILES_ONLY) {
				String dados[][] = excel.importExcel(fc.getSelectedFile(), tblExcel);
				for (int i = 0; i < dados.length; i++) {
					if (dados[i][0] != null) {
						Category p = new Category(dados[i][0], 0, new Date(),
								String.valueOf(User.getInstance().getId()));
						categoryList.add(p);
					}
				}
				CarregarJTable(categoryList);
			}
			break;
		case "delete":
			String nome = tblExcel.getModel().getValueAt(tblExcel.getSelectedRow(), 0).toString();
			categoryList.removeIf(c -> c.getCategory_name().equals(nome));
			this.CarregarJTable(categoryList);
			break;
		case "saveExcel":
			if (categoryList != null) {
				categoryList.forEach((c) -> {
						dao.save(c);
				});
				JOptionPane.showMessageDialog(this, "Registros incluidos com sucesso!");
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

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		InternalFrame frame = (InternalFrame) e.getSource();
		this.centralizaForm(frame);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			btnExcluirExcel.setEnabled(tblExcel.getSelectedRowCount() > 0);
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
		return txtBrand.getText().length() >= 1;
	}
}
