package objects;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Table extends JTable {
	
	public Table(String [] columns, int rows) {
		super(new TableModel(columns, rows));
		this.getTableHeader().setReorderingAllowed (false);
	}
	
	public Table(String[] colunas, int rows, boolean[] canEdit) {
		super(new TableModel(colunas,0,canEdit));
		this.getTableHeader().setReorderingAllowed (false);
	}

	@Override
	public DefaultTableModel getModel() {
		return (DefaultTableModel) super.getModel();
	}
}
