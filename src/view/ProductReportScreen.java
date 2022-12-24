package view;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import controller.ProductDAO;
import model.Product;
import objects.Excel;
import objects.TableModel;

@SuppressWarnings("serial")
public class ProductReportScreen extends BrandReportScreen {

	private final Excel excel = new Excel();
	private final ProductDAO dao = ProductDAO.getInstance();

	public ProductReportScreen() {
		this.setTitle("Relatório Produtos");
		this.loadTable();
	}

	@Override
	public JTable getTblResultado() {
		tblResultado = new JTable(new TableModel(new String[]{ "ID", "Produto", "Marca", "Categoria", "Valor", "Quantidade", "Data",
		"Usuário" }, 0));
		tblResultado.getSelectionModel().addListSelectionListener(this);
		tblResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblResultado.addMouseListener(this);
		return tblResultado;
	}

	@Override
	public void loadTable() {
		DefaultTableModel modelo = (DefaultTableModel) tblResultado.getModel();
		modelo.setRowCount(0);
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy");
		ProductDAO.getInstance().getAll().forEach((p) -> {
			modelo.addRow(new Object[] { p.getId(), p.getNome(), p.getMarca(), p.getCategoria(), p.getValor(),
					p.getQuantidade(), sdf1.format(p.getDate()), p.getUser() });
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "excluir":
			int lineNumber = tblResultado.getSelectedRow();
			int id = Integer.parseInt(tblResultado.getModel().getValueAt(lineNumber, 0).toString());
			if (ProductDAO.getInstance().delete(id)) {
				JOptionPane.showMessageDialog(this, "Produto Excluido com Sucesso!");
			}
			this.loadTable();
			break;
		case "Incluir":
			RegistrationProductScreen rbs = new RegistrationProductScreen();
			getParent().add(rbs);
			rbs.setVisible(true);
			break;
		case "Exportar":
			JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (fc.showSaveDialog(null) != 1) {
				excel.exportExcel(fc.getSelectedFile(), dao.getAll(), "Produtos", tblResultado);
			}
			break;
		case "find":
			if (txtPesquisa.getText().toLowerCase().equals("refresh")
					|| txtPesquisa.getText().toLowerCase().equals("r")) {
				loadTable();
			} else {
				DefaultTableModel modelo = (DefaultTableModel) tblResultado.getModel();
				modelo.setRowCount(0);
				SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy"); // você pode usar outras máscaras
				ProductDAO.getInstance().getBy(txtPesquisa.getText()).forEach((p) -> {
					modelo.addRow(new Object[] { p.getId(), p.getNome(), p.getMarca(), p.getCategoria(), p.getValor(),
							p.getQuantidade(), sdf1.format(p.getDate()), p.getUser() });
				});
				break;
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int lineNumber = tblResultado.getSelectedRow();

			int id = Integer.parseInt(tblResultado.getModel().getValueAt(lineNumber, 0).toString());
			String product_name = tblResultado.getModel().getValueAt(lineNumber, 1).toString();
			String brand = tblResultado.getModel().getValueAt(lineNumber, 2).toString();
			String category = tblResultado.getModel().getValueAt(lineNumber, 3).toString();
			String value = tblResultado.getModel().getValueAt(lineNumber, 4).toString();
			String quantity = tblResultado.getModel().getValueAt(lineNumber, 5).toString();
			String user = tblResultado.getModel().getValueAt(lineNumber, 6).toString();

			Product product = new Product(product_name, Float.parseFloat(value), Integer.parseInt(quantity), category,
					id, brand, null, null, user);
			RegistrationProductScreen rbs = new RegistrationProductScreen(product);
			this.getParent().add(rbs);
			rbs.setVisible(true);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Event.ENTER) {
			if (txtPesquisa.getText().toLowerCase().equals("refresh")
					|| txtPesquisa.getText().toLowerCase().equals("r")) {
				loadTable();
			} else {
				ActionEvent z = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "find");
				this.actionPerformed(z);
			}
		}
	}
}
