package view;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import controller.CategoryDAO;
import model.Category;
import objects.Excel;
import objects.Table;
import objects.TableModel;

@SuppressWarnings("serial")
public class CategoryReportScreen extends BrandReportScreen {

	private final Excel excel = new Excel();
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy");

	public CategoryReportScreen() {
		this.setTitle("Relatório Categorias");
		this.loadTable();
	}

	@Override
	public Table getTblResultado() {
		tblResultado = new Table(new String[] { "ID", "Categoria", "Data", "Usuário" }, 0);
		tblResultado.getSelectionModel().addListSelectionListener(this);
		tblResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblResultado.addMouseListener(this);
		return tblResultado;
	}

	@Override
	public void loadTable() {
		TableModel modelo = (TableModel) tblResultado.getModel();
		modelo.setRowCount(0);

		CategoryDAO.getInstance().getAll().forEach((p) -> {
			modelo.addRow(
					new Object[] { p.getCategory_id(), p.getCategory_name(), sdf1.format(p.getDate()), p.getUser() });
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "excluir":
			int numeroLinha = tblResultado.getSelectedRow();
			int id = Integer.parseInt(tblResultado.getModel().getValueAt(numeroLinha, 0).toString());
			if (CategoryDAO.getInstance().delete(id)) {
				JOptionPane.showMessageDialog(this, "Categoria excluída com sucesso!");
			}
			this.loadTable();
			break;
		case "Incluir":
			RegistrationCategoryScreen rbs = new RegistrationCategoryScreen();
			this.getParent().add(rbs);
			rbs.setVisible(true);
			break;
		case "Exportar":
			JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(this) == JFileChooser.FILES_ONLY) {
				excel.exportExcel(fc.getSelectedFile(), CategoryDAO.getInstance().getAll(), "Categorias", tblResultado);
			}
			break;
		case "find":
			TableModel modelo = (TableModel) tblResultado.getModel();
			modelo.setRowCount(0);

			CategoryDAO.getInstance().getBy(txtPesquisa.getText()).forEach((p) -> {
				modelo.addRow(new Object[] { p.getCategory_id(), p.getCategory_name(), sdf1.format(p.getDate()),
						p.getUser() });
			});
			break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			int numeroLinha = tblResultado.getSelectedRow();

			int id = Integer.parseInt(tblResultado.getModel().getValueAt(numeroLinha, 0).toString());
			String category = tblResultado.getModel().getValueAt(numeroLinha, 1).toString();
			String user = tblResultado.getModel().getValueAt(numeroLinha, 3).toString();

			Category brand = new Category(category, id, null, null, null, user);

			RegistrationCategoryScreen rbs = new RegistrationCategoryScreen(brand);
			this.getParent().add(rbs);
			rbs.setVisible(true);
		}
	}
}