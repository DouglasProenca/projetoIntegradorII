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

import controller.CategoryDAO;
import model.Category;
import objects.Excel;
import objects.TableModel;

@SuppressWarnings("serial")
public class CategoryReportScreen extends BrandReportScreen {

	private final CategoryDAO dao = CategoryDAO.getInstance();
	private final Excel excel = new Excel();

	public CategoryReportScreen() {
		this.setTitle("Relatório Categorias");
		this.loadTable();
	}

	public JTable getTblResultado() {
		tblResultado = new JTable(new TableModel(new String[] {"ID","Categoria","Data","Usuário"}, 0));
		tblResultado.getSelectionModel().addListSelectionListener(this);
		tblResultado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblResultado.addMouseListener(this);
		return tblResultado;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "excluir":
			int numeroLinha = tblResultado.getSelectedRow();
			int id = Integer.parseInt(tblResultado.getModel().getValueAt(numeroLinha, 0).toString());
			if (dao.delete(id)) {
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
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			if (fc.showSaveDialog(null) != 1) {
				excel.exportExcel(fc.getSelectedFile(), dao.getAll(), "Categorias", tblResultado);
			}
			break;
		case "find":
			DefaultTableModel modelo = (DefaultTableModel) tblResultado.getModel();
			modelo.setRowCount(0);
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MMM/yyyy"); // você pode usar outras máscaras
			dao.getBy(txtPesquisa.getText()).forEach((p) -> {
				modelo.addRow(new Object[] { p.getId(), p.getCategoria(), sdf1.format(p.getDate()), p.getUser() });
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

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Event.ENTER) {
			if (txtPesquisa.getText().toLowerCase().equals("refresh")
					|| txtPesquisa.getText().toLowerCase().equals("r")) {
				this.loadTable();
			} else {
				ActionEvent z = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "find");
				this.actionPerformed(z);
			}
		}
	}
}