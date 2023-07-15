package view;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

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
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

import objects.TableModel;
import objects.TextField;
import controller.BrandDao;
import model.Brand;
import model.User;
import objects.Excel;
import objects.InternalFrame;
import objects.images;

@SuppressWarnings("serial")
public class RegistrationBrandScreen extends InternalFrame implements DocumentListener {

	private JTabbedPane painelAbas;
	private JLabel lblBrand;
	private JPanel panelExcel;
	private JLabel lblpais;
	private JButton btnCheck;
	private JButton btnClose;
	private int id;
	private TextField txtBrand;
	private JComboBox<String> jboCountry;
	private JPanel panel;
	private JTable tblExcel;
	private JScrollPane scroll;
	private JButton btnCheckExcel;
	private ArrayList<Brand> brandList = new ArrayList<Brand>();
	private JButton btnExcluirExcel;
	private JButton btnImportExcel;
	private final Excel excel = new Excel();
	private final BrandDao dao = BrandDao.getInstance();
	Vector<Integer> id_pais = new Vector<Integer>();

	public RegistrationBrandScreen() {
		super("Cadastrar", false, true, false, false, 500, 400);
		this.initComponents();
	}

	public RegistrationBrandScreen(Brand brand) {
		super("Alterar", false, true, false, false, 500, 400);
		this.initComponents();
		this.txtBrand.setText(brand.getBrand_name());
		this.jboCountry.setSelectedItem(brand.getCountry_nome());
		this.id = brand.getBrand_id();
		this.painelAbas.remove(1);
		this.panel.setBorder(BorderFactory.createTitledBorder("Alterar Marca"));
		this.btnCheck.setActionCommand("alter");
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
		tblExcel = new JTable(new TableModel(new String[] { "Marca", "Pais" }, 0));
		tblExcel.getSelectionModel().addListSelectionListener(this);
		tblExcel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return tblExcel;
	}

	private JScrollPane getScrollPane() {
		scroll = new JScrollPane(getTblExcel());
		scroll.setBounds(0, 0, 300, 300);
		return scroll;
	}

	public JPanel getPanelCadastro() {
		panel = new JPanel(null);
		panel.setBorder(BorderFactory.createTitledBorder("Cadastrar Marca"));
		panel.add(getTxtBrand());
		panel.add(getLblBrand());
		panel.add(getJboCountry());
		panel.add(getLblpais());
		panel.add(getBtnClose());
		panel.add(getBtnCheck());
		return panel;
	}

	private TextField getTxtBrand() {
		txtBrand = new TextField();
		txtBrand.setBounds(100, 30, 350, 25);
		txtBrand.getDocument().addDocumentListener(this);
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
		
		dao.AllCountry().stream().forEachOrdered((p) -> {
			id_pais.addElement(p.getCountry_id());
			jboCountry.addItem(p.getCountry_nome());
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

	private JButton getBtnCheck() {
		btnCheck = new JButton("Salvar", images.getInstance().imagemCheck());
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

	private void CarregarJTable(ArrayList<Brand> productsList) {
		DefaultTableModel modelo = (DefaultTableModel) tblExcel.getModel();
		modelo.setRowCount(0);
		productsList.forEach((p) -> {
			modelo.addRow(new Object[] { p.getBrand_name(), p.getCountry_nome() });
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "save":
			
			Brand objMarca = new Brand(txtBrand.getText(), id_pais.get(jboCountry.getSelectedIndex()), new Date(),
					String.valueOf(User.getInstance().getId()));
			if (dao.save(objMarca)) {
				JOptionPane.showMessageDialog(this, "Marca Salva Com Sucesso!");
				this.dispose(); 
			}
			break;
		case "alter":
			Brand objMarcaAlt = new Brand(id, txtBrand.getText(),id_pais.get(jboCountry.getSelectedIndex()), new Date(),
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
						Brand p = new Brand(0, dados[i][0], dados[i][1], new Date(),
								String.valueOf(User.getInstance().getId()));
						brandList.add(p);
					}
				}
				CarregarJTable(brandList);
			}
			break;
		case "delete":
			String nome = tblExcel.getModel().getValueAt(tblExcel.getSelectedRow(), 0).toString();
			brandList.removeIf(p -> p.getBrand_name().equals(nome));
			this.CarregarJTable(brandList);
			break;
		case "saveExcel":
			if (brandList != null) {
				brandList.forEach((b) -> {dao.save(b);});
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
			boolean rowsAreSelected = tblExcel.getSelectedRowCount() > 0;
			btnExcluirExcel.setEnabled(rowsAreSelected);
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
