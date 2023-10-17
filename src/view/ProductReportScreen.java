package view;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import controller.ProductDAO;
import model.Product;
import objects.Excel;
import objects.Table;
import objects.TableModel;


public class ProductReportScreen extends BrandReportScreen {

	private static final long serialVersionUID = 1L;
	private final Excel excel = new Excel();
	private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy");

	public ProductReportScreen() {
		this.setTitle("Relatório Produtos");
		this.loadTable();
	}

	@Override
	public Table getTblResultado() {
		tblResultado = new Table(new String[] { "ID", "Produto", "Marca", "Categoria", "Valor", "Quantidade", "Data",
				"Usuário", "Imagem" }, 0);
		tblResultado.getSelectionModel().addListSelectionListener(this);
		tblResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblResultado.addMouseListener(this);
		tblResultado.getColumnModel().getColumn(8).setMaxWidth(0);
		tblResultado.getColumnModel().getColumn(8).setMinWidth(0);
		tblResultado.getColumnModel().getColumn(8).setPreferredWidth(0);
		return tblResultado;
	}

	@Override
	public void loadTable() {
		TableModel modelo = (TableModel) tblResultado.getModel();
		modelo.setRowCount(0);

		ProductDAO.getInstance().getAll().forEach((p) -> {
			modelo.addRow(new Object[] { p.getProduct_id(), p.getProduct_name(), p.getBrand_name(),
					p.getCategory_name(), p.getProduct_valor(), p.getProduct_qtd(), sdf1.format(p.getDate()),
					p.getUser(), p.getProduct_img() });
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "excluir":
			int lineNumber = tblResultado.getSelectedRow();
			int id = Integer.parseInt(tblResultado.getValueAt(lineNumber, 0).toString());
			if (ProductDAO.getInstance().delete(id)) {
				JOptionPane.showMessageDialog(this, "Produto Excluido com Sucesso!");
			}
			this.loadTable();
			break;
		case "Incluir":
			RegistrationProductScreen rbs = new RegistrationProductScreen(this);
			getParent().add(rbs);
			rbs.setVisible(true);
			break;
		case "Exportar":
			JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(this) == JFileChooser.FILES_ONLY) {
				excel.exportExcel(fc.getSelectedFile(), ProductDAO.getInstance().getAll(), "Produtos", tblResultado);
			}
			break;
		case "find":
			if (txtPesquisa.getText().toLowerCase().equals("refresh")
					|| txtPesquisa.getText().toLowerCase().equals("r")) {
				loadTable();
			} else {
				TableModel modelo = (TableModel) tblResultado.getModel();
				modelo.setRowCount(0);

				ProductDAO.getInstance().getBy(txtPesquisa.getText()).forEach((p) -> {
					modelo.addRow(new Object[] { p.getProduct_id(), p.getProduct_name(), p.getBrand_name(),
							p.getCategory_name(), p.getProduct_valor(), p.getProduct_qtd(), sdf1.format(p.getDate()),
							p.getUser(), p.getProduct_img() });
				});
				break;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int lineNumber = tblResultado.getSelectedRow();

			int id = Integer.parseInt(tblResultado.getValueAt(lineNumber, 0).toString());
			String product_name = tblResultado.getValueAt(lineNumber, 1).toString();
			String brand = tblResultado.getValueAt(lineNumber, 2).toString();
			String category = tblResultado.getValueAt(lineNumber, 3).toString();
			String value = tblResultado.getValueAt(lineNumber, 4).toString();
			String quantity = tblResultado.getValueAt(lineNumber, 5).toString();
			String user = tblResultado.getValueAt(lineNumber, 6).toString();
			byte[] imagem = (byte[]) tblResultado.getValueAt(lineNumber, 8);

			Product product = new Product(product_name, Float.parseFloat(value), Integer.parseInt(quantity), category,
					id, brand, null, null, user, imagem);
			RegistrationProductScreen rbs = new RegistrationProductScreen(product,this);
			this.getParent().add(rbs);
			rbs.setVisible(true);
		}
	}
}
