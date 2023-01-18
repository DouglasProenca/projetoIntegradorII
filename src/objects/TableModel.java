package objects;

import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class TableModel extends DefaultTableModel {

    private boolean[] canEdit;

    public TableModel() {
    	this.setRowCount(0);
    }
    
    public TableModel(String[] colunas, int rows) {
        super(colunas, rows);
        this.setRowCount(0);
        canEdit = new boolean[colunas.length];
        for(int i = 0; i < colunas.length; i++){
            canEdit[i] = false;
        }
    }

    public TableModel(String[] colunas, int rows, boolean[] canEdit) {
        super(colunas, rows);
        this.setRowCount(0);
        this.canEdit = canEdit;
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int mColIndex) {
        return canEdit[mColIndex];
    }
    
    @Override
    public Object getValueAt(int arg0, int arg1) {
    	// override para diminuir o tamanho do comando padraão tbl.getModel.getValueAt(arg0,argq)
		return super.getValueAt(arg0, arg1);
	}
}
